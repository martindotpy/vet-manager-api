package com.vluepixel.vetmanager.api.bill.sale.application.usecase;

import static com.vluepixel.vetmanager.api.shared.domain.criteria.Filter.equal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.vluepixel.vetmanager.api.bill.core.domain.repository.BillRepository;
import com.vluepixel.vetmanager.api.bill.sale.application.port.in.UpdateBillTotalPort;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.Sale;
import com.vluepixel.vetmanager.api.bill.sale.domain.repository.SaleRepository;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;
import com.vluepixel.vetmanager.api.shared.domain.query.FieldUpdate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Update bill total use case.
 *
 * <p>
 * This use case requires used in a transactional context.
 * </p>
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public final class UpdateBillTotalUsecase implements UpdateBillTotalPort {
    private final SaleRepository saleRepository;

    private final BillRepository billRepository;

    @Override
    public void updateTotal(Long billId, Sale newSale) {
        log.info("Updating bill total with id {}", billId);

        List<Sale> sales = saleRepository.findAllBy(equal("bill.id", billId));
        sales.add(newSale);

        log.info("Sales found: {}", sales.size());

        BigDecimal total = sales.stream()
                .map(sale -> sale.getPrice()
                        .multiply(BigDecimal.ONE.subtract(
                                BigDecimal.valueOf(sale.getDiscount())
                                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP))))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        log.info("Total calculated: {}", total);

        billRepository.update(billId, FieldUpdate.set("total", total));

        log.info("Bill total updated");
    }
}
