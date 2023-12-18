package com.api.AFTAS.domains.ranking.DTOs;

import lombok.Data;

@Data
public class RankingReqDTO {
    private RankingIdReqDTO id;
    private Integer score;
    private Integer rank;
}
