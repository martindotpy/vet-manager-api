package com.vluepixel.vetmanager.api.appointment.core.application.port.out;

import com.vluepixel.vetmanager.api.appointment.core.application.dto.AppointmentDto;
import com.vluepixel.vetmanager.api.appointment.core.domain.model.Appointment;
import com.vluepixel.vetmanager.api.shared.application.port.out.ExportExcelPort;

/**
 * Export appointment excel port.
 */
public interface ExportAppointmentExcelPort extends ExportExcelPort<Appointment, AppointmentDto> {
}
