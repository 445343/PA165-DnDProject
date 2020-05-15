import { Injectable } from '@angular/core';
import {UserCreateDTO} from "../../dto/user/UserCreateDTO";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiURL = 'http://localhost:8080/pa165/rest/users';
  userName = "testName";
  loggedIn = false;

  constructor(private http: HttpClient) { }

  getName(){
    return this.userName;
  }

  getLoggedIn(){
    return this.loggedIn;
  }

  registerUser(user: UserCreateDTO): Observable<Object>{
    return this.http.post(`${this.apiURL}`, user);
  }

  getAllUsers(): Observable<any>{
    return this.http.get(`${this.apiURL}`);
  }
  deleteUser(id): Observable<any>{
    return this.http.delete(`${this.apiURL}/${id}`)
  }
}
