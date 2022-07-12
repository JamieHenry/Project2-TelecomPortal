import { TestBed } from '@angular/core/testing';

import { ActiveFeeService } from './active-fee.service';

describe('ActiveFeeService', () => {
  let service: ActiveFeeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ActiveFeeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
