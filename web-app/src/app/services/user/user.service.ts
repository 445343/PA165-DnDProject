import { Injectable } from '@angular/core';
import {UserCreateDTO} from "../../dto/user/UserCreateDTO";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

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

  login(name, pass): Observable<any>{
    return this.http.get(`${this.apiURL}/login/${name}/password/${pass}`)

  }
  logout(): Observable<any>{
    return this.http.get(`${this.apiURL}/logout`)
  }

}
