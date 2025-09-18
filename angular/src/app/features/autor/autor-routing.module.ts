import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InicioComponent } from '../../layout/inicio/inicio.component';
import { AutenticacaoGuard } from '../../core/guards/autenticacao.guard';
import { AutorListComponent } from './autor-list/autor-list.component';
import { AutorFormComponent } from './autor-form/autor-form.component';

const routes: Routes = [
  {
    path: '',
    component: InicioComponent,
    canActivate: [AutenticacaoGuard],
    children: [
      { path: '', component: AutorListComponent },
      { path: 'list', component: AutorListComponent },
      { path: 'new', component: AutorFormComponent },
      { path: 'edit/:id', component: AutorFormComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AutorRoutingModule {}
