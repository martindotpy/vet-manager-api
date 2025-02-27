package com.vluepixel.vetmanager.api.medicalrecord.treatment.application.usecase;

import static com.vluepixel.vetmanager.api.shared.domain.criteria.Filter.equal;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.medicalrecord.core.domain.repository.MedicalRecordRepository;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.application.port.in.DeleteTreatmentPort;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.domain.repository.TreatmentRepository;
import com.vluepixel.vetmanager.api.patient.core.domain.model.Patient;
import com.vluepixel.vetmanager.api.patient.core.domain.repository.PatientRepository;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;
import com.vluepixel.vetmanager.api.shared.domain.criteria.Criteria;
import com.vluepixel.vetmanager.api.shared.domain.exception.InternalServerErrorException;
import com.vluepixel.vetmanager.api.shared.domain.exception.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Delete treatment use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class DeleteTreatmentUseCase implements DeleteTreatmentPort {
    private final PatientRepository patientRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    private final TreatmentRepository treatmentRepository;

    @Override
    public void deleteByPatientIdAndMedicalRecordIdAndId(Long patientId, Long medicalRecordId, Long id) {
        MDC.put("operationId", "Treatment id " + id);
        log.info("Deleting treatment by id");

        // Verify if patient id exists
        if (!patientRepository.existsById(patientId)) {
            throw new NotFoundException(Patient.class, patientId);
        }

        // Verify if medical record id exists
        Long count = medicalRecordRepository.countBy(Criteria.of(
                equal("id", medicalRecordId),
                equal("patient.id", patientId)));

        if (count == 0) {
            throw new NotFoundException(Patient.class, patientId);
        } else if (count > 1) {
            throw new InternalServerErrorException(new IllegalStateException("More than one medical record found"));
        }

        // Delete the treatment
        treatmentRepository.deleteById(id);

        log.info("Treatment deleted");
    }
}
