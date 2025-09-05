package br.com.biblioteca.repository;

import br.com.biblioteca.entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IGeneroRepository extends JpaRepository<Genero, Long> {
    Optional<Genero> findByNomeIgnoreCase(String name);
}
