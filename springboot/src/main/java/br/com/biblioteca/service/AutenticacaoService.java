package br.com.biblioteca.service;

import br.com.biblioteca.security.models.Role;
import br.com.biblioteca.security.models.Usuario;
import br.com.biblioteca.security.repository.IRoleRepository;
import br.com.biblioteca.security.repository.IUsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AutenticacaoService {

    private final IUsuarioRepository iUsuarioRepository;
    private final IRoleRepository iRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public AutenticacaoService(IUsuarioRepository iUsuarioRepository, IRoleRepository iRoleRepository, PasswordEncoder passwordEncoder) {
        this.iUsuarioRepository = iUsuarioRepository;
        this.iRoleRepository = iRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrar(String username, String rawPassword, String roleName) {
        Role role = iRoleRepository.findByNome(roleName).orElseGet(() -> iRoleRepository.save(Role.builder().nome(roleName).build()));
        Usuario u = Usuario.builder().username(username).password(passwordEncoder.encode(rawPassword)).roles(Set.of(role)).build();
        return iUsuarioRepository.save(u);
    }
}
