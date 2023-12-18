package com.example.aftas.services.interfaces;


import com.example.aftas.dto.CompetitionReq;
import com.example.aftas.dto.CompetitionResp;

import java.util.List;
import java.util.Optional;

public interface CompetitionServiceInterface {
    Optional<CompetitionResp> AddCompetition(CompetitionReq competition) ;
    Optional<CompetitionResp> findById(String code);
    List<CompetitionResp> getAllCompetitions(int page, int size);
    Optional<CompetitionResp> updateCompetition(String competitionCode , CompetitionReq competition);
    Optional<CompetitionResp> deleteCompetition(String code);
    long getTotalPages(Integer size);
}
