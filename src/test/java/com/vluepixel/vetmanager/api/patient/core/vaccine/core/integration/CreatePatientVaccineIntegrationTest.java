package com.vluepixel.vetmanager.api.patient.core.vaccine.core.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.CreatePatientVaccineDataProvider.INVALID_DOSE_IN_MILLILITERS_NEGATIVE_CREATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.CreatePatientVaccineDataProvider.INVALID_DOSE_IN_MILLILITERS_NULL_CREATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.CreatePatientVaccineDataProvider.INVALID_DOSE_IN_MILLILITERS_TOO_BIG_CREATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.CreatePatientVaccineDataProvider.INVALID_DOSE_IN_MILLILITERS_ZERO_CREATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.CreatePatientVaccineDataProvider.INVALID_NAME_BLANK_CREATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.CreatePatientVaccineDataProvider.INVALID_NAME_EMPTY_CREATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.CreatePatientVaccineDataProvider.INVALID_NAME_NULL_CREATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.CreatePatientVaccineDataProvider.INVALID_NAME_TOO_LONG_CREATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.CreatePatientVaccineDataProvider.INVALID_PATIENT_ID_NEGATIVE_CREATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.CreatePatientVaccineDataProvider.INVALID_PATIENT_ID_NOT_FOUND_CREATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.CreatePatientVaccineDataProvider.INVALID_PRODUCT_SALE_ID_NEGATIVE_CREATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.CreatePatientVaccineDataProvider.INVALID_PRODUCT_SALE_ID_NOT_FOUND_CREATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.CreatePatientVaccineDataProvider.INVALID_PROVIDED_AT_NULL_CREATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.CreatePatientVaccineDataProvider.INVALID_VACCINATOR_ID_NEGATIVE_CREATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.CreatePatientVaccineDataProvider.INVALID_VACCINATOR_ID_NOT_FOUND_CREATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.CreatePatientVaccineDataProvider.INVALID_VACCINATOR_ID_NULL_CREATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.CreatePatientVaccineDataProvider.VALID_CREATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.CreatePatientVaccineDataProvider.VALID_DOSE_IN_MILLILITERS_MAX_VALUE_CREATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.CreatePatientVaccineDataProvider.VALID_NAME_MAX_VALUE_CREATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.CreatePatientVaccineDataProvider.VALID_PRODUCT_SALE_ID_NULL_CREATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.CreatePatientVaccineDataProvider.VALID_PROVIDED_AT_FUTURE_CREATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.CreatePatientVaccineDataProvider.VALID_PROVIDED_AT_MINUS_YEAR_CREATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.CreatePatientVaccineDataProvider.VALID_PROVIDED_AT_TODAY_CREATE_PATIENT_VACCINE_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the create patient vaccine use case.
 */
public class CreatePatientVaccineIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Vacuna creada exitosamente";
    private static final String MESSAGE_VACCINATOR_NOT_FOUND = "Vacunador no encontrado(a)";
    private static final String MESSAGE_PRODUCT_SALE_NOT_FOUND = "product_sale no encontrado(a)"; // TODO
    private static final String MESSAGE_PATIENT_NOT_FOUND = "Paciente no encontrado(a)";
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER

    // Role: ADMIN
    @Test
    @Order(1)
    void admin_CreatePatientVaccineWithValidArguments_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(4),
                        jsonPath("$.content.name").value(VALID_CREATE_PATIENT_VACCINE_REQUEST.getName()),
                        jsonPath("$.content.dose_in_milliliters")
                                .value(VALID_CREATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters()),
                        jsonPath("$.content.provided_at").isString(),
                        jsonPath("$.content.vaccinator.id")
                                .value(VALID_CREATE_PATIENT_VACCINE_REQUEST.getVaccinatorId()),
                        jsonPath("$.content.product_sale.id")
                                .value(VALID_CREATE_PATIENT_VACCINE_REQUEST.getProductSaleId()));
    }

    @Test
    void admin_CreatePatientVaccineWithInvalidArguments_ID_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/vaccine", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("body.patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages")
                                .value("El id del paciente debe ser igual al id del paciente en la ruta"));
    }

    // Name
    @Test
    void admin_CreatePatientVaccineWithInvalidArguments_Name_TooLong_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_TOO_LONG_CREATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages")
                                .value("El nombre no puede tener más de 50 caracteres"));
    }

    @Test
    @Order(2)
    void admin_CreatePatientVaccineWithValidArguments_Name_MaxValue_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_NAME_MAX_VALUE_CREATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(4),
                        jsonPath("$.content.name").value(VALID_NAME_MAX_VALUE_CREATE_PATIENT_VACCINE_REQUEST.getName()),
                        jsonPath("$.content.dose_in_milliliters")
                                .value(VALID_NAME_MAX_VALUE_CREATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters()),
                        jsonPath("$.content.provided_at").isString(),
                        jsonPath("$.content.vaccinator.id")
                                .value(VALID_NAME_MAX_VALUE_CREATE_PATIENT_VACCINE_REQUEST.getVaccinatorId()),
                        jsonPath("$.content.product_sale.id")
                                .value(VALID_NAME_MAX_VALUE_CREATE_PATIENT_VACCINE_REQUEST.getProductSaleId()));
    }

    @Test
    void admin_CreatePatientVaccineWithInvalidArguments_Name_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_BLANK_CREATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages")
                                .value("El nombre es requerido"));
    }

    @Test
    void admin_CreatePatientVaccineWithInvalidArguments_Name_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_EMPTY_CREATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages")
                                .value("El nombre es requerido"));
    }

    @Test
    void admin_CreatePatientVaccineWithInvalidArguments_Name_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_NULL_CREATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages")
                                .value("El nombre es requerido"));
    }

    // Dose In Milliliters
    @Test
    void admin_CreatePatientVaccineWithInvalidArguments_DoseInMilliliters_TooBig_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DOSE_IN_MILLILITERS_TOO_BIG_CREATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("dose_in_milliliters"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages")
                                .value("La dosis en mililitros no puede ser mayor a 250"));
    }

    @Test
    @Order(3)
    void admin_CreatePatientVaccineWithValidArguments_DoseInMilliliters_MaxValue_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_DOSE_IN_MILLILITERS_MAX_VALUE_CREATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(4),
                        jsonPath("$.content.name")
                                .value(VALID_DOSE_IN_MILLILITERS_MAX_VALUE_CREATE_PATIENT_VACCINE_REQUEST.getName()),
                        jsonPath("$.content.dose_in_milliliters")
                                .value(VALID_DOSE_IN_MILLILITERS_MAX_VALUE_CREATE_PATIENT_VACCINE_REQUEST
                                        .getDoseInMilliliters()),
                        jsonPath("$.content.provided_at").isString(),
                        jsonPath("$.content.vaccinator.id")
                                .value(VALID_DOSE_IN_MILLILITERS_MAX_VALUE_CREATE_PATIENT_VACCINE_REQUEST
                                        .getVaccinatorId()),
                        jsonPath("$.content.product_sale.id")
                                .value(VALID_DOSE_IN_MILLILITERS_MAX_VALUE_CREATE_PATIENT_VACCINE_REQUEST
                                        .getProductSaleId()));
    }

    @Test
    void admin_CreatePatientVaccineWithInvalidArguments_DoseInMilliliters_Zero_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DOSE_IN_MILLILITERS_ZERO_CREATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("dose_in_milliliters"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages")
                                .value("La dosis en mililitros debe ser mayor a 0"));
    }

    @Test
    void admin_CreatePatientVaccineWithInvalidArguments_DoseInMilliliters_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DOSE_IN_MILLILITERS_NEGATIVE_CREATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("dose_in_milliliters"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages")
                                .value("La dosis en mililitros debe ser mayor a 0"));
    }

    @Test
    void admin_CreatePatientVaccineWithInvalidArguments_DoseInMilliliters_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DOSE_IN_MILLILITERS_NULL_CREATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("dose_in_milliliters"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages")
                                .value("La dosis en mililitros es requerida"));
    }

    // Provided At
    @Test
    @Order(4)
    void admin_CreatePatientVaccineWithValidArguments_ProvidedAt_MinusYear_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_PROVIDED_AT_MINUS_YEAR_CREATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(4),
                        jsonPath("$.content.name")
                                .value(VALID_PROVIDED_AT_MINUS_YEAR_CREATE_PATIENT_VACCINE_REQUEST.getName()),
                        jsonPath("$.content.dose_in_milliliters")
                                .value(VALID_PROVIDED_AT_MINUS_YEAR_CREATE_PATIENT_VACCINE_REQUEST
                                        .getDoseInMilliliters()),
                        jsonPath("$.content.provided_at").isString(),
                        jsonPath("$.content.vaccinator.id")
                                .value(VALID_PROVIDED_AT_MINUS_YEAR_CREATE_PATIENT_VACCINE_REQUEST
                                        .getVaccinatorId()),
                        jsonPath("$.content.product_sale.id")
                                .value(VALID_PROVIDED_AT_MINUS_YEAR_CREATE_PATIENT_VACCINE_REQUEST
                                        .getProductSaleId()));
    }

    @Test
    @Order(4)
    void admin_CreatePatientVaccineWithValidArguments_ProvidedAt_Today_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_PROVIDED_AT_TODAY_CREATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(4),
                        jsonPath("$.content.name")
                                .value(VALID_PROVIDED_AT_TODAY_CREATE_PATIENT_VACCINE_REQUEST.getName()),
                        jsonPath("$.content.dose_in_milliliters")
                                .value(VALID_PROVIDED_AT_TODAY_CREATE_PATIENT_VACCINE_REQUEST
                                        .getDoseInMilliliters()),
                        jsonPath("$.content.provided_at").isString(),
                        jsonPath("$.content.vaccinator.id")
                                .value(VALID_PROVIDED_AT_TODAY_CREATE_PATIENT_VACCINE_REQUEST
                                        .getVaccinatorId()),
                        jsonPath("$.content.product_sale.id")
                                .value(VALID_PROVIDED_AT_TODAY_CREATE_PATIENT_VACCINE_REQUEST
                                        .getProductSaleId()));
    }

    @Test
    @Order(5)
    void admin_CreatePatientVaccineWithValidArguments_ProvidedAt_Future_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_PROVIDED_AT_FUTURE_CREATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(4),
                        jsonPath("$.content.name")
                                .value(VALID_PROVIDED_AT_FUTURE_CREATE_PATIENT_VACCINE_REQUEST.getName()),
                        jsonPath("$.content.dose_in_milliliters")
                                .value(VALID_PROVIDED_AT_FUTURE_CREATE_PATIENT_VACCINE_REQUEST
                                        .getDoseInMilliliters()),
                        jsonPath("$.content.provided_at").isString(),
                        jsonPath("$.content.vaccinator.id")
                                .value(VALID_PROVIDED_AT_FUTURE_CREATE_PATIENT_VACCINE_REQUEST
                                        .getVaccinatorId()),
                        jsonPath("$.content.product_sale.id")
                                .value(VALID_PROVIDED_AT_FUTURE_CREATE_PATIENT_VACCINE_REQUEST
                                        .getProductSaleId()));
    }

    @Test
    void admin_CreatePatientVaccineWithInvalidArguments_ProvidedAt_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PROVIDED_AT_NULL_CREATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("provided_at"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages")
                                .value("La fecha de aplicación es requerida"));
    }

    // Vaccinator ID
    @Test
    void admin_CreatePatientVaccineWithInvalidArguments_VaccinatorID_NotFound_NotFound()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_VACCINATOR_ID_NOT_FOUND_CREATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_VACCINATOR_NOT_FOUND));
    }

    @Test
    void admin_CreatePatientVaccineWithInvalidArguments_Vaccinator_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_VACCINATOR_ID_NEGATIVE_CREATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("vaccinator_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages")
                                .value("El id del vacunador debe ser mayor a 0"));
    }

    @Test
    void admin_CreatePatientVaccineWithInvalidArguments_Vaccinator_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_VACCINATOR_ID_NULL_CREATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("vaccinator_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages")
                                .value("El id del vacunador es requerido"));
    }

    // Product Sale ID
    @Test
    void admin_CreatePatientVaccineWithInvalidArguments_ProductSaleID_NotFound_NotFound()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PRODUCT_SALE_ID_NOT_FOUND_CREATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_PRODUCT_SALE_NOT_FOUND));
    }

    @Test
    void admin_CreatePatientVaccineWithInvalidArguments_ProductSaleID_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PRODUCT_SALE_ID_NEGATIVE_CREATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("product_sale_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages")
                                .value("El id de la venta de producto debe ser mayor a 0"));
    }

    @Test
    void admin_CreatePatientVaccineWithInvalidArguments_ProductSaleID_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_PRODUCT_SALE_ID_NULL_CREATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk());
        // TODO: Check response
    }

    // Patient ID
    @Test
    void admin_CreatePatientVaccineWithInvalidArguments_PatientID_NotFound_NotFound()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/vaccine",
                INVALID_PATIENT_ID_NOT_FOUND_CREATE_PATIENT_VACCINE_REQUEST.getPatientId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PATIENT_ID_NOT_FOUND_CREATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_PATIENT_NOT_FOUND));
    }

    @Test
    void admin_CreatePatientVaccineWithInvalidArguments_PatientID_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/vaccine",
                INVALID_PATIENT_ID_NEGATIVE_CREATE_PATIENT_VACCINE_REQUEST.getPatientId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PATIENT_ID_NEGATIVE_CREATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(2),
                        jsonPath("$.details[0].field").value("path.patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages")
                                .value("El id del paciente debe ser mayor a 0"),
                        jsonPath("$.details[1].field").value("patient_id"),
                        jsonPath("$.details[1].messages.length()").value(1),
                        jsonPath("$.details[1].messages")
                                .value("El id del paciente debe ser mayor a 0"));
    }
}
