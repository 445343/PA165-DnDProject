import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user/user.service";
import {UserDTO} from "../../dto/user/UserDTO";
import {Observable} from "rxjs";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  isAdmin:boolean = false;
  users: UserDTO[];
  tmp;

  clickedUserId: number;
  clickedUser: UserDTO;

  showHeroesModal = false;

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.getAdminStatus();
    this.loadUsers();
  }
  getAdminStatus(){
    this.userService.isAdmin().subscribe( response => {
      this.isAdmin = response;
      console.log(this.isAdmin);
    })

  }
  loadUsers() {
    this.userService.getAllUsers().subscribe(response => {
      this.tmp = response;
      this.users = this.tmp.content;
    });

  }

  deleteUser(id) {
    this.userService.deleteUser(id).subscribe(response => {
      this.loadUsers()
    });
  }

  loadClickedUser() {
    this.clickedUser = this.users.find(({id}) => this.clickedUserId == id);
  }

  heroesModal(id) {
    this.clickedUserId = id;
    this.loadClickedUser();
    this.showHeroesModal = true;
  }

  closeHeroesModal() {
    this.clickedUserId = 0;
    this.showHeroesModal = false;
  }
}
