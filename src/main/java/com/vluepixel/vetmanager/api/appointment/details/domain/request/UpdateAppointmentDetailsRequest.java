package com.vluepixel.vetmanager.api.appointment.details.domain.request;

import java.math.BigDecimal;

import com.vluepixel.vetmanager.api.shared.domain.request.Request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Update appointment details request.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class UpdateAppointmentDetailsRequest implements Request {
    @NotNull(message = "El id del detalle de la cita es requerido")
    @Positive(message = "El id del detalle de la cita debe ser mayor a 0")
    private Long id;

    @Max(value = 1440, message = "La duración de la cita no puede ser mayor a 1440 minutos")
    @Positive(message = "La duración de la cita debe ser mayor a 0")
    private int durationInMinutes;
    @DecimalMax(value = "9999.99", message = "El precio de la cita no puede ser mayor a 9999.99")
    @Positive(message = "El precio de la cita debe ser mayor a 0")
    private BigDecimal price;

    @NotNull(message = "El id del tipo de cita es requerido")
    @Positive(message = "El id del tipo de cita debe ser mayor a 0")
    private Long appointmentTypeId;
}
