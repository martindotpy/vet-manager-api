package com.vluepixel.vetmanager.api.bill.sale.application.port.in;

/**
 * Delete sale port.
 */
public interface DeleteSalePort {
    /**
     * Delete sale by id.
     *
     * @param id the sale id.
     */
    void deleteById(Long id);
}
