import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Competition } from 'src/app/types/competition';

@Component({
  selector: 'app-add-competition',
  templateUrl: './add-competition.component.html',
  styleUrls: ['./add-competition.component.css']
})
export class AddCompetitionComponent implements OnInit{
  minimum: Date | undefined = undefined;
  minDateString: string | undefined = undefined;  
  ngOnInit(): void {
    const currentDate = new Date();
    currentDate.setHours(0, 0, 0, 0);

    const twoDaysAfter = new Date(currentDate);
    twoDaysAfter.setDate(currentDate.getDate() + 2);
    twoDaysAfter.setHours(0, 0, 0, 0);

    this.minimum = twoDaysAfter;
    this.updateMinDateString();
  }
  updateMinDateString() {
    this.minDateString = this.minimum?.toISOString().split('T')[0];
  }
  text : string = 'Competition';
  yourEntityFields: any[] = [
    { type: 'date', label: 'Competition Date', name: 'dateField', inputType: 'date', minDate:  this.minDateString || undefined  },
    { type: 'text', label: 'Competition location', name: 'location', inputType: 'text' },
    { type: 'time', label: 'Start Time', name: 'startTime', inputType: 'time' },
    { type : 'time', label: 'End Time', name: 'endTime', inputType: 'time' },
    { name: 'numberOfParticipants', label: 'Number of participant', type: 'number', inputType: 'number' , minValue : 1 , maxValue: 10},
    { name: 'amount', label: 'Playing amount', type: 'number', inputType: 'number' , minValue : 0 },
  ];

  @Output() onAddCompetition : EventEmitter<Competition> = new EventEmitter();
  generateCode(location : string, date : Date) : string | undefined{
    const locationCode = location.substring(0, 3).toUpperCase();
    const generatedCode = `${locationCode}-${date}`;
    return generatedCode;
}
  onFormSubmit(formData: any) {
    if(formData){
      const newCompetition : Competition = {
            code : this.generateCode(formData.location , formData.dateField),
            location : formData.location,
            date : formData.dateField,
            startTime : formData.startTime,
            endTime : formData.endTime,
            numberOfParticipants : formData.numberOfParticipants,
            amount : formData.amount
          }
          if(newCompetition){
                this.onAddCompetition.emit(newCompetition);
              }
              formData.location = '',
              formData.date = null,
              formData.startTime = null,
              formData.endTime = null,
              formData.numberOfParticipants = null,
              formData.amount = null
        }

    }

}
