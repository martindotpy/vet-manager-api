package com.vluepixel.vetmanager.api.bill.core.adapter.out.service;

import org.springframework.stereotype.Service;

import com.vluepixel.vetmanager.api.bill.core.application.dto.BillDto;
import com.vluepixel.vetmanager.api.bill.core.application.mapper.BillMapper;
import com.vluepixel.vetmanager.api.bill.core.application.port.out.ExportBillExcelPort;
import com.vluepixel.vetmanager.api.bill.core.domain.model.Bill;
import com.vluepixel.vetmanager.api.bill.core.domain.repository.BillRepository;
import com.vluepixel.vetmanager.api.shared.adapter.out.service.ExportExcelSubService;

/**
 * Export bill excel service.
 */
@Service
public final class ExportBillExcelService
        extends ExportExcelSubService<Bill, BillDto>
        implements ExportBillExcelPort {
    public ExportBillExcelService(
            BillRepository billRepository,
            BillMapper billMapper) {
        super(billRepository, billMapper, BillDto.class);
    }
}
