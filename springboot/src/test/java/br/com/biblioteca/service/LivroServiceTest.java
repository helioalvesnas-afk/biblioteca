package br.com.biblioteca.service;

import br.com.biblioteca.dto.LivroDTO;
import br.com.biblioteca.entity.Autor;
import br.com.biblioteca.entity.Genero;
import br.com.biblioteca.entity.Livro;
import br.com.biblioteca.repository.IAutorRepository;
import br.com.biblioteca.repository.IGeneroRepository;
import br.com.biblioteca.repository.ILivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class LivroServiceTest {

    @Mock
    private ILivroRepository livroRepository;

    @Mock
    private IAutorRepository autorRepository;

    @Mock
    private IGeneroRepository generoRepository;

    @InjectMocks
    private LivroService livroService;

    private Autor autor;
    private Genero genero;
    private Livro livro;
    private LivroDTO livroDTO;

    @BeforeEach
    void setup() {

        autor = new Autor();
        autor.setId(1L);
        autor.setNome("Machado de Assis");

        genero = new Genero();
        genero.setId(1L);
        genero.setNome("Romance");

        livro = new Livro();
        livro.setId(1L);
        livro.setTitulo("Dom Casmurro");
        livro.setAnoPublicacao(1899);
        livro.setAutor(autor);
        livro.setGenero(genero);

        livroDTO = new LivroDTO(0L,"Dom Casmurro", 1899, 1L, 1L);
    }

    @Test
    void deveListarTodos() {
        when(livroRepository.findAllWithAutorAndGenero()).thenReturn(Arrays.asList(livro));

        List<Livro> resultado = livroService.listarTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Dom Casmurro", resultado.get(0).getTitulo());
        verify(livroRepository, times(1)).findAllWithAutorAndGenero();
    }

    @Test
    void deveBuscarPorIdQuandoExistir() {
        when(livroRepository.findByIdWithAutorAndGenero(1L)).thenReturn(Optional.of(livro));

        Livro resultado = livroService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Dom Casmurro", resultado.getTitulo());
        verify(livroRepository, times(1)).findByIdWithAutorAndGenero(1L);
    }

    @Test
    void deveLancarExcecaoQuandoLivroNaoExistir() {
        when(livroRepository.findByIdWithAutorAndGenero(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> livroService.buscarPorId(1L));
        assertEquals("Livro não encontrado", ex.getMessage());
    }

    @Test
    void deveInserirLivro() {
        when(autorRepository.findById(1L)).thenReturn(Optional.of(autor));
        when(generoRepository.findById(1L)).thenReturn(Optional.of(genero));
        when(livroRepository.save(any(Livro.class))).thenReturn(livro);

        Livro resultado = livroService.inserir(livroDTO);

        assertNotNull(resultado);
        assertEquals("Dom Casmurro", resultado.getTitulo());
        verify(livroRepository, times(1)).save(any(Livro.class));
    }

    @Test
    void deveAlterarLivro() {
        when(livroRepository.findByIdWithAutorAndGenero(1L)).thenReturn(Optional.of(livro));
        when(autorRepository.findById(1L)).thenReturn(Optional.of(autor));
        when(generoRepository.findById(1L)).thenReturn(Optional.of(genero));
        when(livroRepository.save(any(Livro.class))).thenReturn(livro);

        LivroDTO dto = new LivroDTO(1L,"Memórias Póstumas", 1881, 1L, 1L);
        Livro resultado = livroService.alterar(1L, dto);

        assertNotNull(resultado);
        assertEquals("Memórias Póstumas", resultado.getTitulo());
        assertEquals(1881, resultado.getAnoPublicacao());
        verify(livroRepository, times(1)).save(any(Livro.class));
    }

    @Test
    void deveExcluirLivro() {
        doNothing().when(livroRepository).deleteById(1L);

        livroService.excluir(1L);

        verify(livroRepository, times(1)).deleteById(1L);
    }

}