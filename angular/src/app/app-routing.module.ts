import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './features/login/login.component';
import { CriarContaComponent } from './features/conta/criar-conta/criar-conta.component';
import { AutenticacaoComponent } from './layout/autenticacao/autenticacao.component';

const routes: Routes = [
  {
    path: 'autores',
    loadChildren: () => import('./features/autor/autor.module')
      .then(m => m.AutorModule)
  },
  {
    path: 'generos',
    loadChildren: () => import('./features/genero/genero.module')
      .then(m => m.GeneroModule)
  },
  {
    path: 'livros',
    loadChildren: () => import('./features/livro/livro.module')
      .then(m => m.LivroModule)
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
