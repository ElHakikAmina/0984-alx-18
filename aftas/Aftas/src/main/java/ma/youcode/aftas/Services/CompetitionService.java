package ma.youcode.aftas.Services;

import ma.youcode.aftas.Models.Dtos.CompetitionDto.CompetitionRequestDto;
import ma.youcode.aftas.Models.Entities.Competition;
import ma.youcode.aftas.Repositories.CompetitionRepository;
import org.apache.catalina.connector.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository, ModelMapper modelMapper) {
        this.competitionRepository = competitionRepository;
        this.modelMapper = modelMapper;
    }

    public List<CompetitionRequestDto> getAllCompetitions() {
        List<Competition> competitions = competitionRepository.findAll();
        return competitions.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CompetitionRequestDto convertToDto(Competition competition) {
        return modelMapper.map(competition, CompetitionRequestDto.class);
    }
    public Competition getCompetitionById(Long id) {
        return competitionRepository.findById(id).orElse(null);
    }

    public CompetitionRequestDto createCompetition(CompetitionRequestDto competitionDto) {
        String location = competitionDto.getLocation();
        String code = generateCompetitionCode(location);
        Competition competition = modelMapper.map(competitionDto, Competition.class);
        competition.setCode(code);

        if (competitionRepository.existsByCode(competition.getCode())) {
            throw new IllegalArgumentException("Competition with code '" + competition.getCode() + "' already exists.");
        }

        if (competitionRepository.existsByDate(competitionDto.getDate())) {
            throw new IllegalArgumentException("Already exists a competition in the same date");
        }

        Competition savedCompetition = competitionRepository.save(competition);
        return modelMapper.map(savedCompetition, CompetitionRequestDto.class);
    }
    public CompetitionRequestDto updateCompetition(CompetitionRequestDto competitionDto) {
        String code = generateCompetitionCode(competitionDto.getLocation());
        Competition competition = modelMapper.map(competitionDto, Competition.class);
        competition.setCode(code);
        Competition savedCompetition = competitionRepository.save(competition);
        return modelMapper.map(savedCompetition, CompetitionRequestDto.class);
    }
    public void deleteById(Long id) {
        competitionRepository.deleteById(id);
    }

    private String generateCompetitionCode(String location) {
        String locationCode = location.substring(0, Math.min(location.length(), 3));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
        String formattedDate = dateFormat.format(new Date());
        return locationCode.toLowerCase() + "-" + formattedDate;
    }
}
