import {Component, OnInit} from '@angular/core';

import {Department, Employee, HttpClientService} from '../service/httpclient.service';

@Component({
  selector: 'app-edit-employee',
  templateUrl: './edit-employee.component.html',
  styleUrls: ['./edit-employee.component.css']
})
export class EditEmployeeComponent implements OnInit {

  employee: Employee = new Employee(' ', ' ', 'NO', ' ', ' ', null);
  updateEmp: Employee = new Employee(null, ' ', 'NO', ' ', ' ', null);
  employees: Employee[];
  departments: Department[];
  selected: Employee;
  pages: Array<number>;
  page: number;
  i: number;
  dep: Department = new Department('', '');

  constructor(
    private httpClientService: HttpClientService
  ) {
  }

  ngOnInit() {
    this.httpClientService.getEmployeeByPage(0).subscribe(
      response => this.handleSuccessfulResponse(response),
    );
    this.httpClientService.getDepartments().subscribe(
      response1 => this.departments = response1,
    );
    this.httpClientService.getEmployeeCount().subscribe(
      response => {
        this.i = response / 10;
        if (this.i % 2 == 0) {
          this.pages = new Array(this.i);
        } else {
          this.pages = new Array(Math.floor(this.i) + 1);
        }
      }
    );
  }

  setPage(i, event: any) {
    event.preventDefault();
    this.page = i;
    this.httpClientService.getEmployeeByPage(i * 10).subscribe(
      response => this.employees = response
    );
  }

  setDepartment(department: Department) {
    this.dep.depId = department.depId;
    this.dep.depName = department.depName;
    this.updateEmp.department = this.dep;
  }

  handleSuccessfulResponse(response) {
    this.employees = response;
  }

  getEmployeeId(id: string) {
    this.httpClientService.getEmployeeById(id).subscribe(
      response => this.updateEmp = response
    );
  }

  updateEmployee(employee: Employee): void {
    this.httpClientService.updateEmployee(employee)
      .subscribe(data => {
        alert('Employee updated successfully.');
        this.httpClientService.getEmployees().subscribe(
          response => this.handleSuccessfulResponse(response),
        );
      });


  };

  click(ev) {
    if (ev.target.value == 'YES') {
      this.updateEmp.active = 'YES';
    }
    if (ev.target.value == 'NO') {
      this.updateEmp.active = 'NO';
    }
  }

  cancelEmp() {
    this.updateEmp.empId = null;
  }
}
