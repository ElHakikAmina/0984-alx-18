package com.ouharri.aftas.model.dto.responces;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class representing the response for authentication.
 * Contains access and refresh tokens.
 *
 * @author Ouharri Outman
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse implements _Response {
    /**
     * Access token for authentication.
     */
    @NotBlank(message = "Access token is required")
    @JsonProperty("access_token")
    private String accessToken;

    /**
     * Refresh token for token refreshing.
     */
    @NotBlank(message = "Refresh token is required")
    @JsonProperty("refresh_token")
    private String refreshToken;
}