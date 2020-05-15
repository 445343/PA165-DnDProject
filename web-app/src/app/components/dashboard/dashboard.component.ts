import { Component, OnInit } from '@angular/core';
import { TestService } from '../../test.service';
import { UserService } from '../../services/user/user.service';
import { UserCreateDTO } from '../../dto/user/UserCreateDTO';
import { Observable, throwError } from 'rxjs';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  userCreateDTO: UserCreateDTO = new UserCreateDTO();

  test;
  userName;
  loggedIn;
  options:string[]= ["true","false"];

  constructor(
    private apiService: TestService,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.getTest();
    this.setName();
    this.isLoggedIn();
  }

  getTest(){
    this.apiService.getNews().subscribe( response => {
      this.test = response;
    })
  }

  setName(){
    this.userName = this.userService.getName();
  }
  isLoggedIn(){
    this.loggedIn = this.userService.getLoggedIn();
  }
  register(){
    console.log(this.userCreateDTO);
    this.userService.registerUser(this.userCreateDTO)
      .subscribe(data => console.log(data));
    this.userCreateDTO = new UserCreateDTO();
  }

}
