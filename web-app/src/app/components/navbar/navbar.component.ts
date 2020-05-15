import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user/user.service";


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  showNav = false;
  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  logOut(){
    this.userService.logout().subscribe(
      response => {
        this.showNav = false;
        location.reload();
      }
    );

  }
}
