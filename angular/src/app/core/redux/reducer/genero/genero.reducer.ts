import { createReducer, on } from '@ngrx/store';
import * as GeneroActions from '../../actions/genero/genero.actions';
import { Genero } from './../../../../shared/models/genero';
import { ApiResponse } from './../../../../shared/dto/api-response';

export interface GeneroState {
  generos: Genero[];
  generoSelecionado: ApiResponse<Genero> | null;
  erro: any;
  loading: boolean;
}

export const initialState: GeneroState = {
  generos: [],
  generoSelecionado: null,
  erro: null,
  loading: false
};

export const generoReducer = createReducer(
  initialState,

  // carregar
  on(GeneroActions.carregarGeneros, state => ({ ...state, loading: true })),
  on(GeneroActions.carregarGenerosSucesso, (state, { generos }) => ({
    ...state,
    generos,
    loading: false
  })),
  on(GeneroActions.carregarGenerosFalha, (state, { erro }) => ({ ...state, error: erro, loading: false })),

  // buscar por id
  on(GeneroActions.buscarGeneroPorIdSucesso, (state, { apiResponse }) => ({
    ...state,
    generoSelecionado: apiResponse,
    loading: false
  })),
  on(GeneroActions.buscarGeneroPorIdFalha, (state, { erro }) => ({ ...state, error: erro, loading: false })),

  // adicionar
  on(GeneroActions.adicionarGeneroSucesso, (state, { genero }) => ({
    ...state,
    generos: [...state.generos, genero],
    loading: false
  })),
  on(GeneroActions.adicionarGeneroFalha, (state, { erro }) => ({ ...state, error: erro, loading: false })),

  // atualizar
  on(GeneroActions.atualizarGeneroSucesso, (state, { genero }) => ({
    ...state,
    generos: state.generos.map(g => g.id === genero.id ? genero : g),
    loading: false
  })),
  on(GeneroActions.atualizarGeneroFalha, (state, { erro }) => ({ ...state, error: erro, loading: false })),

  // remover
  on(GeneroActions.removerGeneroSucesso, (state, { id }) => ({
    ...state,
    generos: state.generos.filter(g => g.id !== id),
    loading: false
  })),
  on(GeneroActions.removerGeneroFalha, (state, { erro }) => ({ ...state, error: erro, loading: false }))
);
