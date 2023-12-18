package com.api.AFTAS.domains.level;

import com.api.AFTAS.domains.fish.Fish;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;
    private String description;
    private Integer points;
    @OneToMany(mappedBy = "level")
    private List<Fish> fishs = new ArrayList<>();
}
