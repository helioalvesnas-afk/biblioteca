import { createFeatureSelector, createSelector } from '@ngrx/store';
import { GeneroState } from '../../reducer/genero/genero.reducer';

export const selectGeneroState = createFeatureSelector<GeneroState>('genero');

export const selectGeneros = createSelector(selectGeneroState, state => state.generos);
export const selectGeneroSelecionado = createSelector(selectGeneroState, state => state.generoSelecionado);
export const selectLoading = createSelector(selectGeneroState, state => state.loading);
export const selectError = createSelector(selectGeneroState, state => state.erro);

export const generoSelectors = {
  selectGeneros,
  selectGeneroSelecionado,
  selectLoading,
  selectError
};

export default generoSelectors;
