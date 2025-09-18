package br.com.biblioteca.controller;

import br.com.biblioteca.dto.LivroDTO;
import br.com.biblioteca.service.ILivroService;
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
import org.springframework.web.util.UriComponentsBuilder;

@Validated
@RestController
@RequestMapping("/api/v1/livros")
public class LivroController {

    private final ILivroService ilivroService;

    public LivroController(ILivroService ilivroService) {
        this.ilivroService = ilivroService;
    }

    @PreAuthorize("hasAnyRole('READ','WRITE')")
    @GetMapping
    public ResponseEntity<?> listar() {
        var result = ilivroService.listarTodos();
        return new ResponseEntity<>(result, HttpStatus.OK );
    }

    @PreAuthorize("hasAnyRole('READ','WRITE')")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
        var result = ilivroService.buscarPorId(id);
        return new ResponseEntity<>(result, HttpStatus.OK );
    }

    @PreAuthorize("hasRole('WRITE')")
    @PostMapping
    public ResponseEntity<?> incluir(@Valid @RequestBody LivroDTO dto, UriComponentsBuilder uriBuilder) {
        var result = ilivroService.inserir(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED );
    }

    @PreAuthorize("hasRole('WRITE')")
    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable("id") Long id, @Valid @RequestBody LivroDTO dto) {
        var result = ilivroService.alterar(id, dto);
        return new ResponseEntity<>(result, HttpStatus.OK );
    }

    @PreAuthorize("hasRole('WRITE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") Long id) {
        ilivroService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
