import { Injectable } from '@angular/core';
import {Observable, throwError} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {TroopCreateDTO} from "../../dto/troop/TroopCreateDTO";
import {TroopUpdateDTO} from "../../dto/troop/TroopUpdateDTO";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class TroopService {

  apiURL = 'http://localhost:8080/pa165/rest/troops';

  constructor(private http: HttpClient) { }

  createTroop(troop: TroopCreateDTO): Observable<Object>{
    return this.http.post(`${this.apiURL}`, troop).pipe(catchError(error => {
      return this.handleError(error)}))
  }

  getAllTroops(): Observable<any>{
    return this.http.get(`${this.apiURL}`).pipe(catchError(error => {
      return this.handleError(error)}))
  }

  getTroop(id): Observable<any>{
    return this.http.get(`${this.apiURL}/${id}`).pipe(catchError(error => {
      return this.handleError(error)}))
  }

  updateTroop(troop: TroopUpdateDTO): Observable<any>{
    return this.http.put(`${this.apiURL}`, troop).pipe(catchError(error => {
      return this.handleError(error)}))
  }

  deleteTroop(id): Observable<any>{
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
