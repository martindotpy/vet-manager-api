package com.vluepixel.vetmanager.api.medicalrecord.treatment.application.usecase;

import static com.vluepixel.vetmanager.api.shared.domain.criteria.Filter.equal;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.medicalrecord.core.domain.model.MedicalRecord;
import com.vluepixel.vetmanager.api.medicalrecord.core.domain.repository.MedicalRecordRepository;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.application.dto.TreatmentDto;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.application.mapper.TreatmentMapper;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.application.port.in.CreateTreatmentPort;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.domain.model.Treatment;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.domain.repository.TreatmentRepository;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.domain.request.CreateTreatmentRequest;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;
import com.vluepixel.vetmanager.api.shared.domain.criteria.Criteria;
import com.vluepixel.vetmanager.api.shared.domain.exception.InternalServerErrorException;
import com.vluepixel.vetmanager.api.shared.domain.exception.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Create treatment use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class CreateTreatmentUseCase implements CreateTreatmentPort {
    private final MedicalRecordRepository medicalRecordRepository;

    private final TreatmentRepository treatmentRepository;
    private final TreatmentMapper treatmentMapper;

    @Override
    public TreatmentDto create(Long patientId, Long recordId, CreateTreatmentRequest request) {
        MDC.put("operationId", "Treatment with medical record id " + request.getMedicalRecordId());
        log.info("Creating treatment");

        // Verify if the patient and record id exists
        Long count = medicalRecordRepository.countBy(Criteria.of(
                equal("id", recordId),
                equal("patient.id", patientId)));

        if (count == 0) {
            throw new NotFoundException(MedicalRecord.class, recordId);
        } else if (count > 1) { // Verify any unexpected behavior
            throw new InternalServerErrorException(new RuntimeException(
                    "Multiple records with patient id '" + patientId + "' and record id '" + recordId + "'"));
        }

        Treatment newTreatment = treatmentMapper.fromRequest(request).build();
        newTreatment = treatmentRepository.save(newTreatment);

        log.info("Treatment created");

        return treatmentMapper.toDto(newTreatment);
    }
}
