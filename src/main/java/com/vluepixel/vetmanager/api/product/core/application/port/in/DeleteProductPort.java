package com.vluepixel.vetmanager.api.product.core.application.port.in;

/**
 * Delete product port.
 */
public interface DeleteProductPort {
    /**
     * Delete product by id.
     *
     * @param id the product id.
     */
    void deleteById(Long id);
}
