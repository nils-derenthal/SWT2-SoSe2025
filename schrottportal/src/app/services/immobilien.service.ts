import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ImmobilieDTO } from '../models/immobilie.model';

@Injectable({
  providedIn: 'root',
})
export class ImmobilienService {
  http = inject(HttpClient);

  getImmobilienBySearchAndFilter(
    search: string,
    statusFilter: string,
  ): Observable<ImmobilieDTO[]> {
    return this.http.get<ImmobilieDTO[]>('/api/immobilien', {
      params: { search: search, statusFilter: statusFilter },
    });
  }

  getAllImmobilien(): Observable<ImmobilieDTO[]> {
    return this.http.get<ImmobilieDTO[]>('/api/immobilien/all');
  }

  getArchivedImmobilien(): Observable<ImmobilieDTO[]> {
    return this.http.get<ImmobilieDTO[]>('/api/immobilien/archived');
  }

  getImmobilieById(id: number): Observable<ImmobilieDTO> {
    return this.http.get<ImmobilieDTO>(`/api/immobilien/${id}`);
  }
}
