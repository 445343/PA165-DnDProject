import { Injectable } from '@angular/core';
import {Observable, throwError} from "rxjs";
import { retry, catchError} from 'rxjs/operators';
import { map } from 'rxjs/operators';

import {HttpClient} from "@angular/common/http";
import {HeroCreateDTO} from "../../dto/hero/HeroCreateDTO";
import {HeroUpdateDTO} from "../../dto/hero/HeroUpdateDTO";

@Injectable({
  providedIn: 'root'
})
export class HeroService {

  apiURL = 'http://localhost:8080/pa165/rest/heroes';

  constructor(private http: HttpClient) { }

  createHero(hero: HeroCreateDTO): Observable<Object>{
    return this.http.post(`${this.apiURL}`, hero).pipe(catchError(error => {
      return this.handleError(error)}))
  }

  getAllHeroes(): Observable<any>{
    return this.http.get(`${this.apiURL}`).pipe(catchError(error => {
      return this.handleError(error)}))
  }

  getHero(id): Observable<any>{
    return this.http.get(`${this.apiURL}/${id}`).pipe(catchError(error => {
      return this.handleError(error)}))
  }

  updateHero(hero: HeroUpdateDTO): Observable<any>{
    return this.http.put(`${this.apiURL}`, hero).pipe(catchError(error => {
      return this.handleError(error)}))

  }

  deleteHero(id): Observable<any>{
    return this.http.delete(`${this.apiURL}/${id}`).pipe(catchError(error => {
      return this.handleError(error)}))
  }
  addRoleToHero(heroId, roleId):Observable<any>{
    return this.http.put(`${this.apiURL}/${heroId}/roles/${roleId}/add`, null).pipe(catchError(error => {
      return this.handleError(error)}))
  }

  removeRoleFromHero(heroId, roleId):Observable<any>{
    return this.http.put(`${this.apiURL}/${heroId}/roles/${roleId}/remove`, null).pipe(catchError(error => {
      return this.handleError(error)}))
  }

  joinTroop(heroId, troopId):Observable<any>{
    return this.http.put(`${this.apiURL}/${heroId}/troops/${troopId}/add`, null).pipe(catchError(error => {
      return this.handleError(error)}))
  }

  leaveTroop(heroId):Observable<any>{
    return this.http.put(`${this.apiURL}/${heroId}/troops/remove`, null).pipe(catchError(error => {
      return this.handleError(error)}))
  }

  listRolesNotInHero(heroId):Observable<any>{
    return this.http.get(`${this.apiURL}/${heroId}/roles/other`).pipe(catchError(err => {
      return this.handleError(err)
    }))
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
