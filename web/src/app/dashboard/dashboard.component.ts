import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { ActiveNumber } from '../models/active-number.model';
import { ActivePlan } from '../models/active-plan.model';
import { User } from '../models/user.model';
import { AuthenticationService } from '../services/authentication.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  
  currentUser!: User | null;
  currentDevices: {
    activeNumber: ActiveNumber,
    planName: string
  }[] = [];
  currentPlans: ActivePlan[] = [];

  constructor(private authService: AuthenticationService,
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

    for (let activePlan of this.currentPlans) {
      for (let activeNumber of activePlan.activeNumbers) {
        if (activeNumber.hasDeviceAssigned) {
          this.currentDevices.push({
            activeNumber,
            'planName': activePlan.plan.name
          });
        }
      }
    }
  }

  navigate(url: string): void {
    this.router.navigateByUrl(`${url}`);
  }
}
