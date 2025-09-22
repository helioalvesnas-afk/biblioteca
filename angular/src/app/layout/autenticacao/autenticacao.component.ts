import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
    selector: 'app-autenticacao',
    templateUrl: './autenticacao.component.html',
    styleUrls: ['./autenticacao.component.css'],
    standalone: true,
    imports: [RouterOutlet]
})
export class AutenticacaoComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
