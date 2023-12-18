package ma.youcode.aftas.Models.Dtos.MemberDto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.youcode.aftas.Models.Dtos.HuntingDto.HuntingResponseDto;
import ma.youcode.aftas.Models.Dtos.RankingDto.RankingResponseDto;
import ma.youcode.aftas.Models.Enums.IdentityDocumentType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDto {
    private Long id;
    private Integer num;
    private String name;
    private String familyName;
    private Date accessionDate;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocumentType;
    private String identityNumber;

    private List<RankingResponseDto> rankings = new ArrayList<RankingResponseDto>();
    private List<HuntingResponseDto> huntings = new ArrayList<HuntingResponseDto>();
}