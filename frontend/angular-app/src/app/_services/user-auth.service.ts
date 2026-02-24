import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, timer, throwError } from 'rxjs';
import { retry } from 'rxjs/operators';
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

  public setRoles(roles: string[]): void {
    localStorage.setItem('roles', JSON.stringify(roles));
  }

  public getRoles(): string[] {
    return JSON.parse(localStorage.getItem('roles') ?? '[]');
  }

  public setToken(jwtToken: string): void {
    localStorage.setItem('Token', jwtToken);
  }

  public getToken(): string {
    return (localStorage.getItem('Token') ?? '');
  }

  public clear(): void {
    localStorage.removeItem('Token');
    localStorage.removeItem('User');
    localStorage.removeItem('roles');
    localStorage.removeItem('Username');
  }

  public isLoggedIn(): boolean {
    return !!this.getToken();
  }

  /**
   * Check if the stored token is still valid (not expired).
   */
  public isTokenValid(): boolean {
    const token = this.getToken();
    if (!token) return false;
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      const expiry = payload.exp * 1000; // JWT exp is in seconds
      return Date.now() < expiry;
    } catch {
      return false;
    }
  }

  /**
   * Login with automatic retry (3 attempts, 2s delay between retries).
   * Uses modern retry() API — NOT deprecated retryWhen().
   */
  public login(credentials: LoginRequestDTO): Observable<LoginResponseDTO> {
    return this.http.post<LoginResponseDTO>(AUTH_API + 'login', credentials, httpOptions).pipe(
      retry({
        count: 2,
        delay: (error: any, retryCount: number) => {
          // Only retry on server/network errors (5xx, 0 = network), not on 4xx (auth errors)
          if (error.status >= 400 && error.status < 500) {
            return throwError(() => error);
          }
          console.warn(`Login attempt ${retryCount} failed, retrying in 2s...`);
          return timer(2000);
        }
      })
    );
  }

  /**
   * Register method - calls auth-service /mobility/users/create
   */
  public register(userData: UserRequestDTO): Observable<any> {
    return this.http.post(USER_API + 'create', userData, httpOptions);
  }
}
