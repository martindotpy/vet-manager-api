package com.vluepixel.vetmanager.api.bill.sale.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vluepixel.vetmanager.api.bill.sale.application.dto.TreatmentSaleDto;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.TreatmentSale;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.CreateTreatmentSaleRequest;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.UpdateTreatmentSaleRequest;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.domain.model.Treatment;
import com.vluepixel.vetmanager.api.shared.application.mapper.CrudMapper;
import com.vluepixel.vetmanager.api.shared.application.mapper.StringUtilsMapper;

/**
 * Treatment sale mapper.
 */
@Mapper(componentModel = "spring", uses = { StringUtilsMapper.class })
public interface TreatmentSaleMapper
        extends CrudMapper<TreatmentSale, TreatmentSaleDto, TreatmentSale> {
    /**
     * Create treatment sale from request.
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
     * @param request the create treatment sale request.
     * @return the treatment sale builder
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "seller", ignore = true)
    @Mapping(target = "treatment", source = "treatmentId")
    TreatmentSale fromRequest(CreateTreatmentSaleRequest request);

    /**
     * Update treatment sale from request.
     *
     * <ul>
     * <li><strong>Ignores:</strong>
     * <ul>
     * <li><code>seller</code></li>
     * </ul>
     * </li>
     * </ul>
     *
     * @param request the update treatment sale request.
     * @return the treatment sale builder
     */
    @Mapping(target = "seller", ignore = true)
    @Mapping(target = "treatment", source = "treatmentId")
    TreatmentSale fromRequest(UpdateTreatmentSaleRequest request);

    /**
     * Maps the treatment id to an treatment.
     *
     * @param treatmentId the treatment id.
     * @return the treatment
     */
    default Treatment mapTreatmentIdToTreatment(Long treatmentId) {
        return Treatment.builder().id(treatmentId).build();
    }
}
