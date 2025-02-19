package com.vluepixel.vetmanager.api.bill.sale.domain.request;

import com.vluepixel.vetmanager.api.bill.sale.domain.model.enums.SaleType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Create treatment sale request.
 */
@Getter
@SuperBuilder
public final class CreateTreatmentSaleRequest extends CreateSaleRequest {
    private final SaleType type = SaleType.TREATMENT;

    @NotNull(message = "El id del tratamiento es requerido")
    @Positive(message = "El id del tratamiento debe ser mayor a 0")
    private Long treatmentId;
}
