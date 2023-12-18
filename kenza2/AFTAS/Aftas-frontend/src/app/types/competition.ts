import { Time } from "@angular/common";

export interface Competition{
    code : string | undefined;
    date : Date;
    startTime : string;
    endTime : string;
    numberOfParticipants : number;
    location : string;
    amount : number;
}