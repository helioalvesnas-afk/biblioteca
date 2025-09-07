package br.com.biblioteca.security.service;

import br.com.biblioteca.security.models.Role;
import br.com.biblioteca.security.models.Usuario;
import br.com.biblioteca.security.repository.IUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UsuarioDetailsServiceImplTest {

    @Mock
    private IUsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioDetailsServiceImpl usuarioDetailsService;

    @Test
    void deveCarregarUsuarioPorUsernameComSucesso() {
        Role role = Role.builder().nome("ROLE_USER").build();
        Usuario usuario = Usuario.builder()
                .username("joao")
                .password("senha123")
                .roles(Set.of(role))
                .build();

        when(usuarioRepository.findByUsername("joao")).thenReturn(Optional.of(usuario));

        UserDetails userDetails = usuarioDetailsService.loadUserByUsername("joao");

        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getUsername()).isEqualTo("joao");
        assertThat(userDetails.getPassword()).isEqualTo("senha123");
        assertThat(userDetails.getAuthorities())
                .extracting(GrantedAuthority::getAuthority)
                .containsExactly("ROLE_USER");
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        when(usuarioRepository.findByUsername("inexistente")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> usuarioDetailsService.loadUserByUsername("inexistente"))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessage("Usuário não encontrado");
    }
}