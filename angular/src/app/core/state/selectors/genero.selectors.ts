import { IAppStateGenero } from '../state/app-sate';

const selectorGenero = () => {

  const selectGeneros = (app: IAppStateGenero) => app.genero.lista;

  const selectGeneroSelecionado = (app: IAppStateGenero) => app.genero.selecionado;

  const selectLoading = (app: IAppStateGenero) => app.genero.loading;

  const selectError = (app: IAppStateGenero) => app.genero.erro;

  return {
    selectGeneros,
    selectGeneroSelecionado,
    selectLoading,
    selectError
  };
}


export default selectorGenero;
