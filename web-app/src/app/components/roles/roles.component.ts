import { Component, OnInit } from '@angular/core';
import {RoleService} from "../../services/role/role.service";
import {Observable} from "rxjs";
import {RoleDTO} from "../../dto/role/RoleDTO";


@Component({
  selector: 'app-roles',
  templateUrl: './roles.component.html',
  styleUrls: ['./roles.component.css']
})
export class RolesComponent implements OnInit {
  roles: Observable<RoleDTO>;
  tmp;

  constructor(private roleService: RoleService) { }

  ngOnInit(): void {
    this.loadRoles();
  }

  loadRoles(){
    this.roleService.getAllRoles().subscribe(response =>{
      this.tmp = response;
      this.roles = this.tmp.content;
    });
  }

  deleteRole(id){
    this.roleService.deleteRole(id).subscribe(response =>{
      this.loadRoles();
    });
  }

}
