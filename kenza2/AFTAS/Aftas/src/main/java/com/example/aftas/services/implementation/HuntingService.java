package com.example.aftas.services.implementation;

import com.example.aftas.dto.HuntingReq;
import com.example.aftas.dto.HuntingResp;
import com.example.aftas.entity.*;
import com.example.aftas.exception.ResourceNotFoundException;
import com.example.aftas.repository.*;
import com.example.aftas.services.interfaces.HuntingServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HuntingService implements HuntingServiceInterface {

    private final HuntingRepository huntingRepository;
    private final ModelMapper modelMapper;
    private final CompetitionRepository competitionRepository;
    private final FishRepository fishRepository;
    private final MemberRepository memberRepository;
    private final RankingRepository rankingRepository;


    public HuntingService(HuntingRepository huntingRepository, ModelMapper modelMapper, CompetitionRepository competitionRepository, FishRepository fishRepository, MemberRepository memberRepository, RankingRepository rankingRepository) {
        this.huntingRepository = huntingRepository;
        this.modelMapper = modelMapper;
        this.competitionRepository = competitionRepository;
        this.fishRepository = fishRepository;
        this.memberRepository = memberRepository;
        this.rankingRepository = rankingRepository;
    }

    @Override
    public List<HuntingResp> getAllHunts() {
        List<Hunting> hunts = huntingRepository.findAll();
        return hunts.stream().map(hunt -> modelMapper.map(hunt, HuntingResp.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<HuntingResp> getHuntingByCode(Long huntingCode) {
        Optional<Hunting> hunting = huntingRepository.findById(huntingCode);
        if(hunting.isPresent()){
            return Optional.of(modelMapper.map(hunting, HuntingResp.class));
        }else{
            throw new ResourceNotFoundException("Hunting not found with ID : " + huntingCode);
        }
    }

    @Override
    public List<HuntingResp> getHuntByCompetition(String competitionCode) {
        Competition competition = competitionRepository.findById(competitionCode).orElseThrow(() -> new ResourceNotFoundException("Invalid competition Code"));
        List<Hunting> hunts = huntingRepository.findByCompetition(competition);
        return hunts.stream().map((hunt) -> modelMapper.map(hunt , HuntingResp.class)).collect(Collectors.toList());
    }

    @Override
    public List<HuntingResp> getHuntByMember(Long memberCode) {
        Member member = memberRepository.findById(memberCode).orElseThrow(() -> new ResourceNotFoundException("Invalid Member code"));
        List<Hunting> hunts = huntingRepository.findByMember(member);
        return hunts.stream().map((hunt) -> modelMapper.map(hunt , HuntingResp.class)).collect(Collectors.toList());
    }

    @Override
    public List<HuntingResp> getHuntByFish(String fishName) {
        Fish fish = fishRepository.findById(fishName).orElseThrow(() -> new ResourceNotFoundException("Invalid Fish code"));
        List<Hunting> hunts = huntingRepository.findByFish(fish);
        return hunts.stream().map((hunt) -> modelMapper.map(hunt , HuntingResp.class)).collect(Collectors.toList());

    }

    @Override
    public List<HuntingResp> getHuntByMemberInParticipant(String code, Long num) {
        Competition competition = competitionRepository.findById(code).orElseThrow(() -> new ResourceNotFoundException("Invalid competition Code"));
        Member member = memberRepository.findById(num).orElseThrow(() -> new ResourceNotFoundException("Invalid Member code"));
        RankingId rankingId = new RankingId(competition.getCode(), member.getNum());
        rankingRepository.findById(rankingId).orElseThrow(()-> new ResourceNotFoundException("This member : "+ member.getName() + member.getFamilyName()+" is not registered in this competition" + competition.getCode()));
        List<Hunting> hunts = huntingRepository.findByCompetitionAndMember(competition,member);
        return hunts.stream().map((hunt) -> modelMapper.map(hunt , HuntingResp.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<HuntingResp> createHunting(HuntingReq hunting) {
        Hunting huntingToSave = modelMapper.map(hunting , Hunting.class);
        Competition competition = competitionRepository.findById(hunting.getCompetition()).orElseThrow(() -> new ResourceNotFoundException("Invalid competition Code"));
        Member member = memberRepository.findById(hunting.getMember()).orElseThrow(() -> new ResourceNotFoundException("Invalid Member code"));
        Fish fish = fishRepository.findById(hunting.getFish()).orElseThrow(() -> new ResourceNotFoundException("Invalid Fish code"));
        RankingId rankingId = new RankingId(competition.getCode(), member.getNum());
        rankingRepository.findById(rankingId).orElseThrow(()-> new ResourceNotFoundException("This member : "+ member.getName() + member.getFamilyName()+" is not registered in this competition" + competition.getCode()));
        Optional<Hunting> huntingFound = huntingRepository.findByCompetitionAndFishAndMember(competition , fish ,member);
        if(huntingFound.isPresent()){
            int number = huntingFound.get().getNumberOfFish();
            huntingFound.get().setNumberOfFish(number + huntingToSave.getNumberOfFish());
            huntingRepository.save(huntingFound.get());
            return Optional.of(modelMapper.map(huntingFound , HuntingResp.class));
        }else{
            huntingToSave.setFish(fish);
            huntingToSave.setMember(member);
            huntingToSave.setCompetition(competition);
            huntingRepository.save(huntingToSave);
            return Optional.of(modelMapper.map(huntingToSave , HuntingResp.class));
        }
    }

    @Override
    public Optional<HuntingResp> increment(Long code) {
        Optional<Hunting> huntingFound = huntingRepository.findById(code);
        if(huntingFound.isPresent()){
            huntingFound.get().setNumberOfFish(1 + huntingFound.get().getNumberOfFish());
            huntingRepository.save(huntingFound.get());
            return Optional.of(modelMapper.map(huntingFound , HuntingResp.class));
        }else{
            throw new IllegalArgumentException("No record match");
        }
    }

    @Override
    public Optional<HuntingResp> decrement(Long code) {
        Optional<Hunting> huntingFound = huntingRepository.findById(code);
        if(huntingFound.isPresent()){
            if(huntingFound.get().getNumberOfFish() <= 1){
                throw new IllegalArgumentException("Number of fish can't decrement to less then 1");
            }
            huntingFound.get().setNumberOfFish(huntingFound.get().getNumberOfFish() - 1);
            huntingRepository.save(huntingFound.get());
            return Optional.of(modelMapper.map(huntingFound , HuntingResp.class));
        }else{
            throw new IllegalArgumentException("No record match");
        }
    }


    @Override
    public Optional<HuntingResp> deleteHunting(Long code) {
        Optional<Hunting> hunting = huntingRepository.findById(code);
        if(hunting.isPresent()){
            hunting.get().setMember(null);
            hunting.get().setCompetition(null);
            hunting.get().setFish(null);
            huntingRepository.delete(hunting.get());
            return Optional.of(modelMapper.map(hunting, HuntingResp.class));
        }else{
            throw new ResourceNotFoundException("Hunting not found with ID : " + code);
        }
    }
}
