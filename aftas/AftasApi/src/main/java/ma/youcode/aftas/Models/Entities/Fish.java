package ma.youcode.aftas.Models.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_fishes")
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private float averageWeight;

    @OneToMany(mappedBy = "fish", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Hunting> huntings = new ArrayList<Hunting>();

    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;
}