import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Member } from '../types/member';
import { Observable } from 'rxjs';
import { MemberReq } from '../types/MemberReq';

@Injectable({
  providedIn: 'root'
})
export class MemberService {

  private apiUrl = 'http://localhost:8080/members';


  constructor(private httpClient : HttpClient) { }

  getMembers() : Observable<Member[]> {
    const url = this.apiUrl + '/list';
    return this.httpClient.get<Member[]>(url);
  }
  add(member : MemberReq) : Observable<Member>{
    const url = this.apiUrl + '/add';
    return this.httpClient.post<Member>(url,member);
  }
  get(num : number) : Observable<Member>{
    const url = this.apiUrl + '/' + num;
    return this.httpClient.get<Member>(url);
  }
}
