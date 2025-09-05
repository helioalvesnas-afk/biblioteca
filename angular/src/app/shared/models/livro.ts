import { Autor } from "./autor";
import { Genero } from "./genero";

export class Livro {
    id: string;
    titulo: string;
    anoPublicacao: string;
    autor: Autor;
    genero: Genero;
  }
