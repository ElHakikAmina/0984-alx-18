package com.example.sb.model.dto;

import com.example.sb.model.Ennum.Statut;
import com.example.sb.model.Entities.Categories;
import com.example.sb.model.Entities.Produits;
import com.example.sb.model.Entities.Responsable;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionsDto {
    private Long id;

    @Valid
    @NotNull(message = "Responsable cannot be null")
    private Responsable responsable;

    private Categories categorie;

    private Produits produit;

    @NotNull(message = "Promotion date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-d")

    private LocalDate datepromo;

    @NotNull(message = "Statut cannot be null")
    private Statut statut;

    @NotNull(message = "Quantity cannot be null")
    private Integer quantity;
}
