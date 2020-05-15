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
  mode; //login or register

  userCreateDTO: UserCreateDTO = new UserCreateDTO();
  currUser: UserDTO = new UserDTO();

  test;

  options:string[]= ["true","false"];

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
  modalPopUp(name){
    this.showModal = !this.showModal;
    this.mode = name;

  }

  getTest(){
    this.apiService.getNews().subscribe( response => {
      this.test = response;
    })
  }

  register(){
    console.log(this.userCreateDTO);
    this.userService.registerUser(this.userCreateDTO)
      .subscribe(data => console.log(data));
    this.userCreateDTO = new UserCreateDTO();
    this.showModal = false;
  }
  login(){
    this.userService.login(this.userCreateDTO.userName, this.userCreateDTO.password).subscribe(response => {
      this.currUser = response;
      this.showModal = false;
      location.reload();
    })
  }

}
