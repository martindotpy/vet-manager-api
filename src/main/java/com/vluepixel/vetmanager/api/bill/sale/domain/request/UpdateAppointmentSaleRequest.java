package com.vluepixel.vetmanager.api.bill.sale.domain.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Update appointment sale request.
 */
@Getter
@SuperBuilder
public final class UpdateAppointmentSaleRequest extends UpdateSaleRequest {
    @NotNull(message = "El id de la cita es requerido")
    @Positive(message = "El id de la cita debe ser mayor a 0")
    private Long appointmentId;
}
