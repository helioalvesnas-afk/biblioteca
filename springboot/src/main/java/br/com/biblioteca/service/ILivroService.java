package br.com.biblioteca.service;

import br.com.biblioteca.dto.LivroDTO;
import br.com.biblioteca.entity.Livro;

import java.util.List;

public interface ILivroService {

    List<Livro> listarTodos();

    Livro buscarPorId(Long id);

    Livro inserir(LivroDTO dto);

    Livro alterar(Long id, LivroDTO dto);

    void excluir(Long id);
}
