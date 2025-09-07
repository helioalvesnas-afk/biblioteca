package br.com.biblioteca.service;

import br.com.biblioteca.dto.AutorDTO;
import br.com.biblioteca.entity.Autor;
import br.com.biblioteca.repository.IAutorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AutorServiceTest {

    @Mock
    private IAutorRepository autorRepository;

    @InjectMocks
    private AutorService autorService;

    @Test
    void deveListarTodosAutores() {
        Autor a1 = new Autor();
        a1.setId(1L);
        a1.setNome("Machado de Assis");

        Autor a2 = new Autor();
        a2.setId(2L);
        a2.setNome("José de Alencar");

        when(autorRepository.findAll()).thenReturn(Arrays.asList(a1, a2));

        List<Autor> result = autorService.listaTodos();

        assertThat(result).hasSize(2);
        assertThat(result).extracting(Autor::getNome)
                .containsExactly("Machado de Assis", "José de Alencar");
        verify(autorRepository).findAll();
    }

    @Test
    void deveBuscarAutorPorId() {
        Autor autor = new Autor();
        autor.setId(1L);
        autor.setNome("Clarice Lispector");

        when(autorRepository.findById(1L)).thenReturn(Optional.of(autor));

        Autor result = autorService.buscarPorId(1L);

        assertThat(result).isNotNull();
        assertThat(result.getNome()).isEqualTo("Clarice Lispector");
        verify(autorRepository).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoAutorNaoEncontrado() {
        when(autorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> autorService.buscarPorId(99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Genero não encontrado");  // Genero nÃ£o encontrado
    }

    @Test
    void deveInserirAutor() {
        AutorDTO dto = new AutorDTO(0L,"Jorge Amado");
        Autor salvo = new Autor();
        salvo.setId(1L);
        salvo.setNome("Jorge Amado");

        when(autorRepository.save(any())).thenReturn(salvo);

        Autor result = autorService.inserir(dto);

        assertThat(result).isNotNull();
        assertThat(result.getNome()).isEqualTo("Jorge Amado");
        verify(autorRepository).save(any());
    }

    @Test
    void deveAlterarAutor() {
        Autor existente = new Autor();
        existente.setId(1L);
        existente.setNome("Nome Antigo");

        AutorDTO dto = new AutorDTO(1L, "Nome Atualizado");

        when(autorRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(autorRepository.save(any(Autor.class))).thenReturn(existente);

        Autor result = autorService.alterar(1L, dto);

        assertThat(result.getNome()).isEqualTo("Nome Atualizado");
        verify(autorRepository).save(existente);
    }

    @Test
    void deveExcluirAutor() {
        autorService.excluir(1L);

        verify(autorRepository).deleteById(1L);
    }
}