package com.vluepixel.vetmanager.api.product.category.application.port.in;

import com.vluepixel.vetmanager.api.product.category.application.dto.CategoryDto;
import com.vluepixel.vetmanager.api.product.category.domain.request.UpdateCategoryRequest;

/**
 * Update category port.
 */
public interface UpdateCategoryPort {
    /**
     * Update category.
     *
     * @param request the update category request.
     * @return the updated category
     */
    CategoryDto update(UpdateCategoryRequest request);
}
