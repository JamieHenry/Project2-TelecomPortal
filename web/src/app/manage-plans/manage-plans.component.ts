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
  selector: 'app-manage-plans',
  templateUrl: './manage-plans.component.html',
  styleUrls: ['./manage-plans.component.css']
})
export class ManagePlansComponent implements OnInit {

  isStale: boolean = false;

  currentUser!: User | null;
  currentPlans: {
    plan: Plan,
    activePlanId: number,
    descriptors: string[],
    lines: {
      activeNumberId: number,
      phoneNumber: string,
      model: string
    }[]
  }[] = [];
  allPlans: {
    plan: Plan,
    userId: number,
    descriptors: string[]
  }[] = [];

  constructor(private deviceService: DeviceService,
              private descriptorService: DescriptorService,
              private activeNumberService: ActiveNumberService,
              private activeDescriptorService: ActiveDescriptorService,
              private planService: PlanService,
              private activePlanService: ActivePlanService,
              private router: Router,
              private authService: AuthenticationService) { }

  async ngOnInit() {
    this.authService.currentUser.subscribe(currUser => {
      this.currentUser = currUser;
    });

    if (this.currentUser === null) {
      this.navigate('/');
      return;
    }

    // active plans
    const activePlansResponse = await lastValueFrom(this.activePlanService.findByUserId(this.currentUser!.id));
    for (let activePlan of activePlansResponse.body!) {
      const activePlanDescriptors = [];
      const activePlanLines = [];
      const planResponse = await lastValueFrom(this.planService.findById(activePlan.planId));
      const activeDescriptorResponse = await lastValueFrom(this.activeDescriptorService.findByPlanId(activePlan.planId));
      const activeNumberResponse = await lastValueFrom(this.activeNumberService.findByActivePlanId(activePlan.id));
      for (let activeDescriptor of activeDescriptorResponse.body!) {
        const descriptorResponse = await lastValueFrom(this.descriptorService.findById(activeDescriptor.descriptorId));
        activePlanDescriptors.push(descriptorResponse.body!.description);
      }
      for (let activeNumber of activeNumberResponse.body!) {
        const deviceResponse = await lastValueFrom(this.deviceService.findById(activeNumber.deviceId));
        activePlanLines.push({'activeNumberId': activeNumber.id, 'phoneNumber': activeNumber.phoneNumber, 'model': deviceResponse.body!.model});
      }
      this.currentPlans.push({
        'plan': planResponse.body!,
        'activePlanId': activePlan.id,
        'descriptors': activePlanDescriptors,
        'lines': activePlanLines
      });
    }

    // all plans
    const planResponse = await lastValueFrom(this.planService.findAll());
    for (let plan of planResponse.body!) {
      const activePlanDescriptors = [];
      const activeDescriptorResponse = await lastValueFrom(this.activeDescriptorService.findByPlanId(plan.id));
      for (let activeDescriptor of activeDescriptorResponse.body!) {
        const descriptorResponse = await lastValueFrom(this.descriptorService.findById(activeDescriptor.descriptorId));
        activePlanDescriptors.push(descriptorResponse.body!.description);
      }
      this.allPlans.push({
        'plan': plan,
        'userId': this.currentUser!.id,
        'descriptors': activePlanDescriptors
      });
    }
  }

  resetValues() {
    this.currentPlans = [];
    this.allPlans = [];
    this.ngOnInit();
    this.isStale = true;
  }

  resetIsStale() {
    this.isStale = false;
  }

  navigate(url: string): void {
    this.router.navigateByUrl(`${url}`);
  }
}
