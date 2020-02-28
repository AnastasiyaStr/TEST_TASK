import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EmployeeComponent } from './employee/employee.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { BasicAuthHtppInterceptorService } from './service/basic-auth-htpp-interceptor.service';
import {EditEmployeeComponent} from './edit-employee/edit-employee.component';
import { DepartmentComponent } from './department/department.component';
import {SearchComponent} from './search/search.component';

@NgModule({
  declarations: [
    AppComponent,
    EmployeeComponent,
    AddEmployeeComponent,
    EditEmployeeComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    LogoutComponent,
    DepartmentComponent,
    SearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
  {
    provide:HTTP_INTERCEPTORS, useClass:BasicAuthHtppInterceptorService, multi:true
  }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
