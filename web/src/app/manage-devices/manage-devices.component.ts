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

  models: any = { model1: "", model2: "", model3: "", model4: ""};
  modelsDefault: any = { model1: "", model2: "", model3: "", model4: ""};
  modelsApple: any = { model1: "iPhone 11", model2: "iPhone 12", model3: "iPhone 13 Pro Max", model4: "iPhone SE" }
  modelsRIM: any = { model1: "BlackBerry 5G", model2: "BlackBerry KeyOne", model3: "BlackBerry Key2", model4: "BlackBerry Priv" }
  modelsSamsung: any = { model1: "Galaxy A", model2: "Galaxy Note", model3: "Galaxy S", model4: "Galaxy Z" }
  modelsOther: any = { model1: "", model2: "", model3: "", model4: ""};
  
  makeOther: boolean = false;
  modelOther: boolean = false;

  selectMake(optionValue: string): void {

    this.makeOther = false;
    this.modelOther = false;

    switch (optionValue) {
      case "":
        this.models = this.modelsDefault;
        break;
      case "Apple":
        this.models = this.modelsApple;
        break;
      case "RIM":
        this.models = this.modelsRIM;
        break;
      case "Samsung":
        this.models = this.modelsSamsung;
        break;
      case "Other":
        this.makeOther = true;
        this.modelOther = true;
        this.models = this.modelsOther;
        break; 
    }
    
  }

  selectModel(optionValue: string): void {
    if (optionValue == "Other") {
      this.modelOther = true;
    } else {
      this.modelOther = false;
    }
  }

}
