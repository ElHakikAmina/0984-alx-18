import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Competition from 'src/app/model/Competition';
import Member from 'src/app/model/Member';
import Ranking from 'src/app/model/Ranking';
import { CompetitionService } from 'src/app/services/competition.service';
import { MemberService } from 'src/app/services/member.service';
import { RankingService } from 'src/app/services/ranking.service';
import { AlertService } from '../../alerts/alert-service.service';

@Component({
  selector: 'app-add-member',
  templateUrl: './add-member.component.html',
  styleUrls: ['./add-member.component.css']
})
export class AddMemberComponent {
  @Input() visible: boolean = false;
  members: Member[] = [];
  showDialog() {
    this.visible = true;
  }
  @Input() competition: Competition = {} as Competition;
  @Input() selectedMembers: Member[] = [];
  @Input() ranking: Ranking = {} as Ranking;
  constructor(private memberService: MemberService, private rankingService: RankingService, private competitionService: CompetitionService, private alertService: AlertService) { }
  ngAfterViewInit() {
    this.memberService.members.subscribe(
      (members) => {
        this.members = members;
      }
    );
  }
  onSubmit() {
    this.selectedMembers.forEach(
      (member) => {
        this.ranking.member_id = member.num;
        this.ranking.competition_id = this.competition.code;
        this.ranking.score = 0;
        this.ranking.rank = 0;
        this.rankingService.save(this.ranking);
      },
    )
    this.alertService.showMsg('Members added successfully');
  }
  onChangeSelectedMembers() {
    if (this.selectedMembers.length > (this.competition.numberOfParticipants - this.competition.ranking.length)) {
      this.alertService.showMsg('Number of participants exceeded');
      this.selectedMembers.pop();
    }
    this.selectedMembers.forEach(
      (member) => {
        if (this.competition.ranking.find(ranking => ranking.member.num == member.num)) {
          this.alertService.showMsg('Member already exists');
          this.selectedMembers.pop();
        }
      },
    )
  }
}
