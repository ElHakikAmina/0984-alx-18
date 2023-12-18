import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import Ranking from '../model/Ranking';
import { MyResponse } from '../model/MyResponse';
import { AlertService } from '../components/alerts/alert-service.service';
import { CompetitionService } from './competition.service';
import { MemberService } from './member.service';

@Injectable({
  providedIn: 'root'
})
export class RankingService {
  constructor(private http: HttpClient, private alertService: AlertService, public competitionService: CompetitionService, public memberService: MemberService) {
    this.findAll();
  }
  public url = 'http://localhost:8080/ranking';
  public rankings = new BehaviorSubject<Ranking[]>([]);
  public pagination = new BehaviorSubject<MyResponse<Ranking>>({} as MyResponse<Ranking>);
  public findAll(): void {
    this.http.get<Ranking[]>(this.url).subscribe(
      (response) => {
        this.rankings.next(response);
      }
    );
  }
  public save(ranking: Ranking): void {
    this.http.post<MyResponse<Ranking>>(this.url, ranking).subscribe(
      (response) => {
        this.rankings.next(this.rankings.getValue().concat(response.data));
        this.competitionService.reafresh(ranking.competition_id);
      },
      (error) => {
        this.alertService.showMsg(error.error.message);
      }
    );
  }
  public findByMemberIdAndCompetitionId(memberId: number, competitionId: string): Ranking {
    this.http.get<Ranking>(this.url + '/' + memberId + '/' + competitionId).subscribe(
      (response) => {
        return response;
      },
      (error) => {
        this.alertService.showMsg(error.error.message);
        return {} as Ranking;
      }
    );
    return {} as Ranking;
  }
  public getPodium(competitionId: string): Observable<Ranking[]> {
    return this.http.get<Ranking[]>(this.url + '/competition/' + competitionId);
  }
}
