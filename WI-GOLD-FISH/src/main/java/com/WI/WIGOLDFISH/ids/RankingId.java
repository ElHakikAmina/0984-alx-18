package com.WI.WIGOLDFISH.ids;

import com.WI.WIGOLDFISH.entities.competition.Competition;
import com.WI.WIGOLDFISH.entities.member.Member;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class RankingId implements Serializable {
    @ManyToOne
    private Member member;
    @ManyToOne
    private Competition competition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RankingId rankingId = (RankingId) o;
        return Objects.equals(member, rankingId.member) && Objects.equals(competition, rankingId.competition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(member, competition);
    }
}
