import { Autor } from "../../../shared/models/autor";
import { Genero } from "../../../shared/models/genero";
import { IState } from "./sate";

export interface IAppStateGenero {
  genero: IState<Genero>;
}

export interface IAppStateAutor {
  autor: IState<Autor>;
}
