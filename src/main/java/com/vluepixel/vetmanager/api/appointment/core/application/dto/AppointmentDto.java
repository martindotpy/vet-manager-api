package com.vluepixel.vetmanager.api.appointment.core.application.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.vluepixel.vetmanager.api.appointment.details.application.dto.AppointmentDetailsDto;
import com.vluepixel.vetmanager.api.patient.core.application.dto.PatientDto;
import com.vluepixel.vetmanager.api.user.core.application.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Appointment DTO.
 */
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public final class AppointmentDto {
    private Long id;

    private LocalDateTime createdAt;
    private LocalDateTime startAt;
    private String description;

    private List<AppointmentDetailsDto> details;
    private PatientDto patient;
    private UserDto createdBy;
}
