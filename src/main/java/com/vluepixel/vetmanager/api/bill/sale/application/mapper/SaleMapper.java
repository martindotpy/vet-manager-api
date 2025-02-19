package com.vluepixel.vetmanager.api.bill.sale.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.SubclassExhaustiveStrategy;
import org.mapstruct.SubclassMapping;

import com.vluepixel.vetmanager.api.bill.sale.application.dto.AppointmentSaleDto;
import com.vluepixel.vetmanager.api.bill.sale.application.dto.ProductSaleDto;
import com.vluepixel.vetmanager.api.bill.sale.application.dto.SaleDto;
import com.vluepixel.vetmanager.api.bill.sale.application.dto.TreatmentSaleDto;
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
import com.vluepixel.vetmanager.api.shared.application.mapper.BasicMapper;
import com.vluepixel.vetmanager.api.shared.application.mapper.StringUtilsMapper;

/**
 * Sale mapper.
 */
@Mapper(componentModel = "spring", subclassExhaustiveStrategy = SubclassExhaustiveStrategy.RUNTIME_EXCEPTION, uses = {
        StringUtilsMapper.class, AppointmentSaleMapper.class, TreatmentSaleMapper.class, ProductSaleMapper.class
})
public interface SaleMapper
        extends BasicMapper<Sale, SaleDto> {
    @Override
    @SubclassMapping(target = AppointmentSaleDto.class, source = AppointmentSale.class)
    @SubclassMapping(target = TreatmentSaleDto.class, source = TreatmentSale.class)
    @SubclassMapping(target = ProductSaleDto.class, source = ProductSale.class)
    SaleDto toDto(Sale sale);

    /**
     * Create sale from request.
     *
     * <ul>
     * <li><strong>Ignores:</strong>
     * <ul>
     * <li><code>id</code></li>
     * <li><code>seller</code></li>
     * </ul>
     * </li>
     * </ul>
     *
     * @param request the create sale request.
     * @return the sale builder
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "seller", ignore = true)
    @SubclassMapping(target = AppointmentSale.class, source = CreateAppointmentSaleRequest.class)
    @SubclassMapping(target = TreatmentSale.class, source = CreateTreatmentSaleRequest.class)
    @SubclassMapping(target = ProductSale.class, source = CreateProductSaleRequest.class)
    Sale fromRequest(CreateSaleRequest request);

    /**
     * Update sale from request.
     *
     * <ul>
     * <li><strong>Ignores:</strong>
     * <ul>
     * <li><code>seller</code></li>
     * </ul>
     * </li>
     * </ul>
     *
     * @param request the update sale request.
     * @return the sale builder
     */
    @Mapping(target = "seller", ignore = true)
    @SubclassMapping(target = AppointmentSale.class, source = UpdateAppointmentSaleRequest.class)
    @SubclassMapping(target = TreatmentSale.class, source = UpdateTreatmentSaleRequest.class)
    @SubclassMapping(target = ProductSale.class, source = UpdateProductSaleRequest.class)
    Sale fromRequest(UpdateSaleRequest request);
}
