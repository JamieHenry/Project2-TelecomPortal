import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { ActivePlan } from '../models/active-plan.model';
import { ActivePlanService } from '../services/active-plan.service';

@Component({
  selector: 'app-available-plan',
  templateUrl: './available-plan.component.html',
  styleUrls: ['./available-plan.component.css', '../../assets/stylesheets/modal.css']
})
export class AvailablePlanComponent implements OnInit {
  @Input() plan: any;
  @Output() changeEvent = new EventEmitter<string>();

  constructor(private activePlanService: ActivePlanService) { }

  ngOnInit(): void {
  }

  showActivePlanModal: boolean = false;

  addActivePlanModal() {
    this.showActivePlanModal = true;
  }

  cancelAddActivePlan() {
    this.showActivePlanModal = false;
  }

  async addActivePlan() {
    const activePlanResponse = await lastValueFrom(this.activePlanService.save(new ActivePlan(0, this.plan.userId, this.plan.plan.id)));
    this.changeEvent.emit('');
  }
}
