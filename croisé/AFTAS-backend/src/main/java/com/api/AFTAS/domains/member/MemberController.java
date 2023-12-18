package com.api.AFTAS.domains.member;

import com.api.AFTAS.config.CrudController;
import com.api.AFTAS.domains.competition.DTOs.CompetitionReqDTO;
import com.api.AFTAS.domains.competition.DTOs.CompetitionRespDTO;
import com.api.AFTAS.domains.member.DTOs.MemberReqDTO;
import com.api.AFTAS.domains.member.DTOs.MemberRespDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("member")
public class MemberController extends CrudController<MemberReqDTO, MemberRespDTO,Integer, MemberServiceInterface> {
}
