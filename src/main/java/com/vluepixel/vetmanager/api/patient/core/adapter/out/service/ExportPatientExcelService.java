package com.vluepixel.vetmanager.api.patient.core.adapter.out.service;

import org.springframework.stereotype.Service;

import com.vluepixel.vetmanager.api.patient.core.application.dto.PatientDto;
import com.vluepixel.vetmanager.api.patient.core.application.mapper.PatientMapper;
import com.vluepixel.vetmanager.api.patient.core.application.port.out.ExportPatientExcelPort;
import com.vluepixel.vetmanager.api.patient.core.domain.model.Patient;
import com.vluepixel.vetmanager.api.patient.core.domain.repository.PatientRepository;
import com.vluepixel.vetmanager.api.shared.adapter.out.service.ExportExcelSubService;

/**
 * Export patient excel service.
 */
@Service
public final class ExportPatientExcelService extends ExportExcelSubService<Patient, PatientDto>
        implements ExportPatientExcelPort {
    public ExportPatientExcelService(PatientRepository patientRepository, PatientMapper patientMapper) {
        super(patientRepository, patientMapper, PatientDto.class);
    }
}
