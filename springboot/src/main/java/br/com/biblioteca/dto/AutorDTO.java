package br.com.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;

public record AutorDTO(Long id, @NotBlank String nome) {
}
