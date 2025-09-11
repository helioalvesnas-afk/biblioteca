import { IAppState } from '../state/app-sate';

const selectorGenero = () => {

  const selectGeneros = (app: IAppState) => app.genero.lista;

  const selectGeneroSelecionado = (app: IAppState) => app.genero.selecionado;

  const selectLoading = (app: IAppState) => app.genero.loading;

  const selectError = (app: IAppState) => app.genero.erro;

  return {
    selectGeneros,
    selectGeneroSelecionado,
    selectLoading,
    selectError
  };
}


export default selectorGenero;
