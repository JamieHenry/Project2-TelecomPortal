import { TestBed } from '@angular/core/testing';

import { ActiveNumberService } from './active-number.service';

describe('ActiveNumberService', () => {
  let service: ActiveNumberService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ActiveNumberService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
