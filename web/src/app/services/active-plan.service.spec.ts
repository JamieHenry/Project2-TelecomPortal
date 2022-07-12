import { TestBed } from '@angular/core/testing';

import { ActivePlanService } from './active-plan.service';

describe('ActivePlanService', () => {
  let service: ActivePlanService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ActivePlanService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
