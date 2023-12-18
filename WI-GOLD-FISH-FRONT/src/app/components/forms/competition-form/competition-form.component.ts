import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Competition from 'src/app/model/Competition';
import { CompetitionService } from 'src/app/services/competition.service';
import AlertProps from '../../alerts/AlertProps';

@Component({
  selector: 'app-competition-form',
  templateUrl: './competition-form.component.html',
  styleUrls: ['./competition-form.component.css']
})
export class CompetitionFormComponent {
  competitionForm: FormGroup;
  @Input() competition: Competition = {} as Competition;
  constructor(private fb: FormBuilder, private competitionService: CompetitionService) {
    this.competitionForm = this.fb.group({
      date: [null, Validators.required],
      startTime: [null, Validators.required],
      endTime: [null, Validators.required],
      numberOfParticipants: [null, Validators.required],
      location: [null, Validators.required],
      amount: [null, Validators.required],
    }, {
      validators: [this.timeValidator]
    });
  }

  timeValidator(competitionForm: FormGroup) {
    const date = new Date(competitionForm?.get('date')?.value);
    const today = new Date();
    const threeMonthsFromNow = new Date();
    threeMonthsFromNow.setMonth(today.getMonth() + 3);
    if (date < today || date > threeMonthsFromNow) {
      competitionForm.get('date')?.setErrors({ 'invalidDate': true });
    } 
    const startTime = competitionForm?.get('startTime')?.value?.split(':')![0];
    const endTime = competitionForm?.get('endTime')?.value?.split(':')![0];
    if (Number(startTime) < 8 || Number(startTime) >= 19) {
      competitionForm.get('startTime')?.setErrors({ 'invalidStartTime': true });
    }
    if (Number(endTime) <= 8 || Number(endTime) >= 20) {
      competitionForm.get('endTime')?.setErrors({ 'invalidEndTime': true });
    }
    if (Number(endTime) - Number(startTime) < 1) {
      competitionForm.get('endTime')?.setErrors({ 'invalidEndTime': true });
    }
  }


  onSubmit() {
    if (this.competitionForm.valid) {
      const competition = this.competitionForm.value as Competition;
      const date = new Date(competition.date);
      const year = date.getFullYear();
      const month = date.getMonth() > 9 ? date.getMonth() : '0' + date.getMonth();
      const day = date.getDate() > 9 ? date.getDate() : '0' + date.getDate();
      const code = competition.location.toLowerCase().replace(' ', '').substring(0, 3) + '-' + day + '-' + month + '-' + year.toString().substring(2);
      competition.code = code;
      this.competitionService.save(competition)
      this.competitionForm.reset();
      this.visible = false;
    }
  }
  @Input() visible: boolean = false;
  showDialog() {
    this.visible = true;
  }
  alertProps: AlertProps = new AlertProps();
}
