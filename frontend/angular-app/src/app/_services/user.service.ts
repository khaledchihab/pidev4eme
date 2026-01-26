import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserAuthService } from './user-auth.service';
import { Observable } from 'rxjs';
import { UserResponseDTO } from '../dto/UserResponseDTO';



@Injectable({
  providedIn: 'root'
})
export class UserService {
  PATH_OF_API = 'http://localhost:8888';
  PATH_OF_API_AUTH = 'http://localhost:8888/mobility';

  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private httpclient: HttpClient, private userAuthService: UserAuthService) { }

  public login(loginData: any) {
    return this.httpclient.post(this.PATH_OF_API + '/api/auth/signin', loginData, { headers: this.requestHeader });
  }

  public forAdmin() {
    return this.httpclient.get(this.PATH_OF_API_AUTH + '/api/test/forAdmin', {
      responseType: 'text',
      headers: new HttpHeaders({ 'Authorization': 'Bearer ' + this.userAuthService.getToken() })
    });
  }
  public forCandidat() {
    return this.httpclient.get(this.PATH_OF_API_AUTH + '/api/test/forCandidat', {
      responseType: 'text',
      headers: new HttpHeaders({ 'Authorization': 'Bearer ' + this.userAuthService.getToken() })
    });
  }
  public forUniversity() {
    return this.httpclient.get(this.PATH_OF_API_AUTH + '/forUniversity', {
      responseType: 'text',
    });
  }
  getPublicContent(): Observable<any> {
    return this.httpclient.get(this.PATH_OF_API_AUTH + '/api/test/all', {
      responseType: 'text',
      headers: new HttpHeaders({ 'Authorization': 'Bearer ' + this.userAuthService.getToken() })
    });
  }
  public roleMatch(allowedRoles: string[]): boolean {
    let isMatch = false;
    const userRoles: string[] = this.userAuthService.getRoles();

    if (userRoles != null && userRoles.length > 0) {
      for (let i = 0; i < userRoles.length; i++) {
        for (let j = 0; j < allowedRoles.length; j++) {
          if (userRoles[i] === allowedRoles[j]) {
            isMatch = true;
          }
        }
      }
    }

    return isMatch;
  }
  public getusers() {
    return this.httpclient.get<any>(this.PATH_OF_API + '/Users/retrieve-all-users');
  }
  getUserById(id: string): Observable<UserResponseDTO> {
    const token = localStorage.getItem('Token'); // JWT token
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.httpclient.get<UserResponseDTO>(`${this.PATH_OF_API}/get/${id}`, { headers });
  }
}
