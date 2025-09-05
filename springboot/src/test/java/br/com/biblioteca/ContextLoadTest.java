package br.com.biblioteca;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ContextLoadTest {
    @Test
    void contextLoads() {
        LibraryApplication.main(new String[]{});
    }
}
