package ma.youcode.aftas.Services;

import ma.youcode.aftas.Models.Dtos.LevelDto.LevelRequestDto;
import ma.youcode.aftas.Models.Entities.Level;
import ma.youcode.aftas.Repositories.LevelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LevelService {
    private final LevelRepository levelRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public LevelService(LevelRepository levelRepository, ModelMapper modelMapper) {
        this.levelRepository = levelRepository;
        this.modelMapper = modelMapper;
    }

    public List<LevelRequestDto> getAllLevels() {
        List<Level> levels = levelRepository.findAll();
        return levels.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private LevelRequestDto convertToDto(Level level) {
        return modelMapper.map(level, LevelRequestDto.class);
    }

    public LevelRequestDto getLevelById(Long id) {
        Level level = levelRepository.findById(id).orElse(null);
        return modelMapper.map(level, LevelRequestDto.class);
    }

    public LevelRequestDto createLevel(LevelRequestDto levelDto) {
        Level level = modelMapper.map(levelDto, Level.class);
        Level savedLevel = levelRepository.save(level);
        return modelMapper.map(savedLevel, LevelRequestDto.class);
    }

    public LevelRequestDto updateLevel(LevelRequestDto levelDto) {
        Level level = modelMapper.map(levelDto, Level.class);
        Level savedLevel = levelRepository.save(level);
        return modelMapper.map(savedLevel, LevelRequestDto.class);
    }

    public void deleteById(Long id) {
        levelRepository.deleteById(id);
    }
}
