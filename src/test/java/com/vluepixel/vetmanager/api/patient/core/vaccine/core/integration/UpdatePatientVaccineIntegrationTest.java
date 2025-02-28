package com.vluepixel.vetmanager.api.patient.core.vaccine.core.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_USER_JWT;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the update patient vaccine use case.
 */
public class UpdatePatientVaccineIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Vacuna actualizada exitosamente";
    private static final Function<String, String> MESSAGE_NOT_FOUND = parameter -> String
            .format("Vacuna con id %s no encontrado(a)", parameter);
    private static final String MESSAGE_VACCINATOR_NOT_FOUND = "Vacunador no encontrado(a)";
    private static final Function<String, String> MESSAGE_PRODUCT_SALE_NOT_FOUND = parameter -> String
            .format("Venta de producto con id %s no encontrado(a)", parameter);
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    @Test
    void noUser_UpdatePatientVaccineWithValidArguments_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_VACCINE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientVaccineWithInvalidArguments_ID_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_VACCINE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Name
    @Test
    void noUser_UpdatePatientVaccineWithInvalidArguments_Name_TooLong_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_TOO_LONG_UPDATE_PATIENT_VACCINE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientVaccineWithValidArguments_Name_MaxValue_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_NAME_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientVaccineWithInvalidArguments_Name_Blank_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_BLANK_UPDATE_PATIENT_VACCINE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientVaccineWithInvalidArguments_Name_Empty_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_EMPTY_UPDATE_PATIENT_VACCINE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientVaccineWithInvalidArguments_Name_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_NULL_UPDATE_PATIENT_VACCINE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Dose In Milliliters
    @Test
    void noUser_UpdatePatientVaccineWithInvalidArguments_DoseInMilliliters_TooBig_Forbidden()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DOSE_IN_MILLILITERS_TOO_BIG_UPDATE_PATIENT_VACCINE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientVaccineWithValidArguments_DoseInMilliliters_MaxValue_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_DOSE_IN_MILLILITERS_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientVaccineWithInvalidArguments_DoseInMilliliters_Zero_Forbidden()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DOSE_IN_MILLILITERS_ZERO_UPDATE_PATIENT_VACCINE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientVaccineWithInvalidArguments_DoseInMilliliters_Negative_Forbidden()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DOSE_IN_MILLILITERS_NEGATIVE_UPDATE_PATIENT_VACCINE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientVaccineWithInvalidArguments_DoseInMilliliters_Null_Forbidden()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DOSE_IN_MILLILITERS_NULL_UPDATE_PATIENT_VACCINE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Provided At
    @Test
    void noUser_UpdatePatientVaccineWithValidArguments_ProvidedAt_MinusYear_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_PROVIDED_AT_MINUS_YEAR_UPDATE_PATIENT_VACCINE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientVaccineWithValidArguments_ProvidedAt_Today_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_PROVIDED_AT_TODAY_UPDATE_PATIENT_VACCINE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientVaccineWithValidArguments_ProvidedAt_Future_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_PROVIDED_AT_FUTURE_UPDATE_PATIENT_VACCINE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientVaccineWithInvalidArguments_ProvidedAt_Null_Forbidden()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PROVIDED_AT_NULL_UPDATE_PATIENT_VACCINE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Vaccinator ID
    @Test
    void noUser_UpdatePatientVaccineWithInvalidArguments_VaccinatorID_NotFound_Forbidden()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_VACCINATOR_ID_NOT_FOUND_UPDATE_PATIENT_VACCINE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientVaccineWithInvalidArguments_Vaccinator_Negative_Forbidden()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_VACCINATOR_ID_NEGATIVE_UPDATE_PATIENT_VACCINE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientVaccineWithInvalidArguments_Vaccinator_Null_Forbidden()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_VACCINATOR_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Product Sale ID
    @Test
    void noUser_UpdatePatientVaccineWithInvalidArguments_ProductSaleID_NotFound_Forbidden()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PRODUCT_SALE_ID_NOT_FOUND_UPDATE_PATIENT_VACCINE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientVaccineWithInvalidArguments_ProductSaleID_Negative_Forbidden()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PRODUCT_SALE_ID_NEGATIVE_UPDATE_PATIENT_VACCINE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientVaccineWithInvalidArguments_ProductSaleID_Null_Forbidden()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_PRODUCT_SALE_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER
    @Test
    @DirtiesContext
    void user_UpdatePatientVaccineWithValidArguments_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getId()),
                        jsonPath("$.content.name").value(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getName()),
                        jsonPath("$.content.dose_in_milliliters")
                                .value(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters()),
                        jsonPath("$.content.provided_at").isString(),
                        jsonPath("$.content.vaccinator.id")
                                .value(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId()),
                        jsonPath("$.content.product_sale.id")
                                .value(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId()));
    }

    @Test
    void user_UpdatePatientVaccineWithInvalidArguments_ID_NotFound() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND.apply("1")));
    }

    // Name
    @Test
    void user_UpdatePatientVaccineWithInvalidArguments_Name_TooLong_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_TOO_LONG_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
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
    @DirtiesContext
    void user_UpdatePatientVaccineWithValidArguments_Name_MaxValue_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_NAME_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_PROVIDED_AT_FUTURE_UPDATE_PATIENT_VACCINE_REQUEST.getId()),
                        jsonPath("$.content.name").value(VALID_NAME_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST.getName()),
                        jsonPath("$.content.dose_in_milliliters")
                                .value(VALID_NAME_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters()),
                        jsonPath("$.content.provided_at").isString(),
                        jsonPath("$.content.vaccinator.id")
                                .value(VALID_NAME_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId()),
                        jsonPath("$.content.product_sale.id")
                                .value(VALID_NAME_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId()));
    }

    @Test
    void user_UpdatePatientVaccineWithInvalidArguments_Name_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_BLANK_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
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
    void user_UpdatePatientVaccineWithInvalidArguments_Name_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_EMPTY_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
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
    void user_UpdatePatientVaccineWithInvalidArguments_Name_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_NULL_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
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
    void user_UpdatePatientVaccineWithInvalidArguments_DoseInMilliliters_TooBig_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DOSE_IN_MILLILITERS_TOO_BIG_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
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
    @DirtiesContext
    void user_UpdatePatientVaccineWithValidArguments_DoseInMilliliters_MaxValue_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_DOSE_IN_MILLILITERS_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_PROVIDED_AT_FUTURE_UPDATE_PATIENT_VACCINE_REQUEST.getId()),
                        jsonPath("$.content.name")
                                .value(VALID_DOSE_IN_MILLILITERS_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST.getName()),
                        jsonPath("$.content.dose_in_milliliters")
                                .value(VALID_DOSE_IN_MILLILITERS_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getDoseInMilliliters()),
                        jsonPath("$.content.provided_at").isString(),
                        jsonPath("$.content.vaccinator.id")
                                .value(VALID_DOSE_IN_MILLILITERS_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getVaccinatorId()),
                        jsonPath("$.content.product_sale.id")
                                .value(VALID_DOSE_IN_MILLILITERS_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getProductSaleId()));
    }

    @Test
    void user_UpdatePatientVaccineWithInvalidArguments_DoseInMilliliters_Zero_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DOSE_IN_MILLILITERS_ZERO_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
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
    void user_UpdatePatientVaccineWithInvalidArguments_DoseInMilliliters_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DOSE_IN_MILLILITERS_NEGATIVE_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
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
    void user_UpdatePatientVaccineWithInvalidArguments_DoseInMilliliters_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DOSE_IN_MILLILITERS_NULL_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
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
    @DirtiesContext
    void user_UpdatePatientVaccineWithValidArguments_ProvidedAt_MinusYear_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_PROVIDED_AT_MINUS_YEAR_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_PROVIDED_AT_FUTURE_UPDATE_PATIENT_VACCINE_REQUEST.getId()),
                        jsonPath("$.content.name")
                                .value(VALID_PROVIDED_AT_MINUS_YEAR_UPDATE_PATIENT_VACCINE_REQUEST.getName()),
                        jsonPath("$.content.dose_in_milliliters")
                                .value(VALID_PROVIDED_AT_MINUS_YEAR_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getDoseInMilliliters()),
                        jsonPath("$.content.provided_at").isString(),
                        jsonPath("$.content.vaccinator.id")
                                .value(VALID_PROVIDED_AT_MINUS_YEAR_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getVaccinatorId()),
                        jsonPath("$.content.product_sale.id")
                                .value(VALID_PROVIDED_AT_MINUS_YEAR_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getProductSaleId()));
    }

    @Test
    @DirtiesContext
    void user_UpdatePatientVaccineWithValidArguments_ProvidedAt_Today_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_PROVIDED_AT_TODAY_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_PROVIDED_AT_FUTURE_UPDATE_PATIENT_VACCINE_REQUEST.getId()),
                        jsonPath("$.content.name")
                                .value(VALID_PROVIDED_AT_TODAY_UPDATE_PATIENT_VACCINE_REQUEST.getName()),
                        jsonPath("$.content.dose_in_milliliters")
                                .value(VALID_PROVIDED_AT_TODAY_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getDoseInMilliliters()),
                        jsonPath("$.content.provided_at").isString(),
                        jsonPath("$.content.vaccinator.id")
                                .value(VALID_PROVIDED_AT_TODAY_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getVaccinatorId()),
                        jsonPath("$.content.product_sale.id")
                                .value(VALID_PROVIDED_AT_TODAY_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getProductSaleId()));
    }

    @Test
    @DirtiesContext
    void user_UpdatePatientVaccineWithValidArguments_ProvidedAt_Future_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_PROVIDED_AT_FUTURE_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_PROVIDED_AT_FUTURE_UPDATE_PATIENT_VACCINE_REQUEST.getId()),
                        jsonPath("$.content.name")
                                .value(VALID_PROVIDED_AT_FUTURE_UPDATE_PATIENT_VACCINE_REQUEST.getName()),
                        jsonPath("$.content.dose_in_milliliters")
                                .value(VALID_PROVIDED_AT_FUTURE_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getDoseInMilliliters()),
                        jsonPath("$.content.provided_at").isString(),
                        jsonPath("$.content.vaccinator.id")
                                .value(VALID_PROVIDED_AT_FUTURE_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getVaccinatorId()),
                        jsonPath("$.content.product_sale.id")
                                .value(VALID_PROVIDED_AT_FUTURE_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getProductSaleId()));
    }

    @Test
    void user_UpdatePatientVaccineWithInvalidArguments_ProvidedAt_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PROVIDED_AT_NULL_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
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
    void user_UpdatePatientVaccineWithInvalidArguments_VaccinatorID_NotFound_NotFound()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_VACCINATOR_ID_NOT_FOUND_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_VACCINATOR_NOT_FOUND));
    }

    @Test
    void user_UpdatePatientVaccineWithInvalidArguments_Vaccinator_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_VACCINATOR_ID_NEGATIVE_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
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
    void user_UpdatePatientVaccineWithInvalidArguments_Vaccinator_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_VACCINATOR_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
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
    void user_UpdatePatientVaccineWithInvalidArguments_ProductSaleID_NotFound_NotFound()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PRODUCT_SALE_ID_NOT_FOUND_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_PRODUCT_SALE_NOT_FOUND.apply("10")));
    }

    @Test
    void user_UpdatePatientVaccineWithInvalidArguments_ProductSaleID_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PRODUCT_SALE_ID_NEGATIVE_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
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
    @DirtiesContext
    void user_UpdatePatientVaccineWithInvalidArguments_ProductSaleID_Null_Ok()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_PRODUCT_SALE_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id")
                                .value(VALID_PRODUCT_SALE_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST.getId()),
                        jsonPath("$.content.name")
                                .value(VALID_PRODUCT_SALE_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST.getName()),
                        jsonPath("$.content.dose_in_milliliters")
                                .value(VALID_PRODUCT_SALE_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getDoseInMilliliters()),
                        jsonPath("$.content.provided_at").isString(),
                        jsonPath("$.content.vaccinator.id")
                                .value(VALID_PRODUCT_SALE_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getVaccinatorId()),
                        jsonPath("$.content.product_sale")
                                .value(VALID_PRODUCT_SALE_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getProductSaleId()));
    }

    // Role: ADMIN
    @Test
    @DirtiesContext
    void admin_UpdatePatientVaccineWithValidArguments_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getId()),
                        jsonPath("$.content.name").value(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getName()),
                        jsonPath("$.content.dose_in_milliliters")
                                .value(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters()),
                        jsonPath("$.content.provided_at").isString(),
                        jsonPath("$.content.vaccinator.id")
                                .value(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId()),
                        jsonPath("$.content.product_sale.id")
                                .value(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId()));
    }

    @Test
    void admin_UpdatePatientVaccineWithInvalidArguments_ID_NotFound() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND.apply("1")));
    }

    // Name
    @Test
    void admin_UpdatePatientVaccineWithInvalidArguments_Name_TooLong_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_TOO_LONG_UPDATE_PATIENT_VACCINE_REQUEST))
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
    @DirtiesContext
    void admin_UpdatePatientVaccineWithValidArguments_Name_MaxValue_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_NAME_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_PROVIDED_AT_FUTURE_UPDATE_PATIENT_VACCINE_REQUEST.getId()),
                        jsonPath("$.content.name").value(VALID_NAME_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST.getName()),
                        jsonPath("$.content.dose_in_milliliters")
                                .value(VALID_NAME_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters()),
                        jsonPath("$.content.provided_at").isString(),
                        jsonPath("$.content.vaccinator.id")
                                .value(VALID_NAME_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId()),
                        jsonPath("$.content.product_sale.id")
                                .value(VALID_NAME_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId()));
    }

    @Test
    void admin_UpdatePatientVaccineWithInvalidArguments_Name_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_BLANK_UPDATE_PATIENT_VACCINE_REQUEST))
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
    void admin_UpdatePatientVaccineWithInvalidArguments_Name_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_EMPTY_UPDATE_PATIENT_VACCINE_REQUEST))
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
    void admin_UpdatePatientVaccineWithInvalidArguments_Name_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_NULL_UPDATE_PATIENT_VACCINE_REQUEST))
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
    void admin_UpdatePatientVaccineWithInvalidArguments_DoseInMilliliters_TooBig_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DOSE_IN_MILLILITERS_TOO_BIG_UPDATE_PATIENT_VACCINE_REQUEST))
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
    @DirtiesContext
    void admin_UpdatePatientVaccineWithValidArguments_DoseInMilliliters_MaxValue_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_DOSE_IN_MILLILITERS_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_PROVIDED_AT_FUTURE_UPDATE_PATIENT_VACCINE_REQUEST.getId()),
                        jsonPath("$.content.name")
                                .value(VALID_DOSE_IN_MILLILITERS_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST.getName()),
                        jsonPath("$.content.dose_in_milliliters")
                                .value(VALID_DOSE_IN_MILLILITERS_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getDoseInMilliliters()),
                        jsonPath("$.content.provided_at").isString(),
                        jsonPath("$.content.vaccinator.id")
                                .value(VALID_DOSE_IN_MILLILITERS_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getVaccinatorId()),
                        jsonPath("$.content.product_sale.id")
                                .value(VALID_DOSE_IN_MILLILITERS_MAX_VALUE_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getProductSaleId()));
    }

    @Test
    void admin_UpdatePatientVaccineWithInvalidArguments_DoseInMilliliters_Zero_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DOSE_IN_MILLILITERS_ZERO_UPDATE_PATIENT_VACCINE_REQUEST))
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
    void admin_UpdatePatientVaccineWithInvalidArguments_DoseInMilliliters_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DOSE_IN_MILLILITERS_NEGATIVE_UPDATE_PATIENT_VACCINE_REQUEST))
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
    void admin_UpdatePatientVaccineWithInvalidArguments_DoseInMilliliters_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DOSE_IN_MILLILITERS_NULL_UPDATE_PATIENT_VACCINE_REQUEST))
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
    @DirtiesContext
    void admin_UpdatePatientVaccineWithValidArguments_ProvidedAt_MinusYear_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_PROVIDED_AT_MINUS_YEAR_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_PROVIDED_AT_FUTURE_UPDATE_PATIENT_VACCINE_REQUEST.getId()),
                        jsonPath("$.content.name")
                                .value(VALID_PROVIDED_AT_MINUS_YEAR_UPDATE_PATIENT_VACCINE_REQUEST.getName()),
                        jsonPath("$.content.dose_in_milliliters")
                                .value(VALID_PROVIDED_AT_MINUS_YEAR_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getDoseInMilliliters()),
                        jsonPath("$.content.provided_at").isString(),
                        jsonPath("$.content.vaccinator.id")
                                .value(VALID_PROVIDED_AT_MINUS_YEAR_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getVaccinatorId()),
                        jsonPath("$.content.product_sale.id")
                                .value(VALID_PROVIDED_AT_MINUS_YEAR_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getProductSaleId()));
    }

    @Test
    @DirtiesContext
    void admin_UpdatePatientVaccineWithValidArguments_ProvidedAt_Today_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_PROVIDED_AT_TODAY_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_PROVIDED_AT_FUTURE_UPDATE_PATIENT_VACCINE_REQUEST.getId()),
                        jsonPath("$.content.name")
                                .value(VALID_PROVIDED_AT_TODAY_UPDATE_PATIENT_VACCINE_REQUEST.getName()),
                        jsonPath("$.content.dose_in_milliliters")
                                .value(VALID_PROVIDED_AT_TODAY_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getDoseInMilliliters()),
                        jsonPath("$.content.provided_at").isString(),
                        jsonPath("$.content.vaccinator.id")
                                .value(VALID_PROVIDED_AT_TODAY_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getVaccinatorId()),
                        jsonPath("$.content.product_sale.id")
                                .value(VALID_PROVIDED_AT_TODAY_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getProductSaleId()));
    }

    @Test
    @DirtiesContext
    void admin_UpdatePatientVaccineWithValidArguments_ProvidedAt_Future_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_PROVIDED_AT_FUTURE_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_PROVIDED_AT_FUTURE_UPDATE_PATIENT_VACCINE_REQUEST.getId()),
                        jsonPath("$.content.name")
                                .value(VALID_PROVIDED_AT_FUTURE_UPDATE_PATIENT_VACCINE_REQUEST.getName()),
                        jsonPath("$.content.dose_in_milliliters")
                                .value(VALID_PROVIDED_AT_FUTURE_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getDoseInMilliliters()),
                        jsonPath("$.content.provided_at").isString(),
                        jsonPath("$.content.vaccinator.id")
                                .value(VALID_PROVIDED_AT_FUTURE_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getVaccinatorId()),
                        jsonPath("$.content.product_sale.id")
                                .value(VALID_PROVIDED_AT_FUTURE_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getProductSaleId()));
    }

    @Test
    void admin_UpdatePatientVaccineWithInvalidArguments_ProvidedAt_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PROVIDED_AT_NULL_UPDATE_PATIENT_VACCINE_REQUEST))
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
    void admin_UpdatePatientVaccineWithInvalidArguments_VaccinatorID_NotFound_NotFound()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_VACCINATOR_ID_NOT_FOUND_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_VACCINATOR_NOT_FOUND));
    }

    @Test
    void admin_UpdatePatientVaccineWithInvalidArguments_Vaccinator_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_VACCINATOR_ID_NEGATIVE_UPDATE_PATIENT_VACCINE_REQUEST))
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
    void admin_UpdatePatientVaccineWithInvalidArguments_Vaccinator_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_VACCINATOR_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST))
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
    void admin_UpdatePatientVaccineWithInvalidArguments_ProductSaleID_NotFound_NotFound()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PRODUCT_SALE_ID_NOT_FOUND_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_PRODUCT_SALE_NOT_FOUND.apply("10")));
    }

    @Test
    void admin_UpdatePatientVaccineWithInvalidArguments_ProductSaleID_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PRODUCT_SALE_ID_NEGATIVE_UPDATE_PATIENT_VACCINE_REQUEST))
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
    @DirtiesContext
    void admin_UpdatePatientVaccineWithInvalidArguments_ProductSaleID_Null_Ok()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_PRODUCT_SALE_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id")
                                .value(VALID_PRODUCT_SALE_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST.getId()),
                        jsonPath("$.content.name")
                                .value(VALID_PRODUCT_SALE_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST.getName()),
                        jsonPath("$.content.dose_in_milliliters")
                                .value(VALID_PRODUCT_SALE_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getDoseInMilliliters()),
                        jsonPath("$.content.provided_at").isString(),
                        jsonPath("$.content.vaccinator.id")
                                .value(VALID_PRODUCT_SALE_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getVaccinatorId()),
                        jsonPath("$.content.product_sale")
                                .value(VALID_PRODUCT_SALE_ID_NULL_UPDATE_PATIENT_VACCINE_REQUEST
                                        .getProductSaleId()));
    }
}
