import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { Genero } from '../../../shared/models/genero';
import * as GeneroActions from '../../../core/redux/actions/genero/genero.actions';
import { Store } from '@ngrx/store';

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

  constructor(private store: Store) { }

  ngOnInit() {
  }

  remove(genero: Genero) {
    if (genero !== undefined) {
      this.onDelete.emit(genero);
      this.store.dispatch(GeneroActions.removerGenero({ id: genero.id }) );
    }
  }

}
