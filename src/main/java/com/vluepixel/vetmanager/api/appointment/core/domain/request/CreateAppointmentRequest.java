package com.vluepixel.vetmanager.api.appointment.core.domain.request;

import java.time.LocalDateTime;
import java.util.List;

import com.vluepixel.vetmanager.api.appointment.details.domain.request.CreateAppointmentDetailsRequest;
import com.vluepixel.vetmanager.api.shared.domain.request.Request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Create appointment request.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class CreateAppointmentRequest implements Request {
    @NotNull(message = "La fecha de inicio es requerida")
    private LocalDateTime startAt;
    private String description;

    @NotEmpty(message = "La lista de detalles es requerido")
    private List<@Valid @NotNull(message = "Ningún detalle puede ser nulo") CreateAppointmentDetailsRequest> details;
    @NotNull(message = "El id del paciente es requerido")
    @Positive(message = "El id del paciente debe ser mayor a 0")
    private Long patientId;

    @AssertTrue(message = "La fecha de inicio debe ser mayor a la fecha actual")
    public boolean isStartAt() {
        if (startAt == null) {
            return true;
        }

        return startAt.isAfter(LocalDateTime.now().minusSeconds(15)); // Seconds added by ping or otherwise
    }
}
