package com.vluepixel.vetmanager.api.patient.medicalhistory.application.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Medical history DTO.
 */
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public final class MedicalHistoryDto {
    private Long id;

    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
