package com.example.aftas.services.interfaces;

import com.example.aftas.dto.HuntingReq;
import com.example.aftas.dto.HuntingResp;
import com.example.aftas.dto.RankingReq;

import java.util.List;
import java.util.Optional;

public interface HuntingServiceInterface {
    List<HuntingResp> getAllHunts();

    Optional<HuntingResp> getHuntingByCode(Long huntingCode);

    List<HuntingResp> getHuntByCompetition(String competitionCode);

    List<HuntingResp> getHuntByMember(Long memberCode);

    List<HuntingResp> getHuntByFish(String fishName);

    List<HuntingResp> getHuntByMemberInParticipant(String code , Long num);

    Optional<HuntingResp> createHunting(HuntingReq hunting);

    Optional<HuntingResp> increment(Long code);

    Optional<HuntingResp> decrement(Long code);

    Optional<HuntingResp> deleteHunting(Long code);

}
