package com.vluepixel.vetmanager.api.patient.core.application.usecase;

import static com.vluepixel.vetmanager.api.shared.adapter.in.util.AnsiShortcuts.fgBrightBlue;
import static com.vluepixel.vetmanager.api.shared.adapter.in.util.AnsiShortcuts.fgBrightGreen;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.patient.core.application.dto.PatientDto;
import com.vluepixel.vetmanager.api.patient.core.application.mapper.PatientMapper;
import com.vluepixel.vetmanager.api.patient.core.application.port.in.FindPatientPort;
import com.vluepixel.vetmanager.api.patient.core.domain.model.Patient;
import com.vluepixel.vetmanager.api.patient.core.domain.repository.PatientRepository;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;
import com.vluepixel.vetmanager.api.shared.domain.criteria.PaginatedCriteria;
import com.vluepixel.vetmanager.api.shared.domain.exception.NotFoundException;
import com.vluepixel.vetmanager.api.shared.domain.query.Paginated;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Find patient use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public final class FindPatientUseCase implements FindPatientPort {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public Paginated<PatientDto> findPaginatedBy(PaginatedCriteria criteria) {
        MDC.put("operationId", "Patients by criteria: " + fgBrightBlue(criteria.hashCode()));
        log.info("Finding patients by {}",
                fgBrightBlue(criteria));

        Paginated<Patient> paginatedPatients = patientRepository.findPaginatedBy(criteria);

        log.info("{} patient found",
                fgBrightGreen(paginatedPatients.getContent().size()));

        return paginatedPatients.map(patientMapper::toDto);
    }

    @Override
    public PatientDto findById(Long id) {
        MDC.put("operationId", "Patient id " + id);
        log.info("Finding patient  by id");

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Patient.class, id));

        log.info("Patient found");

        return patientMapper.toDto(patient);
    }
}
