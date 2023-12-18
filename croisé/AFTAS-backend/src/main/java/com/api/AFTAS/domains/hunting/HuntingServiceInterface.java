package com.api.AFTAS.domains.hunting;

import com.api.AFTAS.config.CrudInterface;
import com.api.AFTAS.domains.competition.DTOs.CompetitionReqDTO;
import com.api.AFTAS.domains.competition.DTOs.CompetitionRespDTO;
import com.api.AFTAS.domains.hunting.DTOs.HuntingReqDTO;
import com.api.AFTAS.domains.hunting.DTOs.HuntingRespDTO;
import com.api.AFTAS.domains.level.DTOs.LevelReqDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HuntingServiceInterface extends CrudInterface<HuntingReqDTO,HuntingRespDTO,Integer>{
}
