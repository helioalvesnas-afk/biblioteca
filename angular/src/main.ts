import { enableProdMode, isDevMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';

if (environment.production) {
  enableProdMode();
}

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
platformBrowserDynamic()
  .bootstrapModule(AppModule)
  .then(() => safeAngularDevToolsInit())
  .catch(err => console.error(err));
