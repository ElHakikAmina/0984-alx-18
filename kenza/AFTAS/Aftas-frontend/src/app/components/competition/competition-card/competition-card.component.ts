import { Component, Input } from '@angular/core';
import { Competition } from 'src/app/types/competition'

@Component({
  selector: 'app-competition-card',
  templateUrl: './competition-card.component.html',
  styleUrls: ['./competition-card.component.css']
})
export class CompetitionCardComponent {
  @Input() competition: Competition | undefined; 
}
