import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { Fee } from '../models/fee.model';
import { User } from '../models/user.model';
import { AuthenticationService } from '../services/authentication.service';
import { FeeService } from '../services/fee.service';
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
    price: number,
    activeLines: string[],
    planFees: Fee[],
    subtotal: number
  }[] = [];
  totalFees: Fee[] = [];
  planFees: Fee[][] = [];
  lineFees: Fee[] = [];
  globalFees: Fee[] = [];
  totalFeeAmount: number = 0;
  totalAmount: number = 0;
  totalAmountWithGlobalFees: number = 0;

  constructor(private feeService: FeeService,
              private userService: UserService,
              private authService: AuthenticationService) { }

  async ngOnInit() {
    this.authService.currentUser.subscribe(currUser => {
      this.currentUser = currUser;
    });

    if (!this.authService.isUserLoggedIn()) {
      return;
    }

    this.currentUser = (await lastValueFrom(this.userService.findById(this.currentUser!.id))).body;

    const feeResponse = await lastValueFrom(this.feeService.findAll());

    this.lineFees.push(feeResponse.body![0]);
    this.globalFees.push(feeResponse.body![4]);

    for (let activePlan of this.currentUser!.activePlans) {
      let planFeeRow = [];
      let activeLines = [];
      let subtotal = 0;
      for (let activeNumber of activePlan.activeNumbers) {
        activeLines.push(activeNumber.phoneNumber);
        for (let lineFee of this.lineFees) {
          this.totalFees.push(lineFee);
        }
      }
      this.totalAmount += activePlan.plan.price;
      subtotal += activePlan.plan.price;
      for (let activeFees of activePlan.plan.activeFees) {
        if(activeFees.fee.percentage) {
          activeFees.fee.percentage = false;
          activeFees.fee.amount = (activeFees.fee.amount / 100) * activePlan.plan.price;
          subtotal += activeFees.fee.amount;
        }
        this.totalFees.push(activeFees.fee);
        planFeeRow.push(activeFees.fee);
      }
      this.planFees.push(planFeeRow);
      this.plans.push({
        'name': activePlan.plan.name,
        'price': activePlan.plan.price,
        activeLines,
        'planFees': planFeeRow,
        subtotal
      });
    }

    for (let globalFee of this.globalFees) {
      this.totalFees.push(globalFee);
    }

    for (let fee of this.totalFees) {
      let feePrice;
      if (fee.percentage) {
        feePrice = (fee.amount / 100) * this.totalAmount;
      } else {
        feePrice = fee.amount;
      }
      this.totalFeeAmount += feePrice;
    }

    this.totalAmount += this.totalFeeAmount;
    this.totalAmountWithGlobalFees = this.totalAmount;

    for (let globalFee of this.globalFees) {
      let feePrice;
      if (globalFee.percentage) {
        feePrice = (globalFee.amount / 100) * this.totalAmount;
      } else {
        feePrice = globalFee.amount;
      }
      this.totalAmountWithGlobalFees += feePrice;
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['isStale'].currentValue === true) {
      this.plans = [];
      this.totalFees = [];
      this.lineFees = [];
      this.planFees = [];
      this.globalFees = [];
      this.totalFeeAmount = 0;
      this.totalAmount = 0;
      this.totalAmountWithGlobalFees = 0;
      this.ngOnInit();
    }
    this.changeEvent.emit('');
  }
}
