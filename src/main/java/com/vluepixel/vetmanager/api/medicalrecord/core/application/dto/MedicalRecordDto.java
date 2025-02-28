package com.vluepixel.vetmanager.api.medicalrecord.core.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.vluepixel.vetmanager.api.medicalrecord.treatment.application.dto.TreatmentDto;
import com.vluepixel.vetmanager.api.user.core.application.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Medical record DTO.
 */
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public final class MedicalRecordDto {
    private Long id;

    private String reason;
    private String physicalExam;
    private LocalDateTime entryAt;
    private BigDecimal temperatureInCelsius;
    private Integer respitarionRate;
    private Integer heartRate;
    private BigDecimal weight;
    private boolean sterilized;
    private String recipe;
    private String diagnosis;

    private UserDto vet;
    private List<TreatmentDto> treatments;
}
