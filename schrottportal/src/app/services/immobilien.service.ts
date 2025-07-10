import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { ImmobilieDTO } from '../models/immobilie.model';

@Injectable({
  providedIn: 'root',
})
export class ImmobilienService {
  http = inject(HttpClient);

  getImmobilienBySearch(search: string): Observable<ImmobilieDTO[]> {
    return this.http
      .get<ImmobilieDTO[]>('/api/immobilien', {
        params: { search: search },
      })
      .pipe(tap(i => console.log(i)));
  }

  getAllImmobilien(): Observable<ImmobilieDTO[]> {
    return this.http.get<ImmobilieDTO[]>('/api/immobilien/all');
  }

  getImmobilieById(id: number): Observable<ImmobilieDTO> {
    return this.http.get<ImmobilieDTO>(`/api/immobilien/${id}`);
  }
}
