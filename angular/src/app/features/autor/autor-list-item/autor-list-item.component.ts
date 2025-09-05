import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { Autor } from '../../../shared/models/autor';
import { AutorService } from '../../../core/services/autor.service';

@Component({
  selector: 'app-autor-list-item',
  templateUrl: './autor-list-item.component.html',
  styleUrls: ['./autor-list-item.component.css'],
})
export class AutorListItemComponent implements OnInit {

  @Input()
  autor: Autor;

  @Output()
  onDelete = new EventEmitter()

  constructor(private autorService: AutorService) { }

  ngOnInit() {
  }

  remove(autor: Autor) {
    if (autor !== undefined) {
      this.autorService.delete(autor.id).subscribe(() => {
        this.onDelete.emit(autor);
      });
    }
  }

}
