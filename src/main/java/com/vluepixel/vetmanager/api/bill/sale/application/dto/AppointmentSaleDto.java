package com.vluepixel.vetmanager.api.bill.sale.application.dto;

import com.vluepixel.vetmanager.api.appointment.core.application.dto.AppointmentDto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Appointment sale DTO.
 */
@Getter
@SuperBuilder
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public final class AppointmentSaleDto extends SaleDto {
    private AppointmentDto appointment;
}
