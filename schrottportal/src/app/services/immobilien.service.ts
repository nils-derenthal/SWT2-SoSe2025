import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ImmobilieDTO } from '../models/immobilie.model';
import {ImmoStatus} from '../models/immoStatus.model';

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

  addImmoStatus(status: ImmoStatus): Observable<number> {
    return this.http.post<number>(`/api/immobilie/status/add`, status);
  }

  setStatusActive(statusId: number, immoId: number) {
    return this.http.post(`/api/immobilien/${statusId}/status/aktiv/${immoId}`, {} );
  }
}
