import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/utils/navbar/navbar.component';
import { HeaderComponent } from './components/home/header/header.component';
import { HomeComponent } from './components/home/home/home.component';
import { ButtonComponent } from './components/utils/button/button.component';
import { FishesComponent } from './components/home/fishes/fishes.component';
import { HttpClientModule } from '@angular/common/http';
import { FishCardComponent } from './components/home/fish-card/fish-card.component';
import { FooterComponent } from './components/utils/footer/footer.component';
import { AboutComponent } from './components/home/about/about.component';
import { CompetitionsComponent } from './components/competition/competitions/competitions.component';
import { CompetitionCardComponent } from './components/competition/competition-card/competition-card.component';
import { ModalComponent } from './components/utils/modal/modal.component';
import { FormComponent } from './components/utils/form/form.component';
import { AddCompetitionComponent } from './components/competition/add-competition/add-competition.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HeaderComponent,
    HomeComponent,
    ButtonComponent,
    FishesComponent,
    FishCardComponent,
    FooterComponent,
    AboutComponent,
    CompetitionsComponent,
    CompetitionCardComponent,
    ModalComponent,
    FormComponent,
    AddCompetitionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
