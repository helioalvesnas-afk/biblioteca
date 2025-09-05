package br.com.biblioteca.dto;

public record ApiResponse<T>(T data, String message, int status) {}