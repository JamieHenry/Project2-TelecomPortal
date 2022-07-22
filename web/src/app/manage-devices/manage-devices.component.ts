import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { ActiveNumber } from '../models/active-number.model';
import { Device } from '../models/device.model';
import { User } from '../models/user.model';
import { ActiveDeviceDescriptorService } from '../services/active-device-descriptor.service';
import { ActiveNumberService } from '../services/active-number.service';
import { ActivePlanService } from '../services/active-plan.service';
import { AuthenticationService } from '../services/authentication.service';
import { DescriptorService } from '../services/descriptor.service';
import { DeviceService } from '../services/device.service';
import { PlanService } from '../services/plan.service';

@Component({
  selector: 'app-manage-devices',
  templateUrl: './manage-devices.component.html',
  styleUrls: ['./manage-devices.component.css']
})
export class ManageDevicesComponent implements OnInit {

  isStale: boolean = false;

  currentUser!: User | null;
  currentNumbers: {
    activeNumber: ActiveNumber,
    device: Device,
    planName: string
  }[] = [];
  allDevices: {
    device: Device,
    userId: number,
    descriptors: string[]
  }[] = [];

  constructor(private descriptorService: DescriptorService,
              private activeDeviceDescriptorService: ActiveDeviceDescriptorService,
              private planService: PlanService,
              private activePlanService: ActivePlanService,
              private deviceService: DeviceService,
              private activeNumberService: ActiveNumberService,
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

    // active numbers
    const activeNumbersResponse = await lastValueFrom(this.activeNumberService.findByUserId(this.currentUser!.id));
    for (let activeNumber of activeNumbersResponse.body!) {
      const deviceResponse = await lastValueFrom(this.deviceService.findById(activeNumber.deviceId));
      const activePlanResponse = await lastValueFrom(this.activePlanService.findById(activeNumber.activePlanId));
      const planResponse = await lastValueFrom(this.planService.findById(activePlanResponse.body!.planId));
      this.currentNumbers.push({
        'activeNumber': activeNumber,
        'device': deviceResponse.body!,
        'planName': planResponse.body!.name
      });
    }

    // all devices
    const deviceResponse = await lastValueFrom(this.deviceService.findAll());
    for (let device of deviceResponse.body!) {
      const activeNumberDescriptors = [];
      const deviceDescriptorResponse = await lastValueFrom(this.activeDeviceDescriptorService.findByDeviceId(device.id));
      for (let deviceDescriptor of deviceDescriptorResponse.body!) {
        const descriptorResponse = await lastValueFrom(this.descriptorService.findById(deviceDescriptor.descriptorId));
        activeNumberDescriptors.push(descriptorResponse.body!.description);
      }
      this.allDevices.push({
        'device': device,
        'userId': this.currentUser.id,
        'descriptors': activeNumberDescriptors
      });
    }
  }

  resetValues() {
    this.currentNumbers = [];
    this.allDevices = [];
    this.ngOnInit();
    this.isStale = true;
  }

  resetIsStale() {
    this.isStale = false;
  }

  navigate(url: string): void {
    this.router.navigateByUrl(`${url}`);
  }

  add(): void {
    
  }

  models: any = { model1: "", model2: "", model3: "", model4: ""};
  modelsDefault: any = { model1: "", model2: "", model3: "", model4: ""};
  modelsApple: any = { model1: "iPhone 11", model2: "iPhone 12", model3: "iPhone 13 Pro Max", model4: "iPhone SE" }
  modelsRIM: any = { model1: "BlackBerry 5G", model2: "BlackBerry KeyOne", model3: "BlackBerry Key2", model4: "BlackBerry Priv" }
  modelsSamsung: any = { model1: "Galaxy A", model2: "Galaxy Note", model3: "Galaxy S", model4: "Galaxy Z" }
  modelsOther: any = { model1: "", model2: "", model3: "", model4: ""};
  
  makeOther: boolean = false;
  modelOther: boolean = false;

  selectMake(optionValue: string): void {

    this.makeOther = false;
    this.modelOther = false;

    switch (optionValue) {
      case "":
        this.models = this.modelsDefault;
        break;
      case "Apple":
        this.models = this.modelsApple;
        break;
      case "RIM":
        this.models = this.modelsRIM;
        break;
      case "Samsung":
        this.models = this.modelsSamsung;
        break;
      case "Other":
        this.makeOther = true;
        this.modelOther = true;
        this.models = this.modelsOther;
        break; 
    }
    
  }

  selectModel(optionValue: string): void {
    if (optionValue == "Other") {
      this.modelOther = true;
    } else {
      this.modelOther = false;
    }
  }

}
