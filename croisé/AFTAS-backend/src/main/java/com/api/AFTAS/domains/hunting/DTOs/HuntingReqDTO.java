package com.api.AFTAS.domains.hunting.DTOs;

import lombok.Data;

@Data
public class HuntingReqDTO {
    private Integer id;
    private Integer numberOfFish;
    private String fish_name;
    private Integer member_num;
    private String competition_code;
}
