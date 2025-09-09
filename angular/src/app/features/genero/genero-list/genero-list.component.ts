import { Component, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';
import * as generoActions from '../../../core/redux/actions/genero/genero.actions';
import { selectGeneros, selectLoading } from '../../../core/redux/selectors/genero/genero.selectors';
import { Genero } from '../../../shared/models/genero';

@Component({
  selector: 'app-genero-list',
  templateUrl: './genero-list.component.html',
  styleUrls: ['./genero-list.component.css']
})

export class GeneroListComponent implements OnInit {

  generos: Genero[] = [];
  loading: boolean = false;

  constructor(private store: Store) {}

  ngOnInit() {
    this.store.dispatch(generoActions.carregarGeneros());
    this.store.pipe(select(selectGeneros)).subscribe(data => this.generos = data);
    this.store.pipe(select(selectLoading)).subscribe(data => this.loading = data);
  }

  onDeleted(genero: Genero) {
    if (genero !== undefined) {
      const index = this.generos.findIndex((generoItem) => generoItem.id == genero.id);
      this.generos.splice(index, 1);
    }
  }
}
