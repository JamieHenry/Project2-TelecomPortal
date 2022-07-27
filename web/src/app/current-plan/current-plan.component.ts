import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { DeviceService } from '../services/device.service';
import { PlanService } from '../services/plan.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-current-plan',
  templateUrl: './current-plan.component.html',
  styleUrls: ['./current-plan.component.css', '../../assets/stylesheets/modal.css']
})
export class CurrentPlanComponent implements OnInit {
  @Input() plan: any;
  @Input() userId!: number;
  @Output() changeEvent = new EventEmitter<string>();

  showDeleteLineModal: boolean = false;
  modalLine: any = null;
  showChangePlanModal: boolean = false;
  showCancelPlanModal: boolean = false;
  changeLineSelection = 0;
  changePlanError = '';
  addLineError = '';
  
  constructor(private planService: PlanService,
              private userService: UserService,
              private deviceService: DeviceService) { }

  ngOnInit(): void {
  }

  onSelected(value: string) {
    this.changePlanError = '';
    this.changeLineSelection = parseInt(value);
    if (this.changeLineSelection === 0) {
      this.changePlanError = 'Required';
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
    await lastValueFrom(this.userService.removeLine(activeNumberId));
    this.changeEvent.emit('');
  }

  async addLine() {
    this.addLineError = '';
    if (this.plan.activeNumbers.length === this.plan.plan.numDevices) {
      this.addLineError = '(Line limit reached!)'
      return;
    }

    let phoneNumber;
    let activeNumberResponse
    do {
      phoneNumber = `(${this.getRandomInt(100, 999)}) ${this.getRandomInt(100, 999)}-${this.getRandomInt(1000, 9999)}`;
      activeNumberResponse = await lastValueFrom(this.deviceService.findByPhoneNumber(phoneNumber));
    } while (activeNumberResponse.body !== null);
    await lastValueFrom(this.userService.addLine(this.userId, this.plan.id, phoneNumber));
    this.changeEvent.emit('');
  }

  getRandomInt(min: number, max: number) {
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
    const numDeviceDiff = this.plan.activeNumbers.length - planResponse.body!.numDevices;
    if (numDeviceDiff > 0) {
      for (let i = 0; i < numDeviceDiff; i++) {
        const poppedLine = this.plan.activeNumbers.pop();
        await lastValueFrom(this.userService.removeLine(poppedLine.id));
      }
    }
    await lastValueFrom(this.userService.assignPlan(this.plan.id, this.changeLineSelection));
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
    for (let line of this.plan.activeNumbers) {
      await lastValueFrom(this.userService.removeLine(line.id));
    }
    await lastValueFrom(this.userService.removePlan(this.plan.id));
    this.changeEvent.emit('');
  }
}
