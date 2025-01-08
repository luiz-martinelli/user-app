import { Component } from '@angular/core';

import { CommonModule } from '@angular/common';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { catchError, tap } from 'rxjs';

import { TokenService } from '../../../services/token_service';

@Component({
  selector: 'app-login-form',
  standalone: true,
  imports: [
    CommonModule,
    MatSnackBarModule,
    MatProgressSpinnerModule,
    MatIconModule, 
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule
  ],
  templateUrl: './login_form.component.html',
  styleUrl: './login_form.component.scss'
})
export class LoginFormComponent {
  isLoading = false;

  constructor(
    private tokenService: TokenService, 
    private snackBar: MatSnackBar,
    private router: Router,) {}

    buscarToken(): void {
      this.isLoading = true;
      localStorage.removeItem('jwtToken')
    
      this.tokenService.getJwtToken().pipe(
        tap((response: any) => {
          localStorage.setItem('jwtToken', response.token);
          this.showSnackBar('Token obtido salvo com sucesso!');
          this.router.navigate(['/usuarios']);
        }),
        catchError((error) => {
          this.showSnackBar('Erro ao buscar o token JWT. Tente novamente.');
          console.error('Erro ao buscar o token JWT', error);
          throw error;
        })
      ).subscribe({
        complete: () => {
          this.isLoading = false;
        },
        error: () => {
          this.isLoading = false;
        }
      });
    }

  showSnackBar(message: string): void {
    this.snackBar.open(message, '', {
      duration: 3000,
      horizontalPosition: 'right',
      verticalPosition: 'top'
    });
  }
}
