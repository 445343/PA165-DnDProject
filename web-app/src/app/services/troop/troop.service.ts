import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {TroopCreateDTO} from "../../dto/troop/TroopCreateDTO";
import {TroopUpdateDTO} from "../../dto/troop/TroopUpdateDTO";

@Injectable({
  providedIn: 'root'
})
export class TroopService {

  apiURL = 'http://localhost:8080/pa165/rest/troops';

  constructor(private http: HttpClient) { }

  createTroop(troop: TroopCreateDTO): Observable<Object>{
    return this.http.post(`${this.apiURL}`, troop);
  }

  getAllTroops(): Observable<any>{
    return this.http.get(`${this.apiURL}`);
  }

  getTroop(id): Observable<any>{
    return this.http.get(`${this.apiURL}/${id}`);
  }

  updateTroop(troop: TroopUpdateDTO): Observable<any>{
    return this.http.put(`${this.apiURL}`, troop);
  }

  deleteTroop(id): Observable<any>{
    return this.http.delete(`${this.apiURL}/${id}`);
  }
}
