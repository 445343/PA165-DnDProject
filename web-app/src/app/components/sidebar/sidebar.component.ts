import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user/user.service";
import {UserDTO} from "../../dto/user/UserDTO";



@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  isAdmin:boolean = false;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getAdminStatus();

  }

  getAdminStatus(){
    this.userService.isAdmin().subscribe( response => {
      this.isAdmin = response;
      console.log(this.isAdmin);
    })

  }

}
