package com.example.aftas.services.interfaces;

import com.example.aftas.dto.FishReq;
import com.example.aftas.dto.FishResp;

import java.util.List;
import java.util.Optional;

public interface FishServiceInterface {
    Optional<FishResp> AddFish(FishReq fish);
    Optional<FishResp> findByName(String name);
    List<FishResp> getAllFishes(int page, int size);
    List<FishResp> getByLevel(Long levelId);
    Optional<FishResp> updateFish(String fishName , FishReq fish);
    Optional<FishResp> deleteFish(String name);
    List<FishResp> getRandomFishes();
}
