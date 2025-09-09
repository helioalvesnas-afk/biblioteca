package br.com.biblioteca.dto;

public class ApiResponse<T> {
    private boolean sucesso;
    private String mensagem;
    private T dado;

    public ApiResponse(boolean sucesso, String mensagem, T dado) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
        this.dado = dado;
    }

    // Fábrica de sucesso
    public static <T> ApiResponse<T> sucesso(T dado) {
        return new ApiResponse<>(true, "Operação realizada com sucesso", dado);
    }

    public static <T> ApiResponse<T> sucesso(T dado, String mensagem) {
        return new ApiResponse<>(true, mensagem, dado);
    }

    // Fábrica de erro
    public static <T> ApiResponse<T> erro() {
        return new ApiResponse<>(false, "Ocorreu um erro", null);
    }

    public static <T> ApiResponse<T> erro(String mensagem) {
        return new ApiResponse<>(false, mensagem, null);
    }

    // Getters e Setters
    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public T getDado() {
        return dado;
    }

    public void setDado(T dado) {
        this.dado = dado;
    }
}