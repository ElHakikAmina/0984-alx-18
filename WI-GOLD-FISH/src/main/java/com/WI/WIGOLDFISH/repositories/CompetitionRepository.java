package com.WI.WIGOLDFISH.repositories;

import com.WI.WIGOLDFISH.entities.competition.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface CompetitionRepository extends JpaRepository<Competition, String> {
    Optional<Competition> findByCode(String code);
    Page<Competition> findAllByDateAfter(LocalDate date, Pageable pageable);

    Page<Competition> findAllByDateBefore(LocalDate date, Pageable pageable);

    Page<Competition> findAllByDateAndStartTimeBeforeAndEndTimeAfter(
            LocalDate date, LocalTime startTime, LocalTime endTime, Pageable pageable);
}
