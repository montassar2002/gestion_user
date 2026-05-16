import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-historique',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './historique.html'
})
export class Historique implements OnInit {
  historique: any[] = [];

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.loadHistorique();
  }

  loadHistorique() {
    this.http.get<any[]>(`${environment.apiUrl}/historique`).subscribe({
      next: (data) => {
        this.historique = data;
        this.cdr.detectChanges();
      },
      error: (err) => console.log('Erreur historique:', err)
    });
  }
}