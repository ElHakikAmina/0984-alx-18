package com.WI.WIGOLDFISH.controllers;

import com.WI.WIGOLDFISH.entities.level.LevelDtoReq;
import com.WI.WIGOLDFISH.services.impl.LevelServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/level")
@RequiredArgsConstructor
public class LevelController {
    private final LevelServiceImpl levelServiceImpl;

    @PostMapping
    public ResponseEntity<?> createLevel(@Valid @RequestBody LevelDtoReq levelDtoReq) {
        levelDtoReq = levelServiceImpl.save(levelDtoReq);
        Map<String, Object> response = new HashMap<>();
        response.put("data", levelDtoReq);
        response.put("message", "Level created successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getLevels() {
        return ResponseEntity.ok(levelServiceImpl.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLevel(@PathVariable Long id) {
        return ResponseEntity.ok(levelServiceImpl.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLevel(@PathVariable Long id, @Valid @RequestBody LevelDtoReq levelDtoReq) {
        levelDtoReq = levelServiceImpl.update(levelDtoReq, id);
        Map<String, Object> response = new HashMap<>();
        response.put("data", levelDtoReq);
        response.put("message", "Level updated successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLevel(@PathVariable Long id) {
        levelServiceImpl.delete(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Level deleted successfully");
        return ResponseEntity.ok(response);
    }
}
