import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-manage-devices',
  templateUrl: './manage-devices.component.html',
  styleUrls: ['./manage-devices.component.css']
})
export class ManageDevicesComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  add(): void {
    
  }

  otherMake: boolean = false;

  selectOtherMake(optionValue: string): void {
    if (optionValue == "Other") {
      this.otherMake = true;
    } else {
      this.otherMake = false;
    }
  }

  otherModel: boolean = false;

  selectOtherModel(optionValue: string): void {
    if (optionValue == "Other") {
      this.otherModel = true;
    } else {
      this.otherModel = false;
    }
  }

}
