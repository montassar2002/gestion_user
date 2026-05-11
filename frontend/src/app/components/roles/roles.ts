import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RoleService } from '../../services/role';
import { PermissionService } from '../../services/permission';

@Component({
  selector: 'app-roles',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './roles.html'
})
export class Roles implements OnInit {
  roles: any[] = [];
  permissions: any[] = [];
  showForm = false;
  isEdit = false;
  form: any = { nom: '', description: '' };

  constructor(
    private roleService: RoleService,
    private permissionService: PermissionService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.loadRoles();
    this.loadPermissions();
  }

  loadRoles() {
    this.roleService.getAll().subscribe({
      next: (data) => {
        this.roles = data;
        this.cdr.detectChanges();
      },
      error: (err) => console.log('Erreur roles:', err)
    });
  }

  loadPermissions() {
    this.permissionService.getAll().subscribe({
      next: (data) => {
        this.permissions = data;
        this.cdr.detectChanges();
      },
      error: (err) => console.log('Erreur permissions:', err)
    });
  }

  openForm() {
    this.showForm = true;
    this.isEdit = false;
    this.form = { nom: '', description: '' };
  }

  editRole(r: any) {
    this.showForm = true;
    this.isEdit = true;
    this.form = { ...r };
  }

  saveRole() {
    if (this.isEdit) {
      this.roleService.update(this.form.idRole, this.form).subscribe(() => {
        this.loadRoles();
        this.showForm = false;
      });
    } else {
      this.roleService.create(this.form).subscribe(() => {
        this.loadRoles();
        this.showForm = false;
      });
    }
  }

  ajouterPermission(idRole: number, event: any) {
    const idPermission = event.target.value;
    if (idPermission) {
      this.roleService.ajouterPermission(idRole, idPermission).subscribe(() => {
        this.loadRoles();
        event.target.value = '';
      });
    }
  }

  retirerPermission(idRole: number, idPermission: number) {
    this.roleService.retirerPermission(idRole, idPermission).subscribe(() => this.loadRoles());
  }

  deleteRole(id: number) {
    if (confirm('Confirmer la suppression ?')) {
      this.roleService.delete(id).subscribe(() => this.loadRoles());
    }
  }

  cancel() {
    this.showForm = false;
  }
}