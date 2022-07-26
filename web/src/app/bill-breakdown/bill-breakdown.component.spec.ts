import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BillBreakdownComponent } from './bill-breakdown.component';

describe('BillBreakdownComponent', () => {
  let component: BillBreakdownComponent;
  let fixture: ComponentFixture<BillBreakdownComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BillBreakdownComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BillBreakdownComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
