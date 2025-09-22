import { Component, OnInit } from '@angular/core';
import { LivroService } from '../../../core/services/livro.service';
import { Livro } from '../../../shared/models/livro';
import { RouterLink } from '@angular/router';
import { NgIf, NgFor } from '@angular/common';
import { LivroListItemComponent } from '../livro-list-item/livro-list-item.component';

@Component({
    selector: 'app-livro-list',
    templateUrl: './livro-list.component.html',
    styleUrls: ['./livro-list.component.css'],
    standalone: true,
    imports: [RouterLink, NgIf, NgFor, LivroListItemComponent]
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
