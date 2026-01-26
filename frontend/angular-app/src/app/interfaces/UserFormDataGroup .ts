import { User } from "./User";
export interface UserFormDataGroup {
    user: User;
    fieldData: { fieldLabel: string, value: string }[];
  }