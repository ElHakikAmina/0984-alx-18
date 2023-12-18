package com.example.aftas.dto;

import com.example.aftas.entity.IdentityDocumentType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResp {
    private Long num;
    private String name;
    private String familyName;
    private String nationality;
    private LocalDate accessionDate;
    private IdentityDocumentType identityDocument;
    private String identityNumber;
}
