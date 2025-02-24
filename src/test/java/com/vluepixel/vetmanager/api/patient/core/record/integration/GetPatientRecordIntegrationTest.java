package com.vluepixel.vetmanager.api.patient.core.record.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the get patient record use case.
 */
public class GetPatientRecordIntegrationTest extends BaseIntegrationTest {
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
    void admin_GetPatientRecordWithValidParams_Ok() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/record", 1)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(0));
    }
}
