import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FormApplicationHistory } from '../dto/FormApplicationHistoryDTO';
export interface FormApplication {
  id: number;
  form: any; // you can create a proper interface for Form if needed
  userId: string;
  status: string; // PENDING, ACCEPTED, REFUSED
  createdAt: string;
  updatedAt: string;
}
@Injectable({
  providedIn: 'root'
})
export class FormapplicationService {

  private baseUrl = 'http://localhost:8888/api/applications';

  constructor(private http: HttpClient) { }

  // Get all applications for a specific user
  getApplicationsByUser(userId: string): Observable<FormApplicationHistory[]> {
    return this.http.get<FormApplicationHistory[]>(`${this.baseUrl}/user/${userId}`);
  }

  // Update status of an application
  updateApplicationStatus(appId: number, newStatus: string): Observable<FormApplication> {
    return this.http.put<FormApplication>(`${this.baseUrl}/${appId}/status?status=${newStatus}`, {});
  }

  // Optional: get all applications (for admin dashboard)
  getAllApplications(): Observable<FormApplication[]> {
    return this.http.get<FormApplication[]>(this.baseUrl);
  }
}
