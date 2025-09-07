package br.com.biblioteca.config;

import br.com.biblioteca.security.models.Role;
import br.com.biblioteca.security.models.Usuario;
import br.com.biblioteca.security.repository.IRoleRepository;
import br.com.biblioteca.security.repository.IUsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DataInitTest {

    private IRoleRepository roleRepository;
    private IUsuarioRepository usuarioRepository;
    private PasswordEncoder encoder;
    private DataInit dataInit;

    @BeforeEach
    void setUp() {
        roleRepository = mock(IRoleRepository.class);
        usuarioRepository = mock(IUsuarioRepository.class);
        encoder = mock(PasswordEncoder.class);

        dataInit = new DataInit(roleRepository, usuarioRepository, encoder);
    }

    @Test
    void deveCriarUsuariosEPapeisQuandoNaoExistirem() {
        // mocks para roles
        when(roleRepository.findByNome("ROLE_READ")).thenReturn(Optional.empty());
        when(roleRepository.findByNome("ROLE_WRITE")).thenReturn(Optional.empty());
        when(roleRepository.save(any(Role.class))).thenAnswer(inv -> {
            Role r = inv.getArgument(0);
            r.setId(1L); // simula ID gerado
            return r;
        });

        // mocks para usuários
        when(usuarioRepository.findByUsername("user")).thenReturn(Optional.empty());
        when(usuarioRepository.findByUsername("admin")).thenReturn(Optional.empty());

        // mock password encoder
        when(encoder.encode(anyString())).thenReturn("encodedPassword");

        // executa o método
        dataInit.run();

        // captura os usuários salvos
        ArgumentCaptor<Usuario> usuarioCaptor = ArgumentCaptor.forClass(Usuario.class);
        verify(usuarioRepository, times(2)).save(usuarioCaptor.capture());

        var usuariosSalvos = usuarioCaptor.getAllValues();

        // verificações
        assertEquals(2, usuariosSalvos.size());

        Usuario user = usuariosSalvos.stream().filter(u -> u.getUsername().equals("user")).findFirst().orElseThrow();
        Usuario admin = usuariosSalvos.stream().filter(u -> u.getUsername().equals("admin")).findFirst().orElseThrow();

        assertEquals("encodedPassword", user.getPassword());
        assertEquals(1, user.getRoles().size());

        assertEquals("encodedPassword", admin.getPassword());
        assertEquals(2, admin.getRoles().size());
    }

    @Test
    void naoDeveCriarUsuariosQuandoJaExistirem() {
        // já existem usuários
        when(usuarioRepository.findByUsername("user")).thenReturn(Optional.of(new Usuario()));
        when(usuarioRepository.findByUsername("admin")).thenReturn(Optional.of(new Usuario()));

        // executa
        dataInit.run();

        // nunca deve salvar usuários novos
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }
}