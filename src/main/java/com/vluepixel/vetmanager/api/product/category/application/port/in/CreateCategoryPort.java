package com.vluepixel.vetmanager.api.product.category.application.port.in;

import com.vluepixel.vetmanager.api.product.category.application.dto.CategoryDto;
import com.vluepixel.vetmanager.api.product.category.domain.request.CreateCategoryRequest;

/**
 * Create category port.
 */
public interface CreateCategoryPort {
    /**
     * Create category.
     *
     * @param request the create category request.
     * @return the created category
     */
    CategoryDto create(CreateCategoryRequest request);
}
