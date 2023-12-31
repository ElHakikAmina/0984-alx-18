package com.example.aftas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Competition {
    @Id
    private String code;

    private java.sql.Date Date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int numberOfParticipants;
    private String location;
    private float amount;

    @OneToMany(mappedBy = "competition", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hunting> hunts;

    @OneToMany(mappedBy = "competition", fetch = FetchType.EAGER , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ranking> members;
}
