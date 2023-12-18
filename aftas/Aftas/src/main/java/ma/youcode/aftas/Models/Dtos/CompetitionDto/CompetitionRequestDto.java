package ma.youcode.aftas.Models.Dtos.CompetitionDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.youcode.aftas.Models.Dtos.RankingDto.RankingResponseDto;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionRequestDto {
    private Long id;
    private Date date;
    private Time startTime;
    private Time endTime;
    private Integer numberOfParticipants;
    private String location;
    private Double amount;
    private List<RankingResponseDto> rankings = new ArrayList<RankingResponseDto>();
}
