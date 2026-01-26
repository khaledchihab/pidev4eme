export interface FormFieldDTO {
  fieldId?: number;
  formId: number;
  fieldName: string;
  fieldLabel: string;
  fieldType: string;
  configuration?: string;
  coefficient: number;
}