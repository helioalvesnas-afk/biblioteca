import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { AutorService } from '../../../core/services/autor.service';
import { GeneroService } from '../../../core/services/genero.service';
import { LivroService } from '../../../core/services/livro.service';
import { Autor } from '../../../shared/models/autor';
import { Genero } from '../../../shared/models/genero';
import { Livro } from '../../../shared/models/livro';
import { FormsModule } from '@angular/forms';
import { NgFor } from '@angular/common';

@Component({
    selector: 'app-livro-form',
    templateUrl: './livro-form.component.html',
    styleUrls: ['./livro-form.component.css'],
    standalone: true,
    imports: [FormsModule, NgFor, RouterLink]
})
export class LivroFormComponent implements OnInit {
  livro: Livro = new Livro();
  generos: Genero[] = [];
  autores: Autor[] = [];
  title: string = 'Novo';

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private livroService: LivroService,
    private generoService: GeneroService,
    private autorService: AutorService,
  ) {
    this.livro.genero = new Genero();
    this.livro.autor = new Autor();
  }

  ngOnInit() {
    const param = this.activatedRoute.snapshot.paramMap.get('id');
    if (param !== undefined && param !== null) {
      const id = Number(param);
      this.livroService.getById(id)
        .then(l => {
          this.livro = l;
          this.livro.genero = l.genero;
          this.livro.autor = l.autor;
          this.title = 'Edição';
        })
        .catch(err => console.error('Erro ao buscar livro', err));
    }

    this.generoService.carregarGeneros().subscribe(genero => {
      this.generos = genero;
    });

    this.autorService.getAll().subscribe(autor => {
      this.autores = autor;
    });

  }

  onSubmit() {
    if (this.livro !== undefined) {
      this.livroService.save(this.livro).subscribe(livros => {
        console.log(livros);
        this.router.navigate(['/livros/list']);
      });
    }
  }
}
