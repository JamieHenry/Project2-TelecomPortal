import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { ActiveNumberService } from '../services/active-number.service';

@Component({
  selector: 'app-current-plan',
  templateUrl: './current-plan.component.html',
  styleUrls: ['./current-plan.component.css', '../../assets/stylesheets/modal.css']
})
export class CurrentPlanComponent implements OnInit {
  @Input() plan: any;
  @Output() changeEvent = new EventEmitter<string>();

  currentLines: number = 0;

  constructor(private activeNumberService: ActiveNumberService) { }

  ngOnInit(): void {
  }

  async deleteLine(activeNumberId: number) {
    await lastValueFrom(this.activeNumberService.delete(activeNumberId));
    this.changeEvent.emit('');
  }

  showChangePlanModal: boolean = false;

  changePlanModal() {
    this.showChangePlanModal = true;
  }

  cancelChangePlan() {
    this.showChangePlanModal = false;
  }

  changePlan() {

  }

  showCancelPlanModal: boolean = false;

  cancelPlanModal() {
    this.showCancelPlanModal = true;
  }

  cancelCancelPlan() {
    this.showCancelPlanModal = false;
  }

  cancelPlan() {

  }
}
