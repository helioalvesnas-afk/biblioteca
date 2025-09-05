import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Genero } from '../../shared/models/genero';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GeneroService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<any[]>(`${environment.api}/api/v1/generos`);
  }

  getById(id: string) {
    return this.http.get<any>(`${environment.api}/api/v1/generos/${id}`);
  }

  save(genero: Genero) {
    const generoBody = {
      id: genero.id,
      nome: genero.nome
    };

    if (genero.id) {
      return this.http.put<any>(`${environment.api}/api/v1/generos/${genero.id}`, generoBody);
    } else {
      return this.http.post<any>(`${environment.api}/api/v1/generos`, generoBody);
    }
  }

  delete(id: string) {
    return this.http.delete(`${environment.api}/api/v1/generos/${id}`);
  }
}
