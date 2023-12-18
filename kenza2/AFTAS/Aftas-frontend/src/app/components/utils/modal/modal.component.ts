import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent {
  @Input() open : boolean = false;
  
  constructor(){}


  close(): void {
    this.open = false;
  }
}
