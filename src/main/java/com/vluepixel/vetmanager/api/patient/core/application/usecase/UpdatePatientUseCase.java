package com.vluepixel.vetmanager.api.patient.core.application.usecase;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.patient.core.application.dto.PatientDto;
import com.vluepixel.vetmanager.api.patient.core.application.mapper.PatientMapper;
import com.vluepixel.vetmanager.api.patient.core.application.port.in.UpdatePatientPort;
import com.vluepixel.vetmanager.api.patient.core.domain.model.Patient;
import com.vluepixel.vetmanager.api.patient.core.domain.repository.PatientRepository;
import com.vluepixel.vetmanager.api.patient.core.domain.request.UpdatePatientRequest;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Update patient use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class UpdatePatientUseCase implements UpdatePatientPort {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public PatientDto update(UpdatePatientRequest request) {
        MDC.put("operationId", "Patient id " + request.getId());
        log.info("Updating patient");

        Patient updatedPatient = patientMapper.fromRequest(request).build();
        updatedPatient = patientRepository.save(updatedPatient);

        log.info("Patient updated");

        return patientMapper.toDto(updatedPatient);
    }
}
