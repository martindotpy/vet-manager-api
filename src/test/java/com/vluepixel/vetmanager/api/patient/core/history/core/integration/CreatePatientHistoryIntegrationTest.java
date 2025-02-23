package com.vluepixel.vetmanager.api.patient.core.history.core.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_USER_JWT;
import static com.vluepixel.vetmanager.api.patient.core.history.core.data.CreatePatientHistoryDataProvider.INVALID_CONTENT_BLANK_CREATE_PATIENT_HISTORY_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.history.core.data.CreatePatientHistoryDataProvider.INVALID_CONTENT_EMPTY_CREATE_PATIENT_HISTORY_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.history.core.data.CreatePatientHistoryDataProvider.INVALID_CONTENT_NULL_CREATE_PATIENT_HISTORY_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.history.core.data.CreatePatientHistoryDataProvider.INVALID_PATIENT_ID_NEGATIVE_CREATE_PATIENT_HISTORY_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.history.core.data.CreatePatientHistoryDataProvider.INVALID_PATIENT_ID_NOT_FOUND_CREATE_PATIENT_HISTORY_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.history.core.data.CreatePatientHistoryDataProvider.INVALID_PATIENT_ID_NULL_CREATE_PATIENT_HISTORY_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.history.core.data.CreatePatientHistoryDataProvider.VALID_CREATE_PATIENT_HISTORY_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the create patient history use case.
 */
public class CreatePatientHistoryIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Historial médico creado exitosamente";
    private static final String MESSAGE_NOT_FOUND = "Paciente no encontrado(a)";
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    @Test
    void noUser_CreatePatientHistoryWithValidArguments_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_PATIENT_HISTORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Content
    @Test
    void noUser_CreatePatientHistoryWithInvalidArguments_Content_Blank_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CONTENT_BLANK_CREATE_PATIENT_HISTORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientHistoryWithInvalidArguments_Content_Empty_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CONTENT_EMPTY_CREATE_PATIENT_HISTORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientHistoryWithInvalidArguments_Content_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CONTENT_NULL_CREATE_PATIENT_HISTORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Patient ID
    @Test
    void noUser_CreatePatientHistoryWithInvalidArguments_PatientID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PATIENT_ID_NOT_FOUND_CREATE_PATIENT_HISTORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientHistoryWithInvalidArguments_PatientID_Negative_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PATIENT_ID_NEGATIVE_CREATE_PATIENT_HISTORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientHistoryWithInvalidArguments_PatientID_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PATIENT_ID_NULL_CREATE_PATIENT_HISTORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // The same Patient ID
    @Test
    void noUser_CreatePatientHistoryWithInvalidArguments_PatientIDSame_NotFound_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history",
                INVALID_PATIENT_ID_NOT_FOUND_CREATE_PATIENT_HISTORY_REQUEST.getPatientId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PATIENT_ID_NOT_FOUND_CREATE_PATIENT_HISTORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientHistoryWithInvalidArguments_PatientIDSame_Negative_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history",
                INVALID_PATIENT_ID_NEGATIVE_CREATE_PATIENT_HISTORY_REQUEST.getPatientId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PATIENT_ID_NEGATIVE_CREATE_PATIENT_HISTORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER
    @Test
    @Order(1)
    @DirtiesContext
    void user_CreatePatientHistoryWithValidArguments_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(4),
                        jsonPath("$.content.content").value(VALID_CREATE_PATIENT_HISTORY_REQUEST.getContent()));
    }

    // Content
    @Test
    void user_CreatePatientHistoryWithInvalidArguments_Content_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CONTENT_BLANK_CREATE_PATIENT_HISTORY_REQUEST))
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
    void user_CreatePatientHistoryWithInvalidArguments_Content_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CONTENT_EMPTY_CREATE_PATIENT_HISTORY_REQUEST))
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
    void user_CreatePatientHistoryWithInvalidArguments_Content_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CONTENT_NULL_CREATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("content"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El contenido del historial médico es requerido"));
    }

    // Patient ID
    @Test
    void user_CreatePatientHistoryWithInvalidArguments_PatientID_NotFound_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PATIENT_ID_NOT_FOUND_CREATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("body.patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El id del paciente no coincide con el id del paciente en la ruta"));
    }

    @Test
    void user_CreatePatientHistoryWithInvalidArguments_PatientID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PATIENT_ID_NEGATIVE_CREATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(2),
                        jsonPath("$.details[0].field").value("body.patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages")
                                .value("El id del paciente no coincide con el id del paciente en la ruta"),
                        jsonPath("$.details[1].field").value("patient_id"),
                        jsonPath("$.details[1].messages.length()").value(1),
                        jsonPath("$.details[1].messages")
                                .value("El id del paciente debe ser mayor a 0"));
    }

    @Test
    void user_CreatePatientHistoryWithInvalidArguments_PatientID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PATIENT_ID_NULL_CREATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(2),
                        jsonPath("$.details[0].field").value("body.patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages")
                                .value("El id del paciente no coincide con el id del paciente en la ruta"),
                        jsonPath("$.details[1].field").value("patient_id"),
                        jsonPath("$.details[1].messages.length()").value(1),
                        jsonPath("$.details[1].messages")
                                .value("El id del paciente es requerido"));
    }

    // The same Patient ID
    @Test
    void user_CreatePatientHistoryWithInvalidArguments_PatientIDSame_NotFound_NotFound() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history",
                INVALID_PATIENT_ID_NOT_FOUND_CREATE_PATIENT_HISTORY_REQUEST.getPatientId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PATIENT_ID_NOT_FOUND_CREATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND));
    }

    @Test
    void user_CreatePatientHistoryWithInvalidArguments_PatientIDSame_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history",
                INVALID_PATIENT_ID_NEGATIVE_CREATE_PATIENT_HISTORY_REQUEST.getPatientId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PATIENT_ID_NEGATIVE_CREATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(2),
                        jsonPath("$.details[0].field").value("path.patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages")
                                .value("El id del paciente debe ser mayor a 0"),
                        jsonPath("$.details[1].field").value("patient_id"),
                        jsonPath("$.details[1].messages.length()").value(1),
                        jsonPath("$.details[1].messages")
                                .value("El id del paciente debe ser mayor a 0"));
    }

    // Role: ADMIN
    @Test
    @Order(2)
    @DirtiesContext
    void admin_CreatePatientHistoryWithValidArguments_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(4),
                        jsonPath("$.content.content").value(VALID_CREATE_PATIENT_HISTORY_REQUEST.getContent()));
    }

    // Content
    @Test
    void admin_CreatePatientHistoryWithInvalidArguments_Content_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CONTENT_BLANK_CREATE_PATIENT_HISTORY_REQUEST))
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
    void admin_CreatePatientHistoryWithInvalidArguments_Content_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CONTENT_EMPTY_CREATE_PATIENT_HISTORY_REQUEST))
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
    void admin_CreatePatientHistoryWithInvalidArguments_Content_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CONTENT_NULL_CREATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("content"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El contenido del historial médico es requerido"));
    }

    // Patient ID
    @Test
    void admin_CreatePatientHistoryWithInvalidArguments_PatientID_NotFound_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PATIENT_ID_NOT_FOUND_CREATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("body.patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El id del paciente no coincide con el id del paciente en la ruta"));
    }

    @Test
    void admin_CreatePatientHistoryWithInvalidArguments_PatientID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PATIENT_ID_NEGATIVE_CREATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(2),
                        jsonPath("$.details[0].field").value("body.patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages")
                                .value("El id del paciente no coincide con el id del paciente en la ruta"),
                        jsonPath("$.details[1].field").value("patient_id"),
                        jsonPath("$.details[1].messages.length()").value(1),
                        jsonPath("$.details[1].messages")
                                .value("El id del paciente debe ser mayor a 0"));
    }

    @Test
    void admin_CreatePatientHistoryWithInvalidArguments_PatientID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PATIENT_ID_NULL_CREATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(2),
                        jsonPath("$.details[0].field").value("body.patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages")
                                .value("El id del paciente no coincide con el id del paciente en la ruta"),
                        jsonPath("$.details[1].field").value("patient_id"),
                        jsonPath("$.details[1].messages.length()").value(1),
                        jsonPath("$.details[1].messages")
                                .value("El id del paciente es requerido"));
    }

    // The same Patient ID
    @Test
    void admin_CreatePatientHistoryWithInvalidArguments_PatientIDSame_NotFound_NotFound() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history",
                INVALID_PATIENT_ID_NOT_FOUND_CREATE_PATIENT_HISTORY_REQUEST.getPatientId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PATIENT_ID_NOT_FOUND_CREATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND));
    }

    @Test
    void admin_CreatePatientHistoryWithInvalidArguments_PatientIDSame_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/history",
                INVALID_PATIENT_ID_NEGATIVE_CREATE_PATIENT_HISTORY_REQUEST.getPatientId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PATIENT_ID_NEGATIVE_CREATE_PATIENT_HISTORY_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(2),
                        jsonPath("$.details[0].field").value("path.patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages")
                                .value("El id del paciente debe ser mayor a 0"),
                        jsonPath("$.details[1].field").value("patient_id"),
                        jsonPath("$.details[1].messages.length()").value(1),
                        jsonPath("$.details[1].messages")
                                .value("El id del paciente debe ser mayor a 0"));
    }
}
