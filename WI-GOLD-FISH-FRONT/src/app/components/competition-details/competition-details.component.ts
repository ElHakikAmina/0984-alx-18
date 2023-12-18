import { Component, EventEmitter, Input, Output } from '@angular/core';
import Competition from 'src/app/model/Competition';
import Member from 'src/app/model/Member';

@Component({
  selector: 'app-competition-details',
  templateUrl: './competition-details.component.html',
  styleUrls: ['./competition-details.component.css']
})
export class CompetitionDetailsComponent {
  @Input() competition: Competition = {} as Competition;
  @Input() openAddMember: boolean = false;
  @Input() canAddMember: boolean = false;
  @Input() leftTimeText : string = "";
  @Output() openAddMemberChange = new EventEmitter<Member>();
  setMember(member: Member) {
    this.openAddMemberChange.emit(member);
  }
}
