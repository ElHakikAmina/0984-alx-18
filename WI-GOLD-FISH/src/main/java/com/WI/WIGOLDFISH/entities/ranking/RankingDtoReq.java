package com.WI.WIGOLDFISH.entities.ranking;

import com.WI.WIGOLDFISH.ids.RankingId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  RankingDtoReq {
     private RankingIdDto rankingId;
     @NotNull
     private  int rank;
     @NotNull
     private int score;
     @NotNull
     private Long member_id;
     @NotNull
     private String competition_id;
}
