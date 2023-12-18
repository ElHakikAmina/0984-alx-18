package ma.youcode.aftas.Models.Dtos.RankingDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RankingResponseDto {
    private Long competitionId;
    private Long memberId;
    private Integer rank;
    private Integer score;
}