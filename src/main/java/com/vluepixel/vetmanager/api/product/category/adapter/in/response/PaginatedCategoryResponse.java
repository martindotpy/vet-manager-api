package com.vluepixel.vetmanager.api.product.category.adapter.in.response;

import com.vluepixel.vetmanager.api.product.category.application.dto.CategoryDto;
import com.vluepixel.vetmanager.api.shared.adapter.in.response.PaginatedResponse;

import lombok.experimental.SuperBuilder;

/**
 * Paginated category response.
 */
@SuperBuilder
public final class PaginatedCategoryResponse extends PaginatedResponse<CategoryDto> {
}
