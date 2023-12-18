package com.example.sb.model.dto;

import com.example.sb.helpers.AuthenticationHelpers;
import com.example.sb.model.Entities.Admin;
import com.example.sb.model.Entities.AgentCaisse;
import com.example.sb.model.Entities.Centre;
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
public class AgentCaisseDtoRequest {
    @Email(message = "Email was not provided")
    @NotEmpty(message = "email must be present")
    @Column(unique = true)
    private String email;
    private String password;
    private long admin_id;

    public AgentCaisse toModel(){
        Admin admin1 = Admin
                .builder()
                .id(admin_id)
                .build();
        return AgentCaisse.
                builder()
                .email(this.email)
                .password(
                        AuthenticationHelpers.hashPassword(
                        this.password
                ))
                .admin(admin1)
                .build();
    }
}
