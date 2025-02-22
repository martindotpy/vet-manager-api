package com.vluepixel.vetmanager.api.bill.core.application.usecase;

import java.util.List;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.bill.core.application.dto.BillDto;
import com.vluepixel.vetmanager.api.bill.core.application.mapper.BillMapper;
import com.vluepixel.vetmanager.api.bill.core.application.port.in.UpdateBillPort;
import com.vluepixel.vetmanager.api.bill.core.domain.model.Bill;
import com.vluepixel.vetmanager.api.bill.core.domain.repository.BillRepository;
import com.vluepixel.vetmanager.api.bill.core.domain.request.UpdateBillRequest;
import com.vluepixel.vetmanager.api.client.core.domain.model.Client;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;
import com.vluepixel.vetmanager.api.shared.domain.exception.NotFoundException;
import com.vluepixel.vetmanager.api.shared.domain.exception.ValidationException;
import com.vluepixel.vetmanager.api.shared.domain.validation.ValidationError;

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

        Bill preUpdateBill = billRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException(Bill.class, request.getId()));

        if (preUpdateBill.getTotal().compareTo(request.getTotalPaid()) < 0) {
            throw new ValidationException(List.of(
                    new ValidationError("total_paid", "El total pagado no puede ser mayor al total de la cuenta")));
        }

        Bill updatedBill = billMapper.toBuilder(preUpdateBill)
                .discount(request.getDiscount())
                .totalPaid(request.getTotalPaid())
                .client(Client.builder().id(request.getClientId()).build())
                .build();
        updatedBill = billRepository.save(updatedBill);
        updatedBill = billRepository.findById(updatedBill.getId()).get();

        log.info("Bill updated");

        return billMapper.toDto(updatedBill);
    }
}
