package com.vluepixel.vetmanager.api.bill.core.application.usecase;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.bill.core.application.port.in.DeleteBillPort;
import com.vluepixel.vetmanager.api.bill.core.domain.repository.BillRepository;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Delete bill use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class DeleteBillUseCase implements DeleteBillPort {
    private final BillRepository billRepository;

    @Override
    public void deleteById(Long id) {
        MDC.put("operationId", "Bill id " + id);
        log.info("Deleting bill by id");

        billRepository.deleteById(id);

        log.info("Bill deleted");
    }
}
