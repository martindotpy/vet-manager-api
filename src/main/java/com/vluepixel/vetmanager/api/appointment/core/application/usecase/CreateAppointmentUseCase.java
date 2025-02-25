package com.vluepixel.vetmanager.api.appointment.core.application.usecase;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.appointment.core.application.dto.AppointmentDto;
import com.vluepixel.vetmanager.api.appointment.core.application.mapper.AppointmentMapper;
import com.vluepixel.vetmanager.api.appointment.core.application.port.in.CreateAppointmentPort;
import com.vluepixel.vetmanager.api.appointment.core.domain.model.Appointment;
import com.vluepixel.vetmanager.api.appointment.core.domain.repository.AppointmentRepository;
import com.vluepixel.vetmanager.api.appointment.core.domain.request.CreateAppointmentRequest;
import com.vluepixel.vetmanager.api.appointment.details.domain.model.AppointmentDetails;
import com.vluepixel.vetmanager.api.appointment.details.domain.repository.AppointmentDetailsRepository;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;
import com.vluepixel.vetmanager.api.shared.application.port.out.TransactionalPort;
import com.vluepixel.vetmanager.api.shared.domain.util.StringUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Create appointment use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class CreateAppointmentUseCase implements CreateAppointmentPort {
    private final TransactionalPort transactionalPort;

    private final AppointmentDetailsRepository appointmentDetailsRepository;

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    @Override
    public AppointmentDto create(CreateAppointmentRequest request) {
        MDC.put("operationId", "Appointment start at " + request.getStartAt());
        log.info("Creating appointment");

        // Make null the description if the value is blank
        String description = StringUtils.toNullIfBlank(request.getDescription());

        Appointment newAppointment = appointmentMapper.fromRequest(request)
                .description(description)
                .build();
        Appointment newAppointmentAux = transactionalPort.run((aux) -> {
            // Save the details
            aux.setEntityClass(AppointmentDetails.class);
            newAppointment.getDetails().forEach(appointmentDetailsRepository::save);

            log.info("Appointment details created");

            // Save the appointment
            aux.setEntityClass(Appointment.class);
            return appointmentRepository.save(newAppointment);
        });

        log.info("Appointment created");

        return appointmentMapper.toDto(appointmentRepository.findById(newAppointmentAux.getId()).get());
    }
}
