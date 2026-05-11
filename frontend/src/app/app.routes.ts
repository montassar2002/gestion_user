import { Routes } from '@angular/router';
import { Login } from './components/login/login';
import { Dashboard } from './components/dashboard/dashboard';
import { Utilisateurs } from './components/utilisateurs/utilisateurs';
import { Roles } from './components/roles/roles';
import { Permissions } from './components/permissions/permissions';
import { Historique } from './components/historique/historique';
import { authGuard } from './guards/auth-guard';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: Login },
  { path: 'dashboard', component: Dashboard, canActivate: [authGuard] },
  { path: 'utilisateurs', component: Utilisateurs, canActivate: [authGuard] },
  { path: 'roles', component: Roles, canActivate: [authGuard] },
  { path: 'permissions', component: Permissions, canActivate: [authGuard] },
  { path: 'historique', component: Historique, canActivate: [authGuard] }
];