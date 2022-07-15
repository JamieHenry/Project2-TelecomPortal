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

  makeDefault: boolean = true;
  makeApple: boolean = false;
  makeRIM: boolean = false;
  makeSamsung: boolean = false;
  makeOther: boolean = false;
  modelOther: boolean = false;

  selectMake(makeValue: string): void {

    this.makeDefault = makeValue == "" ? true : false;
    this.makeApple = makeValue == "Apple" ? true : false;
    this.makeRIM = makeValue == "RIM" ? true : false;
    this.makeSamsung = makeValue == "Samsung" ? true : false;
    this.makeOther = makeValue == "Other" ? true : false;
    this.modelOther = makeValue == "Other" ? true : false;
  }

  selectModel(optionValue: string): void {
    if (optionValue == "Other") {
      this.modelOther = true;
    } else {
      this.modelOther = false;
    }
  }

}
