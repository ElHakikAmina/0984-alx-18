package com.api.AFTAS.domains.ranking.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RankingIdReqDTO {
    @NotBlank(message = "Code cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]{3}-\\d{2}-\\d{2}-\\d{2}$", message = "Invalid pattern")
    private String competition_code;
    @NotNull(message = "Member number cannot be null")
    private Integer member_num;
}
