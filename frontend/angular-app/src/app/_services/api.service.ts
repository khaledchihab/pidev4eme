
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class DynamicServiceService {
  private apiUrl = 'http://localhost:8888';
  PATH_OF_API = 'http://localhost:8888';

  constructor(private http: HttpClient) { }
  getForm(): Observable<any> {
    // Make an HTTP GET request to your backend to fetch the dynamic form model.
    // Replace 'your-api-url' with the actual URL of your backend API.
    return this.http.get<any>(this.PATH_OF_API + '/getForm');
  }
  getFormFields(): Observable<any> {
    // Make an HTTP GET request to your backend to fetch the dynamic form model.
    // Replace 'your-api-url' with the actual URL of your backend API.
    return this.http.get<any>(this.PATH_OF_API + '/getFormField');
  }
  addFormField(formField: any): Observable<any> {
    return this.http.post<any>(this.PATH_OF_API + "/addFormField", formField);
  }

  addFormData(formField: any): Observable<any> {
    return this.http.post<any>(this.PATH_OF_API + "/submitFormDatas", formField);
  }
  getAllFormData(): Observable<any> {
    return this.http.get<any>(this.PATH_OF_API + '/getFormData');
  }
  getAllApplications(): Observable<any> {
    return this.http.get<any>(this.PATH_OF_API + '/getFormApplication');
  }
  getFormFieldsByFormId(formId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/byform/${formId}`);
  }
  getAllMoyennesWithUserAndForm(): Observable<any[]> {
    return this.http.get<any[]>(this.PATH_OF_API + "/getAll");
  }
  getMoyennesByFormName(formName: string): Observable<any[]> {
    const url = `${this.PATH_OF_API}/byFormName?formName=${formName}`;
    return this.http.get<any[]>(url);
  }
  sendEmails(formId: number): Observable<any> {
    return this.http.post(`${this.PATH_OF_API}/sendEmails/${formId}`, {});
  }
  getAllFormDataPipe(): Observable<number[]> {
    return this.http.get<any[]>(`${this.PATH_OF_API}/getFormData`)
      .pipe(
        map(response => response.map(item => item.form?.formId || null))
      );
  }
  getFormIdByName(formName: string): Observable<number> {
    const url = `${this.PATH_OF_API}/getFormIdByName?formName=${formName}`;
    return this.http.get<number>(url);
  }

  addForm(formDTO: any): Observable<any> {
    const url = `${this.apiUrl}/addForm`;
    return this.http.post(url, formDTO);
  }
  addFormFields(formFieldDTOs: any[]): Observable<any[]> {
    const url = `${this.apiUrl}/addFormFields`;
    return this.http.post<any[]>(url, formFieldDTOs);
  }
  addUniversity(university: any): Observable<any> {
    const url = `${this.apiUrl}/addUniversity`;
    return this.http.post(url, university);
  }
  getUniversities(): Observable<any[]> {
    const url = `${this.apiUrl}/getUniversities`;
    return this.http.get<any[]>(url);
  }
  getUniversitiesWithForms(): Observable<any[]> {
    const url = `${this.apiUrl}/getUniversitiesWithForms`;
    return this.http.get<any[]>(url);
  }
  getTest(): Observable<any[]> {
    return this.http.get<any>(this.PATH_OF_API + '/getForms')
  }
  deleteForm(id: number): Observable<void> {
    return this.http.delete<void>(`${this.PATH_OF_API}/deleteForm/${id}`);
  }
  rateUniversity(universityId: number, userId: string, rating: number): Observable<any> {
    return this.http.post(`${this.PATH_OF_API}/universities/${universityId}/rate?userId=${userId}&rating=${rating}`, {});
  }

  getAverageRating(universityId: number): Observable<number> {
    return this.http.get<number>(`${this.PATH_OF_API}/universities/${universityId}/rating`);
  }
}