import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-formfields',
  templateUrl: './formfields.component.html',
  styleUrls: ['./formfields.component.css']
})
export class FormfieldsComponent implements OnInit {
   formFields: any = {
  formId: null, // User's choice from dropdown
  fieldName: null, // User's choice from dropdown
  fieldLabel: '',
  fieldType: { type: '', config: [] }, // Initialize with an empty array for config
  coefficient: 0
};

existingFormIds: number[] = [];
existingFieldNames: string[] = [];
existingFieldTypes: string[] = [];

constructor(private http: HttpClient) { }

ngOnInit() {
  // Fetch existing field names from your API
  //this.fetchExistingFieldNames();

  // Fetch field types and their configurations
  this.fetchExistingFieldTypes();
}

fetchExistingFieldTypes() {
  // Assuming you have an API endpoint to retrieve field types and their configurations
  this.http.get('/getFieldTypes').subscribe((types: any) => {
    this.existingFieldTypes = types;
  });
}

addConfigOption() {
  // Add a new, empty configuration option
  this.formFields.fieldType.config.push('');
}

removeConfigOption(index: number) {
  // Remove a configuration option at the specified index
  this.formFields.fieldType.config.splice(index, 1);
}

onSubmit() {
  // Submit the formFields to the API
  this.http.post('/addFormFields', [this.formFields])
    .subscribe(response => {
      // Handle the API response if needed
    });
}
}