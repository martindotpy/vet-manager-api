package com.vluepixel.vetmanager.api.bill.sale.domain.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Update treatment sale request.
 */
@Getter
@SuperBuilder
public final class UpdateTreatmentSaleRequest extends UpdateSaleRequest {
    @NotNull(message = "El id del tratamiento es requerido")
    @Positive(message = "El id del tratamiento debe ser mayor a 0")
    private Long treatmentId;
}
