package com.example.aftas.controller;

import com.example.aftas.dto.FishReq;
import com.example.aftas.dto.FishResp;
import com.example.aftas.services.interfaces.FishServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fishes")
public class FishController {
    private final FishServiceInterface fishService;

    public FishController(FishServiceInterface fishService) {
        this.fishService = fishService;
    }

    @PostMapping("/add")
    public ResponseEntity<FishResp> AddFish(@Valid @RequestBody FishReq fish){
        Optional<FishResp> savedFish = fishService.AddFish(fish);
        return ResponseEntity.ok(savedFish.get());
    }

    @GetMapping("{name}")
    public ResponseEntity<FishResp> findByName(@PathVariable String name){
        Optional<FishResp> fish = fishService.findByName(name);
        return ResponseEntity.ok(fish.get());
    }

    @GetMapping("/list")
    public ResponseEntity<List<FishResp>> getAllFishes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        List<FishResp> fishes = fishService.getAllFishes(page,size);
        return ResponseEntity.ok(fishes);
    }

    @GetMapping("/random")
    public ResponseEntity<List<FishResp>> getRandomFishes() {
        List<FishResp> fishes = fishService.getRandomFishes();
        return ResponseEntity.ok(fishes);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<FishResp>> deleteFish(@PathVariable String id){
        Optional<FishResp> fish = fishService.deleteFish(id);
        return ResponseEntity.ok(fish);
    }

    @PutMapping("/update/{fishName}")
    public ResponseEntity<Optional<FishResp>> updateFish(
            @PathVariable String fishName,
            @Valid @RequestBody FishReq fish
    ){
        Optional<FishResp> updatedFish= fishService.updateFish(fishName,fish);
        return ResponseEntity.ok(updatedFish);
    }

    @GetMapping("/level/{levelId}")
    public ResponseEntity<List<FishResp>> findByLevel(@PathVariable Long levelId){
        List<FishResp> fishes = fishService.getByLevel(levelId);
        return ResponseEntity.ok(fishes);
    }
}
