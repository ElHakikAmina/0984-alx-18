package com.api.AFTAS.domains.ranking.DTOs;

import com.api.AFTAS.domains.competition.DTOs.CompetitionReqDTO;
import com.api.AFTAS.domains.member.DTOs.MemberReqDTO;
import lombok.Data;

@Data
public class RankingIdRespDTO {
    private CompetitionReqDTO competition;
    private MemberReqDTO member;
}
