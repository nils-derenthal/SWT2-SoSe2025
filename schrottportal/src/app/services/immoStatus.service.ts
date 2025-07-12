import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ImmoStatusService {

  constructor(private http: HttpClient) { }

  getAllStati():Observable<string[]> {
    return this.http.get<string[]>("/api/immobilie/status")
  }
}