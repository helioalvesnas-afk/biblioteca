import { Component, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';
import * as generoActions from '../../../core/state/actions/genero.actions';
import { selectGeneros, selectLoading } from '../../../core/state/selectors/genero.selectors';
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
    setTimeout(() => {
      this.store.pipe(select(selectGeneros)).subscribe(generos => this.generos = generos).unsubscribe();
      this.store.pipe(select(selectLoading)).subscribe(loading => this.loading = loading).unsubscribe();
    }, 500);

  }

  onDeleted(genero: Genero) {
    if (genero !== undefined) {
      const index = this.generos.findIndex((generoItem) => generoItem.id == genero.id);
      this.generos.splice(index, 1);
    }
  }
}
