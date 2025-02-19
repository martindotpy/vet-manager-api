package com.vluepixel.vetmanager.api.bill.sale.application.dto;

import com.vluepixel.vetmanager.api.medicalrecord.treatment.application.dto.TreatmentDto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Treatment sale DTO.
 */
@Getter
@SuperBuilder
public final class TreatmentSaleDto extends SaleDto {
    private TreatmentDto treatment;
}
