import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class UniversityService {
  private baseUrl = 'http://localhost:8888/api/universities';
  private baseUrl1 = 'http://localhost:8888/universities';


  constructor(private http: HttpClient) { }

  rateUniversity(universityId: number, userId: number, rating: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/${universityId}/rate?userId=${userId}&rating=${rating}`, {});
  }

  getAverageRating(universityId: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/${universityId}/rating`);
  }

  getUniversitiesWithAvgRating(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl1}/universities-with-avg-rating`);
  }
}