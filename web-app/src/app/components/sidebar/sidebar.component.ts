import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user/user.service";



@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  isAdmin = true;

  constructor(private userService: UserService) { }

  ngOnInit(): void {

  }

}
