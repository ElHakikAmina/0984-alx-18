package com.WI.WIGOLDFISH.entities.member;

import com.WI.WIGOLDFISH.entities.hunting.Hunting;
import com.WI.WIGOLDFISH.entities.ranking.Ranking;
import com.WI.WIGOLDFISH.enums.IndentityDocumentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "member")
public class    Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;
    private String name;
    private String familyName;
    private LocalDate accessionDate;
    private String nationality;
    private IndentityDocumentType indentityDocumentType;
    private String indentityNumber;
    @OneToMany(mappedBy = "member")
    private List<Ranking> rankings;
    @OneToMany(mappedBy = "member")
    private List<Hunting> huntings;
    public Member(Long num) {
        this.num = num;
    }
}
