import { Component, OnInit } from '@angular/core';
import { HttpClientService, Employee } from '../service/httpclient.service';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  employees:Employee[];
 pages:Array<number>
page:number;
 i:number;
  constructor(
    private httpClientService:HttpClientService
  ) {
    this.httpClientService.getEmployeeCount().subscribe(
      response => {
        this.i = response/10;
        if(this.i%2==0){
          this.pages= new Array(this.i);
        }
        else {this.pages= new Array(Math.floor(this.i)+1);}
      }
    );
  }
  setPage(i, event: any) {
    event.preventDefault();
    this.page = i;
    this.httpClientService.getEmployeeByPage(i*10).subscribe(
      response => this.employees=response
    );
  }

  ngOnInit() {
     this.httpClientService.getEmployeeByPage(0).subscribe(
       response => this.employees=response
     );
  }

handleSuccessfulResponse(response)
{
    this.employees=response;
}

deleteEmployee(employee: Employee): void {
   this.httpClientService.deleteEmployee(employee)
     .subscribe( data => {
      this.employees = this.employees.filter(u => u !== employee);
   })
};


}
