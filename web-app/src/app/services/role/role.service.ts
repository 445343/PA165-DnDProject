import { Injectable } from '@angular/core';
import {RoleCreateDTO} from "../../dto/role/RoleCreateDTO";
import {RoleDTO} from "../../dto/role/RoleDTO";
import {RoleUpdateDTO} from "../../dto/role/RoleUpdateDTO";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  apiURL = 'http://localhost:8080/pa165/rest/roles';

  constructor(private http: HttpClient) { }

  createRole(role: RoleCreateDTO): Observable<Object>{
    return this.http.post(`${this.apiURL}`, role);
  }

  getAllRoles(): Observable<any>{
    return this.http.get(`${this.apiURL}`);
  }

  getRole(id): Observable<any>{
    return this.http.get(`${this.apiURL}/${id}`);
  }

  updateRole(role: RoleUpdateDTO): Observable<any>{
    return this.http.put(`${this.apiURL}`, role);
  }

  deleteRole(id): Observable<any>{
    return this.http.delete(`${this.apiURL}/${id}`);
  }
}
