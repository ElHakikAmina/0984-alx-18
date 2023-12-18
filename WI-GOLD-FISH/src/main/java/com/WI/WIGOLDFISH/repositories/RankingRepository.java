package com.WI.WIGOLDFISH.repositories;

import com.WI.WIGOLDFISH.entities.ranking.Ranking;
import com.WI.WIGOLDFISH.ids.RankingId;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankingRepository extends JpaRepository<Ranking, RankingId> {
    List<Ranking> findAllByCompetition_CodeOrderByScoreDesc(String competitionCode);
}
