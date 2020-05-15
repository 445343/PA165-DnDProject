import { Component, OnInit } from '@angular/core';
import { TestService } from '../test.service';
import { Observable, throwError } from 'rxjs';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {


  test;

  constructor(
    private apiService: TestService
  ) { }

  ngOnInit(): void {
    this.getTest()
  }

  getTest(){
    this.apiService.getNews().subscribe( response => {
      this.test = response;
    })
  }

}
