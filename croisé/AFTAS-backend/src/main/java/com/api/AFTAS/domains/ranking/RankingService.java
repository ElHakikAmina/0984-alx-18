package com.api.AFTAS.domains.ranking;

import com.api.AFTAS.domains.competition.Competition;
import com.api.AFTAS.domains.competition.CompetitionRepository;
import com.api.AFTAS.domains.hunting.Hunting;
import com.api.AFTAS.domains.hunting.HuntingRepository;
import com.api.AFTAS.domains.member.Member;
import com.api.AFTAS.domains.member.MemberRepository;
import com.api.AFTAS.domains.ranking.DTOs.RankingIdReqDTO;
import com.api.AFTAS.domains.ranking.DTOs.RankingReqDTO;
import com.api.AFTAS.domains.ranking.DTOs.RankingRespDTO;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class RankingService implements RankingServiceInterface {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RankingRepository rankingRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired

    private CompetitionRepository competitionRepository;
    @Autowired
    private HuntingRepository huntingRepository;

    Optional<Member> member;
    Optional<Competition> competition;
    RankingId rankingId =new RankingId();

    @Override
    public RankingRespDTO create(RankingReqDTO rankingReqDTO) {
        Ranking rankingE = modelMapper.map(rankingReqDTO, Ranking.class);
        member = memberRepository.findById(rankingReqDTO.getId().getMember_num());
        competition = competitionRepository.findById(rankingReqDTO.getId().getCompetition_code());

        if (member.isPresent() && competition.isPresent()) {

            long daysDifference = ChronoUnit.DAYS.between(LocalDate.now(),competition.get().getDate());

            if (daysDifference > 1) {
                rankingId.setCompetition(competition.get());
                rankingId.setMember(member.get());
                rankingE.setId(rankingId);
                rankingE = rankingRepository.save(rankingE);
                return modelMapper.map(rankingE, RankingRespDTO.class);
            }

        }

        return null;
    }

    @Override
    public RankingRespDTO update(RankingReqDTO rankingReqDTO, RankingIdReqDTO rankingIdReqDTO) {
        Optional<Ranking> rankingE = rankingRepository.findById(modelMapper.map(rankingIdReqDTO,RankingId.class));
        if(rankingE.isPresent())
        {
            member = memberRepository.findById(rankingReqDTO.getId().getMember_num());
            competition = competitionRepository.findById(rankingReqDTO.getId().getCompetition_code());

            if (member.isPresent() && competition.isPresent()) {
                rankingE.get().setId(modelMapper.map(rankingIdReqDTO, RankingId.class));
                rankingE = Optional.of(rankingRepository.save(rankingE.get()));
                return modelMapper.map(rankingE.get(), RankingRespDTO.class);
            }
        }
        return null;
    }

    @Override
    public Integer delete(RankingIdReqDTO rankingIdReqDTO) {
        Optional<Ranking> ranking = rankingRepository.findById(modelMapper.map(rankingIdReqDTO,RankingId.class));
        if(ranking.isPresent()) {
            rankingRepository.delete(ranking.get());
            return 1;
        }else return 0;
    }

    @Override
    public List<RankingRespDTO> getAll() {
        return  rankingRepository.findAll()
                .stream()
                .map(ranking -> modelMapper.map(ranking,RankingRespDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RankingRespDTO getOne(RankingIdReqDTO rankingIdReqDTO) {
        Optional<Ranking> ranking = rankingRepository.findById(modelMapper.map(rankingIdReqDTO,RankingId.class));
        return ranking.map(value -> modelMapper.map(value, RankingRespDTO.class)).orElse(null);
    }
    @Override
    public List<RankingRespDTO> getRankings(String competitionCode){
        return rankingRepository.getAllByCompetition(competitionCode)
                .stream().map(ranking -> modelMapper.map(ranking,RankingRespDTO.class))
                .collect(Collectors.toList());
    }
    public List<RankingRespDTO> calculateAndFetchRankings(String competitionCode) {
        List<Ranking> rankings = rankingRepository.calculateRankingsForCompetition(competitionCode);
        return IntStream.range(0,rankings.size()).mapToObj(i -> {
            rankings.get(i).setRank(i+1);
            return modelMapper.map(rankingRepository.save(rankings.get(i)),RankingRespDTO.class);
        }).collect(Collectors.toList());
    }
}
