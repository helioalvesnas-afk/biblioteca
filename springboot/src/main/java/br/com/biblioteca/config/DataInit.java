package br.com.biblioteca.config;

import br.com.biblioteca.security.models.Role;
import br.com.biblioteca.security.models.Usuario;
import br.com.biblioteca.security.repository.IRoleRepository;
import br.com.biblioteca.security.repository.IUsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInit implements CommandLineRunner {

    private final IRoleRepository iRoleRepository;
    private final IUsuarioRepository iUsuarioRepository;
    private final PasswordEncoder encoder;

    public DataInit(IRoleRepository iRoleRepository, IUsuarioRepository iUsuarioRepository, PasswordEncoder passwordEncoder) {
        this.iRoleRepository = iRoleRepository;
        this.iUsuarioRepository = iUsuarioRepository;
        this.encoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        var rRead  = iRoleRepository.findByNome("ROLE_READ").orElseGet(() -> iRoleRepository.save(new Role(null, "ROLE_READ")));
        var rWrite = iRoleRepository.findByNome("ROLE_WRITE").orElseGet(() -> iRoleRepository.save(new Role(null, "ROLE_WRITE")));

        if (iUsuarioRepository.findByUsername("user").isEmpty()) {
            var u = new Usuario();
            u.setUsername("user");
            u.setPassword(encoder.encode("123456"));
            u.setRoles(Set.of(rRead));
            iUsuarioRepository.save(u);
        }

        if (iUsuarioRepository.findByUsername("admin").isEmpty()) {
            var u = new Usuario();
            u.setUsername("admin");
            u.setPassword(encoder.encode("123456"));
            u.setRoles(Set.of(rRead, rWrite));
            iUsuarioRepository.save(u);
        }
    }
}