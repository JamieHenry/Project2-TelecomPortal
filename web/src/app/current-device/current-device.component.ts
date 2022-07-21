import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-current-device',
  templateUrl: './current-device.component.html',
  styleUrls: ['./current-device.component.css']
})
export class CurrentDeviceComponent implements OnInit {
  @Input() number: any;
  @Output() changeEvent = new EventEmitter<string>();

  constructor() { }

  ngOnInit(): void {
  }

  async removeDevice() {

    this.changeEvent.emit('');
  }
}
