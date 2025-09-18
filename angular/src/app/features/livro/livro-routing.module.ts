import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InicioComponent } from '../../layout/inicio/inicio.component';
import { AutenticacaoGuard } from '../../core/guards/autenticacao.guard';
import { LivroListComponent } from './livro-list/livro-list.component';
import { LivroFormComponent } from './livro-form/livro-form.component';

const routes: Routes = [
  {
    path: '',
    component: InicioComponent,
    canActivate: [AutenticacaoGuard],
    children: [
      { path: '', component: LivroListComponent },
      { path: 'list', component: LivroListComponent },
      { path: 'new', component: LivroFormComponent },
      { path: 'edit/:id', component: LivroFormComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LivroRoutingModule {}
