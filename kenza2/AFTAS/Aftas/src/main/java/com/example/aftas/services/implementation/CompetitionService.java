package com.example.aftas.services.implementation;

import com.example.aftas.dto.CompetitionReq;
import com.example.aftas.dto.CompetitionResp;
import com.example.aftas.entity.Competition;
import com.example.aftas.exception.ResourceNotFoundException;
import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.services.interfaces.CompetitionServiceInterface;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompetitionService implements CompetitionServiceInterface {


    private final CompetitionRepository competitionRepository;
    private final ModelMapper modelMapper;

    public CompetitionService(CompetitionRepository competitionRepository, ModelMapper modelMapper) {
        this.competitionRepository = competitionRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Optional<CompetitionResp> AddCompetition(CompetitionReq competition) {
        Competition competitionToSave = modelMapper.map(competition , Competition.class);
        LocalDate currentDate = LocalDate.now();
        LocalDate minDate = currentDate.plusDays(2);
        if (competitionToSave.getDate().isBefore(minDate)) {
            throw new IllegalArgumentException("Date should be at least 48 hours from now");
        }else{
            if(competitionRepository.existsById(competitionToSave.getCode())){
                throw new IllegalArgumentException("Code must be unique");
            }else{
                competitionRepository.save(competitionToSave);
                return Optional.of(modelMapper.map(competitionToSave, CompetitionResp.class));
            }
        }
    }

    @Override
    public Optional<CompetitionResp> findById(String code) {
        Optional<Competition> competition = competitionRepository.findById(code);
        if(competition.isPresent()){
            return Optional.of(modelMapper.map(competition, CompetitionResp.class));
        }else{
            throw new ResourceNotFoundException("Competition not found with ID : " + code);
        }
    }

    @Override
    public List<CompetitionResp> getAllCompetitions(int page, int size) {
        Page<Competition> competitionsPage = competitionRepository.findAll(PageRequest.of(page, size));
        List<Competition> competitions = competitionsPage.getContent();
        return competitions.stream()
                .map(level -> modelMapper.map(level, CompetitionResp.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CompetitionResp> updateCompetition(String competitionCode, CompetitionReq competition) {
        Optional<Competition> competitionToUpdate = competitionRepository.findById(competitionCode);
        if(competitionToUpdate.isPresent()){
            LocalDate currentDate = LocalDate.now();
            LocalDate minDate = currentDate.plusDays(2);
            if (competition.getDate().isBefore(minDate)) {
                throw new IllegalArgumentException("Date should be at least 48 hours from now");
            }else{
                competitionToUpdate.get().setAmount(competition.getAmount());
                competitionToUpdate.get().setLocation(competition.getLocation());
                competitionToUpdate.get().setStartTime(competition.getStartTime());
                competitionToUpdate.get().setEndTime(competition.getEndTime());
                competitionToUpdate.get().setDate(competition.getDate());
                competitionToUpdate.get().setNumberOfParticipants(competition.getNumberOfParticipants());
                competitionRepository.save(competitionToUpdate.get());
                return Optional.ofNullable(modelMapper.map(competitionToUpdate.get(), CompetitionResp.class));
            }
        }else{
            throw new ResourceNotFoundException("No competition with this code : " + competitionCode);
        }
    }

    @Transactional
    @Override
    public Optional<CompetitionResp> deleteCompetition(String code) {
        Optional<Competition> competition = competitionRepository.findById(code);
        if(competition.isPresent()){
            competitionRepository.delete(competition.get());
            return Optional.of(modelMapper.map(competition, CompetitionResp.class));
        }else{
            throw new ResourceNotFoundException("Competition not found with ID : " + code);
        }
    }

    public long getTotalPages(Integer size) {
        long totalCompetitions = competitionRepository.count();
        return (totalCompetitions + size - 1) / size; // Calculate total pages
    }
}
