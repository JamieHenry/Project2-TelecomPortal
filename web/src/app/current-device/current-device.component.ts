import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-current-device',
  templateUrl: './current-device.component.html',
  styleUrls: ['./current-device.component.css', '../../assets/stylesheets/modal.css']
})
export class CurrentDeviceComponent implements OnInit {
  @Input() number: any;
  @Output() changeEvent = new EventEmitter<string>();

  currentLines: string[] = ["(123) 456-7890", "(456) 789-0123", "(890) 123-4567"];

  constructor() { }

  ngOnInit(): void {
  }

  async removeDevice() {

    this.changeEvent.emit('');
  }

  changeLine(): void {

  }

  showChangeLineModal: boolean = false;

  changeLineModal(): void {
    this.showChangeLineModal = true;
  }

  cancelChangeLine(): void {
    this.showChangeLineModal = false;
  }

  showRemoveDeviceModal: boolean = false;

  removeDeviceModal(): void {
    this.showRemoveDeviceModal = true;
  }

  cancelRemoveDevice(): void {
    this.showRemoveDeviceModal = false;
  }

}
