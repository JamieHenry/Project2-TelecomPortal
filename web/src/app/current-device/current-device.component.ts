import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { ActiveNumber } from '../models/active-number.model';
import { DeviceService } from '../services/device.service';

@Component({
  selector: 'app-current-device',
  templateUrl: './current-device.component.html',
  styleUrls: ['./current-device.component.css', '../../assets/stylesheets/modal.css']
})
export class CurrentDeviceComponent implements OnInit {
  @Input() number: any;
  @Input() availableLines: ActiveNumber[] = [];
  @Output() changeEvent = new EventEmitter<string>();

  showRemoveDeviceModal: boolean = false;
  showChangeLineModal: boolean = false;

  changeLineSeleted = -1;
  changeLineError = '';

  constructor(private deviceService: DeviceService) { }

  ngOnInit(): void {
  }

  onSelected(value: string) {
    if (value === '') return;
    this.changeLineSeleted = parseInt(value);
  }

  async removeDevice() {
    await lastValueFrom(this.deviceService.removeLine(this.number.activeNumber.phoneNumber));
    this.changeEvent.emit('');
  }

  async changeLine() {
    this.changeLineError = '';
    if (this.changeLineSeleted === -1) {
      this.changeLineError = 'Invalid selection'
      return;
    }
    let selectedLine = this.availableLines[this.changeLineSeleted];
    await lastValueFrom(this.deviceService.removeLine(this.number.activeNumber.phoneNumber));
    await lastValueFrom(this.deviceService.assignLine(this.number.activeNumber.device.id, selectedLine.phoneNumber));
    this.changeEvent.emit('');
  }

  changeLineModal(): void {
    this.changeLineError = '';
    if (this.availableLines.length === 0) {
      this.changeLineError = 'No available lines';
      return;
    }
    this.showChangeLineModal = true;
  }

  cancelChangeLine(): void {
    this.changeLineError = '';
    this.showChangeLineModal = false;
  }

  removeDeviceModal(): void {
    this.showRemoveDeviceModal = true;
  }

  cancelRemoveDevice(): void {
    this.changeLineError = '';
    this.showRemoveDeviceModal = false;
  }

}
