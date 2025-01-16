package com.vet.hc.api.patient.species.adapter.in.request;

import com.vet.hc.api.patient.species.domain.payload.UpdateSpeciesPayload;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request payload to update a species.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class UpdateSpeciesRequest implements UpdateSpeciesPayload {
    @NotNull(message = "El id es requerido")
    @Min(value = 1, message = "El id no puede ser menor a 1")
    private Long id;

    @NotBlank(message = "El nombre no puede estar en blanco")
    @Size(max = 12, message = "El nombre no puede tener más de 12 caracteres")
    private String name;
}
