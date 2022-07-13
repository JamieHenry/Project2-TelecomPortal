import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ManageDevicesComponent } from './manage-devices/manage-devices.component';
import { ManagePlansComponent } from './manage-plans/manage-plans.component';
import { ViewBillComponent } from './view-bill/view-bill.component';

const routes: Routes = [
  {
    path: '', component: HomeComponent
  },
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'register', component: RegisterComponent
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
