package com.vluepixel.vetmanager.api.product.category.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Category DTO.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class CategoryDto {
    private Long id;

    private String name;
}
