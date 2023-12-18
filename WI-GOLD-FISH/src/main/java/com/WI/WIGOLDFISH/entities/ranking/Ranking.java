package com.WI.WIGOLDFISH.entities.ranking;

import com.WI.WIGOLDFISH.entities.competition.Competition;
import com.WI.WIGOLDFISH.entities.member.Member;
import com.WI.WIGOLDFISH.ids.RankingId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class  Ranking {
    @EmbeddedId
    private RankingId rankingId;
    private  int rank;
    private int score;
    @ManyToOne
    @MapsId("member")
    private Member member;
    @ManyToOne
    @MapsId("competition")
    private Competition competition;
}
