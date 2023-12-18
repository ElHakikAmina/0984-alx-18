import { Component, OnInit } from '@angular/core';
import { Competition } from 'src/app/Model/competition';
import { CompetitionService } from 'src/app/Service/competition.service';
import { faCoffee, faPlus } from '@fortawesome/free-solid-svg-icons';
import { faPlusCircle,faCalendar } from '@fortawesome/free-solid-svg-icons';
import { DialogService } from 'src/app/Service/dialog.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-competition',
  templateUrl: './competition.component.html',
  styleUrls: ['./competition.component.css']
})
export class CompetitionComponent implements OnInit {
  competitions : Competition[] =[];
  facofe = faPlusCircle;
  date = faCalendar;
  myForm!: FormGroup;
  constructor(private service : CompetitionService,private dialog : DialogService,private fb :FormBuilder){}
  ngOnInit(): void {
    this.getCompetitoin();
    
  }

  

  getCompetitoin(){
    this.service.GetCompetition().subscribe((res : any)=>{
      const comp = res;
      this.competitions = comp;
      console.log(this.competitions);
    },(err)=>{
      
      console.log("no competiton found");
    })
  }

  

  openDialog(){
    this.dialog.openDialog();
  }
}
