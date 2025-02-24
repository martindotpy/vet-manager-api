package com.vluepixel.vetmanager.api.appointment.core.application.usecase;

import java.util.stream.Stream;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.appointment.core.application.dto.AppointmentDto;
import com.vluepixel.vetmanager.api.appointment.core.application.mapper.AppointmentMapper;
import com.vluepixel.vetmanager.api.appointment.core.application.port.in.UpdateAppointmentPort;
import com.vluepixel.vetmanager.api.appointment.core.domain.model.Appointment;
import com.vluepixel.vetmanager.api.appointment.core.domain.repository.AppointmentRepository;
import com.vluepixel.vetmanager.api.appointment.core.domain.request.UpdateAppointmentRequest;
import com.vluepixel.vetmanager.api.appointment.details.domain.model.AppointmentDetails;
import com.vluepixel.vetmanager.api.appointment.details.domain.repository.AppointmentDetailsRepository;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;
import com.vluepixel.vetmanager.api.shared.application.port.out.TransactionalPort;
import com.vluepixel.vetmanager.api.shared.domain.exception.InnerEntityDoNotBelongToEntity;
import com.vluepixel.vetmanager.api.shared.domain.exception.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Update appointment use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class UpdateAppointmentUseCase implements UpdateAppointmentPort {
    private final TransactionalPort transactionalPort;

    private final AppointmentDetailsRepository appointmentDetailsRepository;

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    @Override
    public AppointmentDto update(UpdateAppointmentRequest request) {
        MDC.put("operationId", "Appointment id " + request.getId());
        log.info("Updating appointment");

        // Make null the description if the value is blank
        String description = request.getDescription();
        if (description != null && description.isBlank()) {
            description = null;
        }

        // Verify if the appointment details are valid
        Appointment appointmentToUpdate = appointmentRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException(Appointment.class, request.getId()));
        Appointment updatedAppointment = appointmentMapper.fromRequest(request)
                .description(description)
                .build();
        Stream<AppointmentDetails> streamPreviousDetails = appointmentToUpdate.getDetails().stream();

        for (AppointmentDetails updatedDetails : updatedAppointment.getDetails()) {
            if (!streamPreviousDetails.anyMatch(d -> d.getId().equals(updatedDetails.getId()))) {
                throw new InnerEntityDoNotBelongToEntity(
                        "details", "El detalle con id " + updatedDetails.getId() + " no pertenece a la cita");
            }
        }

        Appointment updatedAppointmentAux = transactionalPort.run((aux) -> {
            // Update the details
            aux.setEntityClass(AppointmentDetails.class);

            updatedAppointment.getDetails().forEach(appointmentDetailsRepository::save);

            log.info("Appointment details updated");

            aux.setEntityClass(Appointment.class);

            return appointmentRepository.save(updatedAppointment);
        });

        log.info("Appointment updated");

        return appointmentMapper.toDto(updatedAppointmentAux);
    }
}
