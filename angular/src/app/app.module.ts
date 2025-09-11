import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';

import { httpInterceptorProviders } from './core/interceptors';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './features/login/login.component';
import { AutenticacaoComponent } from './layout/autenticacao/autenticacao.component';
import { InicioComponent } from './layout/inicio/inicio.component';
import { GeneroListItemComponent } from './features/genero/genero-list-item/genero-list-item.component';
import { GeneroFormComponent } from './features/genero/genero-form/genero-form.component';
import { GeneroListComponent } from './features/genero/genero-list/genero-list.component';
import { CriarContaComponent } from './features/conta/criar-conta/criar-conta.component';
import { AutorListComponent } from './features/autor/autor-list/autor-list.component';
import { AutorListItemComponent } from './features/autor/autor-list-item/autor-list-item.component';
import { AutorFormComponent } from './features/autor/autor-form/autor-form.component';
import { LivroListItemComponent } from './features/livro/livro-list-item/livro-list-item.component';
import { LivroListComponent } from './features/livro/livro-list/livro-list.component';
import { LivroFormComponent } from './features/livro/livro-form/livro-form.component';
import { GeneroService } from './core/services/genero.service';

import generoEffects from './core/state/effects/genero.effects';
import { reducers } from './core/state/state/reducers';

@NgModule({
  declarations: [
    AppComponent,

    GeneroListComponent,
    GeneroListItemComponent,
    GeneroFormComponent,

    AutorListComponent,
    AutorListItemComponent,
    AutorFormComponent,

    LivroListComponent,
    LivroListItemComponent,
    LivroFormComponent,

    LoginComponent,
    CriarContaComponent,
    AutenticacaoComponent,
    InicioComponent
  ],
  imports: [
    ReactiveFormsModule,
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    StoreModule.forRoot(reducers),
    EffectsModule.forRoot([generoEffects]),
    StoreDevtoolsModule.instrument({ maxAge: 25 }),
  ],
  providers: [
    httpInterceptorProviders,
    GeneroService,
  ],
  bootstrap: [AppComponent],
  schemas: []
})
export class AppModule { }
