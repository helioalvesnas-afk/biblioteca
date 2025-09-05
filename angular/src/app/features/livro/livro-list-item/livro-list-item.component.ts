import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { Livro } from '../../../shared/models/livro';
import { LivroService } from 'src/app/core/services/livro.service';

@Component({
  selector: 'app-livro-list-item',
  templateUrl: './livro-list-item.component.html',
  styleUrls: ['./livro-list-item.component.css'],
})
export class LivroListItemComponent implements OnInit {

  @Input()
  livro: Livro;

  @Output()
  onDelete = new EventEmitter()

  constructor(private livroService: LivroService) { }

  ngOnInit() {
  }

  remove(livro: Livro) {
    if (livro !== undefined) {
      const id = livro.id.toString();
      this.livroService.delete(id).subscribe(() => {
        this.onDelete.emit(livro);
      });
    }
  }

}
