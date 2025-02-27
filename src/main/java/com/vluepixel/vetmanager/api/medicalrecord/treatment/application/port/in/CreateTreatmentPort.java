package com.vluepixel.vetmanager.api.medicalrecord.treatment.application.port.in;

import com.vluepixel.vetmanager.api.medicalrecord.treatment.application.dto.TreatmentDto;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.domain.request.CreateTreatmentRequest;

/**
 * Create treatment port.
 */
public interface CreateTreatmentPort {
    /**
     * Create treatment.
     *
     * @param patientId the patient id.
     * @param recordId  the record id.
     * @param request   the create treatment request.
     * @return the created treatment
     */
    TreatmentDto create(Long patientId, Long recordId, CreateTreatmentRequest request);
}
