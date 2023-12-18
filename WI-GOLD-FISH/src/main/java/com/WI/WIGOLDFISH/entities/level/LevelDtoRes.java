package com.WI.WIGOLDFISH.entities.level;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.WI.WIGOLDFISH.entities.fish.FishDtoReq;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LevelDtoRes {
    private  Long code;
    private String description;
    private int points;
    private List<FishDtoReq> fishList;
}
