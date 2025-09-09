package br.com.biblioteca.exception;

import br.com.biblioteca.dto.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setup() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void testHandleNotFound() {
        ResourceNotFoundException ex = new ResourceNotFoundException("Recurso não encontrado");

        ResponseEntity<?> response = handler.handleNotFound(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ApiResponse<?> body = (ApiResponse<?>) response.getBody();
        assertNotNull(body);
        assertFalse(body.isSucesso());
        assertEquals("Recurso não encontrado", body.getMensagem());
        assertNull(body.getDado());
    }

    @Test
    void testHandleValidation() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        var bindingResult = mock(org.springframework.validation.BindingResult.class);

        FieldError fieldError = new FieldError("object", "campo", "Obrigatório");
        when(ex.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));
        when(ex.getMessage()).thenReturn("Erro de validação");

        ResponseEntity<?> response = handler.handleValidation(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ApiResponse<?> body = (ApiResponse<?>) response.getBody();
        assertNotNull(body);
        assertFalse(body.isSucesso());
        assertEquals("Erro de validação", body.getMensagem());

        Map<?, ?> errors = (Map<?, ?>) body.getDado();
        assertNotNull(errors);
        assertEquals("Obrigatório", errors.get("campo"));
    }

    @Test
    void testHandleAny() {
        Exception ex = new Exception("Erro qualquer");

        ResponseEntity<?> response = handler.handleAny(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        ApiResponse<?> body = (ApiResponse<?>) response.getBody();
        assertNotNull(body);
        assertFalse(body.isSucesso());
        assertEquals("Erro interno", body.getMensagem());
        assertNull(body.getDado());
    }

    @Test
    void testHandleRuntime() {
        RuntimeException ex = new RuntimeException("Erro runtime");

        ResponseEntity<?> response = handler.handleRuntime(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ApiResponse<?> body = (ApiResponse<?>) response.getBody();
        assertNotNull(body);
        assertFalse(body.isSucesso());
        assertEquals("Erro runtime", body.getMensagem());
        assertNull(body.getDado());
    }

    @Test
    void testHandleNoHandlerFound() {
        NoHandlerFoundException ex = mock(NoHandlerFoundException.class);

        ResponseEntity<?> response = handler.handleNoSuchElement(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ApiResponse<?> body = (ApiResponse<?>) response.getBody();
        assertNotNull(body);
        assertFalse(body.isSucesso());
        assertEquals("Recurso não encontrado", body.getMensagem());
        assertNull(body.getDado());
    }

}
