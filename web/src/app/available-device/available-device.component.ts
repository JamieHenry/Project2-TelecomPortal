import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { ActiveNumber } from '../models/active-number.model';
import { ActiveNumberService } from '../services/active-number.service';

@Component({
  selector: 'app-available-device',
  templateUrl: './available-device.component.html',
  styleUrls: ['./available-device.component.css', '../../assets/stylesheets/modal.css']
})
export class AvailableDeviceComponent implements OnInit {
  @Input() device: any;
  @Input() availableLines: ActiveNumber[] = [];
  @Output() changeEvent = new EventEmitter<string>();

  showAddAvailableDeviceModal: boolean = false;

  changeLineSelected = -1;
  addDeviceError = '';

  constructor(private activeNumberService: ActiveNumberService) { }

  ngOnInit(): void {
  }

  onSelected(value: string) {
    if (value === "") {
      this.addDeviceError = 'Required';
      return;
    } else {
      this.addDeviceError = '';
    }
    this.changeLineSelected = parseInt(value);
  }

  addAvailableDeviceModal() {
    this.addDeviceError = '';
    if (this.availableLines.length === 0) {
      this.addDeviceError = 'No available lines';
      return;
    }
    this.showAddAvailableDeviceModal = true;
  }

  cancelAddAvailableDevice() {
    this.addDeviceError = '';
    this.showAddAvailableDeviceModal = false;
  }

  async addAvailableDevice() {
    if (this.changeLineSelected === -1) {
      this.addDeviceError = 'Required'
      return;
    }
    let selectedLine = this.availableLines[this.changeLineSelected];
    selectedLine.hasDeviceAssigned = true;
    selectedLine.deviceId = this.device.device.id;
    await lastValueFrom(this.activeNumberService.save(selectedLine));
    this.showAddAvailableDeviceModal = false;
    this.changeEvent.emit('');
  }
}
