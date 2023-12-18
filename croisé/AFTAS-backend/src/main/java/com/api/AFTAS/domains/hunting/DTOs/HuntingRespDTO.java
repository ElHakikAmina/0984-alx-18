package com.api.AFTAS.domains.hunting.DTOs;

import com.api.AFTAS.domains.competition.DTOs.CompetitionReqDTO;
import com.api.AFTAS.domains.fish.DTOs.FishReqDTO;
import com.api.AFTAS.domains.member.DTOs.MemberReqDTO;
import lombok.Data;

@Data
public class HuntingRespDTO {
    private Integer id;
    private Integer numberOfFish;
    private FishReqDTO fish;
    private MemberReqDTO member;
    private CompetitionReqDTO competition;
}