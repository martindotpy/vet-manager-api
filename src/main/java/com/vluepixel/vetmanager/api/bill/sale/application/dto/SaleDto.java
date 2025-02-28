package com.vluepixel.vetmanager.api.bill.sale.application.dto;

import java.math.BigDecimal;

import com.vluepixel.vetmanager.api.user.core.application.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Sale DTO.
 */
@Getter
@SuperBuilder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public abstract sealed class SaleDto permits AppointmentSaleDto, TreatmentSaleDto, ProductSaleDto {
    private Long id;

    private BigDecimal price;
    private Integer discount;
    private UserDto seller;
}
