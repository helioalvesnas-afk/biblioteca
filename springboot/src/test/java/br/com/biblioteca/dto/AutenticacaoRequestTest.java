package br.com.biblioteca.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class AutenticacaoRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidRequest() {
        AutenticacaoRequest request = new AutenticacaoRequest("user", "password");
        Set<ConstraintViolation<AutenticacaoRequest>> violations = validator.validate(request);
        assertThat(violations).isEmpty();
    }

    @Test
    void testUsernameBlank() {
        AutenticacaoRequest request = new AutenticacaoRequest("", "password");
        Set<ConstraintViolation<AutenticacaoRequest>> violations = validator.validate(request);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("username");
    }

    @Test
    void testPasswordBlank() {
        AutenticacaoRequest request = new AutenticacaoRequest("user", "");
        Set<ConstraintViolation<AutenticacaoRequest>> violations = validator.validate(request);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("password");
    }

    @Test
    void testBothBlank() {
        AutenticacaoRequest request = new AutenticacaoRequest("", "");
        Set<ConstraintViolation<AutenticacaoRequest>> violations = validator.validate(request);
        assertThat(violations).hasSize(2);
    }
}