package com.vluepixel.vetmanager.api.bill.sale.application.usecase;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.bill.sale.application.dto.SaleDto;
import com.vluepixel.vetmanager.api.bill.sale.application.mapper.SaleMapper;
import com.vluepixel.vetmanager.api.bill.sale.application.port.in.CreateSalePort;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.AppointmentSale;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.ProductSale;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.Sale;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.TreatmentSale;
import com.vluepixel.vetmanager.api.bill.sale.domain.repository.SaleRepository;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.CreateSaleRequest;
import com.vluepixel.vetmanager.api.product.core.domain.model.Product;
import com.vluepixel.vetmanager.api.product.core.domain.repository.ProductRepository;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;
import com.vluepixel.vetmanager.api.shared.application.port.out.TransactionalPort;
import com.vluepixel.vetmanager.api.shared.domain.exception.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Create sale use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class CreateSaleUseCase implements CreateSalePort {
    private final TransactionalPort transactionalPort;

    private final ProductRepository productRepository;

    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;

    @Override
    public SaleDto create(CreateSaleRequest request) {
        MDC.put("operationId", "Sale type " + request.getClass());
        log.info("Creating sale");

        Sale newSale = saleMapper.fromRequest(request);
        Sale newSaleAux = transactionalPort.run((aux) -> {
            switch (newSale) {
                case ProductSale productSale -> {
                    aux.setEntityClass(Product.class);

                    Integer quantity = productSale.getQuantity();

                    Long productId = productSale.getProduct().getId();
                    Product product = productRepository.findById(productId)
                            .orElseThrow(() -> new NotFoundException(Product.class, productId));
                    product.reduceQuantity(quantity);

                    productRepository.save(product);

                    aux.setEntityClass(productSale.getClass());
                }
                case AppointmentSale appointmentSale -> {
                    aux.setEntityClass(appointmentSale.getClass());
                }
                case TreatmentSale treatmentSale -> {
                    aux.setEntityClass(treatmentSale.getClass());
                }
            }

            return saleRepository.save(newSale);
        });

        log.info("Sale created");

        return saleMapper.toDto(newSaleAux);
    }
}
