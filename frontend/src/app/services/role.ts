import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  private apiUrl = 'http://localhost:8081/api/roles';

  constructor(private http: HttpClient) {}

  getAll(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  getById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  create(role: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, role);
  }

  update(id: number, role: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, role);
  }

  ajouterPermission(idRole: number, idPermission: number): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/${idRole}/permissions/${idPermission}`, {});
  }

  retirerPermission(idRole: number, idPermission: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${idRole}/permissions/${idPermission}`);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}