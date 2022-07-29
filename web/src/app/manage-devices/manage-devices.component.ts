import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { ActiveNumber } from '../models/active-number.model';
import { Device } from '../models/device.model';
import { User } from '../models/user.model';
import { AuthenticationService } from '../services/authentication.service';
import { DeviceService } from '../services/device.service';
import { UntypedFormBuilder, Validators } from '@angular/forms';
import { UserService } from '../services/user.service';
import { DescriptorService } from '../services/descriptor.service';
import { Descriptor } from '../models/descriptor.model';

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
  showNoAvailableLinesModal: boolean = false;
  addLineError = '';
  addDeviceError = '';
  hasSearched: boolean = false;
  
  currentUser!: User | null;
  currentNumbers: {
    activeNumber: ActiveNumber,
    planName: string
  }[] = [];
  allDevices: {
    device: Device,
    userId: number
  }[] = [];
  filteredDevices: {
    device: Device,
    userId: number
  }[] = [];
  currMakes: string[] = [];
  availableLines: ActiveNumber[] = [];
  selectedAvailableLine: ActiveNumber | null = null;
  searchCriteria: string = '';
  
  constructor(private descriptorService: DescriptorService,
              private fb: UntypedFormBuilder, 
              private deviceService: DeviceService,
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

    for (let activePlan of this.currentUser!.activePlans) {
      for (let activeNumber of activePlan.activeNumbers) {
        if (activeNumber.hasDeviceAssigned) {
          this.currentNumbers.push({
            activeNumber,
            'planName': activePlan.plan.name
          });
        } else {
          this.availableLines.push(activeNumber);
        }
      }
    }

    const deviceResponse = await lastValueFrom(this.deviceService.findAll());

    for(let device of deviceResponse.body!) {
      if (!this.currMakes.includes(device.make)) {
        this.currMakes.push(device.make);
      }
      this.allDevices.push({
        device,
        userId: this.currentUser!.id
      });
    }

    this.filteredDevices = this.allDevices;
    this.isStale = false;
  }

  searchDevices() {
    this.hasSearched = true;
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

  navigate(url: string): void {
    this.router.navigateByUrl(`${url}`);
  }

  onAvailableLineSelected(value: string) {
    if (value === "") {
      this.selectedAvailableLine = null;
      this.addLineError = 'Required';
    } else {
      this.addLineError = '';
    }
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
      this.showNoAvailableLinesModal = true;
    } else {
      this.showAddNewDeviceModal = true;
    }
  }

  async addNewDevice() {
    this.addLineError = '';
    if (this.selectedAvailableLine === null || this.selectedAvailableLine === undefined) {
      this.addLineError = 'Required';
      return;
    }
    const deviceResponse = await lastValueFrom(this.deviceService.save(new Device(0, this.make, this.model, [])));
    const deviceDescriptorResponse = await lastValueFrom(this.descriptorService.findAll());
    let newDeviceDescriptors: any[] = [];
    do {
      let randomDeviceDescriptor = deviceDescriptorResponse.body![this.getRandomInt(10, 29)];
      if(!newDeviceDescriptors.includes(randomDeviceDescriptor)) {
        newDeviceDescriptors.push(randomDeviceDescriptor);
      }
    } while (newDeviceDescriptors.length < 3);
    for (let deviceDescriptor of newDeviceDescriptors) {
      await lastValueFrom(this.descriptorService.saveDeviceDescriptor(deviceResponse.body!.id, deviceDescriptor.id));
    }
    await lastValueFrom(this.deviceService.assignLine(deviceResponse.body!.id, this.selectedAvailableLine.phoneNumber));
    this.cancelAddNewDevice();
    this.resetValues();
  }

  getRandomInt(min: number, max: number) {
    return Math.floor((Math.random() * (max - min)) + min);
  }

  cancelAddNewDevice(): void {
    this.addDeviceForm.reset();
    this.showAddNewDeviceModal = false;
  }

  clearAddDeviceError() {
    this.addDeviceError = '';
  }

  cancelNoAvailableLines() {
    this.showNoAvailableLinesModal = false;
  }

  get make() {
    return this.addDeviceForm.value['make'].trim()!;
  }

  get model() {
    return this.addDeviceForm.value['model'].trim()!;
  }
}
