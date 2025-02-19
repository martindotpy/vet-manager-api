package com.vluepixel.vetmanager.api.bill.sale.application.usecase;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.bill.sale.application.dto.SaleDto;
import com.vluepixel.vetmanager.api.bill.sale.application.mapper.SaleMapper;
import com.vluepixel.vetmanager.api.bill.sale.application.port.in.CreateSalePort;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.Sale;
import com.vluepixel.vetmanager.api.bill.sale.domain.repository.SaleRepository;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.CreateSaleRequest;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Create sale use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class CreateSaleUseCase implements CreateSalePort {
    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;

    @Override
    public SaleDto create(CreateSaleRequest request) {
        MDC.put("operationId", "Sale type " + request.getClass());
        log.info("Creating sale");

        Sale newSale = saleMapper.fromRequest(request);
        newSale = saleRepository.save(newSale);

        log.info("Sale created");

        return saleMapper.toDto(newSale);
    }
}
