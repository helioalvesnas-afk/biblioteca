package br.com.biblioteca.repository;

import br.com.biblioteca.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILivroRepository extends JpaRepository<Livro, Long> {
    @Query("SELECT l FROM Livro l " +
            "JOIN FETCH l.autor a " +
            "JOIN FETCH l.genero g")
    List<Livro> findAllWithAutorAndGenero();

    @Query("SELECT l FROM Livro l " +
            "JOIN FETCH l.autor a " +
            "JOIN FETCH l.genero g " +
            "WHERE l.id = :livroId")
    Optional<Livro> findByIdWithAutorAndGenero(@Param("livroId") Long livroId);
}
