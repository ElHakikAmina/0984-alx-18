package com.example.aftas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Fish {
    @Id
    private String name;

    private float averageWeight;

    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;

    @OneToMany(mappedBy = "fish", fetch = FetchType.EAGER , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hunting> hunts;
}
