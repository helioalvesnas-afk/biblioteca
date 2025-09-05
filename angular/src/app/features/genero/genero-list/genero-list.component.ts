import { Component, OnInit } from '@angular/core';
import { GeneroService } from 'src/app/core/services/genero.service';
import { Genero } from 'src/app/shared/models/genero';

@Component({
  selector: 'app-genero-list',
  templateUrl: './genero-list.component.html',
  styleUrls: ['./genero-list.component.css']
})

export class GeneroListComponent implements OnInit {

  generos: Genero[] = [];

  constructor(private generoService: GeneroService) { }

  ngOnInit() {
    this.generoService.getAll().subscribe(genero => {
      this.generos = genero;
    });
  }

  onDeleted(genero: Genero) {
    if (genero !== undefined) {
      const index = this.generos.findIndex((generoItem) => generoItem.id == genero.id);
      this.generos.splice(index, 1);
    }
  }
}
