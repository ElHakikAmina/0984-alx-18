import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { hunts } from '../types/hunts';
import { huntReq } from '../types/huntReq';

@Injectable({
  providedIn: 'root'
})
export class HuntsService {
  private apiUrl = 'http://localhost:8080/hunts';

  constructor(private httpClient : HttpClient) { }

  get(competitionCode : string | null , memberNumber : number ) : Observable<hunts[]>{
    const url = this.apiUrl + '/' + competitionCode + '/' + memberNumber
    return this.httpClient.get<hunts[]>(url);
  }

  add(hunt : huntReq) : Observable<hunts>{
    const url = this.apiUrl + '/' + 'add'
    return this.httpClient.post<hunts>(url,hunt);
  }
  increment(huntId : number) : Observable<hunts>{
    const url = this.apiUrl + '/' + 'increment' + '/' +huntId;
    return this.httpClient.put<hunts>(url,huntId);
  }
  decrement(huntId : number) : Observable<hunts>{
    const url = this.apiUrl + '/' + 'decrement' + '/' +huntId;
    return this.httpClient.put<hunts>(url,huntId);
  }
  delete(huntId : number) : Observable<hunts>{
    const url = this.apiUrl + '/' + 'delete' + '/' +huntId;
    return this.httpClient.delete<hunts>(url);
  }
}
