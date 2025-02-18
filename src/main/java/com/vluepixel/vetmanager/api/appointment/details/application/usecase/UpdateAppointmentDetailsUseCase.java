package com.vluepixel.vetmanager.api.appointment.details.application.usecase;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.appointment.details.application.dto.AppointmentDetailsDto;
import com.vluepixel.vetmanager.api.appointment.details.application.mapper.AppointmentDetailsMapper;
import com.vluepixel.vetmanager.api.appointment.details.application.port.in.UpdateAppointmentDetailsPort;
import com.vluepixel.vetmanager.api.appointment.details.domain.model.AppointmentDetails;
import com.vluepixel.vetmanager.api.appointment.details.domain.repository.AppointmentDetailsRepository;
import com.vluepixel.vetmanager.api.appointment.details.domain.request.UpdateAppointmentDetailsRequest;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Update appointment details use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class UpdateAppointmentDetailsUseCase implements UpdateAppointmentDetailsPort {
    private final AppointmentDetailsRepository appointmentDetailsRepository;
    private final AppointmentDetailsMapper appointmentDetailsMapper;

    @Override
    public AppointmentDetailsDto update(UpdateAppointmentDetailsRequest request) {
        MDC.put("operationId", "Appointment details id " + request.getId());
        log.info("Updating appointment details");

        AppointmentDetails updatedAppointmentDetails = appointmentDetailsMapper.fromRequest(request).build();
        updatedAppointmentDetails = appointmentDetailsRepository.save(updatedAppointmentDetails);

        log.info("Appointment details updated");

        return appointmentDetailsMapper.toDto(updatedAppointmentDetails);
    }
}
