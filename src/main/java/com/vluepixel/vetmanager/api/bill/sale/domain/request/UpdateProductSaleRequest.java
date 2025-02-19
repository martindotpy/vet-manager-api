package com.vluepixel.vetmanager.api.bill.sale.domain.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Update product sale request.
 */
@Getter
@SuperBuilder
public final class UpdateProductSaleRequest extends UpdateSaleRequest {
    @NotNull(message = "La cantidad es requerida")
    @Positive(message = "La cantidad debe ser mayor a 0")
    @Max(value = 999, message = "La cantidad no puede ser mayor a 999")
    private Integer quantity;

    @NotNull(message = "El id del producto es requerido")
    @Positive(message = "El id del producto debe ser mayor a 0")
    private Long productId;
}
