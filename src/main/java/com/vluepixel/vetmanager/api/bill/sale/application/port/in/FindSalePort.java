package com.vluepixel.vetmanager.api.bill.sale.application.port.in;

import com.vluepixel.vetmanager.api.bill.sale.application.dto.SaleDto;
import com.vluepixel.vetmanager.api.shared.domain.criteria.PaginatedCriteria;
import com.vluepixel.vetmanager.api.shared.domain.query.Paginated;

/**
 * Find sale port.
 */
public interface FindSalePort {
    /**
     * Find all sale by paginated criteria.
     *
     * @param criteria the paginated criteria.
     * @return sale found paginated
     */
    Paginated<SaleDto> findPaginatedBy(PaginatedCriteria criteria);

    /**
     * Find sale by id.
     *
     * @param id the id.
     * @return sale found
     */
    SaleDto findById(Integer id);
}
