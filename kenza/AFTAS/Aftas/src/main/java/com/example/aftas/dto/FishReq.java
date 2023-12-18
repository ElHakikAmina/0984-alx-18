package com.example.aftas.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FishReq {
    @NotBlank(message = "Name is required")
    private String name;
    @NotNull
    @Min(value=0 , message = "Average weight should have a positive value")
    private float averageWeight;
    @NotNull(message = "level id is required")
    private Long level_id;
}
