package com.vluepixel.vetmanager.api.patient.core.record.core.data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.vluepixel.vetmanager.api.medicalrecord.core.domain.request.UpdateMedicalRecordRequest;

/**
 * Integration tests for the update patient record use case.
 */
public class UpdatePatientRecordDataProvider {
    public static final UpdateMedicalRecordRequest INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(10L)
            .reason("This is the reason:")
            .entryAt(LocalDateTime.now().plusMinutes(15))
            .physicalExam("Ta bien")
            .temperatureInCelsius(BigDecimal.valueOf(38.6))
            .respitarionRate(80)
            .heartRate(90)
            .weight(BigDecimal.valueOf(40.5))
            .sterilized(true)
            .recipe("Reci pe :v")
            .diagnosis("This is his diagnosis")
            .vetId(1L)
            .build();

    public static final UpdateMedicalRecordRequest INVALID_ID_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(-10L)
            .reason(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    public static final UpdateMedicalRecordRequest INVALID_ID_NULL_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(null)
            .reason(INVALID_ID_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(INVALID_ID_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(INVALID_ID_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(INVALID_ID_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(INVALID_ID_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(INVALID_ID_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(INVALID_ID_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(INVALID_ID_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(INVALID_ID_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(INVALID_ID_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(INVALID_ID_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    public static final UpdateMedicalRecordRequest VALID_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(1L)
            .reason(INVALID_ID_NULL_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(INVALID_ID_NULL_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(INVALID_ID_NULL_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(INVALID_ID_NULL_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(INVALID_ID_NULL_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(INVALID_ID_NULL_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(INVALID_ID_NULL_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(INVALID_ID_NULL_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(INVALID_ID_NULL_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(INVALID_ID_NULL_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(INVALID_ID_NULL_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    // -----------------------------------------------------------------------------------------------------------------
    // Validations
    // -----------------------------------------------------------------------------------------------------

    // Reason
    public static final UpdateMedicalRecordRequest INVALID_REASON_BLANK_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(" ")
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
            .build();

    public static final UpdateMedicalRecordRequest INVALID_REASON_EMPTY_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason("")
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
            .build();

    public static final UpdateMedicalRecordRequest INVALID_REASON_NULL_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(null)
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
            .build();

    // Entry At
    public static final UpdateMedicalRecordRequest VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(LocalDateTime.now().minusYears(1))
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    public static final UpdateMedicalRecordRequest VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(LocalDateTime.now().plusMinutes(15))
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    public static final UpdateMedicalRecordRequest VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(LocalDateTime.now().plusDays(1))
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    public static final UpdateMedicalRecordRequest INVALID_ENTRY_AT_NULL_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(null)
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    // Physical Exam
    public static final UpdateMedicalRecordRequest VALID_PHYSICAL_EXAM_BLANK_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(" ")
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    public static final UpdateMedicalRecordRequest VALID_PHYSICAL_EXAM_EMPTY_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam("")
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    public static final UpdateMedicalRecordRequest VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(null)
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    // Temperature In Celsius
    private static final int MAX_TEMPERATURE_IN_CELSIUS_VALUE = 100;
    public static final UpdateMedicalRecordRequest INVALID_TEMPERATURE_IN_CELSIUS_TOO_BIG_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(BigDecimal.valueOf(MAX_TEMPERATURE_IN_CELSIUS_VALUE + 1))
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    public static final UpdateMedicalRecordRequest VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(BigDecimal.valueOf(MAX_TEMPERATURE_IN_CELSIUS_VALUE))
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    public static final UpdateMedicalRecordRequest INVALID_TEMPERATURE_IN_CELSIUS_ZERO_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(BigDecimal.valueOf(0))
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    public static final UpdateMedicalRecordRequest INVALID_TEMPERATURE_IN_CELSIUS_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(BigDecimal.valueOf(-1))
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    public static final UpdateMedicalRecordRequest INVALID_TEMPERATURE_IN_CELSIUS_NULL_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(null)
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    // Respiration Rate
    public static final UpdateMedicalRecordRequest INVALID_RESPIRATION_RATE_ZERO_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(0)
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    public static final UpdateMedicalRecordRequest INVALID_RESPIRATION_RATE_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(-1)
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    public static final UpdateMedicalRecordRequest INVALID_RESPIRATION_RATE_NULL_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(null)
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    // Heart Rate
    private static final int MAX_HEART_RATE_VALUE = 1000;
    public static final UpdateMedicalRecordRequest INVALID_HEART_RATE_TOO_BIG_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(MAX_HEART_RATE_VALUE + 1)
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    public static final UpdateMedicalRecordRequest VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(MAX_HEART_RATE_VALUE)
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    public static final UpdateMedicalRecordRequest INVALID_HEART_RATE_ZERO_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(0)
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    public static final UpdateMedicalRecordRequest INVALID_HEART_RATE_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(-1)
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    public static final UpdateMedicalRecordRequest INVALID_HEART_RATE_NULL_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(null)
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    // Weight
    private static final int MAX_WEIGHT_VALUE = 999;
    public static final UpdateMedicalRecordRequest INVALID_WEIGHT_TOO_BIG_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(BigDecimal.valueOf(MAX_WEIGHT_VALUE + 1))
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    public static final UpdateMedicalRecordRequest VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(BigDecimal.valueOf(MAX_WEIGHT_VALUE + 0.99))
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    public static final UpdateMedicalRecordRequest INVALID_WEIGHT_ZERO_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(BigDecimal.valueOf(0))
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    public static final UpdateMedicalRecordRequest INVALID_WEIGHT_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(BigDecimal.valueOf(-1))
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    public static final UpdateMedicalRecordRequest INVALID_WEIGHT_NULL_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(null)
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    // Sterilized

    // Recipe
    public static final UpdateMedicalRecordRequest VALID_RECIPE_BLANK_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(" ")
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    public static final UpdateMedicalRecordRequest VALID_RECIPE_EMPTY_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe("")
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    public static final UpdateMedicalRecordRequest VALID_RECIPE_NULL_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(null)
            .diagnosis(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis())
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    // Diagnosis
    public static final UpdateMedicalRecordRequest VALID_DIAGNOSIS_BLANK_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(" ")
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    public static final UpdateMedicalRecordRequest VALID_DIAGNOSIS_EMPTY_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis("")
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    public static final UpdateMedicalRecordRequest VALID_DIAGNOSIS_NULL_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
            .reason(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason())
            .entryAt(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt())
            .physicalExam(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam())
            .temperatureInCelsius(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius())
            .respitarionRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate())
            .heartRate(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate())
            .weight(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight())
            .sterilized(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized())
            .recipe(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe())
            .diagnosis(null)
            .vetId(VALID_UPDATE_PATIENT_RECORD_REQUEST.getVetId())
            .build();

    // Vet ID
    public static final UpdateMedicalRecordRequest INVALID_VET_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
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
            .vetId(10L)
            .build();

    public static final UpdateMedicalRecordRequest INVALID_VET_ID_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
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
            .vetId(-1L)
            .build();

    public static final UpdateMedicalRecordRequest INVALID_VET_ID_NULL_UPDATE_PATIENT_RECORD_REQUEST = UpdateMedicalRecordRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
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
            .vetId(null)
            .build();
}
