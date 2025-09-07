package br.com.biblioteca.security.utils;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = {
        "security.jwt.secret=dGhpc19pc19hX3Zlcnlfc2VjdXJlX3NlY3JldF9rZXk=",
        "security.jwt.expiration=3600000"
})
public class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    @TestConfiguration
    static class Config {
        @Bean
        public JwtUtil jwtUtil() {
            return new JwtUtil();
        }
    }

    String token;

    String secret = "dGhpc19pc19hX3Zlcnlfc2VjdXJlX3NlY3JldF9rZXk="; // Base64 de "testsecretkeytestsecretkeytest"
    long expiration = 1000 * 60 * 60; // 1 hora em ms

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(jwtUtil, "secret", secret);
        ReflectionTestUtils.setField(jwtUtil, "expiration", expiration);
    }

    @Test
    void deveGerarTokenComSucesso() {
        String username = "joao";
        List<String> roles = Arrays.asList("USER", "ADMIN");

        token = jwtUtil.generateToken(username, roles);

        assertThat(token).isNotNull();
        assertThat(token).isNotEmpty();
    }

    @Test
    void deveExtrairUsernameDoToken() {
        String username = "maria";
        List<String> roles = List.of("USER");

        token = jwtUtil.generateToken(username, roles);

        String extractedUsername = jwtUtil.extractUsername(token);

        assertThat(extractedUsername).isEqualTo(username);
    }

    @Test
    void deveExtrairClaimsDoToken() {
        String username = "ana";
        List<String> roles = List.of("USER", "MANAGER");

        token = jwtUtil.generateToken(username, roles);

        Claims claims = jwtUtil.getAllClaimsFromToken(token);

        assertThat(claims.getSubject()).isEqualTo(username);
        assertThat(claims.get("roles", List.class)).containsExactlyInAnyOrderElementsOf(roles);
        assertThat(claims.getIssuedAt()).isNotNull();
        assertThat(claims.getExpiration()).isAfter(new Date());
    }

    @Test
    void deveValidarTokenCorreto() {
        String username = "carlos";
        List<String> roles = List.of("USER");

        token = jwtUtil.generateToken(username, roles);

        boolean isValid = jwtUtil.isTokenValid(token, username);

        assertThat(isValid).isTrue();
    }

    @Test
    void deveInvalidarTokenComUsernameIncorreto() {
        String username = "paulo";
        List<String> roles = List.of("USER");

        token = jwtUtil.generateToken(username, roles);

        boolean isValid = jwtUtil.isTokenValid(token, "outroUsuario");

        assertThat(isValid).isFalse();
    }
}