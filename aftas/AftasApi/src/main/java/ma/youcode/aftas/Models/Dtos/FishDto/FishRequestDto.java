package ma.youcode.aftas.Models.Dtos.FishDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.youcode.aftas.Models.Dtos.HuntingDto.HuntingResponseDto;
import ma.youcode.aftas.Models.Dtos.LevelDto.LevelResponseDto;
import ma.youcode.aftas.Models.Entities.Hunting;
import ma.youcode.aftas.Models.Entities.Level;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FishRequestDto {
    private Long id;
    private String name;
    private float averageWeight;

    private List<HuntingResponseDto> huntings = new ArrayList<HuntingResponseDto>();
    private LevelResponseDto level;
}
