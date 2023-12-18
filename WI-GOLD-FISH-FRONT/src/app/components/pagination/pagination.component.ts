import { Component, Input } from '@angular/core';
import { MyResponse } from 'src/app/model/MyResponse';
import { CompetitionService } from 'src/app/services/competition.service';
interface PageEvent {
  first: number;
  rows: number;
  page: number;
  pageCount: number;
}
@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent {
  constructor(private competitionService: CompetitionService) {

  }
  @Input() pagination: MyResponse<any> = {} as MyResponse<any>;
  first: number = 0;
  rows: number = 10;
  onPageChange(event: any) {
    this.first = event.first;
    this.competitionService.findAll(this.rows, event.page);
  }
}
