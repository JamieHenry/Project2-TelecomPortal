import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-available-plan',
  templateUrl: './available-plan.component.html',
  styleUrls: ['./available-plan.component.css']
})
export class AvailablePlanComponent implements OnInit {
  @Input() plan: any;
  @Input() addActivePlan!: ((args: any) => void);

  constructor() { }

  ngOnInit(): void {
  }

  add() {
    this.addActivePlan!('hello');
  }
}
