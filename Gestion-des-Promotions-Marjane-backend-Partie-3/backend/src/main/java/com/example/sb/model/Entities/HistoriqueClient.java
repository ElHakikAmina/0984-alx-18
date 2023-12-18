package com.example.sb.model.Entities;

import com.example.sb.model.Ennum.Statut;
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
public class HistoriqueClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_produits")
    private Produits produits;
    private Statut statutpromotion;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;
}
