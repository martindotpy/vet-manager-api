package com.vluepixel.vetmanager.api.patient.core.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.patient.core.data.CreatePatientDataProvider.VALID_CREATE_PATIENT_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration test for the create patient functionality.
 */
public class CreatePatientIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Paciente creado exitosamente";
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER

    // Role: ADMIN
    @Test
    @Order(3)
    @DirtiesContext
    void admin_CreatePatientWithValidArguments_Ok() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.name").value(VALID_CREATE_PATIENT_REQUEST.getName()),
                        jsonPath("$.content.birth_date").value(VALID_CREATE_PATIENT_REQUEST.getBirthDate().toString()),
                        // jsonPath("$.content.age").value(10),
                        jsonPath("$.content.gender").value(VALID_CREATE_PATIENT_REQUEST.getGender().toString()),
                        jsonPath("$.content.characteristics").value(VALID_CREATE_PATIENT_REQUEST.getCharacteristics()),
                        jsonPath("$.content.deceased").value(VALID_CREATE_PATIENT_REQUEST.isDeceased()),
                        jsonPath("$.content.medical_histories").isArray(),
                        jsonPath("$.content.medical_records").isArray(),
                        jsonPath("$.content.vaccines").isArray(),
                        jsonPath("$.content.race.id").value(VALID_CREATE_PATIENT_REQUEST.getRaceId()),
                        jsonPath("$.content.owner.id").value(VALID_CREATE_PATIENT_REQUEST.getOwnerId()));
    }
}
