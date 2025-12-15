import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';
import * as generoActions from '../../../core/state/actions/genero.actions';
import selectorGenero from '../../../core/state/selectors/genero.selectors';
import { Genero } from '../../../shared/models/genero';
import { Observable } from 'rxjs';
import { RouterLink } from '@angular/router';
import { AsyncPipe } from '@angular/common';
import { GeneroListItemComponent } from '../genero-list-item/genero-list-item.component';

@Component({
    selector: 'app-genero-list',
    templateUrl: './genero-list.component.html',
    styleUrls: ['./genero-list.component.css'],
    imports: [RouterLink, GeneroListItemComponent, AsyncPipe],
    changeDetection: ChangeDetectionStrategy.OnPush
})

export class GeneroListComponent implements OnInit {

  generos$: Observable<Genero[]>;
  loading$: Observable<boolean>;

  constructor(private store: Store) {}

  ngOnInit(): void {
    this.store.dispatch(generoActions.carregarGeneros());

    const { selectGeneros, selectLoading } = selectorGenero();

    this.generos$ = this.store.pipe(select(selectGeneros));
    this.loading$ = this.store.pipe(select(selectLoading));
  }

  onDeleted(genero: Genero) {
    if (genero) {
      this.store.dispatch(generoActions.removerGenero({ id: genero.id }));
    }
  }
}
