package com.vluepixel.vetmanager.api.product.category.application.port.in;

import com.vluepixel.vetmanager.api.product.category.application.dto.CategoryDto;
import com.vluepixel.vetmanager.api.shared.domain.criteria.PaginatedCriteria;
import com.vluepixel.vetmanager.api.shared.domain.query.Paginated;

/**
 * Find category port.
 */
public interface FindCategoryPort {
    /**
     * Find all category by paginated criteria.
     *
     * @param criteria the paginated criteria.
     * @return category found paginated
     */
    Paginated<CategoryDto> findPaginatedBy(PaginatedCriteria criteria);

    /**
     * Find category by id.
     *
     * @param id the id.
     * @return category found
     */
    CategoryDto findById(Integer id);
}
