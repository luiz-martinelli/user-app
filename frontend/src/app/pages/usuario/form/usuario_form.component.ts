import { Component, OnInit } from '@angular/core';

import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

import { UserService } from '../../../services/user_service';

@Component({
  selector: 'app-usuario-form',
  standalone: true,
  imports: [
    CommonModule, 
    ReactiveFormsModule, 
    MatIconModule, 
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule
  ],
  templateUrl: './usuario_form.component.html',
  styleUrl: './usuario_form.component.scss'
})
export class UsuarioFormComponent implements OnInit {
  usuarioForm: FormGroup;
  usuarioData: any;

  constructor(
    private formBuilder: FormBuilder, 
    private router: Router,
     private usuarioService: UserService,
    private snackBar: MatSnackBar) {
    this.usuarioForm = this.formBuilder.group({
      id: [null],
      nome: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      senha: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  ngOnInit(): void {
    if (history.state.usuario) {
      this.usuarioData = history.state.usuario;
      this.usuarioForm.patchValue(this.usuarioData);
      this.usuarioForm.get('id')?.setValue(this.usuarioData.id);
    }
  }

  onCancel(): void {
    this.usuarioForm.reset();
    this.router.navigate(['']);
  }

  onSubmit(): void {
    if (this.usuarioForm.valid) {
      if (this.usuarioData) {
        this.usuarioService.updateItem(this.usuarioForm.value).subscribe({
          next: () => {
            this.showSnackBar('Usuário atualizado com sucesso!')
            this.router.navigate(['']);
          },
          error: (err) => this.handleError(err),
        });
      } else {
        this.usuarioService.createItem(this.usuarioForm.value).subscribe({
          next: () => {
            this.showSnackBar('Usuário adicionado com sucesso!')
            this.router.navigate(['']);
          },
          error: (err) => this.handleError(err),
        });
      }
    } else {
      console.log('Formulário inválido.');
    }
  }

  private handleError(err: any): void {
    const errorDetails = err?.error?.details;
    if (errorDetails && Array.isArray(errorDetails)) {
      this.showSnackBar(errorDetails.join(' '))
    } else {
      this.showSnackBar('Ocorreu um erro ao processar a solicitação.')
    }
  }
  
  hasError(controlName: string, errorType: string): boolean {
    const control = this.usuarioForm.get(controlName);
    return !!(control?.hasError(errorType) && (control.touched ?? false));
  }

  showSnackBar(message: string): void {
    this.snackBar.open(message, '', {
      duration: 3000,
      horizontalPosition: 'right',
      verticalPosition: 'top',
      panelClass: ['error-snackbar'],
    });
  }
}
