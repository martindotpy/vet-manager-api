package com.vluepixel.vetmanager.api.patient.core.record.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_USER_JWT;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.VALID_UPDATE_PATIENT_RECORD_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the delete patient record functionality.
 */
public class DeletePatientRecordIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Registro médico eliminado exitosamente";
    private static final Function<String, String> MESSAGE_NOT_FOUND = parameter -> String
            .format("Registro médico con id %s no encontrado(a)", parameter);
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    @Test
    void noUser_DeletePatientRecordWithValidParams_Forbidden() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/record/{id}", 1, VALID_UPDATE_PATIENT_RECORD_REQUEST.getId()))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_DeletePatientRecordWithInvalidParams_Forbidden() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/record/{id}", 1, 3))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_DeletePatientRecordWithInvalidParams_ID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/record/{id}", 1,
                INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST.getId()))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_DeletePatientRecordWithInvalidParams_ID_Negative_Forbidden() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/record/{id}", 1, -10))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_DeletePatientRecordWithInvalidParams_ID_NotNumber_Forbidden() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/record/{id}", 1, "abcde"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER
    @Test
    @DirtiesContext
    void user_DeletePatientRecordWithValidParams_Ok() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/record/{id}", 1, VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(MESSAGE_OK));
    }

    @Test
    @DirtiesContext
    void user_DeletePatientRecordWithInvalidParams_Conflict() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/record/{id}", 1, 3)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value(MESSAGE_OK));
    }

    @Test
    void user_DeletePatientRecordWithInvalidParams_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/record/{id}", 1,
                INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST.getId())
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(
                        jsonPath("$.message")
                                .value(MESSAGE_NOT_FOUND.apply(
                                        INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST.getId().toString())));
    }

    @Test
    void user_DeletePatientRecordWithInvalidParams_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/record/{id}", 1, -10)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("query.id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id debe ser mayor a 0"));
    }

    @Test
    void user_DeletePatientRecordWithInvalidParams_ID_NotNumber_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/record/{id}", 1, "abcde")
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("path.id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("Valor numérico inválido"));
    }

    // Role: ADMIN
    @Test
    @DirtiesContext
    void admin_DeletePatientRecordWithValidParams_Ok() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/record/{id}", 1, VALID_UPDATE_PATIENT_RECORD_REQUEST.getId())
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(MESSAGE_OK));
    }

    @Test
    void admin_DeletePatientRecordWithInvalidParams_Conflict() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/record/{id}", 1, 2)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value(MESSAGE_OK));
    }

    @Test
    void admin_DeletePatientRecordWithInvalidParams_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/record/{id}", 1,
                INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST.getId())
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(
                        jsonPath("$.message")
                                .value(MESSAGE_NOT_FOUND.apply(
                                        INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST.getId().toString())));
    }

    @Test
    void admin_DeletePatientRecordWithInvalidParams_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/record/{id}", 1, -10)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("query.id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id debe ser mayor a 0"));
    }

    @Test
    void admin_DeletePatientRecordWithInvalidParams_ID_NotNumber_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/record/{id}", 1, "abcde")
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("path.id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("Valor numérico inválido"));
    }
}
