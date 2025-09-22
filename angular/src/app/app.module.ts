import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { CommonModule } from '@angular/common';

import { httpInterceptorProviders } from './core/interceptors';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './features/login/login.component';
import { AutenticacaoComponent } from './layout/autenticacao/autenticacao.component';
import { InicioComponent } from './layout/inicio/inicio.component';
import { CriarContaComponent } from './features/conta/criar-conta/criar-conta.component';

import { GeneroService } from './core/services/genero.service';

import generoEffects from './core/state/effects/genero.effects';
import { reducers } from './core/state/state/reducers';

@NgModule({ declarations: [AppComponent],
    bootstrap: [AppComponent],
    schemas: [], imports: [ReactiveFormsModule,
        BrowserModule,
        RouterModule,
        CommonModule,
        FormsModule,
        AppRoutingModule,
        StoreModule.forRoot(reducers),
        EffectsModule.forRoot([generoEffects]),
        StoreDevtoolsModule.instrument({ maxAge: 25 }), LoginComponent,
        CriarContaComponent,
        AutenticacaoComponent,
        InicioComponent], providers: [
        httpInterceptorProviders,
        GeneroService,
        provideHttpClient(withInterceptorsFromDi()),
    ] })
export class AppModule { }
