package com.vluepixel.vetmanager.api.bill.core.application.usecase;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.bill.core.application.dto.BillDto;
import com.vluepixel.vetmanager.api.bill.core.application.mapper.BillMapper;
import com.vluepixel.vetmanager.api.bill.core.application.port.in.CreateBillPort;
import com.vluepixel.vetmanager.api.bill.core.domain.model.Bill;
import com.vluepixel.vetmanager.api.bill.core.domain.repository.BillRepository;
import com.vluepixel.vetmanager.api.bill.core.domain.request.CreateBillRequest;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Create bill use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class CreateBillUseCase implements CreateBillPort {
    private final BillRepository billRepository;
    private final BillMapper billMapper;

    @Override
    public BillDto create(CreateBillRequest request) {
        MDC.put("operationId", "Bill client id " + request.getClientId());
        log.info("Creating bill");

        Bill newBill = billMapper.fromRequest(request).build();
        newBill = billRepository.save(newBill);
        newBill = billRepository.findById(newBill.getId()).get();

        log.info("Bill created");

        return billMapper.toDto(newBill);
    }
}
