import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { AutorService } from 'src/app/core/services/autor.service';
import { Autor } from 'src/app/shared/models/autor';
import { FormsModule } from '@angular/forms';

@Component({
    selector: 'app-autor-form',
    templateUrl: './autor-form.component.html',
    styleUrls: ['./autor-form.component.css'],
    imports: [FormsModule, RouterLink]
})
export class AutorFormComponent implements OnInit {
  autor: Autor = new Autor();
  title: string = 'Novo';


  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private autorService: AutorService
  ) { }

  ngOnInit() {
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    if (id) {
      this.autorService.getById(id).subscribe(autor => {
        this.autor = autor.body;
        console.log("autor: ", this.autor);
        this.title = 'Alterando autor';
      });
    }
  }

  onSubmit() {
    if (this.autor !== undefined) {
      this.autorService.save(this.autor).subscribe(autor => {
        console.log(autor);
        this.router.navigate(['/autores/list']);
      });
    }
  }
}
