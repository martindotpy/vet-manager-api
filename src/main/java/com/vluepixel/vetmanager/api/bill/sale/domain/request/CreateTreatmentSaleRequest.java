package com.vluepixel.vetmanager.api.bill.sale.domain.request;

import java.math.BigDecimal;

import com.vluepixel.vetmanager.api.bill.sale.domain.model.enums.SaleType;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Create treatment sale request.
 */
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public final class CreateTreatmentSaleRequest extends CreateSaleRequest {
    private final SaleType type = SaleType.TREATMENT;

    @NotNull(message = "El precio es requerido")
    @Positive(message = "El precio debe ser mayor a 0")
    @DecimalMax(value = "999.99", message = "El precio no puede ser mayor a 999.99")
    private BigDecimal price;

    @NotNull(message = "El id del tratamiento es requerido")
    @Positive(message = "El id del tratamiento debe ser mayor a 0")
    private Long treatmentId;
}
