import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { ActiveNumber } from '../models/active-number.model';
import { ActiveNumberService } from '../services/active-number.service';

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

  constructor(private activeNumberService: ActiveNumberService) { }

  ngOnInit(): void {
  }

  onSelected(value: string) {
    if (value === '') return;
    this.changeLineSeleted = parseInt(value);
  }

  async removeDevice() {
    this.number.activeNumber.hasDeviceAssigned = false;
    await lastValueFrom(this.activeNumberService.save(this.number.activeNumber));
    this.changeEvent.emit('');
  }

  async changeLine() {
    this.changeLineError = '';
    if (this.changeLineSeleted === -1) {
      this.changeLineError = 'Invalid selection'
      return;
    }
    let selectedLine = this.availableLines[this.changeLineSeleted];
    this.number.activeNumber.hasDeviceAssigned = false;
    await lastValueFrom(this.activeNumberService.save(this.number.activeNumber));
    selectedLine.hasDeviceAssigned = true;
    selectedLine.deviceId = this.number.device.id;
    await lastValueFrom(this.activeNumberService.save(selectedLine));
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
