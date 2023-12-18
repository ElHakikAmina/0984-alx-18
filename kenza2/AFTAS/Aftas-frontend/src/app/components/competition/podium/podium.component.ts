import { Component, Input, OnInit } from '@angular/core';
import { Ranking } from 'src/app/types/ranking';

@Component({
  selector: 'app-podium',
  templateUrl: './podium.component.html',
  styleUrls: ['./podium.component.css']
})
export class PodiumComponent implements OnInit{
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  @Input() podium : Ranking[] = [];

}
