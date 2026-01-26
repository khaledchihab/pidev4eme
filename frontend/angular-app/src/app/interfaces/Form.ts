import { University } from "./university";
export interface Form {
    formId: number;
    university: University;
    formName: string;
    description: string;
    placesDisp: number;
    equation: string;
    datelimite: Date;
  }