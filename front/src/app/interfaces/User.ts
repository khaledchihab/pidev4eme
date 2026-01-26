import { UserRole } from "./userRole";
export interface User {
    userId: number;
    username: string;
    email: string;
    password: string;
    code: string | null; // Assuming it can be null or a string
    roles: UserRole[];
  }