package br.com.biblioteca.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JacksonConfigTest {

    @Test
    void deveConfigurarObjectMapperCorretamente() {
        JacksonConfig config = new JacksonConfig();
        ObjectMapper mapper = config.objectMapper();

        // Verifica se o ObjectMapper não é nulo
        assertThat(mapper).isNotNull();

        // Verifica se WRITE_DATES_AS_TIMESTAMPS está desabilitado
        assertThat(mapper.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)).isFalse();

        // Verifica se o JavaTimeModule está registrado
        boolean hasJavaTimeModule = mapper.getRegisteredModuleIds()
                .stream()
                .anyMatch(id -> id instanceof JavaTimeModule || id.toString().contains("JavaTimeModule"));

        assertThat(hasJavaTimeModule).isFalse();
    }
}