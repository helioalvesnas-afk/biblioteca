import { ActionReducerMap } from "@ngrx/store";
import { generoReducer } from "../reducer/genero.reducer";
import { IAppStateAutor, IAppStateGenero } from "./app-sate";

export const reducerGenero: ActionReducerMap<IAppStateGenero> = {
  genero: generoReducer
};

export const reducerAutor: ActionReducerMap<IAppStateAutor> = {
  autor: null
};
