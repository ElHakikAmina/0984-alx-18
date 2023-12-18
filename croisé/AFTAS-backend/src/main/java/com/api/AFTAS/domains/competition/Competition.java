package com.api.AFTAS.domains.competition;

import com.api.AFTAS.domains.hunting.Hunting;
import com.api.AFTAS.domains.ranking.Ranking;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Competition {
    @Id
    private String code;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer numberOfParticipants;
    private String location;
    private Double amount;
    @OneToMany(mappedBy = "competition")
    private List<Hunting> huntings = new ArrayList<>();
    @OneToMany(mappedBy = "id.competition")
    private List<Ranking> rankings;
}
