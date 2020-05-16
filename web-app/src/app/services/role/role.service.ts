import { Injectable } from '@angular/core';
import {RoleCreateDTO} from "../../dto/role/RoleCreateDTO";
import {RoleDTO} from "../../dto/role/RoleDTO";
import {RoleUpdateDTO} from "../../dto/role/RoleUpdateDTO";
import {Observable, throwError} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  apiURL = 'http://localhost:8080/pa165/rest/roles';

  constructor(private http: HttpClient) { }

  createRole(role: RoleCreateDTO): Observable<Object>{
    return this.http.post(`${this.apiURL}`, role).pipe(catchError(error => {
      return this.handleError(error)}))
  }

  getAllRoles(): Observable<any>{
    return this.http.get(`${this.apiURL}`).pipe(catchError(error => {
      return this.handleError(error)}))
  }

  getRole(id): Observable<any>{
    return this.http.get(`${this.apiURL}/${id}`).pipe(catchError(error => {
      return this.handleError(error)}))
  }

  updateRole(role: RoleUpdateDTO): Observable<any>{
    return this.http.put(`${this.apiURL}`, role).pipe(catchError(error => {
      return this.handleError(error)}))
  }

  deleteRole(id): Observable<any>{
    return this.http.delete(`${this.apiURL}/${id}`).pipe(catchError(error => {
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
