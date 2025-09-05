package br.com.biblioteca.security.repository;

import br.com.biblioteca.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNome(String nome);
}
