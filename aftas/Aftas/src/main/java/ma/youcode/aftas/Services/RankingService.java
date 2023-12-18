package ma.youcode.aftas.Services;

import ma.youcode.aftas.Models.Dtos.RankingDto.RankingRequestDto;
import ma.youcode.aftas.Models.Entities.Competition;
import ma.youcode.aftas.Models.Entities.Ranking;
import ma.youcode.aftas.Repositories.RankingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RankingService {
    private final RankingRepository rankingRepository;
    private final CompetitionService competitionService;
    private final ModelMapper modelMapper;

    @Autowired
    public RankingService(RankingRepository rankingRepository, CompetitionService competitionService, MemberService memberService, ModelMapper modelMapper) {
        this.rankingRepository = rankingRepository;
        this.competitionService = competitionService;
        this.modelMapper = modelMapper;
    }

    public List<RankingRequestDto> getAllRankings() {
        List<Ranking> rankings = rankingRepository.findAll();

        return rankings.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<RankingRequestDto> getAllRankingsByCompetitionId(Long competitionId) {
        List<Ranking> rankings = rankingRepository.findAllByCompetitionId(competitionId);

        return rankings.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<RankingRequestDto> getAllRankingsByMemberId(Long memberId) {
        List<Ranking> rankings = rankingRepository.findAllByMemberId(memberId);

        return rankings.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private RankingRequestDto convertToDto(Ranking ranking) {

        return modelMapper.map(ranking, RankingRequestDto.class);
    }

    public RankingRequestDto getRankingById(Long id) {
        Ranking ranking = rankingRepository.findById(id).orElse(null);

        return modelMapper.map(ranking, RankingRequestDto.class);
    }

    public RankingRequestDto createRanking(RankingRequestDto rankingDto) {
        Long competitionId = rankingDto.getCompetitionId();

        Competition competition = competitionService.getCompetitionById(competitionId);

        LocalDateTime competitionStartDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(competition.getDate().getTime()), ZoneId.systemDefault()).with(competition.getStartTime().toLocalTime());

        LocalDateTime currentDateTime = LocalDateTime.now();

        long differenceInHours = ChronoUnit.HOURS.between(currentDateTime, competitionStartDateTime);

        if (differenceInHours < 24) {
            throw new IllegalStateException("You can't share in a competition that is less than 24 hours before starting");
        }

        Ranking ranking = modelMapper.map(rankingDto, Ranking.class);
        Ranking savedRanking = rankingRepository.save(ranking);

        return modelMapper.map(savedRanking, RankingRequestDto.class);
    }

    public RankingRequestDto updateRanking(RankingRequestDto rankingDto) {

        Ranking ranking = modelMapper.map(rankingDto, Ranking.class);
        Ranking savedRanking = rankingRepository.save(ranking);

        return modelMapper.map(savedRanking, RankingRequestDto.class);
    }

    public void deleteById(Long id) {
        rankingRepository.deleteById(id);
    }
}
