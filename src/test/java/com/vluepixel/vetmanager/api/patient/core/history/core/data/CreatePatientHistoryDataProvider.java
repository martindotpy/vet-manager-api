package com.vluepixel.vetmanager.api.patient.core.history.core.data;

import static com.vluepixel.vetmanager.api.patient.core.history.core.data.UpdatePatientHistoryDataProvider.VALID_UPDATE_PATIENT_HISTORY_REQUEST;

import com.vluepixel.vetmanager.api.patient.medicalhistory.domain.request.CreateMedicalHistoryRequest;

/**
 * Create patient history data provider.
 */
public class CreatePatientHistoryDataProvider {
    public static final CreateMedicalHistoryRequest VALID_CREATE_PATIENT_HISTORY_REQUEST = CreateMedicalHistoryRequest
            .builder()
            .content(VALID_UPDATE_PATIENT_HISTORY_REQUEST.getContent())
            .patientId(1L)
            .build();

    // -----------------------------------------------------------------------------------------------------------------
    // Validations
    // -----------------------------------------------------------------------------------------------------

    // Content
    public static final CreateMedicalHistoryRequest INVALID_CONTENT_BLANK_CREATE_PATIENT_HISTORY_REQUEST = CreateMedicalHistoryRequest
            .builder()
            .content(" ")
            .patientId(VALID_CREATE_PATIENT_HISTORY_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalHistoryRequest INVALID_CONTENT_EMPTY_CREATE_PATIENT_HISTORY_REQUEST = CreateMedicalHistoryRequest
            .builder()
            .content("")
            .patientId(VALID_CREATE_PATIENT_HISTORY_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalHistoryRequest INVALID_CONTENT_NULL_CREATE_PATIENT_HISTORY_REQUEST = CreateMedicalHistoryRequest
            .builder()
            .content(null)
            .patientId(VALID_CREATE_PATIENT_HISTORY_REQUEST.getPatientId())
            .build();

    // Patient ID
    public static final CreateMedicalHistoryRequest INVALID_PATIENT_ID_NOT_FOUND_CREATE_PATIENT_HISTORY_REQUEST = CreateMedicalHistoryRequest
            .builder()
            .content(VALID_CREATE_PATIENT_HISTORY_REQUEST.getContent())
            .patientId(10L)
            .build();

    public static final CreateMedicalHistoryRequest INVALID_PATIENT_ID_NEGATIVE_CREATE_PATIENT_HISTORY_REQUEST = CreateMedicalHistoryRequest
            .builder()
            .content(VALID_CREATE_PATIENT_HISTORY_REQUEST.getContent())
            .patientId(-1L)
            .build();

    public static final CreateMedicalHistoryRequest INVALID_PATIENT_ID_NULL_CREATE_PATIENT_HISTORY_REQUEST = CreateMedicalHistoryRequest
            .builder()
            .content(VALID_CREATE_PATIENT_HISTORY_REQUEST.getContent())
            .patientId(null)
            .build();
}
