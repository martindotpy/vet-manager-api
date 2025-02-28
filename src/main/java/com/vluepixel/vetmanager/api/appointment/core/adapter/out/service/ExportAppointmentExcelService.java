package com.vluepixel.vetmanager.api.appointment.core.adapter.out.service;

import org.springframework.stereotype.Service;

import com.vluepixel.vetmanager.api.appointment.core.application.dto.AppointmentDto;
import com.vluepixel.vetmanager.api.appointment.core.application.mapper.AppointmentMapper;
import com.vluepixel.vetmanager.api.appointment.core.application.port.out.ExportAppointmentExcelPort;
import com.vluepixel.vetmanager.api.appointment.core.domain.model.Appointment;
import com.vluepixel.vetmanager.api.appointment.core.domain.repository.AppointmentRepository;
import com.vluepixel.vetmanager.api.shared.adapter.out.service.ExportExcelSubService;

/**
 * Export appointment excel service.
 */
@Service
public final class ExportAppointmentExcelService
        extends ExportExcelSubService<Appointment, AppointmentDto>
        implements ExportAppointmentExcelPort {
    public ExportAppointmentExcelService(
            AppointmentRepository appointmentRepository,
            AppointmentMapper appointmentMapper) {
        super(appointmentRepository, appointmentMapper);
    }
}
