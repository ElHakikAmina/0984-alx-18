import { Component, EventEmitter, Input, Output } from '@angular/core';
import AlertProps from '../AlertProps';
import { AlertService } from '../alert-service.service';

@Component({
  selector: 'app-pop-up-confirmation',
  templateUrl: './pop-up-confirmation.component.html',
  styleUrls: ['./pop-up-confirmation.component.css']
})
export class PopUpConfirmationComponent {
  constructor(private alertsService : AlertService) { }

  @Output() confirmed = new EventEmitter<boolean>();
  alertProps: AlertProps = new AlertProps();
  close(b : boolean) {
    if (b) {
      if (b && this.alertProps.needConfirmation && this.alertProps.onConfirm) {
        this.alertProps.onConfirm();
      }
    }
    this.alertsService.hide();
  }
  ngAfterViewInit() :void {
    this.alertsService.alertprops.subscribe((alertProps) => { 
      this.alertProps = alertProps;
    });
  }
}
