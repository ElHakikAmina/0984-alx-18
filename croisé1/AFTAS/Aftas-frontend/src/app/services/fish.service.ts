import { Injectable } from '@angular/core';
import { Observable  } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Fish } from '../types/fish';


@Injectable({
  providedIn: 'root'
})
export class FishService {

  private apiUrl = 'http://localhost:8080/fishes';

  constructor(private http : HttpClient) { }

  getFishes() : Observable<Fish[]>{
    let listURL : string = `${this.apiUrl}/random`;
    return this.http.get<Fish[]>(listURL);
  }
  getAll() : Observable<Fish[]>{
    let listURL : string = `${this.apiUrl}/list`;
    return this.http.get<Fish[]>(listURL);
  }
}
