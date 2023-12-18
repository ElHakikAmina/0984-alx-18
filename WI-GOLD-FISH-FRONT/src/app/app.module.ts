import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FishPageComponent } from './pages/fish-page/fish-page.component';
import { HttpClientModule } from '@angular/common/http';
import { CompetitionPageComponent } from './pages/competition-page/competition-page.component';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { CompetitionFormComponent } from './components/forms/competition-form/competition-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CardModule } from 'primeng/card';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { CheckboxModule } from 'primeng/checkbox';
import { PaginatorModule } from 'primeng/paginator';
import { CalendarModule } from 'primeng/calendar';
import { RadioButtonModule } from 'primeng/radiobutton';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomepageComponent } from './pages/homepage/homepage.component';
import { CardCompetitionComponent } from './components/card-competition/card-competition.component';
import { PaginationComponent } from './components/pagination/pagination.component';
import { PopUpMessageComponent } from './components/alerts/pop-up-message/pop-up-message.component';
import { AddMemberComponent } from './components/forms/add-member-form/add-member.component';
import { MultiSelectModule } from 'primeng/multiselect';
import { PopUpConfirmationComponent } from './components/alerts/pop-up-confirmation/pop-up-confirmation.component';
import { MemberCardComponent } from './components/member-card/member-card.component';
import { SidebarModule } from 'primeng/sidebar';
import { CompetitionDetailsComponent } from './components/competition-details/competition-details.component';
import { HuntingDetailsComponent } from './components/hunting-details/hunting-details.component';
import { AddHuntingFormComponent } from './components/forms/add-hunting-form/add-hunting-form.component';
import { FilterComponent } from './components/filter/filter.component';
import { PodiumComponent } from './components/podium/podium.component';


@NgModule({
  declarations: [
    AppComponent,
    FishPageComponent,
    CompetitionPageComponent,
    CompetitionFormComponent,
    NavbarComponent,
    HomepageComponent,
    CardCompetitionComponent,
    PaginationComponent,
    PopUpMessageComponent,
    AddMemberComponent,
    PopUpConfirmationComponent,
    MemberCardComponent,
    CompetitionDetailsComponent,
    HuntingDetailsComponent,
    AddHuntingFormComponent,
    FilterComponent,
    PodiumComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MultiSelectModule,
    BrowserAnimationsModule,
    HttpClientModule,
    CalendarModule,
    PaginatorModule,
    ReactiveFormsModule,
    TableModule,
    CardModule,
    ButtonModule,
    SidebarModule,
    DialogModule,
    InputTextModule,
    CheckboxModule,
    RadioButtonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
