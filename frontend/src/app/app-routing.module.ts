import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminRegisterComponent } from './authentication/admin-register/admin-register.component';
import { LoginComponent } from './authentication/login/login.component';
import { RegisterComponent } from './authentication/register/register.component';
import { CategoryComponent } from './components/category/category.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { EmployeeComponent } from './components/employee/employee.component';
import { HelpComponent } from './components/help/help.component';
import { NotificationComponent } from './components/notification/notification.component';
import { ProductsComponent } from './components/products/products.component';
import { ProfileComponent } from './components/profile/profile.component';
import { AuthGuard } from './utils/auth.guard';

const routes: Routes = [
  {
    path:'',
    component: DashboardComponent,
    canActivate:[AuthGuard]
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate:[AuthGuard]
  },
  {
    path: 'category',
    component: CategoryComponent,
    canActivate:[AuthGuard]
  },
  {
    path: 'products',
    component: ProductsComponent,
    canActivate:[AuthGuard]
  },
  {
    path: 'notifications',
    component: NotificationComponent,
    canActivate:[AuthGuard]
  },

  {
    path: 'help',
    component: HelpComponent,
    canActivate:[AuthGuard]
  },
  {
    path: 'profile',
    component: ProfileComponent,
    canActivate:[AuthGuard]
  },
  {
    path: 'newAdmin',
    component: AdminRegisterComponent,
    canActivate:[AuthGuard]
  },
  {
    path: 'employee',
    component: EmployeeComponent,
    canActivate:[AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
