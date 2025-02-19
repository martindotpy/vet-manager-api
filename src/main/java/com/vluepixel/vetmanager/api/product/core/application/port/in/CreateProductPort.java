package com.vluepixel.vetmanager.api.product.core.application.port.in;

import com.vluepixel.vetmanager.api.product.core.application.dto.ProductDto;
import com.vluepixel.vetmanager.api.product.core.domain.request.CreateProductRequest;

/**
 * Create product port.
 */
public interface CreateProductPort {
    /**
     * Create product.
     *
     * @param request the create product request.
     * @return the created product
     */
    ProductDto create(CreateProductRequest request);
}
