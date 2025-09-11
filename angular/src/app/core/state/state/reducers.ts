import { ActionReducerMap } from "@ngrx/store";
import { autorReducer } from "../reducer/autor.reducer";
import { generoReducer } from "../reducer/genero.reducer";
import { IAppState } from "./app-sate";

export const reducers: ActionReducerMap<IAppState> = {
  genero: generoReducer,
  autor: autorReducer
};
