import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Competition } from 'src/app/types/competition'
import { faClockFour , faUser , faMoneyBill} from '@fortawesome/free-solid-svg-icons';
@Component({
  selector: 'app-competition-card',
  templateUrl: './competition-card.component.html',
  styleUrls: ['./competition-card.component.css']
})
export class CompetitionCardComponent implements OnInit {
  ngOnInit(): void {
    console.log(typeof(this.competition))
  }
  @Input() competition: Competition | undefined; 
  @Input() options: boolean = false;
  faTime = faClockFour;
  faUser = faUser;
  faMoney = faMoneyBill;
  formatTime(time: string | undefined): string | undefined {
    if (!time) return undefined;

    const [hours, minutes, seconds] = time.split(':');
    return `${hours}h:${minutes}m`;
  }
}
