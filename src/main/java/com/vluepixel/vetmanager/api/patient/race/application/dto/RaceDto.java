package com.vluepixel.vetmanager.api.patient.race.application.dto;

import com.vluepixel.vetmanager.api.patient.species.application.dto.SpeciesDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Race DTO.
 */
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public final class RaceDto {
    private Long id;

    private String name;

    private SpeciesDto species;
}
