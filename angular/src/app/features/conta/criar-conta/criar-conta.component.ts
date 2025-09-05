import { Component, OnInit } from '@angular/core';
import { ContaService } from '../../../core/services/conta.service';

@Component({
  selector: 'app-criar-conta',
  templateUrl: './criar-conta.component.html',
  styleUrls: ['./criar-conta.component.css']
})
export class CriarContaComponent implements OnInit {
  conta = {
    username: '',
    password: ''
  };

  constructor(
    private contaService: ContaService
  ) { }

  ngOnInit() {
  }

  async onSubmit() {
    try {
      const result = await this.contaService.createAccount(this.conta);

      // exibir uma msg amigavel aqui
      console.log(result);
    } catch (error) {
      console.error(error);
    }
  }
}
