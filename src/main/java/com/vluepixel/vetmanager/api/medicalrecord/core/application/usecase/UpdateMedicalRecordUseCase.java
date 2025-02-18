package com.vluepixel.vetmanager.api.medicalrecord.core.application.usecase;

import static com.vluepixel.vetmanager.api.shared.domain.criteria.Filter.equal;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.medicalrecord.core.application.dto.MedicalRecordDto;
import com.vluepixel.vetmanager.api.medicalrecord.core.application.mapper.MedicalRecordMapper;
import com.vluepixel.vetmanager.api.medicalrecord.core.application.port.in.UpdateMedicalRecordPort;
import com.vluepixel.vetmanager.api.medicalrecord.core.domain.model.MedicalRecord;
import com.vluepixel.vetmanager.api.medicalrecord.core.domain.repository.MedicalRecordRepository;
import com.vluepixel.vetmanager.api.medicalrecord.core.domain.request.UpdateMedicalRecordRequest;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;
import com.vluepixel.vetmanager.api.shared.application.port.out.TransactionalPort;
import com.vluepixel.vetmanager.api.shared.domain.criteria.Criteria;
import com.vluepixel.vetmanager.api.shared.domain.exception.InternalServerErrorException;
import com.vluepixel.vetmanager.api.shared.domain.exception.NotFoundException;
import com.vluepixel.vetmanager.api.shared.domain.query.FieldUpdate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Update medical record use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class UpdateMedicalRecordUseCase implements UpdateMedicalRecordPort {
    private final TransactionalPort transactionalPort;

    private final MedicalRecordRepository medicalRecordRepository;
    private final MedicalRecordMapper medicalRecordMapper;

    @Override
    public MedicalRecordDto update(Long patientId, UpdateMedicalRecordRequest request) {
        MDC.put("operationId", "Medical record id " + request.getId());
        log.info("Updating medical record");

        MedicalRecord updatedMedicalRecord = medicalRecordMapper.fromRequest(request).build();
        int rowsModified = transactionalPort.run((aux) -> {
            aux.setEntityClass(MedicalRecord.class);

            return medicalRecordRepository.updateBy(
                    Criteria.of(
                            equal("id", request.getId()),
                            equal("patient.id", patientId)),
                    FieldUpdate.set("entryAt", updatedMedicalRecord.getEntryAt()),
                    FieldUpdate.set("reason", updatedMedicalRecord.getReason()),
                    FieldUpdate.set("physicalExam", updatedMedicalRecord.getPhysicalExam()),
                    FieldUpdate.set("temperatureInCelsius", updatedMedicalRecord.getTemperatureInCelsius()),
                    FieldUpdate.set("respitarionRate", updatedMedicalRecord.getRespitarionRate()),
                    FieldUpdate.set("heartRate", updatedMedicalRecord.getHeartRate()),
                    FieldUpdate.set("weight", updatedMedicalRecord.getWeight()),
                    FieldUpdate.set("sterilized", updatedMedicalRecord.isSterilized()),
                    FieldUpdate.set("recipe", updatedMedicalRecord.getRecipe()),
                    FieldUpdate.set("diagnosis", updatedMedicalRecord.getDiagnosis()),
                    FieldUpdate.set("vet", updatedMedicalRecord.getVet()));
        });

        // Verify any unexpected behavior
        if (rowsModified == 0) {
            throw new NotFoundException(MedicalRecord.class, request.getId());
        } else if (rowsModified > 1) {
            throw new InternalServerErrorException(
                    new IllegalStateException(
                            "Medical record with patient id '" +
                                    patientId +
                                    "' and id '" +
                                    request.getId() +
                                    "' has more than one row modified"));
        }

        MedicalRecord updatedMedicalRecordAux = medicalRecordRepository.findById(request.getId()).get();

        log.info("Medical record updated");

        return medicalRecordMapper.toDto(updatedMedicalRecordAux);
    }
}
