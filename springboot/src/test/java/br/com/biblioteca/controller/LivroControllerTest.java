package br.com.biblioteca.controller;

import br.com.biblioteca.service.LivroService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
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