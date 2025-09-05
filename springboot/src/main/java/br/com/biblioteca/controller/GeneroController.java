package br.com.biblioteca.controller;

import br.com.biblioteca.entity.Genero;
import br.com.biblioteca.repository.IGeneroRepository;
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

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/generos")
public class GeneroController {

    private final IGeneroRepository iGeneroRepository;

    public GeneroController(IGeneroRepository iGeneroRepository) {
        this.iGeneroRepository = iGeneroRepository;
    }

    @PreAuthorize("hasAnyRole('READ','WRITE')")
    @GetMapping
    public ResponseEntity<List<?>> listar() {
        return new ResponseEntity<>(iGeneroRepository.findAll(), HttpStatus.OK );
    }

    @PreAuthorize("hasAnyRole('READ','WRITE')")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
        var result = iGeneroRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());;
        return new ResponseEntity<>(result, HttpStatus.OK );
    }

    @PreAuthorize("hasRole('WRITE')")
    @PostMapping
    public ResponseEntity<?> incluir(@Valid @RequestBody Genero genero) {
        var result = iGeneroRepository.save(genero);
        return new ResponseEntity<>(result, HttpStatus.CREATED );
    }

    @PreAuthorize("hasRole('WRITE')")
    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable("id") Long id, @Valid @RequestBody Genero genero) {
        var result = iGeneroRepository.findById(id).map(g -> {
            g.setNome(genero.getNome());
            return ResponseEntity.ok(iGeneroRepository.save(g));
        }).orElse(ResponseEntity.notFound().build());
        return new ResponseEntity<>(result, HttpStatus.OK );
    }

    @PreAuthorize("hasRole('WRITE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") Long id) {
        var result = iGeneroRepository.findById(id).map(g -> {
            iGeneroRepository.delete(g);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT );

    }
}