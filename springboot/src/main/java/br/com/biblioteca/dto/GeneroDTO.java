package br.com.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;

public record GeneroDTO(Long id, @NotBlank String nome) {
}
