package com.vluepixel.vetmanager.api.patient.core.record.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.patient.core.record.data.CreatePatientRecordDataProvider.VALID_CREATE_PATIENT_RECORD_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the create patient record use case.
 */
public class CreatePatientRecordIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Registro médico creado exitosamente";
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
    @DirtiesContext
    void admin_CreatePatientRecordWithValidArguments_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason").value(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate").value(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight").value(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized").value(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe").value(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis").value(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }
}
