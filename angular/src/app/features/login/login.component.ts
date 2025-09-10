import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { ContaService } from '../../core/services/conta.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  login = {
    username: '',
    password: ''
  };

  constructor(
    private contaService: ContaService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  async onSubmit() {
    try {
      if (this.login !== undefined) {
        this.router.navigate(['/generos']);
        const result = await this.contaService.login(this.login);
        console.log(`Login efetuado: ${result}`);
      }
    } catch (error) {
      console.error(error);
    }
  }
}
