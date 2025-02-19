package com.vluepixel.vetmanager.api.bill.core.application.usecase;

import static com.vluepixel.vetmanager.api.shared.adapter.in.util.AnsiShortcuts.fgBrightBlue;
import static com.vluepixel.vetmanager.api.shared.adapter.in.util.AnsiShortcuts.fgBrightGreen;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.bill.core.application.dto.BillDto;
import com.vluepixel.vetmanager.api.bill.core.application.mapper.BillMapper;
import com.vluepixel.vetmanager.api.bill.core.application.port.in.FindBillPort;
import com.vluepixel.vetmanager.api.bill.core.domain.model.Bill;
import com.vluepixel.vetmanager.api.bill.core.domain.repository.BillRepository;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;
import com.vluepixel.vetmanager.api.shared.domain.criteria.PaginatedCriteria;
import com.vluepixel.vetmanager.api.shared.domain.exception.NotFoundException;
import com.vluepixel.vetmanager.api.shared.domain.query.Paginated;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Find bill use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public final class FindBillUseCase implements FindBillPort {
    private final BillRepository billRepository;
    private final BillMapper billMapper;

    @Override
    public Paginated<BillDto> findPaginatedBy(PaginatedCriteria criteria) {
        MDC.put("operationId", "Bill by criteria: " + fgBrightBlue(criteria.hashCode()));
        log.info("Finding bill by {}",
                fgBrightBlue(criteria));

        Paginated<Bill> paginatedBill = billRepository.findPaginatedBy(criteria);

        log.info("{} bill found",
                fgBrightGreen(paginatedBill.getContent().size()));

        return paginatedBill.map(billMapper::toDto);
    }

    @Override
    public BillDto findById(Long id) {
        MDC.put("operationId", "Bill id " + id);
        log.info("Finding bill by id");

        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Bill.class, id));

        log.info("Bill found");

        return billMapper.toDto(bill);
    }
}
