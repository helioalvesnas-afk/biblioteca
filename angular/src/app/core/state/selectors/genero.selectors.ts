import { IAppStateGenero } from '../state/app-sate';

export const selectGeneros = (app: IAppStateGenero) => app.genero.lista;

export const selectGeneroSelecionado = (app: IAppStateGenero) => app.genero.selecionado;

export const selectLoading = (app: IAppStateGenero) => app.genero.loading;

export const selectError = (app: IAppStateGenero) => app.genero.erro;

export const generoSelectors = {
  selectGeneros,
  selectGeneroSelecionado,
  selectLoading,
  selectError
};

export default generoSelectors;
