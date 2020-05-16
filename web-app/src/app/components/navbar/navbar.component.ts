import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user/user.service";
import {UserDTO} from "../../dto/user/UserDTO";


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  showNav = false;
  currUser: UserDTO = new UserDTO();
  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getCurrentUser()
  }

  logOut(){
    this.userService.logout().subscribe(
      response => {
        this.showNav = false;
        location.reload();
      }
    );

  }
  getCurrentUser(){
    this.userService.getCurrentUser().subscribe( response => {
      this.currUser = response;
      console.log(this.currUser);
    })

  }
}
