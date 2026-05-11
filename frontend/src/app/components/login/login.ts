import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.html'
})
export class Login {
  loginData = {
    email: '',
    motDePasse: ''
  };
  errorMessage = '';
  loading = false;

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    this.loading = true;
    this.errorMessage = '';
    this.authService.login(this.loginData.email, this.loginData.motDePasse).subscribe({
      next: (res) => {
        this.authService.saveToken(res.token);
        this.authService.saveUser({ nom: res.nom, email: res.email, role: res.role });
        this.loading = false;
        this.router.navigate(['/dashboard']);
      },
      error: () => {
        this.errorMessage = 'Email ou mot de passe incorrect';
        this.loading = false;
      }
    });
  }
}