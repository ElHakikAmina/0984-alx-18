import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CompetitionService } from 'src/app/services/competition.service';
import { FishService } from 'src/app/services/fish.service';
import { HuntsService } from 'src/app/services/hunts.service';
import { MemberService } from 'src/app/services/member.service';
import { Competition } from 'src/app/types/competition';
import { Fish } from 'src/app/types/fish';
import { huntReq } from 'src/app/types/huntReq';
import { hunts } from 'src/app/types/hunts';
import { Member } from 'src/app/types/member';

@Component({
  selector: 'app-hunts',
  templateUrl: './hunts.component.html',
  styleUrls: ['./hunts.component.css']
})
export class HuntsComponent implements OnInit {
  id: string | null = null;
  num: number = 0;
  hunts : hunts[] = [];
  fishes : Fish[] = [];
  competition : Competition | undefined = undefined;
  member : Member | undefined = undefined;
  modalOpen : boolean = false;
  modalDelete : boolean = false;
  selectedHunt : hunts |undefined
  tableColumns = [
    { header: 'Fish', field: 'fish.name' },
    { header: 'Number of fishes', field: 'numberOfFish' },
  ];
  text : string = 'Member'
  yourEntityFields : any[] = [
    { type: 'number', label: 'Number of fishes', name: 'numberOfFishes', inputType: 'number',  minValue: '1',},
     {
      label: 'Fishes',
      type: 'select',
      name: 'fish',
      options: [
      ]
    },
  ];
  constructor (private route: ActivatedRoute , private huntService : HuntsService , private competitionService : CompetitionService , private memberService : MemberService , private fishService : FishService){}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.id = params['code']; 
      this.num = params['num'];
    });  
    this.huntService.get(this.id, this.num).subscribe(res => {this.hunts = res; console.log(res)});
    this.competitionService.getCompetition(this.id).subscribe(res => this.competition = res);
    this.memberService.get(this.num).subscribe(res => this.member = res);
    this.fishService.getAll().subscribe(res => {this.fishes = res;this.updateFishesOptions()});
  }

  OpenModal(){
    this.modalOpen = true;
  }

  updateFishesOptions(): void {
    this.yourEntityFields.find(field => field.name === 'fish').options = this.fishes.map(fish => ({
      value: fish.name,
      label: fish.name
    }));
  }

  onFormSubmit(formData: any): void {
    if (formData) {
      const newHunt: huntReq = {
        competition : this.competition?.code,
        member: this.member?.num,
        fish : formData.fish,
        numberOfFish : formData.numberOfFishes
      };

      if (newHunt) {
        console.log(newHunt);
    this.huntService.add(newHunt).subscribe(hunt => {console.log(hunt);this.huntService.get(this.id, this.num).subscribe(res => {this.hunts = res; console.log(res)});})
      }

      formData.numberOfFishes = ''
      formData.fish = ''
      this.modalOpen = false
    }
  }
  increment(hunt : hunts) {
    this.huntService.increment(hunt.id).subscribe((res) => console.log(res))
  }
  decrement(hunt : hunts){
    this.huntService.decrement(hunt.id).subscribe((res)=> console.log(res))
  }
  onRemove(hunt : hunts){
    this.modalDelete = true;
    this.selectedHunt = hunt
  }
  delete(hunt : hunts | undefined) {
    this.modalDelete = false;
    if(hunt)
    this.huntService.delete(hunt.id).subscribe((res) =>{console.log(res);this.huntService.get(this.id, this.num).subscribe(res => {this.hunts = res; console.log(res)});})
  }
}
