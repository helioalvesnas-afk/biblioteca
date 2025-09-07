package br.com.biblioteca.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class AutenticacaoResponseTest {

    @Test
    void testRecordStoresToken() {
        String token = "meu-token-jwt";
        AutenticacaoResponse response = new AutenticacaoResponse(token);

        // Verifica se o valor foi armazenado corretamente
        assertThat(response.token()).isEqualTo(token);
    }
}