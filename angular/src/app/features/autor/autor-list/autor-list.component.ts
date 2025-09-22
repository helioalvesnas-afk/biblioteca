import { Component, OnInit } from '@angular/core';
import { AutorService } from 'src/app/core/services/autor.service';
import { Autor } from 'src/app/shared/models/autor';
import { RouterLink } from '@angular/router';
import { NgIf, NgFor } from '@angular/common';
import { AutorListItemComponent } from '../autor-list-item/autor-list-item.component';

@Component({
    selector: 'app-autor-list',
    templateUrl: './autor-list.component.html',
    styleUrls: ['./autor-list.component.css'],
    imports: [RouterLink, NgIf, NgFor, AutorListItemComponent]
})
export class AutorListComponent implements OnInit {
  autores: Autor[] = [];

  constructor(private autorService: AutorService) { }

  ngOnInit() {
    this.autorService.getAll().subscribe(autor => {
      this.autores = autor;
    });
  }

  onDeleted(autor: Autor) {
    if (autor !== undefined) {
      const index = this.autores.findIndex((autorItem) => autorItem.id == autor.id);
      this.autores.splice(index, 1);
    }
  }
}
