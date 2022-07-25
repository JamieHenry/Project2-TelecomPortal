import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-available-device',
  templateUrl: './available-device.component.html',
  styleUrls: ['./available-device.component.css', '../../assets/stylesheets/modal.css']
})
export class AvailableDeviceComponent implements OnInit {
  @Input() device: any;
  @Output() changeEvent = new EventEmitter<string>();

  currentLines: string[] = ["(123) 456-7890", "(456) 789-0123", "(890) 123-4567"]

  constructor() { }

  ngOnInit(): void {
  }

  showAddAvailableDeviceModal: boolean = false;

  addAvailableDeviceModal() {
    this.showAddAvailableDeviceModal = true;
  }

  cancelAddAvailableDevice() {
    this.showAddAvailableDeviceModal = false;
  }

  addAvailableDevice() {

  }

}
