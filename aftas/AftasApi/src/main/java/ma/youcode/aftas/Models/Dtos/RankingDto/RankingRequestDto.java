package ma.youcode.aftas.Models.Dtos.RankingDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RankingRequestDto {
    private Long competitionId;
    private Long memberId;
    private Integer rank;
    private Integer score;
    //todo : check if this is needed to be added member and competition objects here
    // And I think it's not needed because we are not going to use them in the body...
}
