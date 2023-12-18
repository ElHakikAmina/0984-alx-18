import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent {
  @Input() fields: any[] = [];
  @Input() text : string = '';
  @Output() formSubmit: EventEmitter<any> = new EventEmitter<any>();
  formData: any = {};


  constructor() {
  }

  ngOnInit() {

  }

  submitForm() {
      this.formSubmit.emit(this.formData);
  }
}
