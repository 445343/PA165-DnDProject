import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TestService {

  apiURL = 'http://localhost:8080/pa165/rest/test';

  constructor(private httpClient: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  insertTestData(): Observable<any>{
    return this.httpClient.post(`${this.apiURL}`, null);
  }

  public getNews(){
    return this.httpClient.get<string>(`${this.apiURL}`)
}
}
