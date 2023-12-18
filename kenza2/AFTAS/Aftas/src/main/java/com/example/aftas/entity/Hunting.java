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

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Fish fish;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Competition competition;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Member member;
}
