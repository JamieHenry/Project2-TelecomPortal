import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-device',
  templateUrl: './device.component.html',
  styleUrls: ['./device.component.css']
})
export class DeviceComponent implements OnInit {
  @Input() device: any;

  constructor() { }

  ngOnInit(): void {
    console.log(this.device)
  }

}
