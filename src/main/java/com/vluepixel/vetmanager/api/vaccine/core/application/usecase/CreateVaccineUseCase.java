package com.vluepixel.vetmanager.api.vaccine.core.application.usecase;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.bill.sale.domain.model.ProductSale;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.Sale;
import com.vluepixel.vetmanager.api.bill.sale.domain.repository.SaleRepository;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;
import com.vluepixel.vetmanager.api.shared.domain.exception.NotFoundException;
import com.vluepixel.vetmanager.api.shared.domain.exception.RegisterNotInstanceOfSubclassException;
import com.vluepixel.vetmanager.api.vaccine.core.application.dto.VaccineDto;
import com.vluepixel.vetmanager.api.vaccine.core.application.mapper.VaccineMapper;
import com.vluepixel.vetmanager.api.vaccine.core.application.port.in.CreateVaccinePort;
import com.vluepixel.vetmanager.api.vaccine.core.domain.model.Vaccine;
import com.vluepixel.vetmanager.api.vaccine.core.domain.repository.VaccineRepository;
import com.vluepixel.vetmanager.api.vaccine.core.domain.request.CreateVaccineRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Create vaccine use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class CreateVaccineUseCase implements CreateVaccinePort {
    private final SaleRepository saleRepository;

    private final VaccineRepository vaccineRepository;
    private final VaccineMapper vaccineMapper;

    @Override
    public VaccineDto create(CreateVaccineRequest request) {
        MDC.put("operationId", "Vaccine name " + request.getName());
        log.info("Creating vaccine");

        // Verify if the product sale id corresponds to a product sale
        Long productSaleId = request.getProductSaleId();
        if (productSaleId != null) {
            Sale sale = saleRepository.findById(productSaleId)
                    .orElseThrow(() -> new NotFoundException(ProductSale.class, productSaleId));
            if (!(sale instanceof ProductSale)) {
                throw new RegisterNotInstanceOfSubclassException(ProductSale.class, productSaleId);
            }
        }

        Vaccine newVaccine = vaccineMapper.fromRequest(request).build();
        newVaccine = vaccineRepository.save(newVaccine);

        log.info("Vaccine created");

        return vaccineMapper.toDto(vaccineRepository.findById(newVaccine.getId()).get());
    }
}
