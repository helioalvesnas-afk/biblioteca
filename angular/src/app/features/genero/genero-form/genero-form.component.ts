import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { select, Store } from '@ngrx/store';
import { filter } from 'rxjs';
import * as generoActions from '../../../core/state/actions/genero.actions';
import { Genero } from '../../../shared/models/genero';
import selectorGenero from '../../../core/state/selectors/genero.selectors';
import { ApiResponse } from '../../../shared/dto/api-response';
import { GenericMapper } from '../../../shared/Mapper/generic-mapper';


@Component({
    selector: 'app-genero-form',
    templateUrl: './genero-form.component.html',
    styleUrls: ['./genero-form.component.css'],
    imports: [FormsModule, ReactiveFormsModule, RouterLink],
    changeDetection: ChangeDetectionStrategy.OnPush
})
export class GeneroFormComponent implements OnInit {
  generoForm!: FormGroup;
  genero: Genero = new Genero();
  loading = false;
  error: any
  title: string = 'Novo';


  constructor(
    private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private store: Store
  ) {
    this.generoForm = this.fb.group({
      id: [''],
      nome: ['', Validators.required]
    });
  }

  ngOnInit() {

    const { selectGeneroSelecionado, selectLoading, selectError } = selectorGenero();

    const id = this.activatedRoute.snapshot.paramMap.get('id');
    if (id) {
      this.store.dispatch(generoActions.buscarGeneroPorId({id: id}));
      this.store.pipe(
        select(selectGeneroSelecionado),
        filter((apiResponse): apiResponse is ApiResponse<Genero> => !!apiResponse)
      ).subscribe(apiResponse => {
        this.genero = GenericMapper.mapApiResponseToData(apiResponse);
        this.generoForm.patchValue(this.genero);
      });
      this.title = 'Alterando genero';
    }

    this.store.pipe(select(selectLoading))
    .subscribe(loading => this.loading = loading);

    this.store.pipe(select(selectError))
    .subscribe(error => this.error = error);

  }

  adicionarGenero() {
    if (this.generoForm.valid) {
      const genero: Genero = this.generoForm.value;
      this.store.dispatch(generoActions.adicionarGenero({ genero }));
      this.generoForm.reset();
    }
  }

  atualizarGenero() {
    if (this.generoForm.valid) {
      const genero: Genero = this.generoForm.value;
      this.store.dispatch(generoActions.atualizarGenero({ genero }));
      this.generoForm.reset();
    }
  }

  onSubmit() {
    if (this.genero !== undefined && this.genero.id !== undefined) {
      this.atualizarGenero();
    } else {
      this.adicionarGenero();
    }
    this.router.navigate(['/generos/list']);
  }
}
