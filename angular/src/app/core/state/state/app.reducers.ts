import { ActionReducerMap } from "@ngrx/store";
import { generoReducer } from "../reducer/genero.reducer";
import { IAppStateGenero } from "./app-sate";

export const reducerGenero: ActionReducerMap<IAppStateGenero> = {
  genero: generoReducer
};
