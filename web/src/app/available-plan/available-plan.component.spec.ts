import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvailablePlanComponent } from './available-plan.component';

describe('AvailablePlanComponent', () => {
  let component: AvailablePlanComponent;
  let fixture: ComponentFixture<AvailablePlanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AvailablePlanComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AvailablePlanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
