package com.vluepixel.vetmanager.api.bill.core.application.port.out;

import com.vluepixel.vetmanager.api.bill.core.application.dto.BillDto;
import com.vluepixel.vetmanager.api.bill.core.domain.model.Bill;
import com.vluepixel.vetmanager.api.shared.application.port.out.ExportExcelPort;

/**
 * Export bill excel port.
 */
public interface ExportBillExcelPort extends ExportExcelPort<Bill, BillDto> {
}
