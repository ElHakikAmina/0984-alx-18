import { Competition } from "./competition";
import { Member } from "./member";

export interface Ranking{
    competition : Competition | undefined;
    member : Member;
    rank : number | null;
    score : number | null;
}