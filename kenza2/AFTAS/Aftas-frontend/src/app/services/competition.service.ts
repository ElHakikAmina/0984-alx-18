import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Competition } from '../types/competition';
import { Observable } from 'rxjs';
const httpOptions = {
  headers : new HttpHeaders({
    'content-type': 'application/json'
  }) 
}
@Injectable({
  providedIn: 'root'
})
export class CompetitionService {

  private apiUrl = 'http://localhost:8080/competitions';

  constructor(private httpClient : HttpClient) { }

  getCompetitions(pageNumber: number, pageSize: number) : Observable<Competition[]>{
    let listURL : string = `${this.apiUrl}/list?page=${pageNumber}&size=${pageSize}`;
    return this.httpClient.get<Competition[]>(listURL);
  }

  getTotalPagesNumber(pageSize : number) : Observable<number>{
    let pagesURL : string = `${this.apiUrl}/pages/${pageSize}`;
    return this.httpClient.get<number>(pagesURL);
  }

  addCompetition(competition : Competition | undefined) : Observable<Competition>{
    let addURL : string = `${this.apiUrl}/add`;
    return this.httpClient.post<Competition>(addURL,competition,httpOptions);
  }

  getCompetition(code : string | null) : Observable<Competition>{
    let url : string = `${this.apiUrl}/${code}`;
    return this.httpClient.get<Competition>(url);
  }
  delete(code : string |undefined) : Observable<Competition>{
    let url : string = `${this.apiUrl}/delete/${code}`;
    return this.httpClient.delete<Competition>(url);
  }
}
