import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators,FormsModule } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Competition } from 'src/app/Model/competition';

@Component({
  selector: 'app-dialog-content',
  templateUrl: './dialog-content.component.html',
  styleUrls: ['./dialog-content.component.css']
})
export class DialogContentComponent implements OnInit{
  constructor(private fb :FormBuilder){}
  myForm!:FormGroup;

  compet =  new Competition();
  ngOnInit(): void {
    this.myForm = this.fb.group({
      date: ['', [Validators.required]],
      starttime: ['', [Validators.required]],
      location: ['', [Validators.required]],
      numParticipants: ['', [Validators.required]],
      endtime: ['', [Validators.required]],
      amount: ['', [Validators.required]],
    });
 
  }
  isValidDate(control:any) {
    const inputDate = new Date(control.value);
    return !isNaN(inputDate.getTime()) ? null : { invalidDate: true };
  }
  Submit() {
    if (this.myForm.valid) {
      // Form is valid, handle the submission here
      console.log('Form data submitted:', this.myForm.value);
    } else {
      // Form is invalid, display an error notification or handle as needed
      console.log('Form data is invalid. Please check the fields.');
    }
  }

}
