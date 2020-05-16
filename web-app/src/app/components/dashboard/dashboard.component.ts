import { Component, OnInit } from '@angular/core';
import { TestService } from '../../test.service';
import { UserService } from '../../services/user/user.service';
import { UserCreateDTO } from '../../dto/user/UserCreateDTO';
import { Observable, throwError } from 'rxjs';
import {UserDTO} from "../../dto/user/UserDTO";


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  showModal = false;

  userCreateDTO: UserCreateDTO = new UserCreateDTO();
  currUser: UserDTO = new UserDTO();

  test;

  constructor(
    private apiService: TestService,
    private userService: UserService

  ) { }

  ngOnInit(): void {
    this.getTest();
    this.getCurrentUser();
  }
  getCurrentUser(){
    this.userService.getCurrentUser().subscribe( response => {
      this.currUser = response;
      console.log(this.currUser);
    })

  }

  getTest(){
    this.apiService.getNews().subscribe( response => {
      this.test = response;
    })
  }

  login(){
    this.userService.login(this.userCreateDTO.userName, this.userCreateDTO.password).subscribe(response => {
      this.currUser = response;
      this.showModal = false;
      location.reload();
    })
  }

}
