import { inject } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, of, switchMap } from 'rxjs';
import { carregarGenerosSucesso, carregarGenerosFalha, buscarGeneroPorIdSucesso, buscarGeneroPorIdFalha, carregarGeneros, buscarGeneroPorId, adicionarGenero, adicionarGeneroSucesso, adicionarGeneroFalha, atualizarGenero, atualizarGeneroSucesso, atualizarGeneroFalha, removerGenero, removerGeneroSucesso, removerGeneroFalha } from '../../actions/genero/genero.actions';
import { GeneroService } from '../../../../core/services/genero.service';
import { ApiResponse } from 'src/app/shared/dto/api-response';


export const carregarGeneroEffect = createEffect((
  actions$ = inject(Actions),
  service = inject(GeneroService)
) => actions$.pipe(
  ofType(carregarGeneros),
  switchMap(() =>
    service.carregarGeneros().pipe(
      map(generos => carregarGenerosSucesso({ generos })),
      catchError(erro => of(carregarGenerosFalha({ erro })))
    )
  )
), { functional: true });

export const buscarGeneroPorIdEffect = createEffect((
  actions$ = inject(Actions),
  service = inject(GeneroService)
) => actions$.pipe(
  ofType(buscarGeneroPorId),
  switchMap(action =>
    service.buscarGeneroPorId(action.id).pipe(
      map(apiResponse => buscarGeneroPorIdSucesso({ apiResponse })),
      catchError(erro => of(buscarGeneroPorIdFalha({ erro })))
    )
  )
), { functional: true });

export const adicionarGeneroEffect = createEffect((
  actions$ = inject(Actions),
  service = inject(GeneroService)
) => actions$.pipe(
  ofType(adicionarGenero),
  switchMap(action =>
    service.adicionarGenero(action.genero).pipe(
      map(genero => adicionarGeneroSucesso({ genero })),
      catchError(erro => of(adicionarGeneroFalha({ erro })))
    )
  )
), { functional: true });

export const atualizarGeneroEffect = createEffect((
  actions$ = inject(Actions),
  service = inject(GeneroService)
) => actions$.pipe(
  ofType(atualizarGenero),
  switchMap(action =>
    service.atualizarGenero(action.genero).pipe(
      map(genero => atualizarGeneroSucesso({ genero })),
      catchError(erro => of(atualizarGeneroFalha({ erro })))
    )
  )
), { functional: true });

export const removerGeneroEffect = createEffect((
  actions$ = inject(Actions),
  service = inject(GeneroService)
) => actions$.pipe(
  ofType(removerGenero),
  switchMap(action =>
    service.removerGenero(action.id).pipe(
      map(() => removerGeneroSucesso({ id: action.id })),
      catchError(erro => of(removerGeneroFalha({ erro })))
    )
  )
), { functional: true });

export const generoEffects = {
  carregarGeneroEffect,
  buscarGeneroPorIdEffect,
  adicionarGeneroEffect,
  atualizarGeneroEffect,
  removerGeneroEffect
};

export default generoEffects;
