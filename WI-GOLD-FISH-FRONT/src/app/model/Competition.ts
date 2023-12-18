import { Time } from "@angular/common";
import Hunting from "./Hunting";
import Ranking from "./Ranking";

export default interface Competition {
    code : string;
    date : Date;
    startTime : Time | string;
    endTime : Time | string;
    numberOfParticipants : number;
    location : string;
    amount : number;
    huntings : Hunting[];
    ranking : Ranking[];
}