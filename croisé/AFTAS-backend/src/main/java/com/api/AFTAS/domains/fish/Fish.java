package com.api.AFTAS.domains.fish;

import com.api.AFTAS.domains.hunting.Hunting;
import com.api.AFTAS.domains.level.Level;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Fish {
    @Id
    private String name;
    private Double averageWeigth;
    @ManyToOne
    @JoinColumn(name = "level_id",referencedColumnName = "code")
    private Level level;
    @OneToMany(mappedBy = "fish")
    private List<Hunting> huntings = new ArrayList<>();
}
