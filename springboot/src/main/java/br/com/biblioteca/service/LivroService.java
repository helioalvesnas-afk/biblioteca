package br.com.biblioteca.service;

import br.com.biblioteca.dto.LivroDTO;
import br.com.biblioteca.entity.Autor;
import br.com.biblioteca.entity.Genero;
import br.com.biblioteca.entity.Livro;
import br.com.biblioteca.repository.IAutorRepository;
import br.com.biblioteca.repository.IGeneroRepository;
import br.com.biblioteca.repository.ILivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LivroService implements ILivroService {
    private final ILivroRepository iLivroRepository;
    private final IAutorRepository iAutorRepository;
    private final IGeneroRepository iGeneroRepository;

    public LivroService(ILivroRepository livroRepository, IAutorRepository autorRepository, IGeneroRepository generoRepository) {
        this.iLivroRepository = livroRepository;
        this.iAutorRepository = autorRepository;
        this.iGeneroRepository = generoRepository;
    }

    @Override
    public List<Livro> listarTodos() {
        return iLivroRepository.findAllWithAutorAndGenero();
    }

    @Override
    public Livro buscarPorId(Long id) {
        return iLivroRepository.findByIdWithAutorAndGenero(id).orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    @Override
    public Livro inserir(LivroDTO dto) {
        Autor autor = iAutorRepository.findById(dto.autorId()).orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        Genero genero = iGeneroRepository.findById(dto.generoId()).orElseThrow(() -> new RuntimeException("Genero não encontrado"));
        Livro b = new Livro();
        b.setTitulo(dto.titulo());
        b.setAnoPublicacao(dto.anoPublicacao());
        b.setAutor(autor);
        b.setGenero(genero);
        return iLivroRepository.save(b);
    }

    @Override
    public Livro alterar(Long id, LivroDTO dto) {
        Livro b = buscarPorId(id);
        if (dto.titulo() != null) b.setTitulo(dto.titulo());
        b.setAnoPublicacao(dto.anoPublicacao());
        if (dto.autorId() != null) {
            b.setAutor(iAutorRepository.findById(dto.autorId()).orElseThrow(() -> new RuntimeException("Autor não encontrado")));
        }
        if (dto.generoId() != null) {
            b.setGenero(iGeneroRepository.findById(dto.generoId()).orElseThrow(() -> new RuntimeException("Genero não encontrado")));
        }
        return iLivroRepository.save(b);
    }

    @Override
    public void excluir(Long id) {
        iLivroRepository.deleteById(id);
    }
}
