package br.com.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;

public record CadastrarRequest(@NotBlank String username, @NotBlank String password) {
}
