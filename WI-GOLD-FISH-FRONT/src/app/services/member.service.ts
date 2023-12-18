import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import Member from '../model/Member';
import { MyResponse } from '../model/MyResponse';
import { AlertService } from '../components/alerts/alert-service.service';

@Injectable({
  providedIn: 'root'
})
export class MemberService {
  constructor(private http: HttpClient, private alertService: AlertService) {
    this.findAll();
  }
  public url = 'http://localhost:8080/member';
  public members = new BehaviorSubject<Member[]>([]);
  public pagination = new BehaviorSubject<MyResponse<Member>>({} as MyResponse<Member>);
  public findAll(): void {
    this.http.get<Member[]>(this.url).subscribe(
      (response) => {
        this.members.next(response);
      }
    );
  }
  public save(member: Member): void {
    this.http.post<MyResponse<Member>>(this.url, member).subscribe(
      (response) => {
        this.members.next(this.members.getValue().concat(response.data));
        this.alertService.showMsg('Member saved successfully');
      },
      (error) => {
        this.alertService.showMsg(error.error.message);
      }
    );
  }
}
