package com.vluepixel.vetmanager.api.patient.core.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.VALID_UPDATE_PATIENT_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the update patient use case.
 */
public class UpdatePatientIntegrationTest extends BaseIntegrationTest {
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER

    // Role: ADMIN

    @Test
    @DirtiesContext
    void admin_UpdatePatientWithValidArguments_Ok() throws Exception {
        mockMvc.perform(put("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value("Paciente eliminado exitosamente"), // TODO ???
                        jsonPath("$.content.id").value(VALID_UPDATE_PATIENT_REQUEST.getId()),
                        jsonPath("$.content.name").value(VALID_UPDATE_PATIENT_REQUEST.getName()),
                        jsonPath("$.content.birth_date").value(VALID_UPDATE_PATIENT_REQUEST.getBirthDate()),
                        jsonPath("$.content.age").value(10),
                        jsonPath("$.content.gender").value(VALID_UPDATE_PATIENT_REQUEST.getGender()),
                        jsonPath("$.content.characteristics").value(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics()),
                        jsonPath("$.content.deceased").value(VALID_UPDATE_PATIENT_REQUEST.isDeceased()),
                        jsonPath("$.content.medical_histories").isArray(),
                        jsonPath("$.content.medical_records").isArray(),
                        jsonPath("$.content.vaccines").isArray(),
                        jsonPath("$.content.race.id").value(VALID_UPDATE_PATIENT_REQUEST.getRaceId()),
                        jsonPath("$.content.owner.id").value(VALID_UPDATE_PATIENT_REQUEST.getOwnerId()));
    }
}
