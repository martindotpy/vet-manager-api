package com.vluepixel.vetmanager.api.bill.core.application.port.in;

import com.vluepixel.vetmanager.api.bill.core.application.dto.BillDto;
import com.vluepixel.vetmanager.api.bill.core.domain.request.CreateBillRequest;

/**
 * Create bill port.
 */
public interface CreateBillPort {
    /**
     * Create bill.
     *
     * @param request the create bill request.
     * @return the created bill
     */
    BillDto create(CreateBillRequest request);
}
