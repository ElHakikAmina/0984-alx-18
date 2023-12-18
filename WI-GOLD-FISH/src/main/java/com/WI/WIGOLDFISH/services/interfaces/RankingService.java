package com.WI.WIGOLDFISH.services.interfaces;

import com.WI.WIGOLDFISH.entities.ranking.RankingDtoReq;
import com.WI.WIGOLDFISH.entities.ranking.RankingDtoRes;
import com.WI.WIGOLDFISH.ids.RankingId;
import com.WI.WIGOLDFISH.services.BaseService;

import java.util.List;

public interface RankingService extends BaseService<RankingDtoRes, RankingDtoReq, RankingId> {
    List<RankingDtoRes> findAllByCompetition_CodeOrderByScoreDesc(String competitionCode);
}
