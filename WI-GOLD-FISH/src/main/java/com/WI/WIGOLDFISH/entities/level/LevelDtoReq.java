package com.WI.WIGOLDFISH.entities.level;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LevelDtoReq {
    @NotNull(message = "Code is required")
    private Long code;
    @NotNull(message = "Points is required")
    private int points;
    @NotNull(message = "Description is required")
    private String description;
}