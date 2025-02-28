package com.vluepixel.vetmanager.api.bill.sale.application.usecase;

import java.math.BigDecimal;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.appointment.core.domain.model.Appointment;
import com.vluepixel.vetmanager.api.appointment.core.domain.repository.AppointmentRepository;
import com.vluepixel.vetmanager.api.bill.core.domain.model.Bill;
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

    private final UpdateBillTotalUsecase updateBillTotalUsecase;

    private final ProductRepository productRepository;

    private final AppointmentRepository appointmentRepository;

    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;

    @Override
    public SaleDto update(UpdateSaleRequest request) {
        MDC.put("operationId", "Sale id " + request.getId());
        log.info("Updating sale");

        Sale updatedSale = saleMapper.fromRequest(request);
        Sale salePreUpdate = saleRepository.findById(updatedSale.getId())
                .orElseThrow(() -> new NotFoundException(Sale.class, updatedSale.getId()));
        Sale updatedSaleAux = transactionalPort.run((aux) -> {
            Sale sale = updatedSale;

            switch (sale) {
                case ProductSale productSale -> {
                    aux.setEntityClass(Sale.class);

                    if (!(salePreUpdate instanceof ProductSale)) {
                        throw new RegisterNotInstanceOfSubclassException(ProductSale.class);
                    }

                    ProductSale preUpdateProductSale = (ProductSale) sale;

                    // Update product
                    aux.setEntityClass(Product.class);

                    Integer quantity = productSale.getQuantity() - preUpdateProductSale.getQuantity();

                    Long productId = productSale.getProduct().getId();
                    Product product = productRepository.findById(productId)
                            .orElseThrow(() -> new NotFoundException(Product.class, productId));
                    product.reduceQuantity(quantity); // Increase quantity if quantity is negative

                    productRepository.save(product);

                    // Update sale price
                    BigDecimal price = product.getPrice().multiply(BigDecimal.valueOf(productSale.getQuantity()));

                    sale = ProductSale.builder()
                            .id(productSale.getId())
                            .price(price)
                            .product(product)
                            .quantity(productSale.getQuantity())
                            .discount(productSale.getDiscount())
                            .seller(salePreUpdate.getSeller())
                            .bill(salePreUpdate.getBill())
                            .build();

                    aux.setEntityClass(productSale.getClass());
                }
                case AppointmentSale appointmentSale -> {
                    // Calculate sale price
                    aux.setEntityClass(Appointment.class);

                    Long appointmentId = appointmentSale.getAppointment().getId();
                    Appointment appointment = appointmentRepository.findById(appointmentId)
                            .orElseThrow(() -> new NotFoundException(Appointment.class, appointmentId));
                    BigDecimal price = appointment.getDetails().stream()
                            .reduce(BigDecimal.ZERO, (total, detail) -> total.add(detail.getPrice()), BigDecimal::add);

                    sale = AppointmentSale.builder()
                            .id(appointmentSale.getId())
                            .price(price)
                            .appointment(appointment)
                            .discount(appointmentSale.getDiscount())
                            .seller(salePreUpdate.getSeller())
                            .bill(salePreUpdate.getBill())
                            .build();

                    aux.setEntityClass(appointmentSale.getClass());
                }
                case TreatmentSale treatmentSale -> {
                    aux.setEntityClass(treatmentSale.getClass());
                    sale = TreatmentSale.builder()
                            .id(treatmentSale.getId())
                            .price(treatmentSale.getPrice())
                            .treatment(treatmentSale.getTreatment())
                            .discount(treatmentSale.getDiscount())
                            .seller(salePreUpdate.getSeller())
                            .bill(salePreUpdate.getBill())
                            .build();
                }
                default -> throw new RegisterNotInstanceOfSubclassException(Sale.class);
            }

            // Update bill total
            aux.setEntityClass(Bill.class);
            updateBillTotalUsecase.updateTotal(sale.getBill().getId(), sale);

            return saleRepository.save(sale);
        });

        log.info("Sale updated");

        return saleMapper.toDto(saleRepository.findById(updatedSaleAux.getId()).get());
    }
}
