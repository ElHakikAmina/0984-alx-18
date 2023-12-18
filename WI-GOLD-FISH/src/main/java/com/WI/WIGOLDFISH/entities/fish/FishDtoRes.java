package com.WI.WIGOLDFISH.entities.fish;

import com.WI.WIGOLDFISH.entities.hunting.HuntingDtoReq;
import com.WI.WIGOLDFISH.entities.level.LevelDtoReq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FishDtoRes {
    private  String  name;
    private double averageWeight;
    private LevelDtoReq level;
    private List<HuntingDtoReq> huntingList;
}
