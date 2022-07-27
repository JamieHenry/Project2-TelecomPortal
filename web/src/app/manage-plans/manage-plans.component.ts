import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { ActivePlan } from '../models/active-plan.model';
import { Plan } from '../models/plan.model';
import { User } from '../models/user.model';
import { AuthenticationService } from '../services/authentication.service';
import { PlanService } from '../services/plan.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-manage-plans',
  templateUrl: './manage-plans.component.html',
  styleUrls: ['./manage-plans.component.css']
})
export class ManagePlansComponent implements OnInit {

  isStale: boolean = false;

  currentUser!: User | null;
  currentPlans: ActivePlan[] = [];
  allPlans: Plan[] = [];

  constructor(private planService: PlanService,
              private authService: AuthenticationService,
              private userService: UserService,
              private router: Router) { }

  async ngOnInit() {
    this.authService.currentUser.subscribe(currUser => {
      this.currentUser = currUser;
    });
    
    if (!this.authService.isUserLoggedIn()) {
      this.navigate('/');
      return;
    }

    this.currentUser = (await lastValueFrom(this.userService.findById(this.currentUser!.id))).body;

    this.currentPlans = this.currentUser!.activePlans;

    this.allPlans = (await lastValueFrom(this.planService.findAll())).body!;
  }

  resetValues() {
    this.currentPlans = [];
    this.allPlans = [];
    this.ngOnInit();
    this.isStale = true;
  }

  ngAfterViewInit() {
    this.isStale = false;
  }

  navigate(url: string): void {
    this.router.navigateByUrl(`${url}`);
  }
}
