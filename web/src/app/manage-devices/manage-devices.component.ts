import { Component, Input, OnInit } from '@angular/core';
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
import { UntypedFormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-manage-devices',
  templateUrl: './manage-devices.component.html',
  styleUrls: ['./manage-devices.component.css', '../../assets/stylesheets/modal.css']
})
export class ManageDevicesComponent implements OnInit {
  @Input() device: any;

  addDevice = this.fb.group({
    make: ['', Validators.required],
    model: ['', Validators.required]
  });

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

  currentDevices: any = [];
  currentLines: string[] = ["(123) 456-7890", "(456) 789-0123", "(890) 123-4567"];

  constructor(private fb: UntypedFormBuilder, 
              private descriptorService: DescriptorService,
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

  showAddNewDeviceModal: boolean = false;

  addNewDeviceModal(): void {
    this.showAddNewDeviceModal = true;
  }

  addNewDevice(): void {

  }

  cancelAddNewDevice(): void {
    this.showAddNewDeviceModal = false;
  }

}
