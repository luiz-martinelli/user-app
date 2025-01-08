import { Component, OnInit } from '@angular/core';

import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

import { UserService } from '../../../services/user_service';

@Component({
  selector: 'app-usuario',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatIconModule,
    MatSnackBarModule,
  ],
  templateUrl: './usuario_list.component.html',
  styleUrls: ['./usuario_list.component.scss'],
})
export class UsuarioListComponent implements OnInit {
  usuarios = [];
  totalUsuarios: number | undefined;
  itemsPorPagina = 10;
  paginaAtual = 0;
  hidePageSize = false;

  constructor(
    private activatedRoute: ActivatedRoute,
    private usuarioService: UserService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      this.paginaAtual = params['page'] ? parseInt(params['page'], 10) : 0;
      this.carregarUsuarios(this.paginaAtual, this.itemsPorPagina);
    });
  }

  carregarUsuarios(pagina: number, limite: number) {
    this.usuarioService.getItems(pagina, limite).subscribe((response: any) => {
      this.usuarios = response.content; 
      this.totalUsuarios = response.totalElements;
    });
  }  

  trocarPagina(event: PageEvent) {
    this.paginaAtual = event.pageIndex;
    this.itemsPorPagina = event.pageSize;
    this.carregarUsuarios(this.paginaAtual, this.itemsPorPagina);
  }

  excluirUsuario(usuarioId: number) {
    if (confirm('Tem certeza que deseja excluir este usuário?')) {
      this.usuarioService.deleteItem(usuarioId).subscribe(() => {
        this.carregarUsuarios(this.paginaAtual, this.itemsPorPagina);
        this.showSnackBar('Usuário excluído com sucesso!')        
      });
    }
  }

  irParaCadastro(): void {
    this.router.navigate(['/cadastrar']);
  }

  irParaEdicao(usuario: any): void {
    this.router.navigate(['/editar'], { state: { usuario: usuario } });
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
