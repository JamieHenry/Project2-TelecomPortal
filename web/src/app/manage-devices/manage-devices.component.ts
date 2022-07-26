import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { filter, lastValueFrom } from 'rxjs';
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

  addDeviceForm = this.fb.group({
    make: ['', Validators.required],
    model: ['', Validators.required]
  });

  isStale: boolean = false;
  showAddNewDeviceModal: boolean = false;
  addLineError = '';
  addDeviceError = '';

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
  filteredDevices: {
    device: Device,
    userId: number,
    descriptors: string[]
  }[] = [];
  currMakes: string[] = [];
  availableLines: ActiveNumber[] = [];
  selectedAvailableLine: ActiveNumber | null = null;
  searchCriteria: string = '';
  
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
      if (!activeNumber.hasDeviceAssigned) {
        this.availableLines.push(activeNumber);
        continue;
      }
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
      if (!this.currMakes.includes(device.make)) this.currMakes.push(device.make);
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
    this.filteredDevices = this.allDevices;
  }

  searchDevices() {
    this.filteredDevices = this.allDevices;
    if (this.searchCriteria === '') return;
    this.filteredDevices = this.allDevices.filter(device => {
      return device.device.make.toLowerCase().includes(this.searchCriteria.toLowerCase())
              || device.device.model.toLowerCase().includes(this.searchCriteria.toLowerCase());
    });
  }

  resetValues() {
    this.currentNumbers = [];
    this.allDevices = [];
    this.availableLines = [];
    this.ngOnInit();
    this.isStale = true;
  }

  ngAfterViewInit() {
    this.isStale = false;
  }

  navigate(url: string): void {
    this.router.navigateByUrl(`${url}`);
  }

  onAvailableLineSelected(value: string) {
    if (value === '') this.selectedAvailableLine = null;
    this.selectedAvailableLine = this.availableLines[parseInt(value)];
  }

  onDeviceFilterSelected(value: string) {
    this.filteredDevices = this.allDevices;
    if (value === '') return;
    this.filteredDevices = this.allDevices.filter(device => device.device.make === value);
  }

  async addNewDeviceModal() {
    this.addDeviceError = '';
    const deviceResponse = await lastValueFrom(this.deviceService.findByMakeAndModel(this.make, this.model));
    if (deviceResponse.body !== null) {
      this.addDeviceError = 'Device already exists';
      return;
    }
    if (this.availableLines.length === 0) {
      this.addDeviceError = 'No available lines';
      this.addDeviceForm.reset();
      return;
    }
    this.showAddNewDeviceModal = true;
  }

  async addNewDevice() {
    this.addLineError = '';
    if (this.selectedAvailableLine === null || this.selectedAvailableLine === undefined) {
      this.addLineError = 'Invalid line selected';
      return;
    }
    const deviceResponse = await lastValueFrom(this.deviceService.save(new Device(0, this.make, this.model)));
    this.selectedAvailableLine!.hasDeviceAssigned = true;
    this.selectedAvailableLine!.deviceId = deviceResponse.body!.id;
    await lastValueFrom(this.activeNumberService.save(this.selectedAvailableLine!));
    this.cancelAddNewDevice();
    this.resetValues();
  }

  cancelAddNewDevice(): void {
    this.addDeviceForm.reset();
    this.showAddNewDeviceModal = false;
  }

  get make() {
    return this.addDeviceForm.value['make']!;
  }

  get model() {
    return this.addDeviceForm.value['model']!;
  }
}
