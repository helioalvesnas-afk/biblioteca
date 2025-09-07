package br.com.biblioteca;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(SpringExtension.class)
class ContextLoadTest {
    @Test
    void contextLoads() {
        //assertDoesNotThrow(() -> LibraryApplication.main(new String[]{}));
    }
}
