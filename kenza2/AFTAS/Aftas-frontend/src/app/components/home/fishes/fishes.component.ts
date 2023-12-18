import { Component, OnInit } from '@angular/core';
import { FishService } from 'src/app/services/fish.service';
import { Fish } from 'src/app/types/fish';

@Component({
  selector: 'app-fishes',
  templateUrl: './fishes.component.html',
  styleUrls: ['./fishes.component.css']
})
export class FishesComponent implements OnInit {

  fishes : Fish[] = [];

  constructor(private fishService : FishService){}
  
  ngOnInit(): void {
    this.fishService.getFishes().subscribe(fishes => this.fishes = fishes);
  }

}
