package br.com.biblioteca.service;

import br.com.biblioteca.dto.GeneroDTO;
import br.com.biblioteca.entity.Genero;
import br.com.biblioteca.repository.IGeneroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GeneroService {
    private final IGeneroRepository iGeneroRepository;

    public GeneroService(IGeneroRepository iGeneroRepository) {
        this.iGeneroRepository = iGeneroRepository;
    }

    public List<Genero> listarTodos() {
        return iGeneroRepository.findAll();
    }

    public Genero buscarPorId(Long id) {
        return iGeneroRepository.findById(id).orElseThrow(() -> new RuntimeException("Genero nÃ£o encontrado"));
    }

    public Genero inserir(GeneroDTO dto) {
        Genero g = new Genero();
        g.setNome(dto.nome());
        return iGeneroRepository.save(g);
    }

    public Genero alterar(Long id, GeneroDTO dto) {
        Genero g = buscarPorId(id);
        g.setNome(dto.nome());
        return iGeneroRepository.save(g);
    }

    public void excluir(Long id) {
        iGeneroRepository.deleteById(id);
    }
}
