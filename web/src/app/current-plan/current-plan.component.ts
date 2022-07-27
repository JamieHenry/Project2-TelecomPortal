import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { generate, lastValueFrom } from 'rxjs';
import { ActiveNumber } from '../models/active-number.model';
import { ActivePlan } from '../models/active-plan.model';
import { ActiveNumberService } from '../services/active-number.service';
import { ActivePlanService } from '../services/active-plan.service';
import { PlanService } from '../services/plan.service';

@Component({
  selector: 'app-current-plan',
  templateUrl: './current-plan.component.html',
  styleUrls: ['./current-plan.component.css', '../../assets/stylesheets/modal.css']
})
export class CurrentPlanComponent implements OnInit {
  @Input() plan: any;
  @Output() changeEvent = new EventEmitter<string>();

  showDeleteLineModal: boolean = false;
  modalLine: any = null;
  showChangePlanModal: boolean = false;
  showCancelPlanModal: boolean = false;
  changeLineSelection = 0;
  changePlanError = '';
  addLineError = '';
  
  constructor(private activeNumberResponse: ActiveNumberService,
              private planService: PlanService,
              private activePlanService: ActivePlanService,
              private activeNumberService: ActiveNumberService) { }

  ngOnInit(): void {
  }

  onSelected(value: string) {
    this.changeLineSelection = parseInt(value);
    if (this.changeLineSelection === 0) {
      this.changePlanError = 'Required';
    } else if (this.changeLineSelection !==0) {
      this.changePlanError = '';
    }
  }

  deleteLineModal(line: any) {
    this.showDeleteLineModal = true;
    this.modalLine = line;
  }

  cancelDeleteLine() {
    this.showDeleteLineModal = false;
  }

  async deleteLine(activeNumberId: number) {
    await lastValueFrom(this.activeNumberService.delete(activeNumberId));
    this.changeEvent.emit('');
  }

  async addLine() {
    this.addLineError = '';
    if (this.plan.lines.length === this.plan.plan.numDevices) {
      this.addLineError = '(Line limit reached!)'
      return;
    }

    let phoneNumber;
    let activeNumberResponse
    do {
      phoneNumber = `(${this.getRandomInt(100, 999)}) ${this.getRandomInt(100, 999)}-${this.getRandomInt(1000, 9999)}`;
      activeNumberResponse = await lastValueFrom(this.activeNumberResponse.findByPhoneNumber(phoneNumber));
    } while (activeNumberResponse.body !== null);
    activeNumberResponse = await lastValueFrom(this.activeNumberService.save(new ActiveNumber(0, phoneNumber, false, this.plan.userId, 1, this.plan.activePlanId)));
    this.changeEvent.emit('');
  }

  getRandomInt(min:number, max: number) {
    return Math.floor((Math.random() * (max - min)) + min);
  }

  changePlanModal() {
    this.showChangePlanModal = true;
  }

  cancelChangePlan() {
    this.showChangePlanModal = false;
    this.changePlanError = '';
    this.addLineError = '';
  }

  async changePlan() {
    this.changePlanError = '';
    if (this.changeLineSelection === 0 || this.changeLineSelection === this.plan.plan.id) {
      this.changePlanError = 'Required'
    }
    
    const planResponse = await lastValueFrom(this.planService.findById(this.changeLineSelection));
    const numDeviceDiff = this.plan.lines.length - planResponse.body!.numDevices;
    if (numDeviceDiff > 0) {
      for (let i = 0; i < numDeviceDiff; i++) {
        const poppedLine = this.plan.lines.pop();
        await lastValueFrom(this.activeNumberService.delete(poppedLine.activeNumberId));
      }
    }
    await lastValueFrom(this.activePlanService.save(new ActivePlan(this.plan.activePlanId, this.plan.userId, planResponse.body!.id)));
    this.changeEvent.emit('');
  }

  cancelPlanModal() {
    this.showCancelPlanModal = true;
  }

  cancelCancelPlan() {
    this.showCancelPlanModal = false;
    this.changePlanError = '';
    this.addLineError = '';
  }

  async cancelPlan() {
    for (let line of this.plan.lines) {
      await lastValueFrom(this.activeNumberResponse.delete(line.activeNumberId));
    }
    await lastValueFrom(this.activePlanService.delete(this.plan.activePlanId));
    this.changeEvent.emit('');
  }
}
