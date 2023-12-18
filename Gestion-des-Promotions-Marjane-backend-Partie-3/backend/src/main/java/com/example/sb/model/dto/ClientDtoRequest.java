package com.example.sb.model.dto;

import com.example.sb.helpers.AuthenticationHelpers;
import com.example.sb.model.Entities.Admin;
import com.example.sb.model.Entities.AgentCaisse;
import com.example.sb.model.Entities.Client;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDtoRequest {
    @Email(message = "Email was not provided")
    @NotEmpty(message = "email must be present")
    @Column(unique = true)
    private String email;
    private String password;
    private Integer numerocartefideliter;
    private Long agentCaisse_id;
    public Client toModel(){
        AgentCaisse agentCaisse = AgentCaisse
                .builder()
                .id(agentCaisse_id)
                .build();
        return Client.
                builder()
                .email(this.email)
                .numerocartefideliter(this.numerocartefideliter)

                .password(
                        AuthenticationHelpers.hashPassword(
                                this.password
                        ))
                .agentCaisse(agentCaisse)
                .build();
    }
}
