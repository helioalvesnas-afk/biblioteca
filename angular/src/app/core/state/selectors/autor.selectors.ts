import { IAppStateAutor } from '../state/app-sate';

export const selectAutors = (app: IAppStateAutor) => app.autor.lista;

export const selectAutorSelecionado = (app: IAppStateAutor) => app.autor.selecionado;

export const selectLoading = (app: IAppStateAutor) => app.autor.loading;

export const selectError = (app: IAppStateAutor) => app.autor.erro;

export const autorSelectors = {
  selectAutors,
  selectAutorSelecionado,
  selectLoading,
  selectError
};

export default autorSelectors;
