package com.api.AFTAS.domains.ranking;

import com.api.AFTAS.config.CrudInterface;
import com.api.AFTAS.domains.ranking.DTOs.RankingIdReqDTO;
import com.api.AFTAS.domains.ranking.DTOs.RankingReqDTO;
import com.api.AFTAS.domains.ranking.DTOs.RankingRespDTO;

import java.util.List;

public interface RankingServiceInterface extends CrudInterface<RankingReqDTO, RankingRespDTO, RankingIdReqDTO> {
    List<RankingRespDTO> calculateAndFetchRankings(String competitionCode);
    List<RankingRespDTO> getRankings(String competitionCode);
}
