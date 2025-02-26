package com.vluepixel.vetmanager.api.patient.core.vaccine.core.data;

import java.time.LocalDateTime;

import com.vluepixel.vetmanager.api.vaccine.core.domain.request.UpdateVaccineRequest;

/**
 * Update patient vaccine data provider.
 */
public class UpdatePatientVaccineDataProvider {
    public static final UpdateVaccineRequest INVALID_ID_NOT_FOUND_UPDATE_PATIENT_VACCINE_REQUEST = UpdateVaccineRequest
            .builder()
            .id(10L)
            .name("New Paracetamol")
            .doseInMilliliters(20)
            .providedAt(LocalDateTime.now())
            .vaccinatorId(1L)
            .productSaleId(1L)
            .build();

    public static final UpdateVaccineRequest INVALID_ID_NEGATIVE_UPDATE_PATIENT_VACCINE_REQUEST = UpdateVaccineRequest
            .builder()
            .id(-10L)
            .name(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .build();

    public static final UpdateVaccineRequest INVALID_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST = UpdateVaccineRequest
            .builder()
            .id(null)
            .name(INVALID_ID_NEGATIVE_UPDATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(INVALID_ID_NEGATIVE_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(INVALID_ID_NEGATIVE_UPDATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(INVALID_ID_NEGATIVE_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(INVALID_ID_NEGATIVE_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .build();

    public static final UpdateVaccineRequest VALID_UPDATE_PATIENT_VACCINE_REQUEST = UpdateVaccineRequest
            .builder()
            .id(1L)
            .name(INVALID_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(INVALID_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(INVALID_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(INVALID_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(INVALID_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .build();

    // -----------------------------------------------------------------------------------------------------------------
    // Validations
    // -----------------------------------------------------------------------------------------------------

    // Name
    private static final int MAX_NAME_LENGTH = 50;
    public static final UpdateVaccineRequest INVALID_NAME_TOO_LONG_UPDATE_PATIENT_VACCINE_REQUEST = UpdateVaccineRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getId())
            .name("a".repeat(MAX_NAME_LENGTH + 1))
            .doseInMilliliters(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .build();

    public static final UpdateVaccineRequest VALID_NAME_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST = UpdateVaccineRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getId())
            .name("a".repeat(MAX_NAME_LENGTH))
            .doseInMilliliters(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .build();

    public static final UpdateVaccineRequest INVALID_NAME_BLANK_UPDATE_PATIENT_VACCINE_REQUEST = UpdateVaccineRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getId())
            .name(" ")
            .doseInMilliliters(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .build();

    public static final UpdateVaccineRequest INVALID_NAME_EMPTY_UPDATE_PATIENT_VACCINE_REQUEST = UpdateVaccineRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getId())
            .name("")
            .doseInMilliliters(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .build();

    public static final UpdateVaccineRequest INVALID_NAME_NULL_UPDATE_PATIENT_VACCINE_REQUEST = UpdateVaccineRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getId())
            .name(null)
            .doseInMilliliters(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .build();

    // Dose In Milliliters
    private static final int MAX_DOSE_IN_MILLILITERS_VALUE = 250;
    public static final UpdateVaccineRequest INVALID_DOSE_IN_MILLILITERS_TOO_BIG_UPDATE_PATIENT_VACCINE_REQUEST = UpdateVaccineRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(MAX_DOSE_IN_MILLILITERS_VALUE + 1)
            .providedAt(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .build();

    public static final UpdateVaccineRequest VALID_DOSE_IN_MILLILITERS_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST = UpdateVaccineRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(MAX_DOSE_IN_MILLILITERS_VALUE)
            .providedAt(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .build();

    public static final UpdateVaccineRequest INVALID_DOSE_IN_MILLILITERS_ZERO_UPDATE_PATIENT_VACCINE_REQUEST = UpdateVaccineRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(0)
            .providedAt(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .build();

    public static final UpdateVaccineRequest INVALID_DOSE_IN_MILLILITERS_NEGATIVE_UPDATE_PATIENT_VACCINE_REQUEST = UpdateVaccineRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(-1)
            .providedAt(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .build();

    public static final UpdateVaccineRequest INVALID_DOSE_IN_MILLILITERS_NULL_UPDATE_PATIENT_VACCINE_REQUEST = UpdateVaccineRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(null)
            .providedAt(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .build();

    // Provided At
    public static final UpdateVaccineRequest VALID_PROVIDED_AT_MINUS_YEAR_UPDATE_PATIENT_VACCINE_REQUEST = UpdateVaccineRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(LocalDateTime.now().minusYears(1))
            .vaccinatorId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .build();

    public static final UpdateVaccineRequest VALID_PROVIDED_AT_TODAY_UPDATE_PATIENT_VACCINE_REQUEST = UpdateVaccineRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(LocalDateTime.now())
            .vaccinatorId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .build();

    public static final UpdateVaccineRequest VALID_PROVIDED_AT_FUTURE_UPDATE_PATIENT_VACCINE_REQUEST = UpdateVaccineRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(LocalDateTime.now().plusDays(1))
            .vaccinatorId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .build();

    public static final UpdateVaccineRequest INVALID_PROVIDED_AT_NULL_UPDATE_PATIENT_VACCINE_REQUEST = UpdateVaccineRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(null)
            .vaccinatorId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .build();

    // Vaccinator ID
    public static final UpdateVaccineRequest INVALID_VACCINATOR_ID_NOT_FOUND_UPDATE_PATIENT_VACCINE_REQUEST = UpdateVaccineRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(10L)
            .productSaleId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .build();

    public static final UpdateVaccineRequest INVALID_VACCINATOR_ID_NEGATIVE_UPDATE_PATIENT_VACCINE_REQUEST = UpdateVaccineRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(-1L)
            .productSaleId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .build();

    public static final UpdateVaccineRequest INVALID_VACCINATOR_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST = UpdateVaccineRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(null)
            .productSaleId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .build();

    // Product Sale ID
    public static final UpdateVaccineRequest INVALID_PRODUCT_SALE_ID_NOT_FOUND_UPDATE_PATIENT_VACCINE_REQUEST = UpdateVaccineRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(10L)
            .build();

    public static final UpdateVaccineRequest INVALID_PRODUCT_SALE_ID_NEGATIVE_UPDATE_PATIENT_VACCINE_REQUEST = UpdateVaccineRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(-1L)
            .build();

    public static final UpdateVaccineRequest VALID_PRODUCT_SALE_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST = UpdateVaccineRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(null)
            .build();
}
