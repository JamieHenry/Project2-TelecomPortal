import { TestBed } from '@angular/core/testing';

import { ActiveDescriptorService } from './active-descriptor.service';

describe('ActiveDescriptorService', () => {
  let service: ActiveDescriptorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ActiveDescriptorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
