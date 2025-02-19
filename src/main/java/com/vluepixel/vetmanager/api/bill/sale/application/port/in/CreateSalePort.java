package com.vluepixel.vetmanager.api.bill.sale.application.port.in;

import com.vluepixel.vetmanager.api.bill.sale.application.dto.SaleDto;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.CreateSaleRequest;

/**
 * Create sale port.
 */
public interface CreateSalePort {
    /**
     * Create sale.
     *
     * @param request the create sale request.
     * @return the created sale
     */
    SaleDto create(CreateSaleRequest request);
}
