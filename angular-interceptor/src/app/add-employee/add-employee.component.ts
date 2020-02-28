import {Component, OnInit} from '@angular/core';
import {Department, Employee, HttpClientService} from '../service/httpclient.service';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {
  departments: Department[];
  dep: Department = new Department('', '');
  employee: Employee = new Employee('', '', 'NO', '', '', null);
  selected: Employee;

  constructor(
    private httpClientService: HttpClientService
  ) {
  }

  ngOnInit() {
    this.httpClientService.getDepartments().subscribe(
      response => this.handleSuccessfulResponse(response),
    );
  }

  handleSuccessfulResponse(response) {
    this.departments = response;
  }

  setDepartment(department: string) {
    this.dep.depId = department;
    this.employee.department = this.dep;
  }

  createEmployee(): void {
    this.httpClientService.createEmployee(this.employee)
      .subscribe(data => {
        alert('Employee created successfully.');
      });

  };

  click(ev) {
    if (ev.target.value == 'YES') {
      this.employee.active = 'YES';
    }
    if (ev.target.value == 'NO') {
      this.employee.active = 'NO';
    }
  }

  cancelEmp() {
    this.employee.name = "";
  }
}
