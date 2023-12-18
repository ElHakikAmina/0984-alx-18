package com.api.AFTAS.domains.ranking;

import com.api.AFTAS.domains.competition.Competition;
import com.api.AFTAS.domains.member.Member;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
public class Ranking {
    @EmbeddedId
    private RankingId id;
    private Long score;
    private Integer rank;
    public Ranking(Member member, Competition competition, Long score) {
        this.id = new RankingId(competition,member);
        this.score = score;
    }
    public Ranking(Member member, Competition competition, Long score,Integer rank) {
        this.id = new RankingId(competition,member);
        this.score = score;
        this.rank = rank;
    }
    public Ranking() {
    }
}
