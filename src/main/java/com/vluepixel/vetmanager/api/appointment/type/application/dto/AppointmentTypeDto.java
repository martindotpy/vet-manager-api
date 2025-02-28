package com.vluepixel.vetmanager.api.appointment.type.application.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Appointment type DTO.
 */
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public final class AppointmentTypeDto {
    private Long id;

    private String name;
    private int durationInMinutes;
    private BigDecimal price;
}
