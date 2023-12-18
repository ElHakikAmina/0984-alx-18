import { Component, OnInit } from '@angular/core';
import { CompetitionService } from 'src/app/services/competition.service';
import { Competition } from 'src/app/types/competition';

@Component({
  selector: 'app-competitions',
  templateUrl: './competitions.component.html',
  styleUrls: ['./competitions.component.css']
})
export class CompetitionsComponent implements OnInit{
  competitions : Competition[] = [];
  filteredCompetitions : Competition[] = [];
  selectedCompetition : Competition | undefined;
  currentPage = 1;
  pageSize = 6; 
  totalPages : number = 0;
  pagesArray: number[] = [];

  modalOpen: boolean = false;



  constructor(private _competitionsService : CompetitionService){}

  ngOnInit(): void {
    this._competitionsService.getTotalPagesNumber(this.pageSize).subscribe(totalPages => this.totalPages = totalPages).add(()=>this.pagesArray = Array.from({ length: this.totalPages }, (_, index) => index + 1));
    this._competitionsService.getCompetitions(this.currentPage -1 , this.pageSize).subscribe(competitions => this.competitions = competitions ).add(() => this.filteredCompetitions = this.competitions);
  }

  onPageChange(pageNumber: number) {
    this.currentPage = pageNumber;
    this._competitionsService.getCompetitions(this.currentPage -1, this.pageSize).subscribe(competitions => this.competitions = competitions ).add(() => this.filteredCompetitions = this.competitions);
  }

  onFilterChange(event: Event): void {
    const currentDate = new Date();
    currentDate.setHours(0, 0, 0, 0);
    console.log(currentDate)
    const selectedFilter = (event.target as HTMLSelectElement)?.value;
    if (selectedFilter === 'pending') {
      this.filteredCompetitions = this.competitions.filter(competition => {
        const competitionDate = new Date(competition.date);
        competitionDate.setHours(0, 0, 0, 0);
        return competitionDate > currentDate;
      });
    } else if (selectedFilter === 'in_progress') {
      this.filteredCompetitions = this.competitions.filter(competition => {
        const competitionDate = new Date(competition.date);
        return competitionDate.toDateString() === currentDate.toDateString();
      });
    } else if (selectedFilter === 'closed') {
      this.filteredCompetitions = this.competitions.filter(competition => {
        const competitionDate = new Date(competition.date);
        competitionDate.setHours(0, 0, 0, 0);
        return competitionDate < currentDate;
      });
    } else {
      this.filteredCompetitions = this.competitions;
    }
  }

  OpenModal(){
    this.modalOpen = true;
  }
  AddCompetition(competition : Competition | undefined){
    console.log(competition);
    this._competitionsService.addCompetition(competition).subscribe((competition) =>{ console.log(competition) , this.ngOnInit()});
    this.modalOpen = false;
  }

}

