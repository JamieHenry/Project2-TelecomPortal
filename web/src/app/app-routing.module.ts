import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeComponent } from './home/home.component';
import { LoginRegisterComponent } from './login-register/login-register.component';
import { ManageDevicesComponent } from './manage-devices/manage-devices.component';
import { ManagePlansComponent } from './manage-plans/manage-plans.component';
import { ViewBillComponent } from './view-bill/view-bill.component';

const routes: Routes = [
  {
    path: 'home', component: HomeComponent
  },
  {
    path: 'loginRegister', component: LoginRegisterComponent
  },
  {
    path: 'dashboard', component: DashboardComponent
  },
  {
    path: 'managePlans', component: ManagePlansComponent
  },
  {
    path: 'manageDevices', component: ManageDevicesComponent
  },
  {
    path: 'viewBill', component: ViewBillComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
