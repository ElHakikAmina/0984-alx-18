package com.example.aftas.repository;

import com.example.aftas.entity.Competition;
import com.example.aftas.entity.Fish;
import com.example.aftas.entity.Hunting;
import com.example.aftas.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting , Long> {
    Optional<Hunting> findByCompetitionAndFishAndMember(Competition competition , Fish fish , Member member);
    List<Hunting> findByCompetition(Competition competition);
    List<Hunting> findByMember(Member member);
    List<Hunting> findByFish(Fish fish);
    List<Hunting> findByCompetitionAndMember(Competition competition, Member member);
}
