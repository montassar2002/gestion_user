import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PermissionService {

  private apiUrl = 'http://localhost:8081/api/permissions';

  constructor(private http: HttpClient) {}

  getAll(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  create(permission: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, permission);
  }

  update(id: number, permission: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, permission);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}