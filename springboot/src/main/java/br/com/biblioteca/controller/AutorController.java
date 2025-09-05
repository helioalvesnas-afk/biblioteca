package br.com.biblioteca.controller;

import br.com.biblioteca.entity.Autor;
import br.com.biblioteca.repository.IAutorRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1/autores")
public class AutorController {

    private final IAutorRepository iAutorRepository;

    public AutorController(IAutorRepository iAutorRepository) {
        this.iAutorRepository = iAutorRepository;
    }

    @PreAuthorize("hasAnyRole('READ','WRITE')")
    @GetMapping
    public ResponseEntity<?> listar() {
        var result = iAutorRepository.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK );
    }

    @PreAuthorize("hasAnyRole('READ','WRITE')")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
        var result = iAutorRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        return new ResponseEntity<>(result, HttpStatus.OK );
    }

    @PreAuthorize("hasRole('WRITE')")
    @PostMapping
    public ResponseEntity<?> incluir(@Valid @RequestBody Autor autor) {
        var result = iAutorRepository.save(autor);
        return new ResponseEntity<>(result, HttpStatus.CREATED );
    }

    @PreAuthorize("hasRole('WRITE')")
    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable("id") Long id, @Valid @RequestBody Autor autor) {
        var result = iAutorRepository.findById(id).map(a -> {
            a.setNome(autor.getNome());
            return ResponseEntity.ok(iAutorRepository.save(a));
        }).orElse(ResponseEntity.notFound().build());
        return new ResponseEntity<>(result, HttpStatus.OK );
    }

    @PreAuthorize("hasRole('WRITE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") Long id) {
        var result = iAutorRepository.findById(id).map(a -> {
            iAutorRepository.delete(a);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT );
    }
}