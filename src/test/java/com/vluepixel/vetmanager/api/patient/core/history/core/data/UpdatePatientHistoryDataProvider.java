package com.vluepixel.vetmanager.api.patient.core.history.core.data;

import com.vluepixel.vetmanager.api.patient.medicalhistory.domain.request.UpdateMedicalHistoryRequest;

/**
 * Update patient history data provider.
 */
public class UpdatePatientHistoryDataProvider {
    public static final UpdateMedicalHistoryRequest INVALID_ID_NOT_FOUND_UPDATE_PATIENT_HISTORY_REQUEST = UpdateMedicalHistoryRequest
            .builder()
            .id(10L)
            .content("Arrived at an appointment")
            .build();

    public static final UpdateMedicalHistoryRequest INVALID_ID_NEGATIVE_UPDATE_PATIENT_HISTORY_REQUEST = UpdateMedicalHistoryRequest
            .builder()
            .id(-10L)
            .content(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_HISTORY_REQUEST.getContent())
            .build();

    public static final UpdateMedicalHistoryRequest INVALID_ID_NULL_UPDATE_PATIENT_HISTORY_REQUEST = UpdateMedicalHistoryRequest
            .builder()
            .id(null)
            .content(INVALID_ID_NEGATIVE_UPDATE_PATIENT_HISTORY_REQUEST.getContent())
            .build();

    public static final UpdateMedicalHistoryRequest VALID_UPDATE_PATIENT_HISTORY_REQUEST = UpdateMedicalHistoryRequest
            .builder()
            .id(1L)
            .content(INVALID_ID_NULL_UPDATE_PATIENT_HISTORY_REQUEST.getContent())
            .build();

    // -----------------------------------------------------------------------------------------------------------------
    // Validations
    // -----------------------------------------------------------------------------------------------------

    // Content
    public static final UpdateMedicalHistoryRequest INVALID_CONTENT_BLANK_UPDATE_PATIENT_HISTORY_REQUEST = UpdateMedicalHistoryRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_HISTORY_REQUEST.getId())
            .content(" ")
            .build();

    public static final UpdateMedicalHistoryRequest INVALID_CONTENT_EMPTY_UPDATE_PATIENT_HISTORY_REQUEST = UpdateMedicalHistoryRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_HISTORY_REQUEST.getId())
            .content("")
            .build();

    public static final UpdateMedicalHistoryRequest INVALID_CONTENT_NULL_UPDATE_PATIENT_HISTORY_REQUEST = UpdateMedicalHistoryRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_HISTORY_REQUEST.getId())
            .content(null)
            .build();
}
