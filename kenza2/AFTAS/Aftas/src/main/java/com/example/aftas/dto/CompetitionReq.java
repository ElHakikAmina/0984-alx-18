package com.example.aftas.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionReq {
    @NotNull(message = "Code is required")
    @Pattern(regexp = "^[a-zA-Z]{3}-\\d{2}-\\d{2}-\\d{2}$", message = "Invalid competition code format")
    private String code;
    @NotNull(message = "Date is required")
    private LocalDate Date;
    @NotNull(message = "Start time is required")
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Invalid time format")
    private LocalTime startTime;
    @NotNull(message = "End time is required")
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Invalid time format")
    private LocalTime endTime;
    @NotNull
    @Min(value= 4 , message = "Number of participants must be greater then 3")
    private int numberOfParticipants;
    @NotNull(message = "Location is required")
    private String location;
    @NotNull
    @Min(value=0 , message = "Amount should be a positive value")
    private float amount;
}
