import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl, ValidatorFn } from '@angular/forms';
import { DynamicServiceService } from '../_services/api.service';
import { ActivatedRoute } from '@angular/router';
import { StorageService } from '../_services/storage.service';
import { switchMap } from 'rxjs/operators';
import { FormDataDTO } from '../FormDataDTO';
import { AbstractControl } from '@angular/forms';
// Define the FormConfig interface
interface FormConfig {
  [key: string]: [any, ValidatorFn[]];
}



@Component({
  selector: 'app-dynamicform',
  templateUrl: './dynamicform.component.html',
  styleUrls: ['./dynamicform.component.css']
})
export class DynamicformComponent implements OnInit{ 
  form: FormGroup;
  formFields: any[]=[];
  currentUser: any;
  username: string | null = localStorage.getItem("Username");
  userId!: string;

  

  constructor(
    private formBuilder: FormBuilder, 
    private formModelService: DynamicServiceService,  
    private route: ActivatedRoute, 
    private storageService: StorageService
  ) {
    this.form = this.formBuilder.group({});
  }

  ngOnInit() {
    this.currentUser = this.storageService.getUser();
    this.userId = this.currentUser.id;

    const formId = this.route.snapshot.params['formId'];

    this.getForms();
    this.formModelService.getFormFieldsByFormId(formId).subscribe((fields) => {
  this.formFields = fields;
  

  // Initialize the form group and dynamically create form controls
  const formGroupConfig: FormConfig = {}; // Use the FormConfig interface

  this.formFields.forEach((field) => {
    const validators: ValidatorFn[] = [Validators.required];

    if (field.fieldName === 'email') {
      // Add email validation if fieldName is 'email'
      validators.push(Validators.email);
    
  } else if (field.fieldName === 'number') {
    validators.push(Validators.pattern('^[0-9]*$'));
  } else if (field.fieldName === 'score') {
    // Add custom validator for "score" field
    validators.push(this.scoreValidator());
  }

    formGroupConfig[field.fieldName] = ['', validators]; // Updated initialization
  });

  this.form = this.formBuilder.group(formGroupConfig);
});


}
 // Custom validator for "score" field
 scoreValidator(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    const value = parseFloat(control.value);
    if (isNaN(value) || value < 0 || value > 20) {
      return { 'scoreOutOfRange': true };
    }
    return null;
  };
}

onSubmit() {
  this.currentUser = this.storageService.getUser();
  const formIdString = this.route.snapshot.params['formId'];
const formId = parseInt(formIdString, 10);
  console.log(this.currentUser.id);

  if (this.form.valid) {
    this.formModelService.getFormFieldsByFormId(formId).pipe(
      switchMap((formModel) => {
        this.formFields = formModel;
        const formData: FormDataDTO[] = [];

        // Iterate over form fields to populate formData
        this.formFields.forEach((field) => {
          const formControlValue = this.form.get(field.fieldName)?.value;

          const formDataItem: FormDataDTO = {
            //formId: formId,
            userId: this.userId,
            formFieldId: field.fieldId,
            value: formControlValue,
            
          };

          formData.push(formDataItem);
        });

        return this.formModelService.addFormData(formData);
      })
    ).subscribe(
      (response) => {
        // Handle success response from the API, e.g., show a success message.
        console.log('Form data added successfully:', response);
      },
      (error) => {
        // Handle error response from the API, e.g., display an error message.
        console.error('Error adding form data:', error);
      }
    );
  }
}
getForms(){
this.formModelService.getForm().subscribe((forms)=>{
  console.log(forms);
  
});}
sanitizeLabel(label: string): string {
  // Remove spaces and other invalid characters from the label
  return label.replace(/[^a-zA-Z0-9-_]/g, '_');
}
getIconClass(fieldName: string): string {
  console.log(this.form)
  if (this.form.get(fieldName)?.valid) {
    return 'fas fa-check'; // Success icon
  } else if (this.form.get(fieldName)?.invalid) {
    return 'far fa-times-circle'; // Error icon
  } else {
    return 'fas fa-question-circle text-muted'; // Default icon class for other cases
  }}
}
