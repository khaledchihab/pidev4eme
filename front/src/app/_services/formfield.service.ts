import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FormFieldDTO } from '../interfaces/FormFieldDTO';

@Injectable({
  providedIn: 'root'
})
export class FormfieldService {

  private baseUrl = 'http://localhost:8090'; // adjust to your backend

  constructor(private http: HttpClient) {}

  createFormField(field: FormFieldDTO): Observable<FormFieldDTO> {
    return this.http.post<FormFieldDTO>(`${this.baseUrl}`, field);
  }

  getFieldsByForm(formId: number): Observable<FormFieldDTO[]> {
    return this.http.get<FormFieldDTO[]>(`${this.baseUrl}/form/${formId}`);
  }

  deleteFormField(fieldId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${fieldId}`);
  }
}
