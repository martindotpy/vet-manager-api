package com.vluepixel.vetmanager.api.bill.sale.application.port.in;

import com.vluepixel.vetmanager.api.bill.sale.application.dto.SaleDto;

/**
 * Find sale port.
 */
public interface FindSalePort {
    /**
     * Find sale by id.
     *
     * @param id the id.
     * @return sale found
     */
    SaleDto findById(Long id);
}
