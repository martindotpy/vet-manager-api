package com.vluepixel.vetmanager.api.appointment.type.application.usecase;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.appointment.type.application.dto.AppointmentTypeDto;
import com.vluepixel.vetmanager.api.appointment.type.application.mapper.AppointmentTypeMapper;
import com.vluepixel.vetmanager.api.appointment.type.application.port.in.UpdateAppointmentTypePort;
import com.vluepixel.vetmanager.api.appointment.type.domain.model.AppointmentType;
import com.vluepixel.vetmanager.api.appointment.type.domain.repository.AppointmentTypeRepository;
import com.vluepixel.vetmanager.api.appointment.type.domain.request.UpdateAppointmentTypeRequest;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Update appointment type use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class UpdateAppointmentTypeUseCase implements UpdateAppointmentTypePort {
    private final AppointmentTypeRepository appointmentTypeRepository;
    private final AppointmentTypeMapper appointmentTypeMapper;

    @Override
    public AppointmentTypeDto update(UpdateAppointmentTypeRequest request) {
        MDC.put("operationId", "Appointment type id " + request.getId());
        log.info("Updating appointment type");

        AppointmentType updatedAppointmentType = appointmentTypeMapper.fromRequest(request).build();
        updatedAppointmentType = appointmentTypeRepository.save(updatedAppointmentType);

        log.info("Appointment type updated");

        return appointmentTypeMapper.toDto(updatedAppointmentType);
    }
}
