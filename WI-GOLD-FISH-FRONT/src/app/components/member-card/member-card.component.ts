import { Component, Input } from '@angular/core';
import Ranking from 'src/app/model/Ranking';

@Component({
  selector: 'app-member-card',
  templateUrl: './member-card.component.html',
  styleUrls: ['./member-card.component.css']
})
export class MemberCardComponent {
  @Input() ranking :Ranking = {} as Ranking;
}
