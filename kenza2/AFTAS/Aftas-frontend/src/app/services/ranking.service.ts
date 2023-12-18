import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Ranking } from '../types/ranking';
import { Observable } from 'rxjs';
import { RankingReq } from '../types/rankingReq';

@Injectable({
  providedIn: 'root'
})
export class RankingService {
  private apiUrl = 'http://localhost:8080/rankings';

  constructor(private httpClient : HttpClient) { }
  getMembers(code : string | null): Observable<Ranking[]>{
    const url : string = `${this.apiUrl}/Competition/${code}`;
    return this.httpClient.get<Ranking[]>(url);
  }

  Add(ranking : RankingReq) : Observable<Ranking>{
    const url : string = `${this.apiUrl}/add`;
    return this.httpClient.post<Ranking>(url, ranking);
  }
  Delete(code : string |undefined , num : number) : Observable<Ranking>{
    console.log("Delete")
    const url : string = `${this.apiUrl}/Delete/${code}/${num}`;
    return this.httpClient.delete<Ranking>(url);
  }
  Get(code : string |undefined , num : number) : Observable<Ranking>{
    console.log("Delete")
    const url : string = `${this.apiUrl}/${code}/${num}`;
    return this.httpClient.get<Ranking>(url);
  }
  calculate(competitionCode: string |undefined) : Observable<Ranking[]>{
    const url : string = `${this.apiUrl}/calculate/${competitionCode}`;
    return this.httpClient.put<Ranking[]>(url,competitionCode);
  } 
  podium(competitionCode: string |undefined) : Observable<Ranking[]>{
    const url : string = `${this.apiUrl}/podium/${competitionCode}`;
    return this.httpClient.get<Ranking[]>(url);
  }
}
