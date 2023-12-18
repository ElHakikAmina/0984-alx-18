package com.example.sb.model.Entities;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;
@Getter
@Builder
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Produits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String produit;
    @ManyToOne
    @JoinColumn(name = "id_categorie")
    private Categories categorie;

}
