import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { HomeComponent } from './home/home.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ManagePlansComponent } from './manage-plans/manage-plans.component';
import { ManageDevicesComponent } from './manage-devices/manage-devices.component';
import { ViewBillComponent } from './view-bill/view-bill.component';
import { PlanComponent } from './plan/plan.component';
import { DeviceComponent } from './device/device.component';
import { CurrentPlanComponent } from './current-plan/current-plan.component';
import { AvailablePlanComponent } from './available-plan/available-plan.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    NavBarComponent,
    HomeComponent,
    DashboardComponent,
    LoginComponent,
    RegisterComponent,
    ManagePlansComponent,
    ManageDevicesComponent,
    ViewBillComponent,
    PlanComponent,
    DeviceComponent,
    CurrentPlanComponent,
    AvailablePlanComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
