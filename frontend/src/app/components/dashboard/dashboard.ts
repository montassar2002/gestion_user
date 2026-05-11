import { Component, OnInit } from '@angular/core';
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
    private permissionService: PermissionService
  ) {}

  ngOnInit() {
    this.utilisateurService.getAll().subscribe(data => {
      this.totalUtilisateurs = data.length;
    });
    this.utilisateurService.getActifs().subscribe(data => {
      this.totalActifs = data.length;
    });
    this.roleService.getAll().subscribe(data => {
      this.totalRoles = data.length;
    });
    this.permissionService.getAll().subscribe(data => {
      this.totalPermissions = data.length;
    });
  }
}