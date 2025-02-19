package com.vluepixel.vetmanager.api.bill.core.application.usecase;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.bill.core.application.dto.BillDto;
import com.vluepixel.vetmanager.api.bill.core.application.mapper.BillMapper;
import com.vluepixel.vetmanager.api.bill.core.application.port.in.UpdateBillPort;
import com.vluepixel.vetmanager.api.bill.core.domain.model.Bill;
import com.vluepixel.vetmanager.api.bill.core.domain.repository.BillRepository;
import com.vluepixel.vetmanager.api.bill.core.domain.request.UpdateBillRequest;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Update bill use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class UpdateBillUseCase implements UpdateBillPort {
    private final BillRepository billRepository;
    private final BillMapper billMapper;

    @Override
    public BillDto update(UpdateBillRequest request) {
        MDC.put("operationId", "Bill id " + request.getId());
        log.info("Updating bill");

        Bill updatedBill = billMapper.fromRequest(request).build();
        updatedBill = billRepository.save(updatedBill);
        updatedBill = billRepository.findById(updatedBill.getId()).get();

        log.info("Bill updated");

        return billMapper.toDto(updatedBill);
    }
}
