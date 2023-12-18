import Competition from "./Competition";
import Fish from "./Fish";
import Member from "./Member";

export default interface Hunting {
    id : number;
    numberOfFish : number;
    fish : Fish;
    member : Member;
    competition : Competition;
}