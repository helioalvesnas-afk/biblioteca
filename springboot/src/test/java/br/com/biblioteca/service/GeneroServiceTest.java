package br.com.biblioteca.service;

import br.com.biblioteca.dto.GeneroDTO;
import br.com.biblioteca.entity.Genero;
import br.com.biblioteca.repository.IGeneroRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GeneroServiceTest {

    @Mock
    private IGeneroRepository generoRepository;

    @InjectMocks
    private GeneroService generoService;

    @Test
    void deveListarTodosGeneros() {
        Genero g1 = new Genero();
        g1.setId(1L);
        g1.setNome("Ficção");

        Genero g2 = new Genero();
        g2.setId(2L);
        g2.setNome("Romance");

        when(generoRepository.findAll()).thenReturn(Arrays.asList(g1, g2));

        List<Genero> result = generoService.listarTodos();
        assertThat(result.size()).isEqualTo(2);

        verify(generoRepository, times(1)).findAll();
    }

    @Test
    void deveBuscarGeneroPorId() {
        Genero g = new Genero();
        g.setId(1L);
        g.setNome("Aventura");

        when(generoRepository.findById(1L)).thenReturn(Optional.of(g));

        Genero result = generoService.buscarPorId(1L);

        assertThat(result).isNotNull();
        assertThat(result.getNome()).isEqualTo("Aventura");
        verify(generoRepository).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoGeneroNaoEncontrado() {
        when(generoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> generoService.buscarPorId(99L))
                //.isInstanceOf(RuntimeException.class)
                .hasMessage("Genero nÃ£o encontrado");
    }

    @Test
    void deveInserirGenero() {
        GeneroDTO dto = new GeneroDTO(0L,"Fantasia");
        Genero g = new Genero();
        g.setId(1L);
        g.setNome("Fantasia");

        when(generoRepository.save(any(Genero.class))).thenReturn(g);

        Genero result = generoService.inserir(dto);

        assertThat(result).isNotNull();
        assertThat(result.getNome()).isEqualTo("Fantasia");
        verify(generoRepository).save(any(Genero.class));
    }

    @Test
    void deveAlterarGenero() {
        GeneroDTO dto = new GeneroDTO(1L,"Terror");
        Genero existente = new Genero();
        existente.setId(1L);
        existente.setNome("Suspense");

        when(generoRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(generoRepository.save(any(Genero.class))).thenReturn(existente);

        Genero result = generoService.alterar(1L, dto);

        assertThat(result.getNome()).isEqualTo("Terror");
        verify(generoRepository).save(existente);
    }

    @Test
    void deveExcluirGenero() {
        generoService.excluir(1L);

        verify(generoRepository, times(1)).deleteById(1L);
    }
}