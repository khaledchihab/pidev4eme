
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormfieldService } from '../_services/formfield.service';
import { FormFieldDTO } from '../interfaces/FormFieldDTO';

@Component({
  selector: 'app-form-field-manager',
  templateUrl: './form-field-manager.component.html'
})
export class FormFieldManagerComponent implements OnInit {


  formId: number = 0;

  newField: FormFieldDTO = {
    formId: 0,
    fieldName: '',
    fieldLabel: '',
    fieldType: 'text',
    configuration: '',
    coefficient: 0
  };

  fieldTypes = ['text', 'number', 'date', 'select', 'radio'];

  fields: FormFieldDTO[] = [];


  constructor(private formFieldService: FormfieldService, private route: ActivatedRoute) {}


  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('formId');
      this.formId = id ? +id : 1;
      this.newField.formId = this.formId;
      this.loadFields();
    });
  }

  loadFields() {
    this.formFieldService.getFieldsByForm(this.formId).subscribe(data => {
      this.fields = data;
    });
  }

  addField() {
    this.formFieldService.createFormField(this.newField).subscribe(res => {
      this.fields.push(res);
      this.resetForm();
    });
  }

  deleteField(fieldId?: number) {
    if (!fieldId) return;
    this.formFieldService.deleteFormField(fieldId).subscribe(() => {
      this.fields = this.fields.filter(f => f.fieldId !== fieldId);
    });
  }

  resetForm() {
    this.newField = {
      formId: this.formId,
      fieldName: '',
      fieldLabel: '',
      fieldType: 'text',
      configuration: '',
      coefficient: 0
    };
  }
}
