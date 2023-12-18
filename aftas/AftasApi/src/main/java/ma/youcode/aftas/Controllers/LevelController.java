package ma.youcode.aftas.Controllers;

import ma.youcode.aftas.Models.Dtos.LevelDto.LevelRequestDto;
import ma.youcode.aftas.Services.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/levels")
public class LevelController {
    private final LevelService levelService;
    @Autowired
    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping
    public List<LevelRequestDto> getAllLevels() {
        return levelService.getAllLevels();
    }
    @GetMapping("/{id}")
    public LevelRequestDto getLevelById(@PathVariable Long id) {
        return levelService.getLevelById(id);
    }

    @PostMapping("/create")
    public LevelRequestDto createLevel(@RequestBody LevelRequestDto levelDto) {
        return levelService.createLevel(levelDto);
    }

    @PutMapping("/update")
    public LevelRequestDto updateLevel(@RequestBody LevelRequestDto levelDto){
        return levelService.updateLevel(levelDto);
    }

    @DeleteMapping("/{id}")
    public void deleteLevelById(@PathVariable Long id){
        levelService.deleteById(id);
    }
}
