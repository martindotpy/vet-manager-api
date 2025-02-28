package com.vluepixel.vetmanager.api.patient.core.application.port.out;

import com.vluepixel.vetmanager.api.patient.core.application.dto.PatientDto;
import com.vluepixel.vetmanager.api.patient.core.domain.model.Patient;
import com.vluepixel.vetmanager.api.shared.application.port.out.ExportExcelPort;

/**
 * Export patient excel port.
 */
public interface ExportPatientExcelPort extends ExportExcelPort<Patient, PatientDto> {
}
