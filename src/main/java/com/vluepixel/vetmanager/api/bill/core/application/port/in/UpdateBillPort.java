package com.vluepixel.vetmanager.api.bill.core.application.port.in;

import com.vluepixel.vetmanager.api.bill.core.application.dto.BillDto;
import com.vluepixel.vetmanager.api.bill.core.domain.request.UpdateBillRequest;

/**
 * Update bill port.
 */
public interface UpdateBillPort {
    /**
     * Update bill.
     *
     * @param request the update bill request.
     * @return the updated bill
     */
    BillDto update(UpdateBillRequest request);
}
