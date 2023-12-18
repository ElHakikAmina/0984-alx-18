package ma.youcode.aftas.Controllers;

import ma.youcode.aftas.Models.Dtos.HuntingDto.HuntingRequestDto;
import ma.youcode.aftas.Services.HuntingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/huntings")
public class HuntingController {
    private final HuntingService huntingService;
    @Autowired
    public HuntingController(HuntingService huntingService){
        this.huntingService = huntingService;
    }
    @GetMapping
    public List<HuntingRequestDto> getAllHuntings() {
        return huntingService.getAllHuntings();
    }
    @GetMapping("/{id}")
    public HuntingRequestDto getHuntingById(@PathVariable Long id ){
        return huntingService.getHuntingById(id);
    }
    @PostMapping("/create")
    public HuntingRequestDto createHunting(@RequestBody HuntingRequestDto hunting){
        return huntingService.createHunting(hunting);
    }
    @PutMapping("/update")
    public HuntingRequestDto updateHunting(@RequestBody HuntingRequestDto hunting){
        return huntingService.updateHunting(hunting);
    }
    @DeleteMapping("/{id}")
    public void deleteHuntingById(@PathVariable Long id){
        huntingService.deleteById(id);
    }
}
