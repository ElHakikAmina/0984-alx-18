package com.example.aftas.dto;

import com.example.aftas.entity.Competition;
import com.example.aftas.entity.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RankingResp {
    private CompetitionResp competition;
    private MemberResp member;
    private int rank;
    private int score;
}
