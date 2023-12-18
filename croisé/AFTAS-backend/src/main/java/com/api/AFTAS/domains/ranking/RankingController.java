package com.api.AFTAS.domains.ranking;

import com.api.AFTAS.config.ResponseMessage;
import com.api.AFTAS.domains.ranking.DTOs.RankingIdReqDTO;
import com.api.AFTAS.domains.ranking.DTOs.RankingReqDTO;
import com.api.AFTAS.domains.ranking.DTOs.RankingRespDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("ranking")
public class RankingController {
    @Autowired
    protected RankingServiceInterface rankingService;
    @Autowired
    protected ResponseMessage responseMessage;
    RankingIdReqDTO rankingIdReqDTO = new RankingIdReqDTO();

    @PostMapping
    public ResponseEntity<RankingRespDTO> create(@Valid @RequestBody RankingReqDTO rankingReqDTO) {
        RankingRespDTO ranking = rankingService.create(rankingReqDTO);
        if(ranking != null)
        {
            return ResponseEntity.ok().body(ranking);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PutMapping("/{competition_code}/{member_id}")
    public ResponseEntity<RankingRespDTO> update(@Valid @RequestBody RankingReqDTO rankingReqDTO,@PathVariable String competition_code,@PathVariable Integer member_id) {
        rankingIdReqDTO.setMember_num(member_id);
        rankingIdReqDTO.setCompetition_code(competition_code);
        RankingRespDTO ranking = rankingService.update(rankingReqDTO,rankingIdReqDTO);
        if(ranking != null)
        {
            return ResponseEntity.ok().body(ranking);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/{competition_code}/{member_id}")
    public ResponseEntity<Object> delete(@PathVariable String competition_code,@PathVariable Integer member_id) {
        rankingIdReqDTO.setMember_num(member_id);
        rankingIdReqDTO.setCompetition_code(competition_code);
        Integer deleted = rankingService.delete(rankingIdReqDTO);
        if(deleted == 1)
        {
            this.responseMessage.setMessage("deleted");
            return ResponseEntity.ok().body(this.responseMessage);
        }
        this.responseMessage.setMessage("not deleted");
        return ResponseEntity.badRequest().body(this.responseMessage);
    }


    @GetMapping
    public ResponseEntity<List<RankingRespDTO>> getAll() {
        return ResponseEntity.ok().body(rankingService.getAll());
    }


    @GetMapping("/{competition_code}/{member_id}")
    public ResponseEntity<RankingRespDTO> getOne(@PathVariable String competition_code,@PathVariable Integer member_id) {
        rankingIdReqDTO.setMember_num(member_id);
        rankingIdReqDTO.setCompetition_code(competition_code);
        return ResponseEntity.ok().body(rankingService.getOne(rankingIdReqDTO));
    }
    @GetMapping("/done/{competitionCode}")
    public ResponseEntity<List<RankingRespDTO>> getRankingsForCompetition(@PathVariable String competitionCode) {
        List<RankingRespDTO> rankings = rankingService.calculateAndFetchRankings(competitionCode);
        return ResponseEntity.ok(rankings);
    }
    @GetMapping("/rapport/{competitionCode}")
    public ResponseEntity<List<RankingRespDTO>> getRapportForCompetition(@PathVariable String competitionCode) {
        List<RankingRespDTO> rankings = rankingService.getRankings(competitionCode);
        return ResponseEntity.ok(rankings);
    }
}
