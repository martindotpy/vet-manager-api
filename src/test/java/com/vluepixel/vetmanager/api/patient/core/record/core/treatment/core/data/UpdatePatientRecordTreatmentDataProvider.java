package com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.data;

import com.vluepixel.vetmanager.api.medicalrecord.treatment.domain.request.UpdateTreatmentRequest;

/**
 * Integration tests for the update patient record treatment data provider.
 */
public class UpdatePatientRecordTreatmentDataProvider {
    public static final UpdateTreatmentRequest INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST = UpdateTreatmentRequest
            .builder()
            .id(10L)
            .description("Tratamiento actualizado")
            .order(1)
            .build();

    public static final UpdateTreatmentRequest INVALID_ID_NEGATIVE_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST = UpdateTreatmentRequest
            .builder()
            .id(-10L)
            .description(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST.getDescription())
            .order(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST.getOrder())
            .build();

    public static final UpdateTreatmentRequest INVALID_ID_NULL_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST = UpdateTreatmentRequest
            .builder()
            .id(null)
            .description(INVALID_ID_NEGATIVE_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST.getDescription())
            .order(INVALID_ID_NEGATIVE_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST.getOrder())
            .build();

    public static final UpdateTreatmentRequest VALID_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST = UpdateTreatmentRequest
            .builder()
            .id(1L)
            .description(INVALID_ID_NULL_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST.getDescription())
            .order(INVALID_ID_NULL_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST.getOrder())
            .build();

    // Description
    public static final UpdateTreatmentRequest INVALID_DESCRIPTION_BLANK_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST = UpdateTreatmentRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST.getId())
            .description(" ")
            .order(VALID_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST.getOrder())
            .build();

    public static final UpdateTreatmentRequest INVALID_DESCRIPTION_EMPTY_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST = UpdateTreatmentRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST.getId())
            .description("")
            .order(VALID_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST.getOrder())
            .build();

    public static final UpdateTreatmentRequest INVALID_DESCRIPTION_NULL_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST = UpdateTreatmentRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST.getId())
            .description(null)
            .order(VALID_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST.getOrder())
            .build();

    // Order
    public static final UpdateTreatmentRequest INVALID_ORDER_NEGATIVE_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST = UpdateTreatmentRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST.getId())
            .description(VALID_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST.getDescription())
            .order(-1)
            .build();

    public static final UpdateTreatmentRequest INVALID_ORDER_ZERO_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST = UpdateTreatmentRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST.getId())
            .description(VALID_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST.getDescription())
            .order(0)
            .build();

    public static final UpdateTreatmentRequest INVALID_ORDER_NULL_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST = UpdateTreatmentRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST.getId())
            .description(VALID_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST.getDescription())
            .order(null)
            .build();
}
