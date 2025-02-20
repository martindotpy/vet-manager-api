package com.vluepixel.vetmanager.api.bill.sale.application.usecase;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.bill.sale.application.dto.SaleDto;
import com.vluepixel.vetmanager.api.bill.sale.application.mapper.SaleMapper;
import com.vluepixel.vetmanager.api.bill.sale.application.port.in.UpdateSalePort;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.AppointmentSale;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.ProductSale;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.Sale;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.TreatmentSale;
import com.vluepixel.vetmanager.api.bill.sale.domain.repository.SaleRepository;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.UpdateSaleRequest;
import com.vluepixel.vetmanager.api.product.core.domain.model.Product;
import com.vluepixel.vetmanager.api.product.core.domain.repository.ProductRepository;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;
import com.vluepixel.vetmanager.api.shared.application.port.out.TransactionalPort;
import com.vluepixel.vetmanager.api.shared.domain.exception.NotFoundException;
import com.vluepixel.vetmanager.api.shared.domain.exception.RegisterNotInstanceOfSubclassException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Update sale use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class UpdateSaleUseCase implements UpdateSalePort {
    private final TransactionalPort transactionalPort;

    private final ProductRepository productRepository;

    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;

    @Override
    public SaleDto update(UpdateSaleRequest request) {
        MDC.put("operationId", "Sale id " + request.getId());
        log.info("Updating sale");

        Sale updatedSale = saleMapper.fromRequest(request);
        Sale updatedSaleAux = transactionalPort.run((aux) -> {
            switch (updatedSale) {
                case ProductSale productSale -> {
                    aux.setEntityClass(Sale.class);

                    Sale sale = saleRepository.findById(productSale.getId())
                            .orElseThrow(() -> new NotFoundException(Sale.class, productSale.getId()));

                    if (!(sale instanceof ProductSale)) {
                        throw new RegisterNotInstanceOfSubclassException(ProductSale.class);
                    }

                    ProductSale preUpdateProductSale = (ProductSale) sale;

                    aux.setEntityClass(Product.class);

                    Integer quantity = productSale.getQuantity() - preUpdateProductSale.getQuantity();

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

            return saleRepository.save(updatedSale);
        });

        log.info("Sale updated");

        return saleMapper.toDto(updatedSaleAux);
    }
}
