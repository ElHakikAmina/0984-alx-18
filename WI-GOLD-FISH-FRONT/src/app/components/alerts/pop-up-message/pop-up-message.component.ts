import { Component, EventEmitter, Input, Output } from '@angular/core';
import AlertProps from '../AlertProps';
import { AlertService } from '../alert-service.service';

@Component({
  selector: 'app-pop-up-message',
  templateUrl: './pop-up-message.component.html',
  styleUrls: ['./pop-up-message.component.css']
})
export class PopUpMessageComponent {
  constructor(private alertsService: AlertService) { }
  @Output() confirmed = new EventEmitter<boolean>();
  alertProps: AlertProps = new AlertProps();
  close(b: boolean) {
    this.alertsService.hide();
  }
  ngAfterViewInit(): void {
    this.alertsService.alertprops.subscribe((alertProps) => {
      this.alertProps = alertProps;
    });
  }
}
