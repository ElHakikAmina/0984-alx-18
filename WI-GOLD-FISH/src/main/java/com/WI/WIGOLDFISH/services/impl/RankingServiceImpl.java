package com.WI.WIGOLDFISH.services.impl;

import com.WI.WIGOLDFISH.entities.competition.Competition;
import com.WI.WIGOLDFISH.entities.member.Member;
import com.WI.WIGOLDFISH.entities.ranking.Ranking;
import com.WI.WIGOLDFISH.entities.ranking.RankingDtoReq;
import com.WI.WIGOLDFISH.entities.ranking.RankingDtoRes;
import com.WI.WIGOLDFISH.exceptions.ResourceNotFound;
import com.WI.WIGOLDFISH.ids.RankingId;
import com.WI.WIGOLDFISH.repositories.CompetitionRepository;
import com.WI.WIGOLDFISH.repositories.MemberRepository;
import com.WI.WIGOLDFISH.repositories.RankingRepository;
import com.WI.WIGOLDFISH.services.interfaces.RankingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService {
    private final RankingRepository rankingRepository;
    private final CompetitionRepository competitionRepository;
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;

    @Override
    public RankingDtoReq save(RankingDtoReq dtoMini) {
        Competition competition = competitionRepository.findById(dtoMini.getCompetition_id())
                .orElseThrow(() -> new ResourceNotFound("Competition not found"));
        Member member = memberRepository.findById(dtoMini.getMember_id()).orElseThrow(() -> new ResourceNotFound("Member not found"));
        Ranking ranking = modelMapper.map(dtoMini, Ranking.class);
        ranking.setCompetition(new Competition(dtoMini.getCompetition_id()));
        ranking.setMember(new Member(dtoMini.getMember_id()));
        RankingId rankingId = new RankingId();
        rankingId.setCompetition(competition);
        rankingId.setMember(member);
        ranking.setRankingId(rankingId);
        ranking = rankingRepository.save(ranking);
        RankingDtoReq rankingDtoReq = modelMapper.map(ranking, RankingDtoReq.class);
        rankingDtoReq.setCompetition_id(ranking.getCompetition().getCode());
        rankingDtoReq.setMember_id(ranking.getMember().getNum());
        return rankingDtoReq;
    }

    @Override
    public RankingDtoReq update(RankingDtoReq dtoMini, RankingId rankingId) {
        competitionRepository.findById(dtoMini.getCompetition_id())
                .orElseThrow(() -> new ResourceNotFound("Competition not found"));
        memberRepository.findById(dtoMini.getMember_id()).orElseThrow(() -> new ResourceNotFound("Member not found"));
        Ranking ranking = modelMapper.map(dtoMini, Ranking.class);
        ranking.setCompetition(new Competition(dtoMini.getCompetition_id()));
        ranking.setMember(new Member(dtoMini.getMember_id()));
        ranking.setRankingId(rankingId);
        ranking = rankingRepository.save(ranking);
        return modelMapper.map(ranking, RankingDtoReq.class);
    }

    @Override
    public Boolean delete(RankingId rankingId) {
        rankingRepository.findById(rankingId).orElseThrow(() -> new ResourceNotFound("Ranking not found"));
        rankingRepository.deleteById(rankingId);
        if (rankingRepository.findById(rankingId).isPresent()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public RankingDtoRes findOne(RankingId rankingId) {
        memberRepository.findById(rankingId.getMember().getNum()).orElseThrow(() -> new ResourceNotFound("Member not found"));
        competitionRepository.findById(rankingId.getCompetition().getCode())
                .orElseThrow(() -> new ResourceNotFound("Competition not found"));
        Ranking ranking = rankingRepository.findById(rankingId)
                .orElseThrow(() -> new ResourceNotFound("Ranking not found"));
        return modelMapper.map(ranking, RankingDtoRes.class);
    }

    @Override
    public List<RankingDtoRes> findAll() {
        List<RankingDtoRes> rankingDtoRes = rankingRepository.findAll().stream()
                .map(ranking -> modelMapper.map(ranking, RankingDtoRes.class)).toList();
        return rankingDtoRes;
    }

    @Override
    public List<RankingDtoRes> findAllByCompetition_CodeOrderByScoreDesc(String competitionCode) {
        List<RankingDtoRes> rankingDtoRes = rankingRepository.findAllByCompetition_CodeOrderByScoreDesc(competitionCode).stream()
                .map(ranking -> modelMapper.map(ranking, RankingDtoRes.class)).toList();
        return rankingDtoRes;
    }
}
