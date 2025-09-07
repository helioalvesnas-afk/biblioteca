package br.com.biblioteca.service;

import br.com.biblioteca.dto.AutorDTO;
import br.com.biblioteca.entity.Autor;
import br.com.biblioteca.repository.IAutorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AutorService {
    private final IAutorRepository iAutorRepository;

    public AutorService(IAutorRepository iAutorRepository) {
        this.iAutorRepository = iAutorRepository;
    }

    public List<Autor> listaTodos() {
        return iAutorRepository.findAll();
    }

    public Autor buscarPorId(Long id) {
        return iAutorRepository.findById(id).orElseThrow(() -> new RuntimeException("Genero não encontrado"));
    }

    public Autor inserir(AutorDTO dto) {
        Autor a = new Autor();
        a.setNome(dto.nome());
        return iAutorRepository.save(a);
    }

    public Autor alterar(Long id, AutorDTO dto) {
        Autor a = buscarPorId(id);
        a.setNome(dto.nome());
        return iAutorRepository.save(a);
    }

    public void excluir(Long id) {
        iAutorRepository.deleteById(id);
    }
}
