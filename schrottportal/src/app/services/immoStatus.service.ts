import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ImmoStatus} from '../models/immoStatus.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ImmoStatusService {

  constructor(private http: HttpClient) { }

  getAllStati():Observable<ImmoStatus[]> {
    return this.http.get<ImmoStatus[]>("/api/immobilie/status")
  }
}
