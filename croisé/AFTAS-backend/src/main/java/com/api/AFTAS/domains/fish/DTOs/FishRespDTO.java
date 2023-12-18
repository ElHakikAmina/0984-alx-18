package com.api.AFTAS.domains.fish.DTOs;

import com.api.AFTAS.domains.level.DTOs.LevelReqDTO;
import lombok.Data;

@Data
public class FishRespDTO {
    private String name;
    private Double averageWeigth;
    private LevelReqDTO level;
}
