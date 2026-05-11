import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

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
    this.http.get<any[]>('http://localhost:8081/api/historique').subscribe({
      next: (data) => {
        this.historique = data;
        this.cdr.detectChanges();
      },
      error: (err) => console.log('Erreur historique:', err)
    });
  }
}