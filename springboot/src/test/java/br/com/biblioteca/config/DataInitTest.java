package br.com.biblioteca.config;

import br.com.biblioteca.security.models.Role;
import br.com.biblioteca.security.models.Usuario;
import br.com.biblioteca.security.repository.IRoleRepository;
import br.com.biblioteca.security.repository.IUsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public  class DataInitTest {

    @Mock
    private IRoleRepository roleRepository;

    @Mock
    private IUsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private DataInit dataInit;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void naoDeveCriarUsuariosOuRolesQuandoJaExistirem() throws Exception {
        when(roleRepository.findByNome(anyString())).thenReturn(Optional.of(new Role()));
        when(usuarioRepository.findByUsername(anyString())).thenReturn(Optional.of(new Usuario()));

        dataInit.run();

        // Verifica que não salvou nada
        verify(roleRepository, never()).save(any(Role.class));
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }
}