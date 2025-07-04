import { TestBed } from '@angular/core/testing';

import { ImmobilienService } from './immobilien.service';

describe('ImmobilienService', () => {
  let service: ImmobilienService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ImmobilienService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
