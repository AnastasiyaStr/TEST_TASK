import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

export class Employee {
  constructor(
    public empId: string,
    public name: string,
    public active: string,
    public designation: string,
    public salary: string,
    public  department:Department
  ) { }
}
export class Department {
  constructor(
    public depId: string,
    public depName: string
  ) { }
}
@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(
    private httpClient: HttpClient
  ) {
  }
  getDepartments() {
    return this.httpClient.get<Department[]>('http://localhost:8080/employee/departments');
  }

  getEmployees() {
    return this.httpClient.get<Employee[]>('http://localhost:8080/employee');
  }

  public deleteEmployee(employee) {
    return this.httpClient.delete<Employee>("http://localhost:8080/employee" + "/" + employee.empId);
  }

  public createEmployee(employee) {
    return this.httpClient.post<Employee>("http://localhost:8080/employee", employee);
  }

  public updateEmployee(employee) {
    return this.httpClient.put<Employee>("http://localhost:8080/employee", employee);
  }
  public getEmployeeById(employee) {
    return this.httpClient.get<Employee>("http://localhost:8080/employee"+ "/" + employee);
  }
  public getEmployeeCount() {
    return this.httpClient.get<number>("http://localhost:8080/employee/count");
  }

  public getEmployeeByPage(page:number) {
    return this.httpClient.get<Employee[]>("http://localhost:8080/employee/page?page="+page);
  }
  public getEmployeeByName(name:string) {
    return this.httpClient.get<Employee[]>("http://localhost:8080/employee/search?name="+name);
  }
}
