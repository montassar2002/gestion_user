import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private apiUrl = `${environment.apiUrl}/auth`;
  constructor(private http: HttpClient, private router: Router) {}
  login(email: string, motDePasse: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, { email, motDePasse });
  }
  saveToken(token: string): void { localStorage.setItem('token', token); }
  saveUser(user: any): void { localStorage.setItem('user', JSON.stringify(user)); }
  getToken(): string | null { return localStorage.getItem('token'); }
  getUser(): any {
    const user = localStorage.getItem('user');
    return user ? JSON.parse(user) : {};
  }
  isLoggedIn(): boolean { return this.getToken() !== null; }
  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    this.router.navigate(['/login']);
  }
}