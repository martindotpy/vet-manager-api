package com.vluepixel.vetmanager.api.bill.sale.application.dto;

import java.math.BigDecimal;

import com.vluepixel.vetmanager.api.user.core.application.dto.UserDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Sale DTO.
 */
@Getter
@SuperBuilder
@NoArgsConstructor
public abstract class SaleDto {
    private Long id;

    private BigDecimal price;
    private Integer discount;
    private UserDto seller;
}
