import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { UtilisateurService } from '../../services/utilisateur';
import { RoleService } from '../../services/role';
import { PermissionService } from '../../services/permission';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './dashboard.html'
})
export class Dashboard implements OnInit {
  totalUtilisateurs = 0;
  totalRoles = 0;
  totalPermissions = 0;
  totalActifs = 0;

  constructor(
    private utilisateurService: UtilisateurService,
    private roleService: RoleService,
    private permissionService: PermissionService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.loadData();
  }

  loadData() {
    this.utilisateurService.getAll().subscribe({
      next: (data) => {
        this.totalUtilisateurs = data.length;
        this.totalActifs = data.filter((u: any) => u.actif).length;
        this.cdr.detectChanges();
      },
      error: (err) => console.log('Erreur utilisateurs:', err)
    });

    this.roleService.getAll().subscribe({
      next: (data) => {
        this.totalRoles = data.length;
        this.cdr.detectChanges();
      },
      error: (err) => console.log('Erreur roles:', err)
    });

    this.permissionService.getAll().subscribe({
      next: (data) => {
        this.totalPermissions = data.length;
        this.cdr.detectChanges();
      },
      error: (err) => console.log('Erreur permissions:', err)
    });
  }
}