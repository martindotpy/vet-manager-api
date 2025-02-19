package com.vluepixel.vetmanager.api.vaccine.core.application.dto;

import java.time.LocalDateTime;

import com.vluepixel.vetmanager.api.user.core.application.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Vaccine DTO.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class VaccineDto {
    private Long id;

    private String name;
    private Integer doseInMilliliters;
    private LocalDateTime providedAt;

    private UserDto vaccinator;
    // TODO: Product sale
}
