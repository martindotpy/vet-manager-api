package com.vluepixel.vetmanager.api.product.core.application.dto;

import java.math.BigDecimal;
import java.util.List;

import com.vluepixel.vetmanager.api.product.category.application.dto.CategoryDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Product DTO.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class ProductDto {
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;

    private List<CategoryDto> categories;
}
