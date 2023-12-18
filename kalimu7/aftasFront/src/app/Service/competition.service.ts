import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CompetitionService {

  constructor(private http:HttpClient) { }
    private url: String = "http://localhost:8000/";
    
    GetCompetition():Observable<any>{
        return this.http.get(`${this.url}competition`);
    }
  
}
