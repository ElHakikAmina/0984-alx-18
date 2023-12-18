package com.WI.WIGOLDFISH.entities.fish;

import com.WI.WIGOLDFISH.entities.hunting.Hunting;
import com.WI.WIGOLDFISH.entities.level.Level;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Fish {
    @Id
    private  String  name;
    private double averageWeight;
    @ManyToOne
    private Level level;
    @OneToMany(mappedBy = "fish", fetch = FetchType.LAZY)
    private List<Hunting> huntingList;
    public Fish(String name) {
        this.name = name;
    }
}
