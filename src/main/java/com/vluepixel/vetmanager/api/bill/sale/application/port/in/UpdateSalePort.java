package com.vluepixel.vetmanager.api.bill.sale.application.port.in;

import com.vluepixel.vetmanager.api.bill.sale.application.dto.SaleDto;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.UpdateSaleRequest;

/**
 * Update sale port.
 */
public interface UpdateSalePort {
    /**
     * Update sale.
     *
     * @param request the update sale request.
     * @return the updated sale
     */
    SaleDto update(UpdateSaleRequest request);
}
