package br.com.biblioteca.service;

import br.com.biblioteca.security.models.Role;
import br.com.biblioteca.security.models.Usuario;
import br.com.biblioteca.security.repository.IRoleRepository;
import br.com.biblioteca.security.repository.IUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AutenticacaoServiceTest {

    @Mock
    private IUsuarioRepository usuarioRepository;

    @Mock
    private IRoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AutenticacaoService autenticacaoService;

    @Test
    void deveRegistrarUsuarioComRoleExistente() {
        String username = "helio";
        String rawPassword = "123456";
        String encodedPassword = "encoded123";
        String roleName = "ADMIN";

        Role role = Role.builder().id(1L).nome(roleName).build();

        when(roleRepository.findByNome(roleName)).thenReturn(Optional.of(role));
        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        Usuario salvo = Usuario.builder()
                .id(1L)
                .username(username)
                .password(encodedPassword)
                .roles(Set.of(role))
                .build();

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(salvo);

        Usuario result = autenticacaoService.registrar(username, rawPassword, roleName);

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("helio");
        assertThat(result.getPassword()).isEqualTo(encodedPassword);
        assertThat(result.getRoles()).extracting(Role::getNome).containsExactly("ADMIN");

        verify(roleRepository).findByNome(roleName);
        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    void deveRegistrarUsuarioCriandoRoleSeNaoExistir() {
        String username = "joao";
        String rawPassword = "654321";
        String encodedPassword = "encoded654";
        String roleName = "USER";

        Role novoRole = Role.builder().id(2L).nome(roleName).build();

        when(roleRepository.findByNome(roleName)).thenReturn(Optional.empty());
        when(roleRepository.save(any(Role.class))).thenReturn(novoRole);
        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        Usuario salvo = Usuario.builder()
                .id(2L)
                .username(username)
                .password(encodedPassword)
                .roles(Set.of(novoRole))
                .build();

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(salvo);

        Usuario result = autenticacaoService.registrar(username, rawPassword, roleName);

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("joao");
        assertThat(result.getPassword()).isEqualTo(encodedPassword);
        assertThat(result.getRoles()).extracting(Role::getNome).containsExactly("USER");

        verify(roleRepository).findByNome(roleName);
        verify(roleRepository).save(any(Role.class));
        verify(usuarioRepository).save(any(Usuario.class));
    }
}