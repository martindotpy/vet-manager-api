package com.vluepixel.vetmanager.api.patient.core.history.core.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_USER_JWT;
import static com.vluepixel.vetmanager.api.patient.core.history.core.data.UpdatePatientHistoryDataProvider.INVALID_CONTENT_BLANK_UPDATE_PATIENT_HISTORY_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.history.core.data.UpdatePatientHistoryDataProvider.INVALID_CONTENT_EMPTY_UPDATE_PATIENT_HISTORY_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.history.core.data.UpdatePatientHistoryDataProvider.INVALID_CONTENT_NULL_UPDATE_PATIENT_HISTORY_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.history.core.data.UpdatePatientHistoryDataProvider.INVALID_ID_NEGATIVE_UPDATE_PATIENT_HISTORY_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.history.core.data.UpdatePatientHistoryDataProvider.INVALID_ID_NOT_FOUND_UPDATE_PATIENT_HISTORY_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.history.core.data.UpdatePatientHistoryDataProvider.INVALID_ID_NULL_UPDATE_PATIENT_HISTORY_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.history.core.data.UpdatePatientHistoryDataProvider.VALID_UPDATE_PATIENT_HISTORY_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the update patient history use case.
 */
public class UpdatePatientHistoryIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Historial médico actualizado exitosamente";
    private static final Function<String, String> MESSAGE_NOT_FOUND = parameter -> String
            .format("Historia clínica con id %s no encontrado(a)", parameter);
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    @Test
    void noUser_UpdatePatientHistoryWithValidArguments_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", VALID_UPDATE_PATIENT_HISTORY_REQUEST.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_HISTORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientHistoryWithInvalidArguments_PatientID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", 10)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_HISTORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientHistoryWithInvalidArguments_PatientID_Invalid_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", "invalid")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NEGATIVE_UPDATE_PATIENT_HISTORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientHistoryWithInvalidArguments_PatientID_Negative_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", -1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_HISTORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientHistoryWithInvalidArguments_ID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_HISTORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientHistoryWithInvalidArguments_ID_Negative_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NEGATIVE_UPDATE_PATIENT_HISTORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientHistoryWithInvalidArguments_ID_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NULL_UPDATE_PATIENT_HISTORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Content
    @Test
    void noUser_UpdatePatientHistoryWithInvalidArguments_Content_Blank_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CONTENT_BLANK_UPDATE_PATIENT_HISTORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientHistoryWithInvalidArguments_Content_Empty_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CONTENT_EMPTY_UPDATE_PATIENT_HISTORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientHistoryWithInvalidArguments_Content_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CONTENT_NULL_UPDATE_PATIENT_HISTORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER
    @Test
    @DirtiesContext
    void user_UpdatePatientHistoryWithValidArguments_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", VALID_UPDATE_PATIENT_HISTORY_REQUEST.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_UPDATE_PATIENT_HISTORY_REQUEST.getId()),
                        jsonPath("$.content.content").value(VALID_UPDATE_PATIENT_HISTORY_REQUEST.getContent()));
    }

    @Test
    void user_UpdatePatientHistoryWithInvalidArguments_PatientID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", 10)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND
                        .apply(VALID_UPDATE_PATIENT_HISTORY_REQUEST.getId().toString())));
    }

    @Test
    void user_UpdatePatientHistoryWithInvalidArguments_PatientID_Invalid_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", "invalid")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NEGATIVE_UPDATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("path.patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("Valor numérico inválido"));
    }

    @Test
    void user_UpdatePatientHistoryWithInvalidArguments_PatientID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", -1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("path.patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del paciente debe ser mayor a 0"));
    }

    @Test
    void user_UpdatePatientHistoryWithInvalidArguments_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND
                        .apply(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_HISTORY_REQUEST.getId().toString())));
    }

    @Test
    void user_UpdatePatientHistoryWithInvalidArguments_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NEGATIVE_UPDATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id debe ser mayor a 0"));
    }

    @Test
    void user_UpdatePatientHistoryWithInvalidArguments_ID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NULL_UPDATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id es requerido"));
    }

    // Content
    @Test
    void user_UpdatePatientHistoryWithInvalidArguments_Content_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CONTENT_BLANK_UPDATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("content"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El contenido del historial médico es requerido"));
    }

    @Test
    void user_UpdatePatientHistoryWithInvalidArguments_Content_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CONTENT_EMPTY_UPDATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("content"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El contenido del historial médico es requerido"));
    }

    @Test
    void user_UpdatePatientHistoryWithInvalidArguments_Content_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CONTENT_NULL_UPDATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("content"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El contenido del historial médico es requerido"));
    }

    // Role: ADMIN
    @Test
    @DirtiesContext
    void admin_UpdatePatientHistoryWithValidArguments_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", VALID_UPDATE_PATIENT_HISTORY_REQUEST.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_UPDATE_PATIENT_HISTORY_REQUEST.getId()),
                        jsonPath("$.content.content").value(VALID_UPDATE_PATIENT_HISTORY_REQUEST.getContent()));
    }

    @Test
    void admin_UpdatePatientHistoryWithInvalidArguments_PatientID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", 10)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND
                        .apply(VALID_UPDATE_PATIENT_HISTORY_REQUEST.getId().toString())));
    }

    @Test
    void admin_UpdatePatientHistoryWithInvalidArguments_PatientID_Invalid_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", "invalid")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NEGATIVE_UPDATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("path.patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("Valor numérico inválido"));
    }

    @Test
    void admin_UpdatePatientHistoryWithInvalidArguments_PatientID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", -1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("path.patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del paciente debe ser mayor a 0"));
    }

    @Test
    void admin_UpdatePatientHistoryWithInvalidArguments_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND
                        .apply(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_HISTORY_REQUEST.getId().toString())));
    }

    @Test
    void admin_UpdatePatientHistoryWithInvalidArguments_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NEGATIVE_UPDATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id debe ser mayor a 0"));
    }

    @Test
    void admin_UpdatePatientHistoryWithInvalidArguments_ID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NULL_UPDATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id es requerido"));
    }

    // Content
    @Test
    void admin_UpdatePatientHistoryWithInvalidArguments_Content_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CONTENT_BLANK_UPDATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("content"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El contenido del historial médico es requerido"));
    }

    @Test
    void admin_UpdatePatientHistoryWithInvalidArguments_Content_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CONTENT_EMPTY_UPDATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("content"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El contenido del historial médico es requerido"));
    }

    @Test
    void admin_UpdatePatientHistoryWithInvalidArguments_Content_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CONTENT_NULL_UPDATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("content"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El contenido del historial médico es requerido"));
    }

}
