import { Router, RouterLink } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { ContaService } from '../../core/services/conta.service';
import { FormsModule } from '@angular/forms';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
    standalone: true,
    imports: [FormsModule, RouterLink]
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
