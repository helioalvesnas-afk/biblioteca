import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { lastValueFrom } from 'rxjs';
import { Livro } from '../../shared/models/livro';
import { environment } from '../../../environments/environment';
import { LivroMapper } from 'src/app/shared/Mapper/livro-mapper';

@Injectable({
  providedIn: 'root'
})
export class LivroService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<any[]>(`${environment.api}/api/v1/livros`);
  }

  getById(id: number): Promise<any> {
    return lastValueFrom(
      this.http.get<any>(`${environment.api}/api/v1/livros/${id}`)
    );
  }

  save(livro: Livro) {
    if (livro !== undefined && livro.id !== undefined && livro.id !== undefined) {
      return this.http.put<any>(`${environment.api}/api/v1/livros/${livro.id}`, LivroMapper.toIncluirLivroDto(livro));
    } else {
      return this.http.post<any>(`${environment.api}/api/v1/livros`, LivroMapper.toAlterarLivroDto(livro));
    }
  }

  delete(id: string) {
    return this.http.delete(`${environment.api}/api/v1/livros/${id}`);
  }
}
