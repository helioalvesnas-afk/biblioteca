import { createAction, props } from '@ngrx/store';
import { Genero } from '../../../../shared/models/genero';
import { ApiResponse } from '../../../../shared/dto/api-response';

// carregar
export const carregarGeneros = createAction('[Genero] Carregar');
export const carregarGenerosSucesso = createAction('[Genero] Carregar Sucesso', props<{ generos: Genero[] }>());
export const carregarGenerosFalha = createAction('[Genero] Carregar Falha', props<{ erro: any }>());

// buscar por id
export const buscarGeneroPorId = createAction('[Genero] Buscar Por Id', props<{ id: string }>());
export const buscarGeneroPorIdSucesso = createAction('[Genero] Buscar Por Id Sucesso', props<{ apiResponse: ApiResponse<Genero> }>());
export const buscarGeneroPorIdFalha = createAction('[Genero] Buscar Por Id Falha', props<{ erro: any }>());

// adicionar
export const adicionarGenero = createAction('[Genero] Adicionar', props<{ genero: Genero }>());
export const adicionarGeneroSucesso = createAction('[Genero] Adicionar Sucesso', props<{ genero: Genero }>());
export const adicionarGeneroFalha = createAction('[Genero] Adicionar Falha', props<{ erro: any }>());

// atualizar
export const atualizarGenero = createAction('[Genero] Atualizar', props<{ genero: Genero }>());
export const atualizarGeneroSucesso = createAction('[Genero] Atualizar Sucesso', props<{ genero: Genero }>());
export const atualizarGeneroFalha = createAction('[Genero] Atualizar Falha', props<{ erro: any }>());

// remover
export const removerGenero = createAction('[Genero] Remover', props<{ id: string }>());
export const removerGeneroSucesso = createAction('[Genero] Remover Sucesso', props<{ id: string }>());
export const removerGeneroFalha = createAction('[Genero] Remover Falha', props<{ erro: any }>());
