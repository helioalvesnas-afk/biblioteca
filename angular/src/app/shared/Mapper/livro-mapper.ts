import { AlterarLivroDto } from "../dto/alterar-livro-dto";
import { IncluirLivroDto } from "../dto/incluir-livro-dto";
import { Livro } from "../models/livro";

export class LivroMapper {

  static toIncluirLivroDto(livro: Livro): IncluirLivroDto {
    const dto = new IncluirLivroDto();
    dto.titulo = livro.titulo;
    dto.anoPublicacao = livro.anoPublicacao;
    dto.autorId = livro.autor.id;
    dto.generoId = livro.genero.id;
    return dto;
  }

  static toAlterarLivroDto(livro: Livro): AlterarLivroDto {
    const dto = new AlterarLivroDto();
    dto.id = livro.id;
    dto.titulo = livro.titulo;
    dto.anoPublicacao = livro.anoPublicacao;
    dto.autorId = livro.autor.id;
    dto.generoId = livro.genero.id;
    return dto;
  }

}
