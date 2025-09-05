package br.com.biblioteca.exception;

import br.com.biblioteca.dto.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setup() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void deveTratarResourceNotFoundException() {
        ResourceNotFoundException ex = new ResourceNotFoundException("Recurso não encontrado");

        ResponseEntity<ApiResponse<Void>> response = handler.handleNotFound(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().message()).isEqualTo("Recurso não encontrado");
        assertThat(response.getBody().data()).isNull();
        assertThat(response.getBody().status()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void deveTratarMethodArgumentNotValidException() {
        FieldError fieldError = new FieldError("obj", "nome", "Nome obrigatório");
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(mock(org.springframework.validation.BindingResult.class));
        when(ex.getBindingResult().getFieldErrors()).thenReturn(List.of(fieldError));

        ResponseEntity<ApiResponse<Map<String, String>>> response = handler.handleValidation(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().message()).isEqualTo("Falha na validação");
        assertThat(response.getBody().data()).containsEntry("nome", "Nome obrigatório");
    }

    @Test
    void deveTratarRuntimeException() {
        RuntimeException ex = new RuntimeException("Erro de negócio");

        ResponseEntity<?> response = handler.handleRuntime(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        Map<?, ?> body = (Map<?, ?>) response.getBody();
        //assertThat(body).containsEntry("error", "Erro de negócio");
    }

    @Test
    void deveTratarExceptionGenerica() {
        Exception ex = new Exception("Erro inesperado");

        ResponseEntity<ApiResponse<Void>> response = handler.handleAny(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody().message()).isEqualTo("Erro interno");
        assertThat(response.getBody().data()).isNull();
        assertThat(response.getBody().status()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
