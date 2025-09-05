import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { Genero } from '../../../shared/models/genero';
import { GeneroService } from '../../../core/services/genero.service';

@Component({
  selector: 'app-genero-list-item',
  templateUrl: './genero-list-item.component.html',
  styleUrls: ['./genero-list-item.component.css'],
})
export class GeneroListItemComponent implements OnInit {

  @Input()
  genero: Genero;

  @Output()
  onDelete = new EventEmitter()

  constructor(private generoService: GeneroService) { }

  ngOnInit() {
  }

  remove(genero: Genero) {
    if (genero !== undefined) {
      this.generoService.delete(genero.id).subscribe(() => {
        this.onDelete.emit(genero);
      });
    }
  }

}
