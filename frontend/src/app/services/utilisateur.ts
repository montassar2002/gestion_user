import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class UtilisateurService {
  private apiUrl = `${environment.apiUrl}/utilisateurs`;
  constructor(private http: HttpClient) {}
  getAll(): Observable<any[]> { return this.http.get<any[]>(this.apiUrl); }
  getActifs(): Observable<any[]> { return this.http.get<any[]>(`${this.apiUrl}/actifs`); }
  getById(id: number): Observable<any> { return this.http.get<any>(`${this.apiUrl}/${id}`); }
  rechercherParNom(nom: string): Observable<any[]> { return this.http.get<any[]>(`${this.apiUrl}/recherche?nom=${nom}`); }
  create(utilisateur: any): Observable<any> { return this.http.post<any>(this.apiUrl, utilisateur); }
  update(id: number, utilisateur: any): Observable<any> { return this.http.put<any>(`${this.apiUrl}/${id}`, utilisateur); }
  toggleActif(id: number): Observable<any> { return this.http.patch<any>(`${this.apiUrl}/${id}/toggle`, {}); }
  delete(id: number): Observable<void> { return this.http.delete<void>(`${this.apiUrl}/${id}`); }
}