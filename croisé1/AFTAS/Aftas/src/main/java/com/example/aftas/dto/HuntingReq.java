package com.example.aftas.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HuntingReq {
    @NotNull
    @Min(value = 0 , message = "Number of fish must be positive")
    private int numberOfFish;
    @NotNull(message = "Fish is required")
    private String fish;
    @NotNull(message = "Competition is required")
    private String competition;
    @NotNull(message = "Member is required")
    private Long member;
}
