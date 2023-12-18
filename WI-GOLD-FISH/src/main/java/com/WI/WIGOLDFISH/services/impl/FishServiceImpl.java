package com.WI.WIGOLDFISH.services.impl;

import com.WI.WIGOLDFISH.entities.fish.Fish;
import com.WI.WIGOLDFISH.entities.fish.FishDtoReq;
import com.WI.WIGOLDFISH.entities.fish.FishDtoRes;
import com.WI.WIGOLDFISH.exceptions.ResourceNotFound;
import com.WI.WIGOLDFISH.repositories.FishRepository;
import com.WI.WIGOLDFISH.repositories.LevelRepository;
import com.WI.WIGOLDFISH.services.interfaces.FishService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import com.WI.WIGOLDFISH.entities.level.Level;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FishServiceImpl implements FishService {
    private final FishRepository fishRepository;
    private final ModelMapper modelMapper;
    private final LevelRepository levelRepository;
    @Override
    public FishDtoReq save(FishDtoReq dtoMini) {
        levelRepository.findById(dtoMini.getLevel_id()).orElseThrow(() -> new ResourceNotFound("Level not found"));
        fishRepository.findByName(dtoMini.getName()).ifPresent(fish -> {
            throw new ResourceNotFound("Fish already exists");
        });
        Fish fish = modelMapper.map(dtoMini, Fish.class);
        fish.setLevel(new Level(dtoMini.getLevel_id()));
        fish = fishRepository.save(fish);
        FishDtoReq fishDtoReq = modelMapper.map(fish, FishDtoReq.class);
        fishDtoReq.setLevel_id(fish.getLevel().getCode());
        return fishDtoReq;
    }

    @Override
    public FishDtoReq update(FishDtoReq dtoMini, String s) {
        fishRepository.findById(s).orElseThrow(() -> new ResourceNotFound("Fish not found"));
        levelRepository.findById(dtoMini.getLevel_id()).orElseThrow(() -> new ResourceNotFound("Level not found"));
        Fish fish = modelMapper.map(dtoMini, Fish.class);
        fish.setName(s);
        fish.setLevel(new Level(dtoMini.getLevel_id()));
        fish = fishRepository.save(fish);
        return modelMapper.map(fish, FishDtoReq.class);
    }

    @Override
    public Boolean delete(String s) {
        fishRepository.findById(s).orElseThrow(() -> new ResourceNotFound("Fish not found"));
        fishRepository.deleteById(s);
        if (fishRepository.findById(s).isPresent()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public FishDtoRes findOne(String s) {
        Fish fish = fishRepository.findById(s).orElseThrow(() -> new ResourceNotFound("Fish not found"));
        return modelMapper.map(fish, FishDtoRes.class);
    }

    @Override
    public List<FishDtoRes> findAll() {
        List<FishDtoRes> fishDtoRes = fishRepository.findAll().stream().map(fish -> modelMapper.map(fish, FishDtoRes.class)).toList();
        return fishDtoRes;
    }
}
