import { Role } from "./role";

export class User {
    id?: number;
    userName?: string;
    firstName?: string;
    lastName?: string;
    role?: Role;
    token?: string;
}