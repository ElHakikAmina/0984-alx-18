package com.WI.WIGOLDFISH.services.impl;

import com.WI.WIGOLDFISH.entities.level.Level;
import com.WI.WIGOLDFISH.entities.level.LevelDtoReq;
import com.WI.WIGOLDFISH.entities.level.LevelDtoRes;
import com.WI.WIGOLDFISH.exceptions.ResourceNotFound;
import com.WI.WIGOLDFISH.repositories.LevelRepository;
import com.WI.WIGOLDFISH.services.interfaces.LevelService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService {
    private final LevelRepository levelRepository;
    private final ModelMapper modelMapper;
    @Override
    public LevelDtoReq save(LevelDtoReq dtoMini) {
        levelRepository.findById(dtoMini.getCode()).ifPresent(level -> {
            throw new ResourceNotFound("Level already exists");
        });
        levelRepository.findFirstByCodeAfter(dtoMini.getCode()).ifPresent(level -> {
           if (level.getPoints() <= dtoMini.getPoints()) {
               throw new ResourceNotFound("Points must be less than the " + level.getPoints() + " for the next level " + level.getCode() + " level");
           }
        });

        levelRepository.findFirstOneByCodeBefore(dtoMini.getCode()).ifPresent(level -> {
            if (level.getPoints() >= dtoMini.getPoints()) {
                throw new ResourceNotFound("Points must be greater than the " + level.getPoints() + " for the previous level " + level.getCode() + " level" );
            }
        });
        Level level = modelMapper.map(dtoMini, Level.class);
        level = levelRepository.save(level);
        return modelMapper.map(level, LevelDtoReq.class);
    }

    @Override
    public LevelDtoReq update(LevelDtoReq dtoMini, Long aLong) {
        levelRepository.findById(aLong).orElseThrow(() -> new ResourceNotFound("Level not found"));
        Level level = modelMapper.map(dtoMini, Level.class);
        level.setCode(aLong);
        level = levelRepository.save(level);
        return modelMapper.map(level, LevelDtoReq.class);
    }

    @Override
    public Boolean delete(Long aLong) {
        levelRepository.findById(aLong).orElseThrow(() -> new ResourceNotFound("Level not found"));
        levelRepository.deleteById(aLong);
        if (levelRepository.findById(aLong).isPresent()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public LevelDtoRes findOne(Long aLong) {
        Level level = levelRepository.findById(aLong).orElseThrow(() -> new ResourceNotFound("Level not found"));
        return modelMapper.map(level, LevelDtoRes.class);
    }

    @Override
    public List<LevelDtoRes> findAll() {
        List<LevelDtoRes> levelDtoResList = levelRepository.findByOrderByCodeAsc().stream().map(level -> modelMapper.map(level, LevelDtoRes.class)).toList();
        return levelDtoResList;
    }
}
