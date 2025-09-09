import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Genero } from '../../shared/models/genero';
import { environment } from '../../../environments/environment';
import { Observable, of } from 'rxjs';

@Injectable()
export class GeneroService {

  constructor(private http: HttpClient) { }

  carregarGeneros(): Observable<any[]> {
    return this.http.get<any[]>(`${environment.api}/api/v1/generos`);
  }

  buscarGeneroPorId(id: string) {
    return this.http.get<any>(`${environment.api}/api/v1/generos/${id}`);
  }

  adicionarGenero(genero: Genero) {
    return this.http.post<any>(`${environment.api}/api/v1/generos`, genero);
  }

  atualizarGenero(genero: Genero) {
    return this.http.put<any>(`${environment.api}/api/v1/generos/${genero.id}`, genero);
  }

  removerGenero(id: string) {
    return this.http.delete(`${environment.api}/api/v1/generos/${id}`);
  }

}
