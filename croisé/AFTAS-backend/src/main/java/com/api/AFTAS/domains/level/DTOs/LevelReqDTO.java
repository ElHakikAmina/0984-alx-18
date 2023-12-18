package com.api.AFTAS.domains.level.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LevelReqDTO {
    @NotNull(message = "Code cannot be null")
    private Integer code;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 255, message = "Description must be at most 255 characters")
    private String description;

    @NotNull(message = "Points cannot be null")
    @Min(value = 1, message = "Points must be greater than or equal to 1")
    private Integer points;
}
