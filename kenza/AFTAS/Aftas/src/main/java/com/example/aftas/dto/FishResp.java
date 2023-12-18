package com.example.aftas.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FishResp {
    private String name;
    private float averageWeight;
    private LevelResp level;
}
