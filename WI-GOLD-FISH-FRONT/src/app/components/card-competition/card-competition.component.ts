import { Component, Input } from '@angular/core';
import Competition from 'src/app/model/Competition';
import { PopUpMessageComponent } from '../alerts/pop-up-message/pop-up-message.component';
import { CompetitionService } from 'src/app/services/competition.service';
import { AlertService } from '../alerts/alert-service.service';
import Member from 'src/app/model/Member';
import { Time } from '@angular/common';

@Component({
  selector: 'app-card-competition',
  templateUrl: './card-competition.component.html',
  styleUrls: ['./card-competition.component.css'],
})
export class CardCompetitionComponent {
  @Input() competition: Competition = {} as Competition;
  member: Member = {} as Member;
  showDetails: boolean = false;
  showHunting: boolean = false;
  openPodium = false; 
  openAddMember: boolean = false;
  leftTime: number = 0;
  leftTimeText: string = '';
  still24: boolean = false;
  ngOnInit(): void {
    this.competition.date = new Date(this.competition.date);
    let startDate = new Date(this.competition.date)
    startDate.setHours(Number(this.competition.startTime.toString().split(":")[0]), Number(this.competition.startTime.toString().split(":")[1]), Number(this.competition.startTime.toString().split(":")[2]))
    let endDate = new Date(this.competition.date)
    endDate.setHours(Number(this.competition.endTime.toString().split(":")[0]), Number(this.competition.endTime.toString().split(":")[1]), Number(this.competition.endTime.toString().split(":")[2]))
    if (new Date() < startDate) {
      this.leftTime = startDate.getTime() - new Date().getTime();
      this.leftTimeText = "Time left: " + Math.floor(this.leftTime / 3600000) + " hours " + Math.floor((this.leftTime % 3600000) / 60000) + " minutes " + Math.floor(((this.leftTime % 3600000) % 60000) / 1000) + " seconds to start"
      setInterval(() => {
        this.leftTime = startDate.getTime() - new Date().getTime();
        this.leftTimeText = "Time left: " + Math.floor(this.leftTime / 3600000) + " hours " + Math.floor((this.leftTime % 3600000) / 60000) + " minutes " + Math.floor(((this.leftTime % 3600000) % 60000) / 1000) + " seconds to start"
      }, 1000)
      if (Math.floor(this.leftTime / 3600000) < 24) {
        this.still24 = true;
      }
    } else if (new Date() > startDate && new Date() < endDate) {
      this.leftTime = endDate.getTime() - new Date().getTime();
      this.leftTimeText = "Time left: " + Math.floor(this.leftTime / 3600000) + " hours " + Math.floor((this.leftTime % 3600000) / 60000) + " minutes " + Math.floor(((this.leftTime % 3600000) % 60000) / 1000) + " seconds to end"
      setInterval(() => {
        this.leftTime = endDate.getTime() - new Date().getTime();
        this.leftTimeText = "Time left: " + Math.floor(this.leftTime / 3600000) + " hours " + Math.floor((this.leftTime % 3600000) / 60000) + " minutes " + Math.floor(((this.leftTime % 3600000) % 60000) / 1000) + " seconds to end"
      }, 1000)
    } else {
      this.leftTime = 0;
      this.leftTimeText = "Finished"
    }
  }
  constructor(private competitionService: CompetitionService, private alertService: AlertService) { }
  onOpenDeleteDialog(competition: Competition) {
    this.alertService.showConfirmation(
      'Are you sure you want to delete this competition ' + this.competition.code + '?',
      () => this.competitionService.delete(this.competition.code)
    );
  }
}
