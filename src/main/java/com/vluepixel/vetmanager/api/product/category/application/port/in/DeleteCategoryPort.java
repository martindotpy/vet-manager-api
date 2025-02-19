package com.vluepixel.vetmanager.api.product.category.application.port.in;

/**
 * Delete category port.
 */
public interface DeleteCategoryPort {
    /**
     * Delete category by id.
     *
     * @param id the category id.
     */
    void deleteById(Integer id);
}
