import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginRequestDTO } from '../dto/LoginRequestDTO';
import { LoginResponseDTO } from '../dto/LoginResponseDTO';
import { UserRequestDTO } from '../dto/UserRequestDTO';

const AUTH_API = 'http://localhost:8888/mobility/authentication/';
const USER_API = 'http://localhost:8888/mobility/users/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  constructor(private http: HttpClient) { }
  public setRoles(roles: []) {
    localStorage.setItem('roles', JSON.stringify(roles));
  }
  public getRoles(): string[] {
    return JSON.parse(localStorage.getItem('roles') ?? '[]');
  }


  public setToken(jwtToken: string) {
    localStorage.setItem('Token', jwtToken);
  }

  public getToken(): string {
    return (localStorage.getItem('Token') ?? '');
  }

  public Clear() {
    localStorage.clear();
  }

  public isLoggedIn() {
    return this.getRoles() && this.getToken();
  }

  /**
   * Login method - calls auth-service /mobility/authentication/login
   */
  public login(credentials: LoginRequestDTO): Observable<LoginResponseDTO> {
    return this.http.post<LoginResponseDTO>(AUTH_API + 'login', credentials, httpOptions);
  }

  /**
   * Register method - calls auth-service /mobility/users/create
   */
  public register(userData: UserRequestDTO): Observable<any> {
    return this.http.post(USER_API + 'create', userData, httpOptions);
  }

}

