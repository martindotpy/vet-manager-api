package com.vluepixel.vetmanager.api.product.core.adapter.in.response;

import com.vluepixel.vetmanager.api.product.core.application.dto.ProductDto;
import com.vluepixel.vetmanager.api.shared.adapter.in.response.ContentResponse;

import lombok.experimental.SuperBuilder;

/**
 * Product response.
 */
@SuperBuilder
public final class ProductResponse extends ContentResponse<ProductDto> {
}
