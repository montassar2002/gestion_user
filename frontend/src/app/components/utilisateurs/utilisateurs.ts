import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UtilisateurService } from '../../services/utilisateur';
import { RoleService } from '../../services/role';

@Component({
  selector: 'app-utilisateurs',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './utilisateurs.html'
})
export class Utilisateurs implements OnInit {
  utilisateurs: any[] = [];
  roles: any[] = [];
  recherche = '';
  showForm = false;
  isEdit = false;

  form: any = {
    nom: '', email: '', motDePasse: '', actif: true, role: { idRole: null }
  };

  constructor(
    private utilisateurService: UtilisateurService,
    private roleService: RoleService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.loadUtilisateurs();
    this.loadRoles();
  }

  loadUtilisateurs() {
    this.utilisateurService.getAll().subscribe({
      next: (data) => {
        this.utilisateurs = data;
        this.cdr.detectChanges();
      },
      error: (err) => console.log('Erreur:', err)
    });
  }

  loadRoles() {
    this.roleService.getAll().subscribe({
      next: (data) => {
        this.roles = data;
        this.cdr.detectChanges();
      },
      error: (err) => console.log('Erreur:', err)
    });
  }

  rechercher() {
    if (this.recherche.trim()) {
      this.utilisateurService.rechercherParNom(this.recherche).subscribe(data => {
        this.utilisateurs = data;
        this.cdr.detectChanges();
      });
    } else {
      this.loadUtilisateurs();
    }
  }

  openForm() {
    this.showForm = true;
    this.isEdit = false;
    this.form = { nom: '', email: '', motDePasse: '', actif: true, role: { idRole: null } };
  }

  editUtilisateur(u: any) {
    this.showForm = true;
    this.isEdit = true;
    this.form = { ...u, motDePasse: '', role: { idRole: u.role?.idRole } };
  }

  saveUtilisateur() {
    if (this.isEdit) {
      this.utilisateurService.update(this.form.idUtilisateur, this.form).subscribe(() => {
        this.loadUtilisateurs();
        this.showForm = false;
      });
    } else {
      this.utilisateurService.create(this.form).subscribe(() => {
        this.loadUtilisateurs();
        this.showForm = false;
      });
    }
  }

  toggleActif(id: number) {
    this.utilisateurService.toggleActif(id).subscribe(() => this.loadUtilisateurs());
  }

  deleteUtilisateur(id: number) {
    if (confirm('Confirmer la suppression ?')) {
      this.utilisateurService.delete(id).subscribe(() => this.loadUtilisateurs());
    }
  }

  cancel() {
    this.showForm = false;
  }
}