package br.com.biblioteca.controller;

import br.com.biblioteca.dto.AutenticacaoRequest;
import br.com.biblioteca.dto.CadastrarRequest;
import br.com.biblioteca.security.repository.IUsuarioRepository;
import br.com.biblioteca.security.utils.JwtUtil;
import br.com.biblioteca.service.AutenticacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/api/v1/autenticacao")
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final IUsuarioRepository iUsuarioRepository;
    private final AutenticacaoService autenticacaoService;

    public AutenticacaoController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, IUsuarioRepository iUsuarioRepository, AutenticacaoService autenticacaoService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.iUsuarioRepository = iUsuarioRepository;
        this.autenticacaoService = autenticacaoService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AutenticacaoRequest request) {
        var authToken = new UsernamePasswordAuthenticationToken(request.username(), request.password());
        var auth = authenticationManager.authenticate(authToken);
        String token = jwtUtil.generateToken(auth.getName(), auth.getAuthorities().stream().map(a -> a.getAuthority()).toList());
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("token", token);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@Valid @RequestBody CadastrarRequest request) {
        if (iUsuarioRepository.findByUsername(request.username()).isPresent()) {
            return ResponseEntity.badRequest().body("Usuário já existe");
        }
        var usuario = autenticacaoService.registrar(request.username(), request.password(), "ROLE_READ");
        return new ResponseEntity<>(usuario, HttpStatus.CREATED );
    }
}
