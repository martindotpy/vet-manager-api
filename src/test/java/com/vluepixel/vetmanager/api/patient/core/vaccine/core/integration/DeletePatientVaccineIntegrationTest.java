package com.vluepixel.vetmanager.api.patient.core.vaccine.core.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_USER_JWT;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.UpdatePatientVaccineDataProvider.INVALID_ID_NOT_FOUND_UPDATE_PATIENT_VACCINE_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.UpdatePatientVaccineDataProvider.VALID_UPDATE_PATIENT_VACCINE_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the delete patient vaccine functionality.
 */
public class DeletePatientVaccineIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Vacuna eliminada exitosamente";
    private static final Function<String, String> MESSAGE_NOT_FOUND = parameter -> String
            .format("Vacuna con id %s no encontrado(a)", parameter);
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    @Test
    void noUser_DeletePatientHistoryWithValidParams_Forbidden() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/vaccine/{id}", 1, VALID_UPDATE_PATIENT_VACCINE_REQUEST.getId()))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_DeletePatientHistoryWithInvalidParams_Forbidden() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/vaccine/{id}", 1, 3))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_DeletePatientHistoryWithInvalidParams_ID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/vaccine/{id}", 1,
                INVALID_ID_NOT_FOUND_UPDATE_PATIENT_VACCINE_REQUEST.getId()))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_DeletePatientHistoryWithInvalidParams_ID_Negative_Forbidden() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/vaccine/{id}", 1, -10))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_DeletePatientHistoryWithInvalidParams_ID_NotNumber_Forbidden() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/vaccine/{id}", 1, "abcde"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER
    @Test
    @DirtiesContext
    void user_DeletePatientHistoryWithValidParams_Ok() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/vaccine/{id}", 1, VALID_UPDATE_PATIENT_VACCINE_REQUEST.getId())
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(MESSAGE_OK));
    }

    @Test
    @DirtiesContext
    void user_DeletePatientHistoryWithInvalidParams_NotFound() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/vaccine/{id}", 1, 3)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND.apply("3")));
    }

    @Test
    void user_DeletePatientHistoryWithInvalidParams_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/vaccine/{id}", 1,
                INVALID_ID_NOT_FOUND_UPDATE_PATIENT_VACCINE_REQUEST.getId())
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(
                        jsonPath("$.message")
                                .value(MESSAGE_NOT_FOUND.apply(
                                        INVALID_ID_NOT_FOUND_UPDATE_PATIENT_VACCINE_REQUEST.getId().toString())));
    }

    @Test
    void user_DeletePatientHistoryWithInvalidParams_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/vaccine/{id}", 1, -10)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("query.id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id de la vacuna debe ser mayor a 0"));
    }

    @Test
    void user_DeletePatientHistoryWithInvalidParams_ID_NotNumber_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/vaccine/{id}", 1, "abcde")
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
    void admin_DeletePatientHistoryWithValidParams_Ok() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/vaccine/{id}", 1, VALID_UPDATE_PATIENT_VACCINE_REQUEST.getId())
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(MESSAGE_OK));
    }

    @Test
    @DirtiesContext
    void admin_DeletePatientHistoryWithInvalidParams_NotFound() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/vaccine/{id}", 1, 3)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND.apply("3")));
    }

    @Test
    void admin_DeletePatientHistoryWithInvalidParams_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/vaccine/{id}", 1,
                INVALID_ID_NOT_FOUND_UPDATE_PATIENT_VACCINE_REQUEST.getId())
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(
                        jsonPath("$.message")
                                .value(MESSAGE_NOT_FOUND.apply(
                                        INVALID_ID_NOT_FOUND_UPDATE_PATIENT_VACCINE_REQUEST.getId().toString())));
    }

    @Test
    void admin_DeletePatientHistoryWithInvalidParams_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/vaccine/{id}", 1, -10)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("query.id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id de la vacuna debe ser mayor a 0"));
    }

    @Test
    void admin_DeletePatientHistoryWithInvalidParams_ID_NotNumber_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/vaccine/{id}", 1, "abcde")
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
