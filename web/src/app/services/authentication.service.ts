import { Injectable } from '@angular/core';
import { BehaviorSubject, lastValueFrom, Observable } from 'rxjs';
import { ActiveNumber } from '../models/active-number.model';
import { ActivePlan } from '../models/active-plan.model';
import { Plan } from '../models/plan.model';
import { User } from '../models/user.model';
import { ActiveNumberService } from './active-number.service';
import { ActivePlanService } from './active-plan.service';
import { PlanService } from './plan.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private currentUserSubject: BehaviorSubject<User | null>;
  public currentUser: Observable<User | null>;

  public allPlans!: Plan[] | null;

  public activePlans!: ActivePlan[] | null;
  public activeNumbers!: ActiveNumber[] | null;

  constructor(private activeNumberService: ActiveNumberService, private activePlanService: ActivePlanService, private planService: PlanService) {
    this.currentUserSubject = new BehaviorSubject<User | null>(
      JSON.parse(sessionStorage.getItem('currentUser')!)
    );

    this.currentUser = this.currentUserSubject.asObservable();

    this.setValues();
  }

  setUser(user: User) {
    this.resetCredentials();
    sessionStorage.setItem('currentUser', JSON.stringify(user));
    this.currentUserSubject.next(user);
    this.setValues();
  }

  async setValues() {
    console.log('SET VALUES');
    if (this.currentUserSubject.getValue() === null) return;

    const planResponse = await lastValueFrom(this.planService.findAll());
    this.allPlans = planResponse.body;

    const activePlanResponse = await lastValueFrom(this.activePlanService.findByUserId(this.currentUserSubject.getValue()!.id));
    this.activePlans = activePlanResponse.body;

    const activeNumberResponse = await lastValueFrom(this.activeNumberService.findByUserId(this.currentUserSubject.getValue()!.id));
    this.activeNumbers = activeNumberResponse.body;

    console.log('USER: ', this.currentUserSubject.getValue());
    console.log('ALL PLANS: ', this.allPlans);
    console.log('ACTIVE PLANS: ', this.activePlans);
    console.log('ACTIVE NUMBERS: ', this.activeNumbers);
  }

  resetCredentials() {
    sessionStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }

  logout() {
    this.resetCredentials();
  }
}
