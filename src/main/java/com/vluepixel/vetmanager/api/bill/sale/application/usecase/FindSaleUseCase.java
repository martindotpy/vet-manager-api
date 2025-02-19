package com.vluepixel.vetmanager.api.bill.sale.application.usecase;

import static com.vluepixel.vetmanager.api.shared.adapter.in.util.AnsiShortcuts.fgBrightBlue;
import static com.vluepixel.vetmanager.api.shared.adapter.in.util.AnsiShortcuts.fgBrightGreen;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.bill.sale.application.dto.SaleDto;
import com.vluepixel.vetmanager.api.bill.sale.application.mapper.SaleMapper;
import com.vluepixel.vetmanager.api.bill.sale.application.port.in.FindSalePort;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.Sale;
import com.vluepixel.vetmanager.api.bill.sale.domain.repository.SaleRepository;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;
import com.vluepixel.vetmanager.api.shared.domain.criteria.PaginatedCriteria;
import com.vluepixel.vetmanager.api.shared.domain.exception.NotFoundException;
import com.vluepixel.vetmanager.api.shared.domain.query.Paginated;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Find sale use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public final class FindSaleUseCase implements FindSalePort {
    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;

    @Override
    public Paginated<SaleDto> findPaginatedBy(PaginatedCriteria criteria) {
        MDC.put("operationId", "Sale by criteria: " + fgBrightBlue(criteria.hashCode()));
        log.info("Finding sale by {}",
                fgBrightBlue(criteria));

        Paginated<Sale> paginatedSale = saleRepository.findPaginatedBy(criteria);

        log.info("{} sale found",
                fgBrightGreen(paginatedSale.getContent().size()));

        return paginatedSale.map(saleMapper::toDto);
    }

    @Override
    public SaleDto findById(Integer id) {
        MDC.put("operationId", "Sale id " + id);
        log.info("Finding sale by id");

        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Sale.class, id));

        log.info("Sale found");

        return saleMapper.toDto(sale);
    }
}
