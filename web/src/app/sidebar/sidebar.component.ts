import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { Fee } from '../models/fee.model';
import { User } from '../models/user.model';
import { AuthenticationService } from '../services/authentication.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit, OnChanges {
  @Input() isStale: boolean = false;
  @Output() changeEvent = new EventEmitter<string>();

  currentUser!: User | null;
  plans: {
    name: string,
    price: number
  }[] = [];
  fees: Fee[] = [];
  totalFees: number = 0;
  total: number = 0;

  constructor(private userService: UserService,
              private authService: AuthenticationService) { }

  async ngOnInit() {
    this.authService.currentUser.subscribe(currUser => {
      this.currentUser = currUser;
    });

    if (!this.authService.isUserLoggedIn()) {
      return;
    }

    this.currentUser = (await lastValueFrom(this.userService.findById(this.currentUser!.id))).body;

    for (let activePlan of this.currentUser!.activePlans) {
      this.plans.push({
        'name': activePlan.plan.name,
        'price': activePlan.plan.price
      });
      this.total += activePlan.plan.price;
      for (let activeFees of activePlan.plan.activeFees) {
        this.fees.push(activeFees.fee);
      }
    }

    for (let fee of this.fees) {
      let feePrice;
      if (fee.isPercentage) {
        feePrice = (fee.amount / 100) * this.total;
      } else {
        feePrice = fee.amount;
      }
      this.totalFees += feePrice;
    }

    this.total += this.totalFees;
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['isStale'].currentValue === true) {
      this.plans = [];
      this.fees = [];
      this.totalFees = 0;
      this.total = 0;
      this.ngOnInit();
    }
    this.changeEvent.emit('');
  }
}
