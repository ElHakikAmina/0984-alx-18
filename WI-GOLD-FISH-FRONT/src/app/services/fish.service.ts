import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import Fish from '../model/Fish';
import { MyResponse } from '../model/MyResponse';
import { AlertService } from '../components/alerts/alert-service.service';

@Injectable({
  providedIn: 'root'
})
export class FishService {
  constructor(private http: HttpClient, private alertService: AlertService) {
    this.findAll();
  }
  public url = 'http://localhost:8080/fish';
  public fishs = new BehaviorSubject<Fish[]>([]);
  public pagination = new BehaviorSubject<MyResponse<Fish>>({} as MyResponse<Fish>);
  public findAll(): void {
    this.http.get<Fish[]>(this.url).subscribe(
      (response) => {
        this.fishs.next(response);
      }
    );
  }
  public save(fish: Fish): void {
    this.http.post<MyResponse<Fish>>(this.url, fish).subscribe(
      (response) => {
        this.fishs.next(this.fishs.getValue().concat(response.data));
        this.alertService.showMsg('Fish saved successfully');
      },
      (error) => {
        this.alertService.showMsg(error.error.message);
      }
    );
  }
}
