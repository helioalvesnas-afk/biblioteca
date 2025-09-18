import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InicioComponent } from '../../layout/inicio/inicio.component';
import { AutenticacaoGuard } from '../../core/guards/autenticacao.guard';
import { GeneroListComponent } from './genero-list/genero-list.component';
import { GeneroFormComponent } from './genero-form/genero-form.component';

const routes: Routes = [
  {
    path: '',
    component: InicioComponent,
    canActivate: [AutenticacaoGuard],
    children: [
      { path: '', component: GeneroListComponent },
      { path: 'list', component: GeneroListComponent },
      { path: 'new', component: GeneroFormComponent },
      { path: 'edit/:id', component: GeneroFormComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GeneroRoutingModule {}
