package com.WI.WIGOLDFISH.entities.level;

import com.WI.WIGOLDFISH.entities.fish.Fish;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Level {
    @Id
    private Long code;
    private String description;
    private int points;
    @OneToMany(mappedBy = "level")
    private List<Fish> fishList;
    public Level(Long code) {
        this.code = code;
    }
}
