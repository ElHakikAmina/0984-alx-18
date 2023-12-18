package com.example.sb.model.dto;

        import com.example.sb.model.Ennum.Statut;
        import com.example.sb.model.Entities.*;
        import com.fasterxml.jackson.annotation.JsonFormat;
        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Data;
        import lombok.NoArgsConstructor;

        import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionRequest {


    private Statut statut;
    private Integer quantity;
    private Long responsable_id;
    private Long categorie_id;
    private Long produit_id;
    @JsonFormat(pattern = "yyyy-MM-d")
    private LocalDate datepromo ;

    public Promotions toModel() {
        Responsable responsable1 = Responsable.builder().id(responsable_id).build();
        Categories categorie1 = Categories.builder().id(categorie_id).build();
        Produits produits1 = Produits.builder().id(produit_id).build();

        return Promotions.builder()
                .datepromo(this.datepromo)
                .statut(this.statut)
                .quantity(this.quantity)
                .responsable(responsable1)
                .categorie(categorie1)
                .produit(produits1)
                .build();
    }
}
