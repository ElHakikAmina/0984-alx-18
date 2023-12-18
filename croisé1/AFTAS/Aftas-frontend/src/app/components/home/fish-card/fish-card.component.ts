import { Component, Input } from '@angular/core';
import { Fish } from 'src/app/types/fish';

@Component({
  selector: 'app-fish-card',
  templateUrl: './fish-card.component.html',
})
export class FishCardComponent {
  @Input() fish: Fish | undefined; 
}
