package com.example.aftas.controller;

import com.example.aftas.dto.CompetitionReq;
import com.example.aftas.dto.CompetitionResp;
import com.example.aftas.services.interfaces.CompetitionServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/competitions")
public class CompetitionController {

    private final CompetitionServiceInterface competitionService;

    public CompetitionController(CompetitionServiceInterface competitionService) {
        this.competitionService = competitionService;
    }

    @PostMapping("/add")
    public ResponseEntity<Optional<CompetitionResp>> AddCompetition(@RequestBody CompetitionReq competition){
        Optional<CompetitionResp> competitionSaved = competitionService.AddCompetition(competition);
        return ResponseEntity.ok(competitionSaved);
    }

    @GetMapping("{id}")
    public ResponseEntity<CompetitionResp> findById(@PathVariable String id){
        Optional<CompetitionResp> competition = competitionService.findById(id);
        return ResponseEntity.ok(competition.get());
    }

    @GetMapping("/list")
    public ResponseEntity<List<CompetitionResp>> getAllCompetitions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        List<CompetitionResp> competitions = competitionService.getAllCompetitions(page,size);
        return ResponseEntity.ok(competitions);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<CompetitionResp>> deleteCompetitions(@PathVariable String id){
        Optional<CompetitionResp> competition = competitionService.deleteCompetition(id);
        return ResponseEntity.ok(competition);
    }

    @PutMapping("/update/{competitionCode}")
    public ResponseEntity<Optional<CompetitionResp>> updateCompetition(
            @PathVariable String competitionCode,
            @Valid @RequestBody CompetitionReq competition
    ){
        Optional<CompetitionResp> updatedCompetition = competitionService.updateCompetition(competitionCode,competition);
        return ResponseEntity.ok(updatedCompetition);
    }

    @GetMapping("pages/{size}")
    public ResponseEntity<Long> getNumberOfPages(@PathVariable Integer size){
        return ResponseEntity.ok(competitionService.getTotalPages(size));
    }

}
