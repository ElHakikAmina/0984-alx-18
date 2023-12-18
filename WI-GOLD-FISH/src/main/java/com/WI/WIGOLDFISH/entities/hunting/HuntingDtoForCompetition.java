package com.WI.WIGOLDFISH.entities.hunting;

import com.WI.WIGOLDFISH.entities.competition.Competition;
import com.WI.WIGOLDFISH.entities.competition.CompetitionDtoReq;
import com.WI.WIGOLDFISH.entities.fish.Fish;
import com.WI.WIGOLDFISH.entities.fish.FishDtoReq;
import com.WI.WIGOLDFISH.entities.fish.FishDtoReqForCompetition;
import com.WI.WIGOLDFISH.entities.member.Member;
import com.WI.WIGOLDFISH.entities.member.MemberDtoReq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HuntingDtoForCompetition {
    private  Long id;
    private int numberOfFish;
    private FishDtoReqForCompetition fish;
    private MemberDtoReq member;
    //private CompetitionDtoReq competition;
}
