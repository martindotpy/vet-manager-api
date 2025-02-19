package com.vluepixel.vetmanager.api.product.core.adapter.in.response;

import com.vluepixel.vetmanager.api.product.core.application.dto.ProductDto;
import com.vluepixel.vetmanager.api.shared.adapter.in.response.PaginatedResponse;

import lombok.experimental.SuperBuilder;

/**
 * Paginated product response.
 */
@SuperBuilder
public final class PaginatedProductResponse extends PaginatedResponse<ProductDto> {
}
