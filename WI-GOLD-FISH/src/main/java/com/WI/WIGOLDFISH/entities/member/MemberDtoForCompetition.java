package com.WI.WIGOLDFISH.entities.member;

import com.WI.WIGOLDFISH.enums.IndentityDocumentType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.LocalDate;

public class MemberDtoForCompetition {
    @Null
    private Long num;
    @NotNull
    private String name;
    @NotNull
    private String familyName;
    @NotNull
    private LocalDate accessionDate;
    @NotNull
    private String nationality;
    @NotNull
    private IndentityDocumentType indentityDocumentType;
    @NotNull
    private String indentityNumber;
}
