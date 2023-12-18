package com.WI.WIGOLDFISH.services.impl;

import com.WI.WIGOLDFISH.entities.competition.Competition;
import com.WI.WIGOLDFISH.entities.fish.Fish;
import com.WI.WIGOLDFISH.entities.hunting.Hunting;
import com.WI.WIGOLDFISH.entities.hunting.HuntingDtoReq;
import com.WI.WIGOLDFISH.entities.hunting.HuntingDtoRes;
import com.WI.WIGOLDFISH.entities.member.Member;
import com.WI.WIGOLDFISH.exceptions.ResourceNotFound;
import com.WI.WIGOLDFISH.ids.RankingId;
import com.WI.WIGOLDFISH.repositories.*;
import com.WI.WIGOLDFISH.services.interfaces.HuntingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HuntingServiceImpl implements HuntingService {
    private final HuntingRepository huntingRepository;
    @Autowired  
    private ModelMapper modelMapper;
    private final FishRepository fishRepository;
    private final MemberRepository memberRepository;
    private final CompetitionRepository competitionRepository;
    private  final RankingRepository rankingRepository;
    @Override
    public HuntingDtoReq save(HuntingDtoReq dtoMini) {
        Competition competition = competitionRepository.findById(dtoMini.getCompetition_id()).orElseThrow(() -> new ResourceNotFound("Competition not found"));
        Fish fish = fishRepository.findById(dtoMini.getFish_id()).orElseThrow(() -> new ResourceNotFound("Fish not found"));
        Member member = memberRepository.findById(dtoMini.getMember_id()).orElseThrow(() -> new ResourceNotFound("Member not found"));
        Optional<Hunting> optionalHunting = huntingRepository.findByMemberAndCompetitionAndFish(member, competition, fish);
        if (optionalHunting.isPresent()) {
            Hunting hunting = optionalHunting.get();
            hunting.setNumberOfFish(hunting.getNumberOfFish() + dtoMini.getNumberOfFish());
            hunting = huntingRepository.save(hunting);
            HuntingDtoReq huntingDtoReq = modelMapper.map(hunting, HuntingDtoReq.class);
            huntingDtoReq.setCompetition_id(hunting.getCompetition().getCode());
            huntingDtoReq.setFish_id(hunting.getFish().getName());
            huntingDtoReq.setMember_id(hunting.getMember().getNum());
            RankingId rankingId = new RankingId();
            rankingId.setCompetition(competition);
            rankingId.setMember(member);
            rankingRepository.findById(rankingId).ifPresent(ranking -> {;
                ranking.setScore(ranking.getScore() +  (huntingDtoReq.getNumberOfFish() * fish.getLevel().getPoints()));
                rankingRepository.save(ranking);
            });
            return huntingDtoReq;
        }
        Hunting hunting = modelMapper.map(dtoMini, Hunting.class);
        hunting.setCompetition(new Competition(dtoMini.getCompetition_id()));
        hunting.setFish(new Fish(dtoMini.getFish_id()));
        hunting.setMember(new Member(dtoMini.getMember_id()));
        hunting = huntingRepository.save(hunting);
        HuntingDtoReq huntingDtoReq = modelMapper.map(hunting, HuntingDtoReq.class);
        huntingDtoReq.setCompetition_id(hunting.getCompetition().getCode());
        huntingDtoReq.setFish_id(hunting.getFish().getName());
        huntingDtoReq.setMember_id(hunting.getMember().getNum());
        RankingId rankingId = new RankingId();
        rankingId.setCompetition(competition);
        rankingId.setMember(member);
        rankingRepository.findById(rankingId).ifPresent(ranking -> {;
            ranking.setScore(ranking.getScore() +  (huntingDtoReq.getNumberOfFish() * fish.getLevel().getPoints()));
            rankingRepository.save(ranking);
        });
        return huntingDtoReq;
    }

    @Override
    public HuntingDtoReq update(HuntingDtoReq dtoMini, Long aLong) {
        competitionRepository.findById(dtoMini.getCompetition_id()).orElseThrow(() -> new ResourceNotFound("Competition not found"));
        fishRepository.findById(dtoMini.getFish_id()).orElseThrow(() -> new ResourceNotFound("Fish not found"));
        memberRepository.findById(dtoMini.getMember_id()).orElseThrow(() -> new ResourceNotFound("Member not found"));
        Hunting hunting = modelMapper.map(dtoMini, Hunting.class);
        hunting.setCompetition(new Competition(dtoMini.getCompetition_id()));
        hunting.setFish(new Fish(dtoMini.getFish_id()));
        hunting.setMember(new Member(dtoMini.getMember_id()));
        hunting.setId(aLong);
        hunting = huntingRepository.save(hunting);
        return modelMapper.map(hunting, HuntingDtoReq.class);
    }

    @Override
    public Boolean delete(Long aLong) {
        huntingRepository.findById(aLong).orElseThrow(() -> new ResourceNotFound("Hunting not found"));
        huntingRepository.deleteById(aLong);
        if (huntingRepository.findById(aLong).isPresent()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public HuntingDtoRes findOne(Long aLong) {
        Hunting hunting = huntingRepository.findById(aLong).orElseThrow(() -> new ResourceNotFound("Hunting not found"));
        return modelMapper.map(hunting, HuntingDtoRes.class);
    }

    @Override
    public List<HuntingDtoRes> findAll() {
        return huntingRepository.findAll().stream().map(hunting -> modelMapper.map(hunting, HuntingDtoRes.class)).toList();
    }
}
