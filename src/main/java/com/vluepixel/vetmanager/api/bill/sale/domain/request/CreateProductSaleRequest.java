package com.vluepixel.vetmanager.api.bill.sale.domain.request;

import com.vluepixel.vetmanager.api.bill.sale.domain.model.enums.SaleType;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Create product sale request.
 */
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public final class CreateProductSaleRequest extends CreateSaleRequest {
    private final SaleType type = SaleType.PRODUCT;

    @NotNull(message = "La cantidad es requerida")
    @Positive(message = "La cantidad debe ser mayor a 0")
    @Max(value = 99, message = "La cantidad no puede ser mayor a 99")
    private Integer quantity;

    @NotNull(message = "El id del product es requerido")
    @Positive(message = "El id del product debe ser mayor a 0")
    private Long productId;
}
