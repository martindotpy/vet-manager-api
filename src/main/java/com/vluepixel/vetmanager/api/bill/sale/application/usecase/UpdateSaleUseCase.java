package com.vluepixel.vetmanager.api.bill.sale.application.usecase;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.bill.sale.application.dto.SaleDto;
import com.vluepixel.vetmanager.api.bill.sale.application.mapper.SaleMapper;
import com.vluepixel.vetmanager.api.bill.sale.application.port.in.UpdateSalePort;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.Sale;
import com.vluepixel.vetmanager.api.bill.sale.domain.repository.SaleRepository;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.UpdateSaleRequest;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Update sale use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class UpdateSaleUseCase implements UpdateSalePort {
    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;

    @Override
    public SaleDto update(UpdateSaleRequest request) {
        MDC.put("operationId", "Sale id " + request.getId());
        log.info("Updating sale");

        Sale updatedSale = saleMapper.fromRequest(request);
        updatedSale = saleRepository.save(updatedSale);

        log.info("Sale updated");

        return saleMapper.toDto(updatedSale);
    }
}
