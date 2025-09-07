package br.com.biblioteca.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class OpenApiConfigTest {

    @Test
    void testOpenAPIBean() {
        // Instancia a configuração
        OpenApiConfig config = new OpenApiConfig();

        // Chama o método openAPI()
        OpenAPI openAPI = config.openAPI();

        // Verifica se não é nulo
        assertThat(openAPI).isNotNull();

        // Verifica título, versão e descrição
        Info info = openAPI.getInfo();
        assertThat(info).isNotNull();
        assertThat(info.getTitle()).isEqualTo("Biblioteca API");
        assertThat(info.getVersion()).isEqualTo("v1");
        assertThat(info.getDescription()).isEqualTo("API para gerenciar autores, generos e livros");

        // Verifica se a segurança está configurada
        assertThat(openAPI.getComponents()).isNotNull();
        SecurityScheme scheme = openAPI.getComponents().getSecuritySchemes().get("bearerAuth");
        assertThat(scheme).isNotNull();
        assertThat(scheme.getType()).isEqualTo(SecurityScheme.Type.HTTP);
        assertThat(scheme.getScheme()).isEqualTo("bearer");
        assertThat(scheme.getBearerFormat()).isEqualTo("JWT");

        // Verifica se o security item foi adicionado
        assertThat(openAPI.getSecurity()).isNotEmpty();
        assertThat(openAPI.getSecurity().get(0).containsKey("bearerAuth")).isTrue();
    }
}