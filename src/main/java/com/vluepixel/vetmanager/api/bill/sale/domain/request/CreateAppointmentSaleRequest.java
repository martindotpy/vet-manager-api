package com.vluepixel.vetmanager.api.bill.sale.domain.request;

import com.vluepixel.vetmanager.api.bill.sale.domain.model.enums.SaleType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Create appointment sale request.
 */
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public final class CreateAppointmentSaleRequest extends CreateSaleRequest {
    private final SaleType type = SaleType.APPOINTMENT;

    @NotNull(message = "El id de la cita es requerido")
    @Positive(message = "El id de la cita debe ser mayor a 0")
    private Long appointmentId;
}
