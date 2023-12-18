package com.ouharri.aftas.model.dto.requests;

import com.ouharri.aftas.model.entities.Address;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

/**
 * DTO for {@link Address}
 */
public record AddressRequest(
        @Size(message = "Region name is too long", max = 30)
        String region,

        @Size(message = "District name is too long", max = 30)
        String district,

        @Size(message = "City name is too long", max = 30)
        String city,

        @Size(message = "Street name is too long", max = 30)
        String street,

        @Min(message = "Building number must be greater than 0", value = 1)
        @Max(message = "Building number is too big", value = 32767)
        Integer building,

        @Min(message = "Apartment number must be greater than 0", value = 1)
        @Max(message = "Apartment number is too big", value = 32767)
        Integer apartment,

        @Min(message = "Invalid postal code", value = 1001)
        @Max(message = "Invalid postal code", value = 99999)
        Integer postalCode
) implements _Request {
}