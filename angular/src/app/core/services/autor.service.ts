import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Autor } from '../../shared/models/autor';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AutorService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<any[]>(`${environment.api}/api/v1/autores`);
  }

  getById(id: string) {
    return this.http.get<any>(`${environment.api}/api/v1/autores/${id}`);
  }

  save(autor: Autor) {
    const autorBody = {
      id: autor.id,
      nome: autor.nome
    };

    if (autor.id) {
      return this.http.put<any>(`${environment.api}/api/v1/autores/${autor.id}`, autorBody);
    } else {
      return this.http.post<any>(`${environment.api}/api/v1/autores`, autorBody);
    }
  }

  delete(id: string) {
    return this.http.delete(`${environment.api}/api/v1/autores/${id}`);
  }
}
