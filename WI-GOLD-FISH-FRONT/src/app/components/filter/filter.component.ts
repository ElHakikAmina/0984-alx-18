import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { FilterCompetition } from 'src/app/enum/FilterCompetition';
import { CompetitionService } from 'src/app/services/competition.service';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent {
  @Input() selectedFilter : FilterCompetition = FilterCompetition.ALL
  setFilter(filter : string){
    this.selectedFilter = filter as FilterCompetition
    this.competitionService.filter = this.selectedFilter
    this.competitionService.findAll()
  }
  constructor(private competitionService : CompetitionService) { }

}
