import { Injectable } from '@angular/core';
import {UserCreateDTO} from "../../dto/user/UserCreateDTO";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {TroopUpdateDTO} from "../../dto/troop/TroopUpdateDTO";
import {UserUpdateDTO} from "../../dto/user/UserUpdateDTO";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiURL = 'http://localhost:8080/pa165/rest/users';

  constructor(private http: HttpClient) { }


  registerUser(user: UserCreateDTO): Observable<Object>{
    return this.http.post(`${this.apiURL}`, user);
  }

  getAllUsers(): Observable<any>{
    return this.http.get(`${this.apiURL}`);
  }
  deleteUser(id): Observable<any>{
    return this.http.delete(`${this.apiURL}/${id}`)
  }

  getCurrentUser(): Observable<any>{
    return this.http.get(`${this.apiURL}/current`)
  }

  updateUser(user: UserUpdateDTO): Observable<any>{
    console.log(user);
    return this.http.put(`${this.apiURL}`, user);
  }

  login(name, pass): Observable<any>{
    return this.http.get(`${this.apiURL}/login/${name}/password/${pass}`)

  }
  logout(): Observable<any>{
    return this.http.get(`${this.apiURL}/logout`)
  }

}
