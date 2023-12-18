package ma.youcode.aftas.Services;

import ma.youcode.aftas.Models.Dtos.FishDto.FishRequestDto;
import ma.youcode.aftas.Models.Entities.Fish;
import ma.youcode.aftas.Repositories.FishRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FishService {
    private final FishRepository fishRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public FishService(FishRepository fishRepository, ModelMapper modelMapper) {
        this.fishRepository = fishRepository;
        this.modelMapper = modelMapper;
    }

    public List<FishRequestDto> getAllFishes() {
        List<Fish> fishes = fishRepository.findAll();
        return fishes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private FishRequestDto convertToDto(Fish fish) {
        return modelMapper.map(fish, FishRequestDto.class);
    }
    public FishRequestDto getFishById(Long id) {
        Fish fish = fishRepository.findById(id).orElse(null);
        return modelMapper.map(fish, FishRequestDto.class);
    }

    public FishRequestDto createFish(FishRequestDto fishDto) {
        Fish fish = modelMapper.map(fishDto, Fish.class);
        Fish savedFish = fishRepository.save(fish);
        return modelMapper.map(savedFish, FishRequestDto.class);
    }

    public FishRequestDto updateFish(FishRequestDto fishDto) {
        Fish fish = modelMapper.map(fishDto, Fish.class);
        Fish savedFish = fishRepository.save(fish);
        return modelMapper.map(savedFish, FishRequestDto.class);
    }

    public void deleteFishById(Long id) {
        fishRepository.deleteById(id);
    }
}
