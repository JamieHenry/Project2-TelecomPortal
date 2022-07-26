import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { User } from '../models/user.model';
import { ActiveFeeService } from '../services/active-fee.service';
import { ActivePlanService } from '../services/active-plan.service';
import { AuthenticationService } from '../services/authentication.service';
import { FeeService } from '../services/fee.service';
import { PlanService } from '../services/plan.service';

@Component({
  selector: 'app-bill-breakdown',
  templateUrl: './bill-breakdown.component.html',
  styleUrls: ['./bill-breakdown.component.css']
})
export class BillBreakdownComponent implements OnInit, OnChanges {
  @Input() isStale: boolean = false;
  @Output() changeEvent = new EventEmitter<string>();

  currentUser!: User | null;
  plans: {
    name: string,
    price: number
  }[] = [];
  fees: {
    name: string,
    price: number
  }[] = [];
  total: number = 0;

  constructor(private activeFeeService: ActiveFeeService,
              private feeService: FeeService,
              private planService: PlanService,
              private activePlanService: ActivePlanService,
              private authService: AuthenticationService) { }

  async ngOnInit() {
    this.authService.currentUser.subscribe(currUser => {
      this.currentUser = currUser;
    });

    const activePlanResponse = await lastValueFrom(this.activePlanService.findByUserId(this.currentUser!.id));
    for (let activePlan of activePlanResponse.body!) {
      const planResponse = await lastValueFrom(this.planService.findById(activePlan.planId));
      this.total += planResponse.body!.price;
      this.plans.push({
        'name': planResponse.body!.name,
        'price': planResponse.body!.price
      });

      const activeFeeResponse = await lastValueFrom(this.activeFeeService.findByPlanId(activePlan.planId));
      for (let activeFee of activeFeeResponse.body!) {
        const feeResposne = await lastValueFrom(this.feeService.findById(activeFee.feeId));
        if (feeResposne.body!.isPercentage) {
          const feePrice = (feeResposne.body!.amount / 100) * this.total;
          this.fees.push({
            'name': feeResposne.body!.description,
            'price': feePrice
          });
        } else {
          this.fees.push({
            'name': feeResposne.body!.description,
            'price': feeResposne.body!.amount
          });
        }
      }
    }

    for (let fee of this.fees) {
      this.total += fee.price;
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['isStale'].currentValue === true) {
      this.plans = [];
      this.fees = [];
      this.total = 0;
      this.ngOnInit();
    }
    this.changeEvent.emit('');
  }
}
