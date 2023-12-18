import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import Competition from '../model/Competition';
import { MyResponse } from '../model/MyResponse';
import { AlertService } from '../components/alerts/alert-service.service';

@Injectable({
  providedIn: 'root'
})
export class CompetitionService {
  constructor(private http: HttpClient, private alertService: AlertService) {
    this.findAll();
  }
  public url = 'http://localhost:8080/competition';
  public competitions = new BehaviorSubject<Competition[]>([]);
  public pagination = new BehaviorSubject<MyResponse<Competition>>({} as MyResponse<Competition>);
  public filter = 'all';
  public findAll(size = 10, page = 0): void {
    this.http.get<MyResponse<Competition>>(this.url + '?page=' + page + '&size=' + size + '&filter=' + this.filter).subscribe(
      (response) => {
        this.competitions.next(response.content);
        this.pagination.next(response);
      }
    );
  }
  public save(competition: Competition): void {
    this.http.post<MyResponse<Competition>>(this.url, competition).subscribe(
      (response) => {
        this.competitions.next(this.competitions.getValue().concat(response.data));
        this.alertService.showMsg('Competition saved successfully');
      },
      (error) => {
        this.alertService.showMsg(error.error.message);
      }
    );
  }
  public delete(code: string): void {
    this.http.delete<MyResponse<Competition>>(this.url + '/' + code).subscribe(
      (response) => {
        this.competitions.next(this.competitions.getValue().filter((competition) => competition.code !== code));
        this.alertService.showMsg('Competition deleted successfully');
      },
      (error) => {
        this.alertService.showMsg(error.error.message);
      }
    );
  }
  public reafresh(code: string): void {
    this.http.get<Competition>(this.url + '/' + code).subscribe(
      (response) => {
        this.competitions.next(this.competitions.getValue().map(
          (competition) => {
            if (competition.code === code) {
              return response;
            } else {
              return competition;
            }
          }
        ));
      },
      (error) => {
        this.alertService.showMsg(error.error.message);
      }
    );
  }
}
