import { IAppState } from '../state/app-sate';

const selectorAutor = () => {

  const selectAutors = (app: IAppState) => app.autor.lista;

  const selectAutorSelecionado = (app: IAppState) => app.autor.selecionado;

  const selectLoading = (app: IAppState) => app.autor.loading;

  const selectError = (app: IAppState) => app.autor.erro;

  return {
    selectAutors,
    selectAutorSelecionado,
    selectLoading,
    selectError
  };
}


export default selectorAutor;
