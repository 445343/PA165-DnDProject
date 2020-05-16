import { Injectable } from '@angular/core';
import {UserCreateDTO} from "../../dto/user/UserCreateDTO";
import {Observable, throwError} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {catchError} from "rxjs/operators";
import {UserUpdateDTO} from "../../dto/user/UserUpdateDTO";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiURL = 'http://localhost:8080/pa165/rest/users';

  constructor(private http: HttpClient) { }


  registerUser(user: UserCreateDTO): Observable<Object>{
    return this.http.post(`${this.apiURL}`, user).pipe(catchError(error => {
      return this.handleError(error)}))
  }

  getAllUsers(): Observable<any>{
    return this.http.get(`${this.apiURL}`).pipe(catchError(error => {
      return this.handleError(error)}))
  }
  deleteUser(id): Observable<any>{
    return this.http.delete(`${this.apiURL}/${id}`).pipe(catchError(error => {
      return this.handleError(error)}))
  }

  getCurrentUser(): Observable<any>{
    return this.http.get(`${this.apiURL}/current`);
  }

  updateUser(user: UserUpdateDTO): Observable<any>{
    console.log(user);
    return this.http.put(`${this.apiURL}`, user);
  }

  login(name, pass): Observable<any>{
    return this.http.get(`${this.apiURL}/login/${name}/password/${pass}`).pipe(catchError(error => {
      return this.handleError(error)}))

  }
  logout(): Observable<any>{
    return this.http.get(`${this.apiURL}/logout`).pipe(catchError(error => {
      return this.handleError(error)}))
  }

  insertTestData(): Observable<any>{
    return this.http.get(`${this.apiURL}/test`).pipe(catchError(error => {
      return this.handleError(error)}))
  }
  isAdmin(): Observable<any>{
    return this.http.get(`${this.apiURL}/admin`).pipe(catchError(error => {
      return this.handleError(error)}))
  }
  handleError(error):Observable<never> {
    let errorMessage = '';
    if(error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }



}
