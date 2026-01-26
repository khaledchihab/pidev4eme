import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
const AUTH_API = 'http://localhost:8888/api/auth/';
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
    localStorage.setItem('jwtToken', jwtToken);
  }

  public getToken(): string {
    return (localStorage.getItem('Token') ?? '[]');
  }

  public Clear() {
    localStorage.clear();
  }

  public isLoggedIn() {
    return this.getRoles() && this.getToken();
  }

  /**
   * Login method - calls monolith /api/auth/signin
   * Maps backend response (token) to frontend format (jwt)
   */
  public login(credentials: any): Observable<any> {
    return this.http.post(AUTH_API + 'signin', {
      username: credentials.username,
      password: credentials.password
    }, httpOptions).pipe(
      map((response: any) => {
        // Map monolith response format to frontend DTO format
        return {
          jwt: response.token,  // Backend uses 'token', frontend expects 'jwt'
          username: response.username,
          roles: response.roles,
          id: response.id,
          code: response.code
        };
      })
    );
  }

  /**
   * Register method - calls monolith /api/auth/signup
   */
  public register(userData: any): Observable<any> {
    return this.http.post(AUTH_API + 'signup', {
      username: userData.username,
      email: userData.email,
      password: userData.password,
      role: ['candidat']  // Default role for student registration
    }, httpOptions);
  }


}
