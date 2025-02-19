package com.vluepixel.vetmanager.api.bill.sale.application.usecase;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.bill.sale.application.port.in.DeleteSalePort;
import com.vluepixel.vetmanager.api.bill.sale.domain.repository.SaleRepository;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Delete sale use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class DeleteSaleUseCase implements DeleteSalePort {
    private final SaleRepository saleRepository;

    @Override
    public void deleteById(Integer id) {
        MDC.put("operationId", "Sale id " + id);
        log.info("Deleting sale by id");

        saleRepository.deleteById(id);

        log.info("Sale deleted");
    }
}
