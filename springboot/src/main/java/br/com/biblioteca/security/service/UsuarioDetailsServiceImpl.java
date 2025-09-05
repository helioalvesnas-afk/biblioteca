package br.com.biblioteca.security.service;

import br.com.biblioteca.security.models.Usuario;
import br.com.biblioteca.security.repository.IUsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    private final IUsuarioRepository iUsuarioRepository;

    public UsuarioDetailsServiceImpl(IUsuarioRepository iUsuarioRepository) {
        this.iUsuarioRepository = iUsuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = iUsuarioRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("UsuÃ¡rio nÃ£o encontrado"));
        Set<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority(r.getNome()))
                .collect(Collectors.toSet());
        return new User(usuario.getUsername(), usuario.getPassword(), authorities);
    }
}
