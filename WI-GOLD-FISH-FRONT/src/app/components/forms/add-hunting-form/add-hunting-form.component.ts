import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import Competition from 'src/app/model/Competition';
import Fish from 'src/app/model/Fish';
import Hunting from 'src/app/model/Hunting';
import Member from 'src/app/model/Member';
import { FishService } from 'src/app/services/fish.service';
import { HuntingService } from 'src/app/services/hunting.service';

@Component({
  selector: 'app-add-hunting-form',
  templateUrl: './add-hunting-form.component.html',
  styleUrls: ['./add-hunting-form.component.css']
})
export class AddHuntingFormComponent implements OnChanges {
  @Input() visible: boolean = false;
  @Input() member : Member = {} as Member;
  @Input() hunting: Hunting = {} as Hunting;
  @Input() competition : Competition = {} as Competition;
  fishList: Observable<Fish[]> = this.fishService.fishs;
  huntingForm: FormGroup;
  constructor(private fb: FormBuilder, private fishService: FishService, private huntingService: HuntingService){
    this.huntingForm = this.fb.group({
      numberOfFish: [this.hunting.numberOfFish, Validators.required],
      fish_id: [this.hunting.fish?.name, Validators.required],
    });
  }
  onSubmit() {
    console.log(this.huntingForm.value, this.competition, this.member);
    this.huntingForm.setControl('competition_id', this.fb.control(this.competition.code));
    this.huntingForm.setControl('member_id', this.fb.control(this.member.num));
    this.huntingService.save(this.huntingForm.value);
  }
  ngOnChanges(changes: SimpleChanges): void {
    this.huntingForm.patchValue({
      // numberOfFish: this.hunting.numberOfFish,
      fish_id: this.hunting.fish?.name,
    });
  }
}
