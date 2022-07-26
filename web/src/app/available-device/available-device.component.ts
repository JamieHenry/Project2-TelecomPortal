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

  changeLineSeleted = -1;
  addDeviceError = '';

  constructor(private activeNumberService: ActiveNumberService) { }

  ngOnInit(): void {
    console.log(this.device)
  }

  onSelected(value: string) {
    if (value === '') return;
    this.changeLineSeleted = parseInt(value);
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
    if (this.changeLineSeleted === -1) {
      this.addDeviceError = 'Invalid selection'
      return;
    }
    let selectedLine = this.availableLines[this.changeLineSeleted];
    selectedLine.hasDeviceAssigned = true;
    selectedLine.deviceId = this.device.device.id;
    await lastValueFrom(this.activeNumberService.save(selectedLine));
    this.showAddAvailableDeviceModal = false;
    this.changeEvent.emit('');
  }
}
