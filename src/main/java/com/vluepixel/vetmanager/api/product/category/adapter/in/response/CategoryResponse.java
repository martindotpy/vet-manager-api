package com.vluepixel.vetmanager.api.product.category.adapter.in.response;

import com.vluepixel.vetmanager.api.product.category.application.dto.CategoryDto;
import com.vluepixel.vetmanager.api.shared.adapter.in.response.ContentResponse;

import lombok.experimental.SuperBuilder;

/**
 * Category response.
 */
@SuperBuilder
public final class CategoryResponse extends ContentResponse<CategoryDto> {
}
