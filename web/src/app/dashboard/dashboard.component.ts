import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { Plan } from '../models/plan.model';
import { User } from '../models/user.model';
import { ActiveDescriptorService } from '../services/active-descriptor.service';
import { ActiveNumberService } from '../services/active-number.service';
import { ActivePlanService } from '../services/active-plan.service';
import { AuthenticationService } from '../services/authentication.service';
import { DescriptorService } from '../services/descriptor.service';
import { DeviceService } from '../services/device.service';
import { PlanService } from '../services/plan.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  currentUser!: User | null;
  currentNumbers: {model: string, phoneNumber: string, plan: string}[] = [];
  currentPlans: {plan: Plan, descriptors: string[], currLines: number}[] = [];

  constructor(private descriptorService: DescriptorService,
              private planService: PlanService,
              private deviceService: DeviceService,
              private activeDescriptorService: ActiveDescriptorService,
              private activeNumberService: ActiveNumberService,
              private activePlanService: ActivePlanService,
              private authService: AuthenticationService,
              private router: Router) { }

  async ngOnInit() {
    this.authService.currentUser.subscribe(currUser => {
      this.currentUser = currUser;
    });
    
    if (this.currentUser === null) {
      this.navigate('/');
      return;
    }

    // plans
    const activePlansResponse = await lastValueFrom(this.activePlanService.findByUserId(this.currentUser!.id));
    for (let activePlan of activePlansResponse.body!) {
      const activePlanDescriptors = [];
      const planResponse = await lastValueFrom(this.planService.findById(activePlan.planId));
      const activeDescriptorResponse = await lastValueFrom(this.activeDescriptorService.findByPlanId(activePlan.id));
      const activeNumberResponse = await lastValueFrom(this.activeNumberService.findByActivePlanId(activePlan.id));
      for (let activeDescriptor of activeDescriptorResponse.body!) {
        const descriptorResponse = await lastValueFrom(this.descriptorService.findById(activeDescriptor.descriptorId));
        activePlanDescriptors.push(descriptorResponse.body!.description);
      }
      this.currentPlans.push({
        'plan': planResponse.body!,
        'descriptors': activePlanDescriptors,
        'currLines': activeNumberResponse.body!.length
      });
    }

    // numbers
    const activeNumbersResponse = await lastValueFrom(this.activeNumberService.findByUserId(this.currentUser!.id));
    for (let activeNumber of activeNumbersResponse.body!) {
      const deviceResponse = await lastValueFrom(this.deviceService.findById(activeNumber.deviceId));
      const activePlanResponse = await lastValueFrom(this.activePlanService.findById(activeNumber.activePlanId));
      const planResponse = await lastValueFrom(this.planService.findById(activePlanResponse.body!.planId));
      this.currentNumbers.push({
        'model': deviceResponse.body!.model,
        'phoneNumber': activeNumber.phoneNumber,
        'plan': planResponse.body!.name
      });
    }
  }

  navigate(url: string): void {
    this.router.navigateByUrl(`${url}`);
  }
}
