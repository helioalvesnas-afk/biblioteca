import { ApiResponse } from "../../../shared/dto/api-response";

export interface IState<T> {
  lista: T[] | null;
  selecionado: ApiResponse<T> | null;
  erro: any;
  loading: boolean;
}
