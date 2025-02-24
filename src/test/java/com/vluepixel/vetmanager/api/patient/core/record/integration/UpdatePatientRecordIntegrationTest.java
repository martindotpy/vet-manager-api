package com.vluepixel.vetmanager.api.patient.core.record.integration;

import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.VALID_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.ADMIN_DTO;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the update patient record use case.
 */
public class UpdatePatientRecordIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "";
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
    void admin_UpdatePatientRecordWithValidArguments_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason").value(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.entry_at")
                                .value(VALID_UPDATE_PATIENT_RECORD_REQUEST.getEntryAt().toString()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate").value(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight").value(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized").value(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe").value(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis").value(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.patient.id").value("TODO"),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatment").isArray());
    }
}
