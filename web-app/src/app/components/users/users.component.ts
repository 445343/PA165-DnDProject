import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user/user.service";
import {UserDTO} from "../../dto/user/UserDTO";
import {Observable} from "rxjs";
import {TroopUpdateDTO} from "../../dto/troop/TroopUpdateDTO";
import {TroopCreateDTO} from "../../dto/troop/TroopCreateDTO";
import {UserUpdateDTO} from "../../dto/user/UserUpdateDTO";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  isAdmin:boolean = false;
  users: UserDTO[];
  tmp;

  userUpdateDTO: UserUpdateDTO;

  clickedUserId: number;
  clickedUser: UserDTO;

  showHeroesModal = false;
  showUpdateUserModal = false;

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.getAdminStatus();
    this.loadUsers();
    this.userUpdateDTO = new UserUpdateDTO();
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

  updateUser() {
    this.userService.updateUser(this.userUpdateDTO)
      .subscribe(data => {
        this.loadUsers();
      });
    this.userUpdateDTO = new UserUpdateDTO();
    this.closeUpdateUserModal();
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

  updateUserModal(id,username,isAdmin) {
    this.userUpdateDTO.id = id;
    this.userUpdateDTO.username = username;
    this.userUpdateDTO.isAdmin = isAdmin;
    this.showUpdateUserModal = true;
  }

  closeUpdateUserModal() {
    this.clickedUserId = 0;
    this.showUpdateUserModal = false;
  }
}
