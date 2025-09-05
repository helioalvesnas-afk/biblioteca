import { TestBed, async, inject } from '@angular/core/testing';

import { AutenticacaoGuard } from './autenticacao.guard';

describe('AuthGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AutenticacaoGuard]
    });
  });

  it('should ...', inject([AutenticacaoGuard], (guard: AutenticacaoGuard) => {
    expect(guard).toBeTruthy();
  }));
});
