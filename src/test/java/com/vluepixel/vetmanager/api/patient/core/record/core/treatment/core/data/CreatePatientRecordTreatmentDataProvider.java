package com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.data;

import com.vluepixel.vetmanager.api.medicalrecord.treatment.domain.request.CreateTreatmentRequest;

/**
 * Integration tests for the create patient record treatment data provider.
 */
public class CreatePatientRecordTreatmentDataProvider {
    public static final CreateTreatmentRequest VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST = CreateTreatmentRequest
            .builder()
            .description("Nuevo tratamiento")
            .order(1)
            .medicalRecordId(1L)
            .build();

    // Description
    public static final CreateTreatmentRequest INVALID_DESCRIPTION_BLANK_CREATE_PATIENT_RECORD_TREATMENT_REQUEST = CreateTreatmentRequest
            .builder()
            .description(" ")
            .order(VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST.getOrder())
            .medicalRecordId(VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST.getMedicalRecordId())
            .build();

    public static final CreateTreatmentRequest INVALID_DESCRIPTION_EMPTY_CREATE_PATIENT_RECORD_TREATMENT_REQUEST = CreateTreatmentRequest
            .builder()
            .description("")
            .order(VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST.getOrder())
            .medicalRecordId(VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST.getMedicalRecordId())
            .build();

    public static final CreateTreatmentRequest INVALID_DESCRIPTION_NULL_CREATE_PATIENT_RECORD_TREATMENT_REQUEST = CreateTreatmentRequest
            .builder()
            .description("")
            .order(VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST.getOrder())
            .medicalRecordId(VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST.getMedicalRecordId())
            .build();

    // Order
    public static final CreateTreatmentRequest INVALID_ORDER_NEGATIVE_CREATE_PATIENT_RECORD_TREATMENT_REQUEST = CreateTreatmentRequest
            .builder()
            .description(VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST.getDescription())
            .order(-1)
            .medicalRecordId(VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST.getMedicalRecordId())
            .build();

    public static final CreateTreatmentRequest INVALID_ORDER_ZERO_CREATE_PATIENT_RECORD_TREATMENT_REQUEST = CreateTreatmentRequest
            .builder()
            .description(VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST.getDescription())
            .order(0)
            .medicalRecordId(VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST.getMedicalRecordId())
            .build();

    public static final CreateTreatmentRequest INVALID_ORDER_NULL_CREATE_PATIENT_RECORD_TREATMENT_REQUEST = CreateTreatmentRequest
            .builder()
            .description(VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST.getDescription())
            .order(null)
            .medicalRecordId(VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST.getMedicalRecordId())
            .build();

    // Record ID
    public static final CreateTreatmentRequest INVALID_MEDICAL_RECORD_ID_NOT_FOUND_CREATE_PATIENT_RECORD_TREATMENT_REQUEST = CreateTreatmentRequest
            .builder()
            .description(VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST.getDescription())
            .order(VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST.getOrder())
            .medicalRecordId(10L)
            .build();

    public static final CreateTreatmentRequest INVALID_MEDICAL_RECORD_ID_NEGATIVE_CREATE_PATIENT_RECORD_TREATMENT_REQUEST = CreateTreatmentRequest
            .builder()
            .description(VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST.getDescription())
            .order(VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST.getOrder())
            .medicalRecordId(-1L)
            .build();

    public static final CreateTreatmentRequest INVALID_MEDICAL_RECORD_ID_NULL_CREATE_PATIENT_RECORD_TREATMENT_REQUEST = CreateTreatmentRequest
            .builder()
            .description(VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST.getDescription())
            .order(VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST.getOrder())
            .medicalRecordId(null)
            .build();
}
