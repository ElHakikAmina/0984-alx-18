package com.WI.WIGOLDFISH.services.interfaces;

import com.WI.WIGOLDFISH.entities.competition.CompetitionDtoReq;
import com.WI.WIGOLDFISH.entities.competition.CompetitionDtoRes;
import com.WI.WIGOLDFISH.enums.FilterCompetition;
import com.WI.WIGOLDFISH.services.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
public interface CompetitionService extends BaseService<CompetitionDtoRes, CompetitionDtoReq, String> {
    Page<CompetitionDtoRes> findAll(Pageable pageable, FilterCompetition filter);
}
