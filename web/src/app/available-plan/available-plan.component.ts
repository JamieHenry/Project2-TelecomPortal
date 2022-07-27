import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-available-plan',
  templateUrl: './available-plan.component.html',
  styleUrls: ['./available-plan.component.css', '../../assets/stylesheets/modal.css']
})
export class AvailablePlanComponent implements OnInit {
  @Input() plan: any;
  @Input() userId!: number;
  @Output() changeEvent = new EventEmitter<string>();

  showActivePlanModal: boolean = false;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  addActivePlanModal() {
    this.showActivePlanModal = true;
  }

  cancelAddActivePlan() {
    this.showActivePlanModal = false;
  }

  async addActivePlan() {
    await lastValueFrom(this.userService.addPlan(this.userId, this.plan.id));
    this.changeEvent.emit('');
  }
}
