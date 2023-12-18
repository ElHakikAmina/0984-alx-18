package com.WI.WIGOLDFISH.entities.ranking;

import com.WI.WIGOLDFISH.entities.competition.Competition;
import com.WI.WIGOLDFISH.entities.competition.CompetitionDtoReq;
import com.WI.WIGOLDFISH.entities.member.Member;
import com.WI.WIGOLDFISH.entities.member.MemberDtoReq;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RankingIdDto {
    private Long member_id;
    private String competition_id;
}
