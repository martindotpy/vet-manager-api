package com.vluepixel.vetmanager.api.bill.sale.application.port.in;

import com.vluepixel.vetmanager.api.bill.sale.domain.model.Sale;

/**
 * Update bill total port.
 */
public interface UpdateBillTotalPort {
    /**
     * Update the total of a bill.
     *
     * @param billId  the bill id.
     * @param newSale the new sale.
     */
    void updateTotal(Long billId, Sale newSale);
}
