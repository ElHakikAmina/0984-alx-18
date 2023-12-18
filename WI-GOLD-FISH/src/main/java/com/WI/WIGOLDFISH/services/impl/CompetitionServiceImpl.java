package com.WI.WIGOLDFISH.services.impl;

import com.WI.WIGOLDFISH.entities.competition.Competition;
import com.WI.WIGOLDFISH.entities.competition.CompetitionDtoReq;
import com.WI.WIGOLDFISH.entities.competition.CompetitionDtoRes;
import com.WI.WIGOLDFISH.enums.FilterCompetition;
import com.WI.WIGOLDFISH.exceptions.DuplicatedResource;
import com.WI.WIGOLDFISH.exceptions.ResourceNotFound;
import com.WI.WIGOLDFISH.repositories.CompetitionRepository;
import com.WI.WIGOLDFISH.services.interfaces.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;

    private final ModelMapper modelMapper;

    @Override
    public CompetitionDtoReq save(CompetitionDtoReq dtoMini) {
        competitionRepository.findByCode(dtoMini.getCode()).ifPresent(competition -> {
            throw new DuplicatedResource("Competition already exists");
        });
        Competition competition = modelMapper.map(dtoMini, Competition.class);
        competition = competitionRepository.save(competition);
        return modelMapper.map(competition, CompetitionDtoReq.class);
    }

    @Override
    public CompetitionDtoReq update(CompetitionDtoReq dtoMini, String s) {
        Competition competition = modelMapper.map(dtoMini, Competition.class);
        competition.setCode(s);
        competitionRepository.findById(s).orElseThrow(() -> new ResourceNotFound("Competition not found"));
        competition = competitionRepository.save(competition);
        return modelMapper.map(competition, CompetitionDtoReq.class);
    }

    @Override
    public Boolean delete(String s) {
        competitionRepository.findById(s).orElseThrow(() -> new ResourceNotFound("Competition not found"));
        competitionRepository.deleteById(s);
        if (competitionRepository.findById(s).isPresent()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public CompetitionDtoRes findOne(String s) {
        Competition competition = competitionRepository.findById(s).orElseThrow(() -> new ResourceNotFound("Competition not found"));
        return modelMapper.map(competition, CompetitionDtoRes.class);
    }

    @Override
    public List<CompetitionDtoRes> findAll() {
        return competitionRepository.findAll().stream().map(competition -> modelMapper.map(competition, CompetitionDtoRes.class)).toList();
    }

    @Override
    public Page<CompetitionDtoRes> findAll(Pageable pageable, FilterCompetition filterCompetition) {
        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();

        switch (filterCompetition) {
            case ALL:
                return competitionRepository.findAll(pageable)
                        .map(competition -> modelMapper.map(competition, CompetitionDtoRes.class));

            case UPCOMING:
                return competitionRepository.findAllByDateAfter(nowDate, pageable)
                        .map(competition -> modelMapper.map(competition, CompetitionDtoRes.class));

            case INPROGRESS:
                return competitionRepository.findAllByDateAndStartTimeBeforeAndEndTimeAfter(
                                nowDate, nowTime, nowTime, pageable)
                        .map(competition -> modelMapper.map(competition, CompetitionDtoRes.class));

            case FINISHED:
                return competitionRepository.findAllByDateBefore(nowDate, pageable)
                        .map(competition -> modelMapper.map(competition, CompetitionDtoRes.class));

            default:
                throw new IllegalArgumentException("Unsupported filter: " + filterCompetition);
        }
    }


}
