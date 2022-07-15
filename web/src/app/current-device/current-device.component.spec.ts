import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrentDeviceComponent } from './current-device.component';

describe('CurrentDeviceComponent', () => {
  let component: CurrentDeviceComponent;
  let fixture: ComponentFixture<CurrentDeviceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CurrentDeviceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrentDeviceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
