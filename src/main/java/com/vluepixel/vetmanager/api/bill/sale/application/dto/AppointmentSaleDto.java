package com.vluepixel.vetmanager.api.bill.sale.application.dto;

import com.vluepixel.vetmanager.api.appointment.core.application.dto.AppointmentDto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Appointment sale DTO.
 */
@Getter
@SuperBuilder
public final class AppointmentSaleDto extends SaleDto {
    private AppointmentDto appointment;
}
