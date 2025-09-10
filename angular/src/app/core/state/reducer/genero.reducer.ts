import { createReducer, on } from '@ngrx/store';
import * as GeneroActions from '../actions/genero.actions';
import { Genero } from '../../../shared/models/genero';
import { IState } from '../state/sate';

export const initialState: IState<Genero> = {
  lista: [],
  selecionado: null,
  erro: null,
  loading: false
};

export const generoReducer = createReducer(
  initialState,

  // carregar
  on(GeneroActions.carregarGeneros, state => ({
    ...state,
    loading: false
  })),
  on(GeneroActions.carregarGenerosSucesso, (state, { generos }) => ({
    ...state,
    lista: generos,
    loading: false
  })),
  on(GeneroActions.carregarGenerosFalha, (state, { erro }) => ({
    ...state,
    error: erro,
    loading: false
  })),

  // buscar por id
  on(GeneroActions.buscarGeneroPorIdSucesso, (state, { apiResponse }) => ({
    ...state,
    selecionado: apiResponse,
    loading: false
  })),
  on(GeneroActions.buscarGeneroPorIdFalha, (state, { erro }) => ({
    ...state,
    error: erro,
    loading: false
  })),

  // adicionar
  on(GeneroActions.adicionarGeneroSucesso, (state, { genero }) => ({
    ...state,
    lista: [...state.lista, genero],
    loading: false
  })),
  on(GeneroActions.adicionarGeneroFalha, (state, { erro }) => ({
    ...state,
    error: erro,
    loading: false
  })),

  // atualizar
  on(GeneroActions.atualizarGeneroSucesso, (state, { genero }) => ({
    ...state,
    lista: state.lista.map(g => g.id === genero.id ? genero : g),
    loading: false
  })),
  on(GeneroActions.atualizarGeneroFalha, (state, { erro }) => ({
    ...state,
    error: erro,
    loading: false
  })),

  // remover
  on(GeneroActions.removerGeneroSucesso, (state, { id }) => ({
    ...state,
    lista: state.lista.filter(g => g.id !== id),
    loading: false
  })),
  on(GeneroActions.removerGeneroFalha, (state, { erro }) => ({
    ...state,
    error: erro,
    loading: false
  }))
);
