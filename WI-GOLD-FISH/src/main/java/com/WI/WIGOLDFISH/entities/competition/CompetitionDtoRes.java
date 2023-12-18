package com.WI.WIGOLDFISH.entities.competition;

import com.WI.WIGOLDFISH.entities.hunting.HuntingDtoForCompetition;
import com.WI.WIGOLDFISH.entities.ranking.RankingDtoForCompetition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.WI.WIGOLDFISH.entities.hunting.HuntingDtoReq;
import com.WI.WIGOLDFISH.entities.ranking.RankingDtoReq;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionDtoRes {
    private String code;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int numberOfParticipants;
    private String location;
    private Double amount;
    private List<RankingDtoForCompetition> ranking;
    private List<HuntingDtoForCompetition> huntings;
}
