import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './common/navbar/navbar.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { CategoryComponent } from './components/category/category.component';
import { ProductsComponent} from './components/products/products.component';
import { NotificationComponent } from './components/notification/notification.component';
import { HelpComponent } from './components/help/help.component';
import { LoginComponent } from './authentication/login/login.component';
import { RegisterComponent } from './authentication/register/register.component';

import { JwtInterceptor } from './utils/jwt.interceptor';
import { ErrorInterceptor } from './utils/error.interceptor';
import { YesNoPipe } from './pipe/yes-no.pipe';
import { ProfileComponent } from './components/profile/profile.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { DataTableComponent } from './common/data-table/data-table.component';
import { AdminRegisterComponent } from './authentication/admin-register/admin-register.component';
import { EmployeeComponent } from './components/employee/employee.component';
import { IsResolvedPipe } from './pipe/is-resolved.pipe';

@NgModule({
  declarations: [
    YesNoPipe,
    DataTableComponent,
    AppComponent,
    NavbarComponent,
    DashboardComponent,
    CategoryComponent,
    ProductsComponent,
    NotificationComponent,
    HelpComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    AdminRegisterComponent,
    EmployeeComponent,
    IsResolvedPipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
