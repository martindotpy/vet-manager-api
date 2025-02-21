package com.vluepixel.vetmanager.api.bill.sale.application.usecase;

import java.math.BigDecimal;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.appointment.core.domain.model.Appointment;
import com.vluepixel.vetmanager.api.appointment.core.domain.repository.AppointmentRepository;
import com.vluepixel.vetmanager.api.bill.core.domain.model.Bill;
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
import com.vluepixel.vetmanager.api.shared.domain.exception.RegisterNotInstanceOfSubclassException;

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

    private final UpdateBillTotalUsecase updateBillTotalUsecase;

    private final ProductRepository productRepository;

    private final AppointmentRepository appointmentRepository;

    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;

    @Override
    public SaleDto create(CreateSaleRequest request) {
        MDC.put("operationId", "Sale type " + request.getClass());
        log.info("Creating sale");

        Sale newSale = saleMapper.fromRequest(request);
        Sale newSaleAux = transactionalPort.run((aux) -> {
            Sale sale = newSale;

            switch (sale) {
                case ProductSale productSale -> {
                    // Update product
                    aux.setEntityClass(Product.class);

                    Long productId = productSale.getProduct().getId();
                    Product product = productRepository.findById(productId)
                            .orElseThrow(() -> new NotFoundException(Product.class, productId));

                    Integer quantity = productSale.getQuantity();

                    product.reduceQuantity(quantity);

                    productRepository.save(product);

                    // Calculate sale price
                    BigDecimal price = product.getPrice().multiply(BigDecimal.valueOf(productSale.getQuantity()));

                    sale = ProductSale.builder()
                            .price(price)
                            .product(product)
                            .quantity(productSale.getQuantity())
                            .discount(productSale.getDiscount())
                            .seller(productSale.getSeller())
                            .bill(productSale.getBill())
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
                            .price(price)
                            .appointment(appointment)
                            .discount(appointmentSale.getDiscount())
                            .seller(appointmentSale.getSeller())
                            .bill(appointmentSale.getBill())
                            .build();

                    aux.setEntityClass(appointmentSale.getClass());
                }
                case TreatmentSale treatmentSale -> {
                    aux.setEntityClass(treatmentSale.getClass());
                }
                default -> throw new RegisterNotInstanceOfSubclassException(sale.getClass());
            }

            sale = saleRepository.save(sale);

            // Update bill total
            aux.setEntityClass(Bill.class);
            updateBillTotalUsecase.updateTotal(sale.getBill().getId(), sale);

            return sale;
        });

        log.info("Sale created");

        return saleMapper.toDto(newSaleAux);
    }
}
