package com.example.sb.model.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Builder
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private Integer numerocartefideliter;

    @ManyToOne
    @JoinColumn(name = "id_agentCaisse")
    private AgentCaisse agentCaisse;
}
