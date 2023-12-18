import { Component, Input } from '@angular/core';
import { Observable } from 'rxjs';
import Competition from 'src/app/model/Competition';
import Ranking from 'src/app/model/Ranking';
import { RankingService } from 'src/app/services/ranking.service';

@Component({
  selector: 'app-podium',
  templateUrl: './podium.component.html',
  styleUrls: ['./podium.component.css']
})
export class PodiumComponent {
  @Input() competition: Competition = {} as Competition;
  podium: Ranking[] = []
  constructor(private rankingService : RankingService ) {
  }
  ngOnInit(): void {
    this.rankingService.getPodium(this.competition.code).subscribe(podium => {
      this.podium = podium
    })
  }
  clicked() {
    console.log(this.podium)
  }
}
