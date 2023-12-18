package com.api.AFTAS.domains.member.DTOs;

import com.api.AFTAS.domains.member.IdentityDocumentType;
import lombok.Data;

import java.time.LocalDate;
@Data
public class MemberReqDTO {
    private Integer num;
    private String name;
    private String familtyName;
    private LocalDate AccessionDate;
    private String nationality;
    private IdentityDocumentType identityDocument;
    private String identityNumber;
}
