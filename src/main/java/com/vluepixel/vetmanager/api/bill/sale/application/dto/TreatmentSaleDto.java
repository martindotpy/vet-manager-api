package com.vluepixel.vetmanager.api.bill.sale.application.dto;

import com.vluepixel.vetmanager.api.medicalrecord.treatment.application.dto.TreatmentDto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Treatment sale DTO.
 */
@Getter
@SuperBuilder
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public final class TreatmentSaleDto extends SaleDto {
    private TreatmentDto treatment;
}
