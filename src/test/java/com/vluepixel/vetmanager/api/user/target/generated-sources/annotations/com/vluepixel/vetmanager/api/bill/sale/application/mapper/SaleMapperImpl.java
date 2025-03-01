package com.vluepixel.vetmanager.api.bill.sale.application.mapper;

import com.vluepixel.vetmanager.api.bill.sale.application.dto.SaleDto;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.AppointmentSale;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.ProductSale;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.Sale;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.TreatmentSale;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.CreateAppointmentSaleRequest;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.CreateProductSaleRequest;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.CreateSaleRequest;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.CreateTreatmentSaleRequest;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.UpdateAppointmentSaleRequest;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.UpdateProductSaleRequest;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.UpdateSaleRequest;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.UpdateTreatmentSaleRequest;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-28T21:41:21-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.41.0.z20250213-2037, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class SaleMapperImpl implements SaleMapper {

    @Autowired
    private AppointmentSaleMapper appointmentSaleMapper;
    @Autowired
    private TreatmentSaleMapper treatmentSaleMapper;
    @Autowired
    private ProductSaleMapper productSaleMapper;

    @Override
    public SaleDto toDto(Sale sale) {
        if ( sale == null ) {
            return null;
        }

        if (sale instanceof AppointmentSale) {
            return appointmentSaleMapper.toDto( (AppointmentSale) sale );
        }
        else if (sale instanceof TreatmentSale) {
            return treatmentSaleMapper.toDto( (TreatmentSale) sale );
        }
        else if (sale instanceof ProductSale) {
            return productSaleMapper.toDto( (ProductSale) sale );
        }
        else {
            throw new IllegalArgumentException("Not all subclasses are supported for this mapping. Missing for " + sale.getClass());
        }
    }

    @Override
    public Sale fromRequest(CreateSaleRequest request) {
        if ( request == null ) {
            return null;
        }

        if (request instanceof CreateAppointmentSaleRequest) {
            return appointmentSaleMapper.fromRequest( (CreateAppointmentSaleRequest) request );
        }
        else if (request instanceof CreateTreatmentSaleRequest) {
            return treatmentSaleMapper.fromRequest( (CreateTreatmentSaleRequest) request );
        }
        else if (request instanceof CreateProductSaleRequest) {
            return productSaleMapper.fromRequest( (CreateProductSaleRequest) request );
        }
        else {
            throw new IllegalArgumentException("Not all subclasses are supported for this mapping. Missing for " + request.getClass());
        }
    }

    @Override
    public Sale fromRequest(UpdateSaleRequest request) {
        if ( request == null ) {
            return null;
        }

        if (request instanceof UpdateAppointmentSaleRequest) {
            return appointmentSaleMapper.fromRequest( (UpdateAppointmentSaleRequest) request );
        }
        else if (request instanceof UpdateTreatmentSaleRequest) {
            return treatmentSaleMapper.fromRequest( (UpdateTreatmentSaleRequest) request );
        }
        else if (request instanceof UpdateProductSaleRequest) {
            return productSaleMapper.fromRequest( (UpdateProductSaleRequest) request );
        }
        else {
            throw new IllegalArgumentException("Not all subclasses are supported for this mapping. Missing for " + request.getClass());
        }
    }
}
