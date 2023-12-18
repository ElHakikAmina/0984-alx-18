import { Component } from '@angular/core';

import { CompetitionService } from '../../services/competition.service';
import Competition from 'src/app/model/Competition';
import { MyResponse } from 'src/app/model/MyResponse';
import AlertProps from 'src/app/components/alerts/AlertProps';
import { Observable } from 'rxjs';
@Component({
  selector: 'app-competition-page',
  templateUrl: './competition-page.component.html',
  styleUrls: ['./competition-page.component.css']
})
export class CompetitionPageComponent {
  constructor(public competitionService: CompetitionService) { }
  public competitions : Observable<Competition[]> = this.competitionService.competitions
  public pagination: MyResponse<Competition> = {} as MyResponse<Competition>;
  ngAfterViewInit(): void {
    // this.competitionService.competitions.subscribe((data) => {
    //   this.competitions = data;
    // });
    this.competitionService.pagination.subscribe((data) => {
      this.pagination = data;
    });
  }
  visible: boolean = false;
}
