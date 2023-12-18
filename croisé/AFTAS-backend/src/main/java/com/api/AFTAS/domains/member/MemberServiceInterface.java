package com.api.AFTAS.domains.member;

import com.api.AFTAS.config.CrudInterface;
import com.api.AFTAS.domains.competition.DTOs.CompetitionReqDTO;
import com.api.AFTAS.domains.competition.DTOs.CompetitionRespDTO;
import com.api.AFTAS.domains.member.DTOs.MemberReqDTO;
import com.api.AFTAS.domains.member.DTOs.MemberRespDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberServiceInterface extends CrudInterface<MemberReqDTO, MemberRespDTO,Integer> {
}
