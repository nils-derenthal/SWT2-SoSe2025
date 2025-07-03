import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ImmobilieDTO} from '../models/immobilie.model';

@Injectable({
  providedIn: 'root'
})
export class ImmobilienService {
  // http = inject(HttpClient);

  constructor(private http: HttpClient) { }


  getImmobilienBySearch(search: string): Observable<ImmobilieDTO[]> {
    console.log('sending')
    let params = new HttpParams();
    params.set('search', search);
    return this.http.get<ImmobilieDTO[]>('/api/immobilien', { params });
  }
}
