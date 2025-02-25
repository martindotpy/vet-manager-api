package com.vluepixel.vetmanager.api.medicalrecord.core.application.usecase;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.medicalrecord.core.application.dto.MedicalRecordDto;
import com.vluepixel.vetmanager.api.medicalrecord.core.application.mapper.MedicalRecordMapper;
import com.vluepixel.vetmanager.api.medicalrecord.core.application.port.in.CreateMedicalRecordPort;
import com.vluepixel.vetmanager.api.medicalrecord.core.domain.model.MedicalRecord;
import com.vluepixel.vetmanager.api.medicalrecord.core.domain.repository.MedicalRecordRepository;
import com.vluepixel.vetmanager.api.medicalrecord.core.domain.request.CreateMedicalRecordRequest;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;
import com.vluepixel.vetmanager.api.shared.domain.util.StringUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Create medical record use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class CreateMedicalRecordUseCase implements CreateMedicalRecordPort {
    private final MedicalRecordRepository medicalrecordRepository;
    private final MedicalRecordMapper medicalrecordMapper;

    @Override
    public MedicalRecordDto create(CreateMedicalRecordRequest request) {
        MDC.put("operationId", "Medical record with patient id " + request.getPatientId());
        log.info("Creating medical record");

        // Make null the attributes if the value is blank
        String physicalExam = StringUtils.toNullIfBlank(request.getPhysicalExam());
        String recipe = StringUtils.toNullIfBlank(request.getRecipe());
        String diagnosis = StringUtils.toNullIfBlank(request.getDiagnosis());

        MedicalRecord newMedicalRecord = medicalrecordMapper.fromRequest(request)
                .physicalExam(physicalExam)
                .recipe(recipe)
                .diagnosis(diagnosis)
                .build();
        newMedicalRecord = medicalrecordRepository.save(newMedicalRecord);

        log.info("Medical record created");

        return medicalrecordMapper.toDto(medicalrecordRepository.findById(newMedicalRecord.getId()).get());
    }
}
