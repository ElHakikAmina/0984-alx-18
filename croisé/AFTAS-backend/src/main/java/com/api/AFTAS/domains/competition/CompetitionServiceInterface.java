package com.api.AFTAS.domains.competition;

import com.api.AFTAS.config.CrudInterface;
import com.api.AFTAS.domains.competition.DTOs.CompetitionReqDTO;
import com.api.AFTAS.domains.competition.DTOs.CompetitionRespDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompetitionServiceInterface extends CrudInterface<CompetitionReqDTO, CompetitionRespDTO,String> {
    Page<CompetitionRespDTO> getAllWithPagination(Pageable pageable) ;
}
