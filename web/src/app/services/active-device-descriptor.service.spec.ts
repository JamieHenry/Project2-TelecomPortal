import { TestBed } from '@angular/core/testing';

import { ActiveDeviceDescriptorService } from './active-device-descriptor.service';

describe('ActiveDeviceDescriptorService', () => {
  let service: ActiveDeviceDescriptorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ActiveDeviceDescriptorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
