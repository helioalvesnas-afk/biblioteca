import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GeneroService } from 'src/app/core/services/genero.service';
import { Genero } from 'src/app/shared/models/genero';

@Component({
  selector: 'app-genero-form',
  templateUrl: './genero-form.component.html',
  styleUrls: ['./genero-form.component.css']
})
export class GeneroFormComponent implements OnInit {
  genero: Genero = new Genero();
  title: string = 'Novo';

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private generoService: GeneroService
  ) { }

  ngOnInit() {
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    if (id) {
      this.generoService.getById(id).subscribe(genero => {
        this.genero = genero.body;
        this.title = 'Alterando genero';
      });
    }
  }

  onSubmit() {
    if (this.genero !== undefined) {
      this.generoService.save(this.genero).subscribe(genero => {
        console.log(genero);
        this.router.navigate(['/generos/list']);
      });
    }
  }
}
