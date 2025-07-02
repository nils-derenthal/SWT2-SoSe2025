import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

export class StatusService {
  constructor(private readonly httpClient: HttpClient) {
  }

  getImmoStatus$(immoId: number): Observable<void> {
    return this.httpClient.get<void>(`immobilie/status/${immoId}`)
  }
}
