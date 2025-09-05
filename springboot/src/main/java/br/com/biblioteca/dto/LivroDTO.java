package br.com.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroDTO(Long id, @NotBlank String titulo, Integer anoPublicacao, @NotNull Long autorId, @NotNull Long generoId) {
}
