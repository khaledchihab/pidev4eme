import { User } from "./User";
import { FormField } from "./FormField";
export interface MyFormData {
    dataId: number;
    user: User;
    field: FormField;
    value: string | null;
  }