package com.example.aftas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Hunting {
    @Id
    @GeneratedValue
    private Long id;

    private int numberOfFish;

    @ManyToOne
    private Fish fish;
    @ManyToOne
    private Competition competition;
    @ManyToOne
    private Member member;
}
