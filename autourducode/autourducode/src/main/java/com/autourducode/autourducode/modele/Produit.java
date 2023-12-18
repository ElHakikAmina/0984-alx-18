package com.autourducode.autourducode.modele;

import jakarta.persistence.Entity;

@Entity
@Table(name="produit")
@Getter
@Setter
@NoArgsConstructor
public class Produit {
    @Id;
    @GeneratedValue(Strategy = GeneratioType.IDENTITY)
    @Column(length = 58)
    private Long id;
    @Column(length = 158)
    private String nom;

    private String description ;
    private Ineteger prix;


}
