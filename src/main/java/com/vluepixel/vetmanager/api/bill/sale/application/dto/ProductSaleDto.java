package com.vluepixel.vetmanager.api.bill.sale.application.dto;

import com.vluepixel.vetmanager.api.product.core.application.dto.ProductDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Product sale DTO.
 */
@Getter
@Builder
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public final class ProductSaleDto extends SaleDto {
    private Integer quantity;

    private ProductDto product;
}
