package com.vluepixel.vetmanager.api.product.core.application.port.in;

import com.vluepixel.vetmanager.api.product.core.application.dto.ProductDto;
import com.vluepixel.vetmanager.api.shared.domain.criteria.PaginatedCriteria;
import com.vluepixel.vetmanager.api.shared.domain.query.Paginated;

/**
 * Find product port.
 */
public interface FindProductPort {
    /**
     * Find all product by paginated criteria.
     *
     * @param criteria the paginated criteria.
     * @return product found paginated
     */
    Paginated<ProductDto> findPaginatedBy(PaginatedCriteria criteria);

    /**
     * Find product by id.
     *
     * @param id the id.
     * @return product found
     */
    ProductDto findById(Long id);
}
