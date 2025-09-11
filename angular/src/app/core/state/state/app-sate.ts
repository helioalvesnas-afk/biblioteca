import { Autor } from "../../../shared/models/autor";
import { Genero } from "../../../shared/models/genero";
import { IState } from "./sate";

export interface IAppState {
  genero: IState<Genero>;
  autor: IState<Autor>;
}
