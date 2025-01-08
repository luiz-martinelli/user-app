import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsuarioListComponent } from './pages/usuario/list/usuario_list.component';
import { UsuarioFormComponent } from './pages/usuario/form/usuario_form.component';
import { LoginFormComponent } from './pages/login/form/login_form.component';

export const routes: Routes = [
  { path: '', component: LoginFormComponent, pathMatch: 'full' },
  { path: 'usuarios', component: UsuarioListComponent },
  { path: 'cadastrar', component: UsuarioFormComponent },
  { path: 'editar', component: UsuarioFormComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})

export class AppRoutesModule {};
