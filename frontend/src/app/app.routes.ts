import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsuarioListComponent } from './pages/usuario/list/usuario_list.component';
import { UsuarioFormComponent } from './pages/usuario/form/usuario_form.component';

export const routes: Routes = [
  { path: '', component: UsuarioListComponent, pathMatch: 'full' },
  { path: 'cadastrar', component: UsuarioFormComponent },
  { path: 'editar', component: UsuarioFormComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})

export class AppRoutesModule {};
