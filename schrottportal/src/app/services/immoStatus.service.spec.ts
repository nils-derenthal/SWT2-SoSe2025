import { TestBed } from '@angular/core/testing';

import { ImmoStatusService } from './immoStatus.service';

describe('ImmoStatusService', () => {
  let service: ImmoStatusService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ImmoStatusService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
