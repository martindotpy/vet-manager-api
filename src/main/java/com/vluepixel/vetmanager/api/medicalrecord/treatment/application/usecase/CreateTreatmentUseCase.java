package com.vluepixel.vetmanager.api.medicalrecord.treatment.application.usecase;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.medicalrecord.treatment.application.dto.TreatmentDto;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.application.mapper.TreatmentMapper;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.application.port.in.CreateTreatmentPort;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.domain.model.Treatment;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.domain.repository.TreatmentRepository;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.domain.request.CreateTreatmentRequest;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Create treatment use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class CreateTreatmentUseCase implements CreateTreatmentPort {
    private final TreatmentRepository treatmentRepository;
    private final TreatmentMapper treatmentMapper;

    @Override
    public TreatmentDto create(CreateTreatmentRequest request) {
        MDC.put("operationId", "Treatment with medical record id " + request.getMedicalRecordId());
        log.info("Creating treatment");

        Treatment newTreatment = treatmentMapper.fromRequest(request).build();
        newTreatment = treatmentRepository.save(newTreatment);

        log.info("Treatment created");

        return treatmentMapper.toDto(newTreatment);
    }
}
