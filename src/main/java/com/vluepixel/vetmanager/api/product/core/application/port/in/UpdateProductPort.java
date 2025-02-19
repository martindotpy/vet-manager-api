package com.vluepixel.vetmanager.api.product.core.application.port.in;

import com.vluepixel.vetmanager.api.product.core.application.dto.ProductDto;
import com.vluepixel.vetmanager.api.product.core.domain.request.UpdateProductRequest;

/**
 * Update product port.
 */
public interface UpdateProductPort {
    /**
     * Update product.
     *
     * @param request the update product request.
     * @return the updated product
     */
    ProductDto update(UpdateProductRequest request);
}
