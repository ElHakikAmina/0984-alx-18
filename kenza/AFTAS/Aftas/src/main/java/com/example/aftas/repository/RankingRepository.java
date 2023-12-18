package com.example.aftas.repository;

import com.example.aftas.entity.Competition;
import com.example.aftas.entity.Member;
import com.example.aftas.entity.Ranking;
import com.example.aftas.entity.RankingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, RankingId> {
    int countRankingByCompetition(Competition competition);
    List<Ranking> findByCompetition(Competition competition);
    List<Ranking> findByMember(Member member);
}
