package br.com.biblioteca.controller;

import br.com.biblioteca.dto.ApiResponse;
import br.com.biblioteca.entity.Livro;
import br.com.biblioteca.service.LivroService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LivroControllerTest {

    @Mock
    private LivroService livroService;

    @InjectMocks
    private LivroController livroController;

    @Test
    void deveExcluirLivro() {
        doNothing().when(livroService).excluir(1L);

        ResponseEntity<?> response = livroController.excluir(1L);

        assertThat(response.getStatusCode().value()).isEqualTo(204);
        verify(livroService, times(1)).excluir(1L);
    }
}