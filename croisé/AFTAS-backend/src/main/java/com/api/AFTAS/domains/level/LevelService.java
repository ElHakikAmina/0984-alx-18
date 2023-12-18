package com.api.AFTAS.domains.level;

import com.api.AFTAS.domains.level.DTOs.LevelReqDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LevelService implements LevelServiceInterface{
    @Autowired
    private LevelRepository levelRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public LevelReqDTO create(LevelReqDTO levelReqDTO) {
        if(levelRepository.findAllByPointsGreaterThanEqual(levelReqDTO.getPoints()).isEmpty()) {
            Level level = modelMapper.map(levelReqDTO, Level.class);
            level = levelRepository.save(level);
            return modelMapper.map(level, LevelReqDTO.class);
        }
        return null;
    }

    @Override
    public LevelReqDTO update(LevelReqDTO levelReqDTO, Integer id) {
        if(levelRepository.findAllByPointsGreaterThan(levelReqDTO.getPoints()).isEmpty()) {
            Optional<Level> existLevel = levelRepository.findById(id);
            if (existLevel.isPresent()) {
                levelReqDTO.setCode(existLevel.get().getCode());
                return modelMapper.map(levelRepository.save(modelMapper.map(levelReqDTO, Level.class)), LevelReqDTO.class);
            }
        }
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        Optional<Level> level = levelRepository.findById(id);
        if(level.isPresent()) {
            levelRepository.delete(level.get());
            return 1;
        }else return 0;
    }

    @Override
    public List<LevelReqDTO> getAll() {
        return levelRepository.findAll()
                .stream().map(level -> modelMapper.map(level,LevelReqDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public LevelReqDTO getOne(Integer id) {
        Optional<Level> level = levelRepository.findById(id);
        return level.map(value -> modelMapper.map(value, LevelReqDTO.class)).orElse(null);
    }
}
