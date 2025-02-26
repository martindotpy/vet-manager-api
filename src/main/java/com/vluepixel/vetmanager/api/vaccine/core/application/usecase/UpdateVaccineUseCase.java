package com.vluepixel.vetmanager.api.vaccine.core.application.usecase;

import static com.vluepixel.vetmanager.api.shared.domain.criteria.Filter.equal;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.bill.sale.domain.model.ProductSale;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.Sale;
import com.vluepixel.vetmanager.api.bill.sale.domain.repository.SaleRepository;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;
import com.vluepixel.vetmanager.api.shared.application.port.out.TransactionalPort;
import com.vluepixel.vetmanager.api.shared.domain.criteria.Criteria;
import com.vluepixel.vetmanager.api.shared.domain.exception.InternalServerErrorException;
import com.vluepixel.vetmanager.api.shared.domain.exception.NotFoundException;
import com.vluepixel.vetmanager.api.shared.domain.exception.RegisterNotInstanceOfSubclassException;
import com.vluepixel.vetmanager.api.shared.domain.query.FieldUpdate;
import com.vluepixel.vetmanager.api.vaccine.core.application.dto.VaccineDto;
import com.vluepixel.vetmanager.api.vaccine.core.application.mapper.VaccineMapper;
import com.vluepixel.vetmanager.api.vaccine.core.application.port.in.UpdateVaccinePort;
import com.vluepixel.vetmanager.api.vaccine.core.domain.model.Vaccine;
import com.vluepixel.vetmanager.api.vaccine.core.domain.repository.VaccineRepository;
import com.vluepixel.vetmanager.api.vaccine.core.domain.request.UpdateVaccineRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Update vaccine use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class UpdateVaccineUseCase implements UpdateVaccinePort {
    private final TransactionalPort transactionalPort;

    private final SaleRepository saleRepository;

    private final VaccineRepository vaccineRepository;
    private final VaccineMapper vaccineMapper;

    @Override
    public VaccineDto update(Long patientId, UpdateVaccineRequest request) {
        MDC.put("operationId", "Vaccine id " + request.getId());
        log.info("Updating vaccine info");

        // Verify if the product sale id corresponds to a product sale
        Long productSaleId = request.getProductSaleId();
        if (productSaleId != null) {
            Sale sale = saleRepository.findById(productSaleId)
                    .orElseThrow(() -> new NotFoundException(ProductSale.class, productSaleId));
            if (!(sale instanceof ProductSale)) {
                throw new RegisterNotInstanceOfSubclassException(ProductSale.class, productSaleId);
            }
        }

        Vaccine updatedVaccine = vaccineMapper.fromRequest(request).build();
        int rowsModified = transactionalPort.run((aux) -> {
            aux.setEntityClass(Vaccine.class);

            return vaccineRepository.updateBy(
                    Criteria.of(
                            equal("id", request.getId()),
                            equal("patient.id", patientId)),
                    FieldUpdate.set("name", updatedVaccine.getName()),
                    FieldUpdate.set("doseInMilliliters", updatedVaccine.getDoseInMilliliters()),
                    FieldUpdate.set("providedAt", updatedVaccine.getProvidedAt()),
                    FieldUpdate.set("vaccinator", updatedVaccine.getVaccinator()),
                    FieldUpdate.set("productSale", updatedVaccine.getProductSale()));
        });

        // Verify any unexpected behavior
        if (rowsModified == 0) {
            throw new NotFoundException(Vaccine.class, request.getId());
        } else if (rowsModified > 1) {
            throw new InternalServerErrorException(
                    new IllegalStateException(
                            "Vaccine with patient id '" +
                                    patientId +
                                    "' and id '" +
                                    request.getId() +
                                    "' has more than one row modified"));
        }

        Vaccine updatedVaccineAux = vaccineRepository.findById(request.getId()).get();

        log.info("Vaccine updated");

        return vaccineMapper.toDto(updatedVaccineAux);
    }
}
