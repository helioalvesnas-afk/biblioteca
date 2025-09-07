package br.com.biblioteca.service;

import br.com.biblioteca.entity.Genero;
import br.com.biblioteca.repository.IGeneroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class GenreServiceTest {

    @Mock
    IGeneroRepository iGeneroRepository;

    @InjectMocks
    GeneroService generoService;

    @Test
    void findAllReturnsList() {
        when(iGeneroRepository.findAll()).thenReturn(List.of(new Genero(1L, "Ficção", null)));
        var result = generoService.listarTodos();
        assertEquals(1, result.size());
    }
}
