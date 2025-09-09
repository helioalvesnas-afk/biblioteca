export class ApiResponse<T> {
  sucesso: boolean;
  mensagem: string;
  dado: T | null;

  constructor(sucesso: boolean, mensagem: string, dado: T | null) {
    this.dado = dado;
    this.mensagem = mensagem;
    this.sucesso = sucesso;
  }

  static sucesso<T>(dado: T, mensagem = 'Operação realizada com sucesso'): ApiResponse<T> {
    return new ApiResponse<T>(true, mensagem, dado);
  }

  static erro<T>(mensagem = 'Ocorreu um erro'): ApiResponse<T> {
    return new ApiResponse<T>(false, mensagem, null);
  }

}
