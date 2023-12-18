package com.WI.WIGOLDFISH.entities.fish;

import ch.qos.logback.classic.Level;
import com.WI.WIGOLDFISH.entities.level.LevelDtoReq;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FishDtoReqForCompetition {
  @NotNull
  private String name;
  @NotNull
  private double averageWeight;
  private LevelDtoReq level;
}
