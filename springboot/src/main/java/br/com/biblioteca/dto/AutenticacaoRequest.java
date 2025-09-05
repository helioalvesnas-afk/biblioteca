package br.com.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;

public record AutenticacaoRequest(@NotBlank String username, @NotBlank String password) {
}
