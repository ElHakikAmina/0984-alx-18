import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import AlertProps from './AlertProps';

@Injectable({
  providedIn: 'root'
})
export class AlertService {

  public alertprops = new BehaviorSubject<AlertProps>(new AlertProps());
  public showMsg(msg: string) {
    this.alertprops.next(new AlertProps(true, msg, false));
  }
  public showConfirmation(msg: string, onConfirm: () => void) {
    this.alertprops.next(new AlertProps(true, msg, true, onConfirm));
  }
  public hide() {
    this.alertprops.next(new AlertProps());
  }
}
