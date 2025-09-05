import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AutenticacaoGuard } from './core/guards/autenticacao.guard';
import { LoginComponent } from './features/login/login.component';
import { InicioComponent } from './layout/inicio/inicio.component';
import { GeneroListComponent } from './features/genero/genero-list/genero-list.component';
import { GeneroFormComponent } from './features/genero/genero-form/genero-form.component';
import { AutorListComponent } from './features/autor/autor-list/autor-list.component';
import { AutorFormComponent } from './features/autor/autor-form/autor-form.component';
import { CriarContaComponent } from './features/conta/criar-conta/criar-conta.component';
import { AutenticacaoComponent } from './layout/autenticacao/autenticacao.component';
import { LivroListComponent } from './features/livro/livro-list/livro-list.component';
import { LivroFormComponent } from './features/livro/livro-form/livro-form.component';

const routes: Routes = [
  {
    path: 'autores',
    component: InicioComponent,
    children: [
      { path: '', component: AutorListComponent },
      { path: 'list', component: AutorListComponent },
      { path: 'new', component: AutorFormComponent },
      { path: 'edit/:id', component: AutorFormComponent },
    ],
    canActivate: [AutenticacaoGuard]
  },
  {
    path: 'generos',
    component: InicioComponent,
    children: [
      { path: '', component: GeneroListComponent },
      { path: 'list', component: GeneroListComponent },
      { path: 'new', component: GeneroFormComponent },
      { path: 'edit/:id', component: GeneroFormComponent },
    ],
    canActivate: [AutenticacaoGuard]
  },
  {
    path: 'livros',
    component: InicioComponent,
    children: [
      { path: '', component: LivroListComponent },
      { path: 'list', component: LivroListComponent },
      { path: 'new', component: LivroFormComponent },
      { path: 'edit/:id', component: LivroFormComponent },
    ],
    canActivate: [AutenticacaoGuard]
  },
  {
    path: '',
    component: AutenticacaoComponent,
    children: [
      { path: '', redirectTo: 'login', pathMatch: 'full' },
      { path: 'login', component: LoginComponent },
      { path: 'criar-conta', component: CriarContaComponent }
    ]
  },
  { path: '**', redirectTo: '' }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
