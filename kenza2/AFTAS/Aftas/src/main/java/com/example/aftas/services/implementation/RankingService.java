package com.example.aftas.services.implementation;

import com.example.aftas.dto.*;
import com.example.aftas.entity.*;
import com.example.aftas.exception.ResourceNotFoundException;
import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.repository.MemberRepository;
import com.example.aftas.repository.RankingRepository;
import com.example.aftas.services.interfaces.HuntingServiceInterface;
import com.example.aftas.services.interfaces.RankingServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RankingService implements RankingServiceInterface {

    private final RankingRepository rankingRepository;
    private final CompetitionRepository competitionRepository;
    private final MemberRepository memberRepository;
    private final HuntingServiceInterface huntingService;
    private final ModelMapper modelMapper;

    public RankingService(RankingRepository rankingRepository, CompetitionRepository competitionRepository, MemberRepository memberRepository, HuntingServiceInterface huntingService, ModelMapper modelMapper) {
        this.rankingRepository = rankingRepository;
        this.competitionRepository = competitionRepository;
        this.memberRepository = memberRepository;
        this.huntingService = huntingService;
        this.modelMapper = modelMapper;
    }


    @Override
    public Optional<RankingResp> saveRanking(RankingReq ranking) {
        if(validateRanking(ranking.getCompetition())){
            throw new IllegalArgumentException("Competition is closed : number of participant reached");
        }
        if(validateDate(ranking.getCompetition())){
            throw new IllegalArgumentException("Competition is closed : date of the competition is on less then 24h");
        }
        Competition competition = competitionRepository.findById(ranking.getCompetition()).orElseThrow(() -> new ResourceNotFoundException("Invalid competition Code"));
        Member member = memberRepository.findById(ranking.getMember()).orElseThrow(() -> new ResourceNotFoundException("Invalid Member code"));
        RankingId rankingId = new RankingId(competition.getCode(), member.getNum());
        Ranking rankingToSave = modelMapper.map(ranking , Ranking.class);
        rankingToSave.setCompetition(competition);
        rankingToSave.setMember(member);
        rankingToSave.setId(rankingId);
        Optional<Ranking> isFound = rankingRepository.findById(rankingToSave.getId());
        if(isFound.isPresent()){
            throw new IllegalArgumentException("This member : "+isFound.get().getMember().getName() + " " +isFound.get().getMember().getFamilyName() + " is already registered in the competition "+ isFound.get().getCompetition().getCode());
        }else{
            rankingRepository.save(rankingToSave);
            return Optional.of(modelMapper.map(rankingToSave , RankingResp.class));
        }
    }

    @Override
    public Optional<RankingResp> getRankingById(RankingId id) {
        Optional<Ranking> isFound = rankingRepository.findById(id);
        if(isFound.isPresent()){
            return Optional.ofNullable(modelMapper.map(isFound.get(), RankingResp.class));
        }else throw new IllegalArgumentException("This participant :" + id.getMemberNum() + " is not registered in this competitions" + id.getCompetitionCode());
    }

    @Override
    public List<RankingResp> getRankingsByCompetitionCode(String competitionCode) {
        Competition competition = competitionRepository.findById(competitionCode).orElseThrow(() -> new ResourceNotFoundException("Invalid competition Code"));
        List<Ranking> rankings = rankingRepository.findByCompetition(competition);
        return rankings.stream().map(ranking -> modelMapper.map(ranking, RankingResp.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RankingResp> getRankingsByMemberNumber(Long memberNumber) {
        Member member = memberRepository.findById(memberNumber).orElseThrow(() -> new ResourceNotFoundException("Invalid member Code"));
        List<Ranking> rankings = rankingRepository.findByMember(member);
        return rankings.stream().map(ranking -> modelMapper.map(ranking, RankingResp.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RankingResp> calculateAndSetRankings(String competitionCode) {
        Competition competition = competitionRepository.findById(competitionCode).orElseThrow(() -> new ResourceNotFoundException("Invalid competition Code"));
        List<Ranking> rankings = rankingRepository.findByCompetition(competition);
        for(Ranking r : rankings){
            r.setScore(calculateScoreForRanking(r,competitionCode));
            rankingRepository.save(r);
        }
        setRankForInCompetition(competition);
        return rankings.stream().map((rank) -> modelMapper.map(rank , RankingResp.class)).collect(Collectors.toList());
    }

    private void setRankForInCompetition(Competition competitionCode) {
        List<Ranking> memberRankings = rankingRepository.findByCompetition(competitionCode);
        Collections.sort(memberRankings, Comparator.comparingInt(Ranking::getScore).reversed());

        int rank = 1;
        for (Ranking ranking : memberRankings) {
            ranking.setRank(rank++);
            rankingRepository.save(ranking);
        }
    }

    public int calculateScoreForRanking(Ranking ranking, String competitionCode) {
        int totalScore = 0;
        List<HuntingResp> hunts = huntingService.getHuntByMemberInParticipant(competitionCode, ranking.getMember().getNum());
        for (HuntingResp hunt : hunts) {
            int score = hunt.getFish().getLevel().getPoint() * hunt.getNumberOfFish();
            totalScore += score;
        }

        return totalScore;
    }

    @Override
    public List<RankingResp> getPodiumByCompetitionCode(String competitionCode) {
        List<RankingResp> allRankings = getRankingsByCompetitionCode(competitionCode);
        allRankings.sort(Comparator.comparingInt(RankingResp::getScore).reversed());
        List<RankingResp> podium = allRankings.stream()
                .limit(3)
                .collect(Collectors.toList());

        return podium;
    }


    @Override
    public Optional<RankingResp> deleteRankingById(RankingId id) {
        Optional<Ranking> isFound = rankingRepository.findById(id);
        if(isFound.isPresent()){
            isFound.get().setCompetition(null);
            isFound.get().setMember(null);
            rankingRepository.delete(isFound.get());
            return Optional.ofNullable(modelMapper.map(isFound, RankingResp.class));
        }else throw new IllegalArgumentException("This participant :" + id.getMemberNum() + " is not registered in this competitions" + id.getCompetitionCode());
    }

    @Override
    public boolean validateRanking(String competitionCode) {
        Competition competition = competitionRepository.findById(competitionCode).orElseThrow(() -> new IllegalArgumentException("Invalid competition Code"));
        int numberOfParticipant = rankingRepository.countRankingByCompetition(competition);
        return numberOfParticipant >= competition.getNumberOfParticipants();
    }

    @Override
    public boolean validateDate(String competitionCode) {
        Competition competition = competitionRepository.findById(competitionCode).orElseThrow(() -> new IllegalArgumentException("Invalid competition Code"));
        LocalDate currentDate = LocalDate.now();
        LocalDate minDate = currentDate.plusDays(1);
        return minDate.isAfter(competition.getDate());
    }
}
