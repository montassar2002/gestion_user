import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PermissionService } from '../../services/permission';

@Component({
  selector: 'app-permissions',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './permissions.html'
})
export class Permissions implements OnInit {
  permissions: any[] = [];
  showForm = false;
  isEdit = false;
  form: any = { nom: '', description: '' };

  constructor(
    private permissionService: PermissionService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.loadPermissions();
  }

  loadPermissions() {
    this.permissionService.getAll().subscribe({
      next: (data) => {
        this.permissions = data;
        this.cdr.detectChanges();
      },
      error: (err) => console.log('Erreur:', err)
    });
  }

  openForm() {
    this.showForm = true;
    this.isEdit = false;
    this.form = { nom: '', description: '' };
  }

  editPermission(p: any) {
    this.showForm = true;
    this.isEdit = true;
    this.form = { ...p };
  }

  savePermission() {
    if (this.isEdit) {
      this.permissionService.update(this.form.idPermission, this.form).subscribe(() => {
        this.loadPermissions();
        this.showForm = false;
      });
    } else {
      this.permissionService.create(this.form).subscribe(() => {
        this.loadPermissions();
        this.showForm = false;
      });
    }
  }

  deletePermission(id: number) {
    if (confirm('Confirmer la suppression ?')) {
      this.permissionService.delete(id).subscribe(() => this.loadPermissions());
    }
  }

  cancel() {
    this.showForm = false;
  }
}