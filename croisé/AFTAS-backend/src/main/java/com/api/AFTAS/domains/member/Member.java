package com.api.AFTAS.domains.member;

import com.api.AFTAS.domains.hunting.Hunting;
import com.api.AFTAS.domains.ranking.Ranking;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer num;
    private String name;
    private String familtyName;
    private LocalDate AccessionDate;
    private String nationality;
    private IdentityDocumentType identityDocument;
    private String identityNumber;
    @OneToMany(mappedBy = "member")
    private List<Hunting> huntings = new ArrayList<>();
    @OneToMany(mappedBy = "id.member")
    private List<Ranking> rankings;
}
