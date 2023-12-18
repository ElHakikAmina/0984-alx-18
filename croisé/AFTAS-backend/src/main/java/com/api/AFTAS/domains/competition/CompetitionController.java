package com.api.AFTAS.domains.competition;

import com.api.AFTAS.config.CrudController;
import com.api.AFTAS.domains.competition.DTOs.CompetitionReqDTO;
import com.api.AFTAS.domains.competition.DTOs.CompetitionRespDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("competetion")
public class CompetitionController extends CrudController<CompetitionReqDTO, CompetitionRespDTO,String,CompetitionServiceInterface> {
    @GetMapping("all")
    public ResponseEntity<Page<CompetitionRespDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok().body(this.service.getAllWithPagination(pageable));
    }
}
