package com.vluepixel.vetmanager.api.bill.sale.application.dto;

import com.vluepixel.vetmanager.api.product.core.application.dto.ProductDto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Product sale DTO.
 */
@Getter
@SuperBuilder
public final class ProductSaleDto extends SaleDto {
    private Integer quantity;

    private ProductDto product;
}
