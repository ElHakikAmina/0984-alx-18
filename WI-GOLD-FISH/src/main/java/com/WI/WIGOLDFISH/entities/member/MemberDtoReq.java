package com.WI.WIGOLDFISH.entities.member;

import com.WI.WIGOLDFISH.enums.IndentityDocumentType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class    MemberDtoReq {
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
