package com.vluepixel.vetmanager.api.patient.core.record.core.data;

import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.INVALID_ENTRY_AT_NULL_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.INVALID_HEART_RATE_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.INVALID_HEART_RATE_NULL_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.INVALID_HEART_RATE_TOO_BIG_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.INVALID_HEART_RATE_ZERO_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.INVALID_REASON_BLANK_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.INVALID_REASON_EMPTY_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.INVALID_REASON_NULL_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.INVALID_RESPIRATION_RATE_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.INVALID_RESPIRATION_RATE_NULL_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.INVALID_RESPIRATION_RATE_ZERO_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.INVALID_TEMPERATURE_IN_CELSIUS_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.INVALID_TEMPERATURE_IN_CELSIUS_NULL_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.INVALID_TEMPERATURE_IN_CELSIUS_TOO_BIG_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.INVALID_TEMPERATURE_IN_CELSIUS_ZERO_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.INVALID_VET_ID_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.INVALID_VET_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.INVALID_VET_ID_NULL_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.INVALID_WEIGHT_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.INVALID_WEIGHT_NULL_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.INVALID_WEIGHT_TOO_BIG_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.INVALID_WEIGHT_ZERO_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.VALID_DIAGNOSIS_BLANK_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.VALID_DIAGNOSIS_EMPTY_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.VALID_DIAGNOSIS_NULL_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.VALID_PHYSICAL_EXAM_BLANK_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.VALID_RECIPE_BLANK_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.VALID_RECIPE_EMPTY_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.VALID_RECIPE_NULL_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.VALID_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.UpdatePatientRecordDataProvider.VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST;

import com.vluepixel.vetmanager.api.medicalrecord.core.domain.request.CreateMedicalRecordRequest;

/**
 * Integration tests for the create patient record use case.
 */
public class CreatePatientRecordDataProvider {
    public static final CreateMedicalRecordRequest VALID_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(1L)
            .build();

    // -----------------------------------------------------------------------------------------------------------------
    // Validations
    // -----------------------------------------------------------------------------------------------------

    // Reason
    public static final CreateMedicalRecordRequest INVALID_REASON_BLANK_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(INVALID_REASON_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest INVALID_REASON_EMPTY_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(INVALID_REASON_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest INVALID_REASON_NULL_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(INVALID_REASON_NULL_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    // Entry At
    public static final CreateMedicalRecordRequest VALID_ENTRY_AT_MINUS_YEAR_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest VALID_ENTRY_AT_TODAY_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest VALID_ENTRY_AT_FUTURE_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest INVALID_ENTRY_AT_NULL_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(INVALID_ENTRY_AT_NULL_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    // Physical Exam
    public static final CreateMedicalRecordRequest VALID_PHYSICAL_EXAM_BLANK_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_PHYSICAL_EXAM_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest VALID_PHYSICAL_EXAM_EMPTY_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_PHYSICAL_EXAM_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest VALID_PHYSICAL_EXAM_NULL_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    // Temperature In Celsius
    public static final CreateMedicalRecordRequest INVALID_TEMPERATURE_IN_CELSIUS_TOO_BIG_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(
                    INVALID_TEMPERATURE_IN_CELSIUS_TOO_BIG_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(
                    VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest INVALID_TEMPERATURE_IN_CELSIUS_ZERO_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(
                    INVALID_TEMPERATURE_IN_CELSIUS_ZERO_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest INVALID_TEMPERATURE_IN_CELSIUS_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(
                    INVALID_TEMPERATURE_IN_CELSIUS_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest INVALID_TEMPERATURE_IN_CELSIUS_NULL_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(
                    INVALID_TEMPERATURE_IN_CELSIUS_NULL_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    // Respiration Rate
    public static final CreateMedicalRecordRequest INVALID_RESPIRATION_RATE_ZERO_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(INVALID_RESPIRATION_RATE_ZERO_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest INVALID_RESPIRATION_RATE_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(INVALID_RESPIRATION_RATE_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest INVALID_RESPIRATION_RATE_NULL_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(INVALID_RESPIRATION_RATE_NULL_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    // Heart Rate
    public static final CreateMedicalRecordRequest INVALID_HEART_RATE_TOO_BIG_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(INVALID_HEART_RATE_TOO_BIG_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest VALID_HEART_RATE_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest INVALID_HEART_RATE_ZERO_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(INVALID_HEART_RATE_ZERO_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest INVALID_HEART_RATE_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(INVALID_HEART_RATE_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest INVALID_HEART_RATE_NULL_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(INVALID_HEART_RATE_NULL_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    // Weight
    public static final CreateMedicalRecordRequest INVALID_WEIGHT_TOO_BIG_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(INVALID_WEIGHT_TOO_BIG_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest VALID_WEIGHT_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest INVALID_WEIGHT_ZERO_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(INVALID_WEIGHT_ZERO_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest INVALID_WEIGHT_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(INVALID_WEIGHT_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest INVALID_WEIGHT_NULL_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(INVALID_WEIGHT_NULL_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    // Sterilized

    // Recipe
    public static final CreateMedicalRecordRequest VALID_RECIPE_BLANK_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_RECIPE_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest VALID_RECIPE_EMPTY_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_RECIPE_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest VALID_RECIPE_NULL_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_RECIPE_NULL_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    // Diagnosis
    public static final CreateMedicalRecordRequest VALID_DIAGNOSIS_BLANK_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_DIAGNOSIS_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest VALID_DIAGNOSIS_EMPTY_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_DIAGNOSIS_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest VALID_DIAGNOSIS_NULL_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_DIAGNOSIS_NULL_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    // Vet ID
    public static final CreateMedicalRecordRequest INVALID_VET_ID_NOT_FOUND_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(INVALID_VET_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest INVALID_VET_ID_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(INVALID_VET_ID_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    public static final CreateMedicalRecordRequest INVALID_VET_ID_NULL_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(INVALID_VET_ID_NULL_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(VALID_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
            .build();

    // Patient ID
    public static final CreateMedicalRecordRequest INVALID_PATIENT_ID_NOT_FOUND_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(INVALID_VET_ID_NULL_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(10L)
            .build();

    public static final CreateMedicalRecordRequest INVALID_PATIENT_ID_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(INVALID_VET_ID_NULL_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(-1L)
            .build();

    public static final CreateMedicalRecordRequest INVALID_PATIENT_ID_NULL_CREATE_PATIENT_RECORD_REQUEST = CreateMedicalRecordRequest
            .builder()
            .reason(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_CREATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(INVALID_VET_ID_NULL_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .patientId(null)
            .build();
}
