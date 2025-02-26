package com.vluepixel.vetmanager.api.patient.core.vaccine.core.data;

import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.UpdatePatientVaccineDataProvider.INVALID_DOSE_IN_MILLILITERS_NEGATIVE_UPDATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.UpdatePatientVaccineDataProvider.INVALID_DOSE_IN_MILLILITERS_NULL_UPDATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.UpdatePatientVaccineDataProvider.INVALID_DOSE_IN_MILLILITERS_TOO_BIG_UPDATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.UpdatePatientVaccineDataProvider.INVALID_DOSE_IN_MILLILITERS_ZERO_UPDATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.UpdatePatientVaccineDataProvider.INVALID_NAME_BLANK_UPDATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.UpdatePatientVaccineDataProvider.INVALID_NAME_EMPTY_UPDATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.UpdatePatientVaccineDataProvider.INVALID_NAME_NULL_UPDATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.UpdatePatientVaccineDataProvider.INVALID_NAME_TOO_LONG_UPDATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.UpdatePatientVaccineDataProvider.INVALID_PRODUCT_SALE_ID_NEGATIVE_UPDATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.UpdatePatientVaccineDataProvider.INVALID_PRODUCT_SALE_ID_NOT_FOUND_UPDATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.UpdatePatientVaccineDataProvider.INVALID_PROVIDED_AT_NULL_UPDATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.UpdatePatientVaccineDataProvider.INVALID_VACCINATOR_ID_NEGATIVE_UPDATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.UpdatePatientVaccineDataProvider.INVALID_VACCINATOR_ID_NOT_FOUND_UPDATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.UpdatePatientVaccineDataProvider.INVALID_VACCINATOR_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.UpdatePatientVaccineDataProvider.VALID_DOSE_IN_MILLILITERS_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.UpdatePatientVaccineDataProvider.VALID_NAME_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.UpdatePatientVaccineDataProvider.VALID_PRODUCT_SALE_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.UpdatePatientVaccineDataProvider.VALID_PROVIDED_AT_FUTURE_UPDATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.UpdatePatientVaccineDataProvider.VALID_PROVIDED_AT_MINUS_YEAR_UPDATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.UpdatePatientVaccineDataProvider.VALID_PROVIDED_AT_TODAY_UPDATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.UpdatePatientVaccineDataProvider.VALID_UPDATE_PATIENT_VACCINE_REQUEST;

import com.vluepixel.vetmanager.api.vaccine.core.domain.request.CreateVaccineRequest;

/**
 * Create patient vaccine data provider.
 */
public class CreatePatientVaccineDataProvider {
    public static final CreateVaccineRequest VALID_CREATE_PATIENT_VACCINE_REQUEST = CreateVaccineRequest
            .builder()
            .name(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .patientId(1L)
            .build();

    // -----------------------------------------------------------------------------------------------------------------
    // Validations
    // -----------------------------------------------------------------------------------------------------

    // Name
    public static final CreateVaccineRequest INVALID_NAME_TOO_LONG_CREATE_PATIENT_VACCINE_REQUEST = CreateVaccineRequest
            .builder()
            .name(INVALID_NAME_TOO_LONG_UPDATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_CREATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .patientId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getPatientId())
            .build();

    public static final CreateVaccineRequest VALID_NAME_MAX_VALUE_CREATE_PATIENT_VACCINE_REQUEST = CreateVaccineRequest
            .builder()
            .name(VALID_NAME_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_CREATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .patientId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getPatientId())
            .build();

    public static final CreateVaccineRequest INVALID_NAME_BLANK_CREATE_PATIENT_VACCINE_REQUEST = CreateVaccineRequest
            .builder()
            .name(INVALID_NAME_BLANK_UPDATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_CREATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .patientId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getPatientId())
            .build();

    public static final CreateVaccineRequest INVALID_NAME_EMPTY_CREATE_PATIENT_VACCINE_REQUEST = CreateVaccineRequest
            .builder()
            .name(INVALID_NAME_EMPTY_UPDATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_CREATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .patientId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getPatientId())
            .build();

    public static final CreateVaccineRequest INVALID_NAME_NULL_CREATE_PATIENT_VACCINE_REQUEST = CreateVaccineRequest
            .builder()
            .name(INVALID_NAME_NULL_UPDATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_CREATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .patientId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getPatientId())
            .build();

    // Dose In Milliliters
    public static final CreateVaccineRequest INVALID_DOSE_IN_MILLILITERS_TOO_BIG_CREATE_PATIENT_VACCINE_REQUEST = CreateVaccineRequest
            .builder()
            .name(VALID_CREATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(
                    INVALID_DOSE_IN_MILLILITERS_TOO_BIG_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .patientId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getPatientId())
            .build();

    public static final CreateVaccineRequest VALID_DOSE_IN_MILLILITERS_MAX_VALUE_CREATE_PATIENT_VACCINE_REQUEST = CreateVaccineRequest
            .builder()
            .name(VALID_CREATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(
                    VALID_DOSE_IN_MILLILITERS_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .patientId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getPatientId())
            .build();

    public static final CreateVaccineRequest INVALID_DOSE_IN_MILLILITERS_ZERO_CREATE_PATIENT_VACCINE_REQUEST = CreateVaccineRequest
            .builder()
            .name(VALID_CREATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(INVALID_DOSE_IN_MILLILITERS_ZERO_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .patientId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getPatientId())
            .build();

    public static final CreateVaccineRequest INVALID_DOSE_IN_MILLILITERS_NEGATIVE_CREATE_PATIENT_VACCINE_REQUEST = CreateVaccineRequest
            .builder()
            .name(VALID_CREATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(
                    INVALID_DOSE_IN_MILLILITERS_NEGATIVE_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .patientId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getPatientId())
            .build();

    public static final CreateVaccineRequest INVALID_DOSE_IN_MILLILITERS_NULL_CREATE_PATIENT_VACCINE_REQUEST = CreateVaccineRequest
            .builder()
            .name(VALID_CREATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(INVALID_DOSE_IN_MILLILITERS_NULL_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .patientId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getPatientId())
            .build();

    // Provided At
    public static final CreateVaccineRequest VALID_PROVIDED_AT_MINUS_YEAR_CREATE_PATIENT_VACCINE_REQUEST = CreateVaccineRequest
            .builder()
            .name(VALID_CREATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_CREATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_PROVIDED_AT_MINUS_YEAR_UPDATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .patientId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getPatientId())
            .build();

    public static final CreateVaccineRequest VALID_PROVIDED_AT_TODAY_CREATE_PATIENT_VACCINE_REQUEST = CreateVaccineRequest
            .builder()
            .name(VALID_CREATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_CREATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_PROVIDED_AT_TODAY_UPDATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .patientId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getPatientId())
            .build();

    public static final CreateVaccineRequest VALID_PROVIDED_AT_FUTURE_CREATE_PATIENT_VACCINE_REQUEST = CreateVaccineRequest
            .builder()
            .name(VALID_CREATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_CREATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_PROVIDED_AT_FUTURE_UPDATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .patientId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getPatientId())
            .build();

    public static final CreateVaccineRequest INVALID_PROVIDED_AT_NULL_CREATE_PATIENT_VACCINE_REQUEST = CreateVaccineRequest
            .builder()
            .name(VALID_CREATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_CREATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(INVALID_PROVIDED_AT_NULL_UPDATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .patientId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getPatientId())
            .build();

    // Vaccinator ID
    public static final CreateVaccineRequest INVALID_VACCINATOR_ID_NOT_FOUND_CREATE_PATIENT_VACCINE_REQUEST = CreateVaccineRequest
            .builder()
            .name(VALID_CREATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_CREATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(INVALID_VACCINATOR_ID_NOT_FOUND_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .patientId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getPatientId())
            .build();

    public static final CreateVaccineRequest INVALID_VACCINATOR_ID_NEGATIVE_CREATE_PATIENT_VACCINE_REQUEST = CreateVaccineRequest
            .builder()
            .name(VALID_CREATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_CREATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(INVALID_VACCINATOR_ID_NEGATIVE_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .patientId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getPatientId())
            .build();

    public static final CreateVaccineRequest INVALID_VACCINATOR_ID_NULL_CREATE_PATIENT_VACCINE_REQUEST = CreateVaccineRequest
            .builder()
            .name(VALID_CREATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_CREATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(INVALID_VACCINATOR_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .patientId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getPatientId())
            .build();

    // Product Sale ID
    public static final CreateVaccineRequest INVALID_PRODUCT_SALE_ID_NOT_FOUND_CREATE_PATIENT_VACCINE_REQUEST = CreateVaccineRequest
            .builder()
            .name(VALID_CREATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_CREATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(INVALID_PRODUCT_SALE_ID_NOT_FOUND_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .patientId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getPatientId())
            .build();

    public static final CreateVaccineRequest INVALID_PRODUCT_SALE_ID_NEGATIVE_CREATE_PATIENT_VACCINE_REQUEST = CreateVaccineRequest
            .builder()
            .name(VALID_CREATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_CREATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(INVALID_PRODUCT_SALE_ID_NEGATIVE_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .patientId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getPatientId())
            .build();

    public static final CreateVaccineRequest VALID_PRODUCT_SALE_ID_NULL_CREATE_PATIENT_VACCINE_REQUEST = CreateVaccineRequest
            .builder()
            .name(VALID_CREATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_CREATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_PRODUCT_SALE_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .patientId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getPatientId())
            .build();

    // Patient ID
    public static final CreateVaccineRequest INVALID_PATIENT_ID_NOT_FOUND_CREATE_PATIENT_VACCINE_REQUEST = CreateVaccineRequest
            .builder()
            .name(VALID_CREATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_CREATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .patientId(10L)
            .build();

    public static final CreateVaccineRequest INVALID_PATIENT_ID_NEGATIVE_CREATE_PATIENT_VACCINE_REQUEST = CreateVaccineRequest
            .builder()
            .name(VALID_CREATE_PATIENT_VACCINE_REQUEST.getName())
            .doseInMilliliters(VALID_CREATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters())
            .providedAt(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProvidedAt())
            .vaccinatorId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getVaccinatorId())
            .productSaleId(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProductSaleId())
            .patientId(-1L)
            .build();
}
