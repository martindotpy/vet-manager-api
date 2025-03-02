package com.vluepixel.vetmanager.api.medicalrecord.treatment.domain.request;

import com.vluepixel.vetmanager.api.shared.domain.payload.Payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Update treatment request.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class UpdateTreatmentRequest implements Payload {
    @NotNull(message = "El id es requerido")
    @Positive(message = "El id debe ser mayor a 0")
    private Long id;

    @NotBlank(message = "La descripción es requerida")
    private String description;
    @NotNull(message = "El orden es requerido")
    @Positive(message = "El orden debe ser mayor a 0")
    private Integer order;
}
