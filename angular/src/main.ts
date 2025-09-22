import { isDevMode, importProvidersFrom } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import 'zone.js';


import { httpInterceptorProviders } from './app/core/interceptors';
import { GeneroService } from './app/core/services/genero.service';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { BrowserModule, bootstrapApplication } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from './app/app-routing.module';
import { StoreModule } from '@ngrx/store';
import { reducers } from './app/core/state/state/reducers';
import { EffectsModule } from '@ngrx/effects';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { AppComponent } from './app/app.component';
import generoEffects from './app/core/state/effects/genero.effects';

function safeAngularDevToolsInit() {
  try {
    if (isDevMode()) {
      // Só em modo dev o DevTools é carregado
      console.info('✅ Angular DevTools ativo (modo desenvolvimento).');
    } else {
      // Em prod, apenas ignora — nada de loop
      console.info('ℹ️ Angular DevTools desativado (modo produção).');
      // Remove referência global se existir
      if ((window as any).ng) {
        delete (window as any).ng;
      }
    }
  } catch (err) {
    console.warn('⚠️ Erro ao inicializar Angular DevTools:', err);
  }
}

// Bootstrap principal
bootstrapApplication(AppComponent, {
    providers: [
        importProvidersFrom(
          ReactiveFormsModule,
          BrowserModule,
          RouterModule,
          CommonModule,
          FormsModule,
          AppRoutingModule,
          StoreModule.forRoot(reducers),
          EffectsModule.forRoot([generoEffects]),
          StoreDevtoolsModule.instrument({ maxAge: 25 })),
        httpInterceptorProviders,
        GeneroService,
        provideHttpClient(withInterceptorsFromDi())
    ]
})
  .then(() => safeAngularDevToolsInit())
  .catch(err => console.error(err));
