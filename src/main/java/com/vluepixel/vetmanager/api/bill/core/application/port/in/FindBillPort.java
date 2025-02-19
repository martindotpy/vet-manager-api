package com.vluepixel.vetmanager.api.bill.core.application.port.in;

import com.vluepixel.vetmanager.api.bill.core.application.dto.BillDto;
import com.vluepixel.vetmanager.api.shared.domain.criteria.PaginatedCriteria;
import com.vluepixel.vetmanager.api.shared.domain.query.Paginated;

/**
 * Find bill port.
 */
public interface FindBillPort {
    /**
     * Find all bill by paginated criteria.
     *
     * @param criteria the paginated criteria.
     * @return bill found paginated
     */
    Paginated<BillDto> findPaginatedBy(PaginatedCriteria criteria);

    /**
     * Find bill by id.
     *
     * @param id the id.
     * @return bill found
     */
    BillDto findById(Long id);
}
