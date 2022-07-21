import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { ActiveNumberService } from '../services/active-number.service';

@Component({
  selector: 'app-current-plan',
  templateUrl: './current-plan.component.html',
  styleUrls: ['./current-plan.component.css']
})
export class CurrentPlanComponent implements OnInit {
  @Input() plan: any;
  @Output() changeEvent = new EventEmitter<string>();

  constructor(private activeNumberService: ActiveNumberService) { }

  ngOnInit(): void {
  }

  async deleteLine(activeNumberId: number) {
    await lastValueFrom(this.activeNumberService.delete(activeNumberId));
    this.changeEvent.emit('');
  }

  cancelPlan() {

  }
}
