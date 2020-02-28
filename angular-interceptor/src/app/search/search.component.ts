import { Component, OnInit } from '@angular/core';
import {Employee, HttpClientService} from '../service/httpclient.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
public search1:string;
  employees:Employee[];
  constructor(private httpClientService: HttpClientService) { }

  ngOnInit() {
  }
search() {
  this.httpClientService.getEmployeeByName(this.search1).subscribe(
    response => this.employees = response,
  );
}
}
