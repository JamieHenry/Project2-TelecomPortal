import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-current-plan',
  templateUrl: './current-plan.component.html',
  styleUrls: ['./current-plan.component.css']
})
export class CurrentPlanComponent implements OnInit {
  @Input() plan: any;
  @Input() addLine!: ((args: any) => void);
  @Input() changePlan!: ((args: any) => void);
  @Input() cancelPlan!: ((args: any) => void);
  @Input() deleteLine!: ((args: any) => void);

  constructor() { }

  ngOnInit(): void {
  }

  add() {
    this.addLine('hello');
  }

  change() {
    this.changePlan('hello');
  }

  cancel() {
    this.cancelPlan('hello');
  }

  delete(args: any) {
    this.deleteLine(args);
  }
}
