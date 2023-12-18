package com.api.AFTAS.domains.ranking.DTOs;

import lombok.Data;

@Data
public class RankingRespDTO {
    private RankingIdRespDTO id;
    private Integer score;
    private Integer rank;
}
