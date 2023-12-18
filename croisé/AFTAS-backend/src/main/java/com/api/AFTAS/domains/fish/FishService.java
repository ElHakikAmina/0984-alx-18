package com.api.AFTAS.domains.fish;

import com.api.AFTAS.domains.fish.DTOs.FishReqDTO;
import com.api.AFTAS.domains.fish.DTOs.FishRespDTO;
import com.api.AFTAS.domains.level.Level;
import com.api.AFTAS.domains.level.LevelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FishService implements FishServiceInterface {
    @Autowired
    FishRepository fishRepository;
    @Autowired
    LevelRepository levelRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public FishRespDTO create(FishReqDTO fishReqDTO) {
        Fish fish = modelMapper.map(fishReqDTO, Fish.class);
        Optional<Level> level = levelRepository.findById(fishReqDTO.getLevelId());
        if (level.isPresent()) {
            fish.setLevel(level.get());
            fish = fishRepository.save(fish);
            return modelMapper.map(fish, FishRespDTO.class);
        }
        return null;
    }

    @Override
    public FishRespDTO update(FishReqDTO fishReqDTO, String name) {
        Optional<Fish> existFish = fishRepository.findById(name);
        Optional<Level> level = levelRepository.findById(fishReqDTO.getLevelId());
        if (existFish.isPresent() && level.isPresent()) {
            fishReqDTO.setName(existFish.get().getName());
            Fish fish =modelMapper.map(fishReqDTO, Fish.class);
            fish.setLevel(level.get());
            return modelMapper.map(fishRepository.save(fish), FishRespDTO.class);
        }
        return null;
    }

    @Override
    public Integer delete(String name) {
        Optional<Fish> fish = fishRepository.findById(name);
        if(fish.isPresent()) {
            fishRepository.delete(fish.get());
            return 1;
        }else return 0;
    }

    @Override
    public List<FishRespDTO> getAll() {
        return fishRepository.findAll()
                .stream().map(fish -> modelMapper.map(fish,FishRespDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public FishRespDTO getOne(String name) {
        Optional<Fish> fish = fishRepository.findById(name);
        return modelMapper.map(fish.orElse(null),FishRespDTO.class);
    }
}
