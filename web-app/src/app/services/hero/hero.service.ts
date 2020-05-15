import { Injectable } from '@angular/core';
import {HeroDTO} from "../../dto/hero/HeroDTO";
import {Observable} from "rxjs";
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
    return this.http.post(`${this.apiURL}`, hero);
  }

  getAllHeroes(): Observable<any>{
    return this.http.get(`${this.apiURL}`);
  }

  getHero(id): Observable<any>{
    return this.http.get(`${this.apiURL}/${id}`);
  }

  updateHero(hero: HeroUpdateDTO): Observable<any>{
    return this.http.put(`${this.apiURL}`, hero);
  }

  deleteHero(id): Observable<any>{
    return this.http.delete(`${this.apiURL}/${id}`);
  }
  addRoleToHero(heroId, roleId):Observable<any>{
    return this.http.put(`${this.apiURL}/${heroId}/roles/${roleId}/add`, null);
  }
}
