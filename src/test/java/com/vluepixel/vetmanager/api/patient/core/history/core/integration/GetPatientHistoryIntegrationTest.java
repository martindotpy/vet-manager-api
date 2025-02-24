package com.vluepixel.vetmanager.api.patient.core.history.core.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_USER_JWT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the get patient history use case.
 */
public class GetPatientHistoryIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Historiales médicos del paciente encontrados exitosamente";
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    @Test
    void noUser_GetPatientHistoryWithValidParams_Forbidden() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/history", 1))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetPatientHistoryWithInvalidParams_ID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/history", 10))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetPatientHistoryWithInvalidParams_ID_Negative_Forbidden() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/history", -10))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetPatientHistoryWithInvalidParams_ID_NotNumber_Forbidden() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/history", "abcde"))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER
    @Test
    void user_GetPatientHistoryWithValidParams_Ok() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/history", 1)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    @Test
    void user_GetPatientHistoryWithInvalidParams_ID_NotFound_Ok() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/history", 10)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(0));
    }

    @Test
    void user_GetPatientHistoryWithInvalidParams_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/history", -10)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("path.patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id del paciente debe ser mayor a 0"));
    }

    @Test
    void user_GetPatientHistoryWithInvalidParams_ID_NotNumber_UnprocessableEntity() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/history", "abcde")
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("path.patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("Valor numérico inválido"));
    }

    // Role: ADMIN
    @Test
    void admin_GetPatientHistoryWithValidParams_Ok() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/history", 1)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    @Test
    void admin_GetPatientHistoryWithInvalidParams_ID_NotFound_Ok() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/history", 10)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(0));
    }

    @Test
    void admin_GetPatientHistoryWithInvalidParams_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/history", -10)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("path.patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id del paciente debe ser mayor a 0"));
    }

    @Test
    void admin_GetPatientHistoryWithInvalidParams_ID_NotNumber_UnprocessableEntity() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/history", "abcde")
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("path.patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("Valor numérico inválido"));
    }

}
