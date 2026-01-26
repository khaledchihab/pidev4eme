import { Form } from "./Form";
export interface FormField {
    fieldId: number;
    form: Form;
    fieldName: string;
    fieldLabel: string;
    fieldType: string;
    configuration: string | null; // Assuming it can be null or a string
    coefficient: number| null;
  }