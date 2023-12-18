package ma.youcode.aftas.Models.Dtos.HuntingDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.youcode.aftas.Models.Dtos.CompetitionDto.CompetitionResponseDto;
import ma.youcode.aftas.Models.Dtos.FishDto.FishResponseDto;
import ma.youcode.aftas.Models.Dtos.MemberDto.MemberResponseDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HuntingRequestDto {
    private Long id;
    private Integer numberOfFish;

    private MemberResponseDto member;
    private CompetitionResponseDto competition;
    private FishResponseDto fish;
}
