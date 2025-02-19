package com.vluepixel.vetmanager.api.bill.core.application.port.in;

/**
 * Delete bill port.
 */
public interface DeleteBillPort {
    /**
     * Delete bill by id.
     *
     * @param id the bill id.
     */
    void deleteById(Long id);
}
