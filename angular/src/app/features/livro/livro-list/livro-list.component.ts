import { Component, OnInit } from '@angular/core';
import { LivroService } from '../../../core/services/livro.service';
import { Livro } from '../../../shared/models/livro';

@Component({
  selector: 'app-livro-list',
  templateUrl: './livro-list.component.html',
  styleUrls: ['./livro-list.component.css']
})
export class LivroListComponent implements OnInit {
  livros: Livro[] = [];

  constructor(private livroService: LivroService) { }

  ngOnInit() {
    this.livroService.getAll().subscribe(livro => {
      this.livros = livro;
      console.log(this.livros);
    });
  }

  onDeleted(livro: Livro) {
    if (livro !== undefined) {
      const index = this.livros.findIndex((livroItem) => livroItem.id == livro.id);
      this.livros.splice(index, 1);
    }
  }
}
