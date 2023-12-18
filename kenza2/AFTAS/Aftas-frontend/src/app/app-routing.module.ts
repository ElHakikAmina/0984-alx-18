import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home/home.component';
import { CompetitionsComponent } from './components/competition/competitions/competitions.component';
import { CompetitionDetailComponent } from './components/competition/competition-detail/competition-detail.component';
import { HuntsComponent } from './components/competition/hunting/hunts/hunts.component';

const routes: Routes = [
  {path : '' , component : HomeComponent},
  {path : 'Competition' , component : CompetitionsComponent},
  {path : 'Competition/:code' , component : CompetitionDetailComponent},
  {path : 'Competition/:code/:num', component : HuntsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
