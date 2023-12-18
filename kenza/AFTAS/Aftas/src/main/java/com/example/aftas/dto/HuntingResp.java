package com.example.aftas.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HuntingResp {
    private Long id;
    private int numberOfFish;
    private FishResp fish;
    private CompetitionResp competition;
    private MemberResp member;
}
