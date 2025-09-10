import { Genero } from "../../../shared/models/genero";
import { IState } from "./sate";

export interface IAppStateGenero {
  genero: IState<Genero>;
}
