package ma.youcode.aftas.Models.Dtos.LevelDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LevelResponseDto {
    private Long id;
    private Integer code;
    private String description;
    private Integer points;
}
