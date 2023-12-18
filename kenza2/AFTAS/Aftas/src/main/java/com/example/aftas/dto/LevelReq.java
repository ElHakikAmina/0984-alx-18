package com.example.aftas.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LevelReq {

    @NotNull(message = "Id is required")
    private Long id;
    @NotEmpty(message = "Description is required")
    private String description;
    @NotNull
    @Min(value = 0 , message = "point should be a positive value")
    private int point;
}
