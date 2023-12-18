package com.example.aftas.services.implementation;

import com.example.aftas.dto.FishReq;
import com.example.aftas.dto.FishResp;
import com.example.aftas.entity.Fish;
import com.example.aftas.entity.Level;
import com.example.aftas.exception.ResourceNotFoundException;
import com.example.aftas.repository.FishRepository;
import com.example.aftas.repository.LevelRepository;
import com.example.aftas.services.interfaces.FishServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FishService implements FishServiceInterface {

    private final FishRepository fishRepository;
    private final LevelRepository levelRepository;
    private final ModelMapper modelMapper;

    public FishService(FishRepository fishRepository, LevelRepository levelRepository, ModelMapper modelMapper) {
        this.fishRepository = fishRepository;
        this.levelRepository = levelRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Optional<FishResp> AddFish(FishReq fish) {
        Fish fishToSave = modelMapper.map(fish , Fish.class);
        if(fishRepository.existsById(fishToSave.getName())){
            throw new IllegalArgumentException("Fish name must be unique");
        }else{
            Level level = levelRepository.findById(fish.getLevel_id()).orElseThrow(() -> new IllegalArgumentException("Invalid level ID"));
            fishToSave.setLevel(level);
            fishRepository.save(fishToSave);
            return Optional.of(modelMapper.map(fishToSave, FishResp.class));
        }
    }

    @Override
    public Optional<FishResp> findByName(String name) {
        Optional<Fish> fish = fishRepository.findById(name);
        if(fish.isPresent()){
            return Optional.of(modelMapper.map(fish, FishResp.class));
        }else{
            throw new ResourceNotFoundException("Fish not found with name : " + name);
        }
    }

    @Override
    public List<FishResp> getAllFishes(int page, int size) {
        Page<Fish> fishesPage = fishRepository.findAll(PageRequest.of(page, size));
        List<Fish> fishes = fishesPage.getContent();
        return fishes.stream()
                .map(fish -> modelMapper.map(fish, FishResp.class))
                .collect(Collectors.toList());
    }

    public List<FishResp> getRandomFishes() {
        List<Fish> fishes = fishRepository.getRandomFishes();
        return fishes.stream()
                .map(fish -> modelMapper.map(fish, FishResp.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<FishResp> getByLevel(Long levelId) {
        Level level = levelRepository.findById(levelId).orElseThrow(() -> new IllegalArgumentException("Invalid level ID"));
        List<Fish> fishesByLevel = fishRepository.findByLevel(level);
        return fishesByLevel.stream()
                .map(fish -> modelMapper.map(fish, FishResp.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FishResp> updateFish(String fishName, FishReq fish) {
        Optional<Fish> fishToUpdate = fishRepository.findById(fishName);
        if(fishToUpdate.isPresent()){
            Level level = levelRepository.findById(fish.getLevel_id()).orElseThrow(() -> new IllegalArgumentException("Invalid level ID"));
            fishToUpdate.get().setLevel(level);
            fishToUpdate.get().setName(fish.getName());
            fishToUpdate.get().setAverageWeight(fish.getAverageWeight());
            fishRepository.save(fishToUpdate.get());
            return Optional.ofNullable(modelMapper.map(fishToUpdate, FishResp.class));
        }else{
            throw new ResourceNotFoundException("Fish not found with name : " + fishName);
        }
    }

    @Override
    public Optional<FishResp> deleteFish(String name) {
        Optional<Fish> fish = fishRepository.findById(name);
        if(fish.isPresent()){
            fish.get().setLevel(null);
            fishRepository.delete(fish.get());
            return Optional.of(modelMapper.map(fish, FishResp.class));
        }else{
            throw new ResourceNotFoundException("Fish not found with name : " + name);
        }
    }
}
