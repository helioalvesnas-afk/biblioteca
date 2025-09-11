import { createReducer, on } from '@ngrx/store';
//import * as AutorActions from '../actions/autor.actions';
import { Autor } from '../../../shared/models/autor';
import { IState } from '../state/sate';

export const initialState: IState<Autor> = {
  lista: [],
  selecionado: null,
  erro: null,
  loading: false
};

export const autorReducer = createReducer(
  initialState,

  // carregar

  // buscar por id

  // adicionar

  // atualizar

  // remover

);
