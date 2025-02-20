package com.vluepixel.vetmanager.api.appointment.core.data;

import java.time.LocalDateTime;

import com.vluepixel.vetmanager.api.appointment.core.domain.request.UpdateAppointmentRequest;

/**
 * Update appointment data provider.
 */
public class UpdateAppointmentDataProvider {

    public static final UpdateAppointmentRequest INVALID_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(10L)
            .startAt(LocalDateTime.now())
            .details(null)
            .patientId(null)
            .build();
}
