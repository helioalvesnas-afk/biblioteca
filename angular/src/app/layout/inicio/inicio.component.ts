import { Component, OnInit } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { ContaService } from '../../core/services/conta.service';

@Component({
    selector: 'app-inicio',
    templateUrl: './inicio.component.html',
    styleUrls: ['./inicio.component.css'],
    standalone: true,
    imports: [RouterLink, RouterOutlet]
})
export class InicioComponent implements OnInit {

  constructor(private contaService: ContaService, private router: Router) { }

  ngOnInit() {
  }

  async logout() {
    try {
      await this.contaService.logout();
      console.log(`Logout com sucesso`);
      this.router.navigate(['/login']);
    } catch (error) {
      console.error(error);
    }
  }

}
