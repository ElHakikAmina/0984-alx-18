package com.api.AFTAS.domains.fish.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class FishReqDTO {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Average weight cannot be null")
    @Min(value = 0, message = "Average weight must be greater than or equal to 0")
    private Double averageWeigth;

    @NotNull(message = "Level ID cannot be null")
    @Positive(message = "Level ID must be a positive integer")
    private Integer levelId;
}
