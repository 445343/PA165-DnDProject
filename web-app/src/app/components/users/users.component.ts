import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user/user.service";
import {UserDTO} from "../../dto/user/UserDTO";
import {Observable} from "rxjs";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  admin = true;
  users: Observable<UserDTO[]>;
  tmp;


  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.loadUsers();
  }
  loadUsers(){
    this.userService.getAllUsers().subscribe( response => {
      this.tmp = response;
      this.users = this.tmp.content;
      console.log(this.users);
    });

  }
  deleteUser(id){
    this.userService.deleteUser(id).subscribe(response =>{
      this.loadUsers()
    });
  }

}
