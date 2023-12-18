package com.example.aftas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ranking {
    @EmbeddedId
    private RankingId id;

    @ManyToOne
    @MapsId("competitionCode")
    private Competition competition;

    @ManyToOne
    @MapsId("memberNum")
    private Member member;

    private int rank;
    private int score;

}
