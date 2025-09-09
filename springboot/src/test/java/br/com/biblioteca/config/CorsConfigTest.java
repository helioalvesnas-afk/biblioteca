package br.com.biblioteca.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistration;

import static org.mockito.Mockito.*;

class CorsConfigTest {

    private CorsConfig corsConfig;

    @BeforeEach
    void setUp() {
        corsConfig = new CorsConfig();
    }

    @Test
    void deveConfigurarCorsCorretamente() {
        // Mock dos objetos
        CorsRegistry registry = mock(CorsRegistry.class);
        CorsRegistration registration = mock(CorsRegistration.class);

        // Quando chamarem addMapping, retorna o mock de CorsRegistration
        when(registry.addMapping("/**")).thenReturn(registration);
        when(registration.allowedOriginPatterns("*")).thenReturn(registration);
        when(registration.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")).thenReturn(registration);
        when(registration.allowedHeaders("*")).thenReturn(registration);
        when(registration.allowCredentials(true)).thenReturn(registration);

        // Executa método
        corsConfig.addCorsMappings(registry);

        // Verifica se as chamadas ocorreram
        verify(registry).addMapping("/**");
        verify(registration).allowedOriginPatterns("*");
        verify(registration).allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
        verify(registration).allowedHeaders("*");
        verify(registration).allowCredentials(true);

        verifyNoMoreInteractions(registry, registration);
    }
}