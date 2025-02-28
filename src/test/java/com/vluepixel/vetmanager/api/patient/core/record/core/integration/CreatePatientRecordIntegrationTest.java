package com.vluepixel.vetmanager.api.patient.core.record.core.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_USER_JWT;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.INVALID_ENTRY_AT_NULL_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.INVALID_HEART_RATE_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.INVALID_HEART_RATE_NULL_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.INVALID_HEART_RATE_TOO_BIG_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.INVALID_HEART_RATE_ZERO_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.INVALID_PATIENT_ID_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.INVALID_PATIENT_ID_NOT_FOUND_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.INVALID_PATIENT_ID_NULL_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.INVALID_REASON_BLANK_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.INVALID_REASON_EMPTY_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.INVALID_REASON_NULL_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.INVALID_RESPIRATION_RATE_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.INVALID_RESPIRATION_RATE_NULL_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.INVALID_RESPIRATION_RATE_ZERO_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.INVALID_TEMPERATURE_IN_CELSIUS_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.INVALID_TEMPERATURE_IN_CELSIUS_NULL_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.INVALID_TEMPERATURE_IN_CELSIUS_TOO_BIG_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.INVALID_TEMPERATURE_IN_CELSIUS_ZERO_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.INVALID_VET_ID_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.INVALID_VET_ID_NOT_FOUND_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.INVALID_VET_ID_NULL_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.INVALID_WEIGHT_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.INVALID_WEIGHT_NULL_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.INVALID_WEIGHT_TOO_BIG_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.INVALID_WEIGHT_ZERO_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.VALID_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.VALID_DIAGNOSIS_BLANK_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.VALID_DIAGNOSIS_EMPTY_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.VALID_DIAGNOSIS_NULL_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.VALID_ENTRY_AT_FUTURE_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.VALID_ENTRY_AT_MINUS_YEAR_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.VALID_ENTRY_AT_TODAY_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.VALID_HEART_RATE_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.VALID_PHYSICAL_EXAM_BLANK_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.VALID_PHYSICAL_EXAM_EMPTY_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.VALID_PHYSICAL_EXAM_NULL_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.VALID_RECIPE_BLANK_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.VALID_RECIPE_EMPTY_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.VALID_RECIPE_NULL_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.data.CreatePatientRecordDataProvider.VALID_WEIGHT_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST;
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
    private static final String MESSAGE_VET_NOT_FOUND = "Veterinario no encontrado(a)";
    private static final String MESSAGE_PATIENT_NOT_FOUND = "Paciente no encontrado(a)";

    @Test
    void noUser_CreatePatientRecordWithValidArguments_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithInvalidArguments_ID_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithInvalidArguments_Reason_Blank_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_REASON_BLANK_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithInvalidArguments_Reason_Empty_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_REASON_EMPTY_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithInvalidArguments_Reason_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_REASON_NULL_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithValidArguments_EntryAt_MinusYear_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_ENTRY_AT_MINUS_YEAR_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithValidArguments_EntryAt_Today_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_ENTRY_AT_TODAY_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithValidArguments_EntryAt_Future_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_ENTRY_AT_FUTURE_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithInvalidArguments_EntryAt_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ENTRY_AT_NULL_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithValidArguments_PhysicalExam_Blank_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PHYSICAL_EXAM_BLANK_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithValidArguments_PhysicalExam_Empty_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PHYSICAL_EXAM_EMPTY_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithValidArguments_PhysicalExam_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PHYSICAL_EXAM_NULL_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithInvalidArguments_TemperatureInCelsius_TooBig_Forbidden()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_TEMPERATURE_IN_CELSIUS_TOO_BIG_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithValidArguments_TemperatureInCelsius_MaxValue_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithInvalidArguments_TemperatureInCelsius_Zero_Forbidden()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_TEMPERATURE_IN_CELSIUS_ZERO_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithInvalidArguments_TemperatureInCelsius_Negative_Forbidden()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_TEMPERATURE_IN_CELSIUS_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithInvalidArguments_TemperatureInCelsius_Null_Forbidden()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_TEMPERATURE_IN_CELSIUS_NULL_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithInvalidArguments_RespirationRate_Zero_Forbidden()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_RESPIRATION_RATE_ZERO_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithInvalidArguments_RespirationRate_Negative_Forbidden()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_RESPIRATION_RATE_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithInvalidArguments_RespirationRate_Null_Forbidden()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_RESPIRATION_RATE_NULL_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithInvalidArguments_HeartRate_TooBig_Forbidden()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_HEART_RATE_TOO_BIG_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithValidArguments_HeartRate_MaxValue_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_HEART_RATE_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithInvalidArguments_HeartRate_Zero_Forbidden()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_HEART_RATE_ZERO_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithInvalidArguments_HeartRate_Negative_Forbidden()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_HEART_RATE_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithInvalidArguments_HeartRate_Null_Forbidden()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_HEART_RATE_NULL_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithInvalidArguments_Weight_TooBig_Forbidden()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_WEIGHT_TOO_BIG_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithValidArguments_Weight_MaxValue_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_WEIGHT_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithInvalidArguments_Weight_Zero_Forbidden()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_WEIGHT_ZERO_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithInvalidArguments_Weight_Negative_Forbidden()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_WEIGHT_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithInvalidArguments_Weight_Null_Forbidden()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_WEIGHT_NULL_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithValidArguments_Recipe_Blank_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_RECIPE_BLANK_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithValidArguments_Recipe_Empty_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_RECIPE_EMPTY_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithValidArguments_Recipe_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_RECIPE_NULL_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithValidArguments_Diagnosis_Blank_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_DIAGNOSIS_BLANK_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithValidArguments_Diagnosis_Empty_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_DIAGNOSIS_EMPTY_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithValidArguments_Diagnosis_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_DIAGNOSIS_NULL_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithInvalidArguments_VetID_NotFound_Forbidden()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_VET_ID_NOT_FOUND_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithInvalidArguments_VetID_Negative_Forbidden()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_VET_ID_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithInvalidArguments_VetID_Null_Forbidden()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_VET_ID_NULL_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithInvalidArguments_PatientID_NotFound_Forbidden()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record",
                INVALID_PATIENT_ID_NOT_FOUND_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PATIENT_ID_NOT_FOUND_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithInvalidArguments_PatientID_Negative_Forbidden()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record",
                INVALID_PATIENT_ID_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PATIENT_ID_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientRecordWithInvalidArguments_PatientID_Null_Forbidden()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PATIENT_ID_NULL_CREATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    @Order(1)
    @DirtiesContext
    void user_CreatePatientRecordWithValidArguments_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason").value(VALID_CREATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_CREATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespirationRate()),
                        jsonPath("$.content.heart_rate").value(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight").value(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized").value(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe").value(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis").value(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    void user_CreatePatientRecordWithInvalidArguments_ID_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("path.patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El id del paciente no coincide con el id del historial médico"));
    }

    @Test
    void user_CreatePatientRecordWithInvalidArguments_Reason_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_REASON_BLANK_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("reason"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La razón es requerida"));
    }

    @Test
    void user_CreatePatientRecordWithInvalidArguments_Reason_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_REASON_EMPTY_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("reason"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La razón es requerida"));
    }

    @Test
    void user_CreatePatientRecordWithInvalidArguments_Reason_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_REASON_NULL_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("reason"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La razón es requerida"));
    }

    @Test
    @Order(2)
    @DirtiesContext
    void user_CreatePatientRecordWithValidArguments_EntryAt_MinusYear_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_ENTRY_AT_MINUS_YEAR_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_CREATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_CREATE_PATIENT_RECORD_REQUEST.getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_CREATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_CREATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_CREATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_CREATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @Order(3)
    @DirtiesContext
    void user_CreatePatientRecordWithValidArguments_EntryAt_Today_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_ENTRY_AT_TODAY_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_ENTRY_AT_TODAY_CREATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_ENTRY_AT_TODAY_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_ENTRY_AT_TODAY_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_ENTRY_AT_TODAY_CREATE_PATIENT_RECORD_REQUEST.getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_ENTRY_AT_TODAY_CREATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_ENTRY_AT_TODAY_CREATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_ENTRY_AT_TODAY_CREATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_ENTRY_AT_TODAY_CREATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_ENTRY_AT_TODAY_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id")
                                .value(VALID_ENTRY_AT_TODAY_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @Order(4)
    @DirtiesContext
    void user_CreatePatientRecordWithValidArguments_EntryAt_Future_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_ENTRY_AT_FUTURE_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_ENTRY_AT_FUTURE_CREATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_ENTRY_AT_FUTURE_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_ENTRY_AT_FUTURE_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_ENTRY_AT_FUTURE_CREATE_PATIENT_RECORD_REQUEST.getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_ENTRY_AT_FUTURE_CREATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_ENTRY_AT_FUTURE_CREATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_ENTRY_AT_FUTURE_CREATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_ENTRY_AT_FUTURE_CREATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_ENTRY_AT_FUTURE_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id")
                                .value(VALID_ENTRY_AT_FUTURE_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    void user_CreatePatientRecordWithInvalidArguments_EntryAt_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ENTRY_AT_NULL_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("entry_at"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La fecha de ingreso es requerida"));
    }

    @Test
    @Order(5)
    @DirtiesContext
    void user_CreatePatientRecordWithValidArguments_PhysicalExam_Blank_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PHYSICAL_EXAM_BLANK_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_PHYSICAL_EXAM_BLANK_CREATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam").doesNotExist(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_PHYSICAL_EXAM_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_PHYSICAL_EXAM_BLANK_CREATE_PATIENT_RECORD_REQUEST.getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_PHYSICAL_EXAM_BLANK_CREATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_PHYSICAL_EXAM_BLANK_CREATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_PHYSICAL_EXAM_BLANK_CREATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_PHYSICAL_EXAM_BLANK_CREATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_PHYSICAL_EXAM_BLANK_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id")
                                .value(VALID_PHYSICAL_EXAM_BLANK_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @Order(6)
    @DirtiesContext
    void user_CreatePatientRecordWithValidArguments_PhysicalExam_Empty_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PHYSICAL_EXAM_EMPTY_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_CREATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam").doesNotExist(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_CREATE_PATIENT_RECORD_REQUEST.getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_CREATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_CREATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_CREATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_CREATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @Order(7)
    @DirtiesContext
    void user_CreatePatientRecordWithValidArguments_PhysicalExam_Null_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PHYSICAL_EXAM_NULL_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_PHYSICAL_EXAM_NULL_CREATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam").doesNotExist(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_PHYSICAL_EXAM_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_PHYSICAL_EXAM_NULL_CREATE_PATIENT_RECORD_REQUEST.getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_PHYSICAL_EXAM_NULL_CREATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_PHYSICAL_EXAM_NULL_CREATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_PHYSICAL_EXAM_NULL_CREATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_PHYSICAL_EXAM_NULL_CREATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_PHYSICAL_EXAM_NULL_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id")
                                .value(VALID_PHYSICAL_EXAM_NULL_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    void user_CreatePatientRecordWithInvalidArguments_TemperatureInCelsius_TooBig_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_TEMPERATURE_IN_CELSIUS_TOO_BIG_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("temperature_in_celsius"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La temperatura no puede ser mayor a 100"));
    }

    @Test
    @Order(8)
    @DirtiesContext
    void user_CreatePatientRecordWithValidArguments_TemperatureInCelsius_MaxValue_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getReason()),
                        jsonPath("$.content.physical_exam").value(
                                VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(Double.parseDouble(
                                        VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                                .getTemperatureInCelsius().toString())),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getDiagnosis()),
                        jsonPath("$.content.vet.id")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    void user_CreatePatientRecordWithInvalidArguments_TemperatureInCelsius_Zero_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_TEMPERATURE_IN_CELSIUS_ZERO_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("temperature_in_celsius"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La temperatura debe ser mayor a 0"));
    }

    @Test
    void user_CreatePatientRecordWithInvalidArguments_TemperatureInCelsius_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_TEMPERATURE_IN_CELSIUS_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("temperature_in_celsius"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La temperatura debe ser mayor a 0"));
    }

    @Test
    void user_CreatePatientRecordWithInvalidArguments_TemperatureInCelsius_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_TEMPERATURE_IN_CELSIUS_NULL_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("temperature_in_celsius"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La temperatura es requerida"));
    }

    @Test
    void user_CreatePatientRecordWithInvalidArguments_RespirationRate_Zero_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_RESPIRATION_RATE_ZERO_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("respiration_rate"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La frecuencia respiratoria debe ser mayor a 0"));
    }

    @Test
    void user_CreatePatientRecordWithInvalidArguments_RespirationRate_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_RESPIRATION_RATE_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("respiration_rate"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La frecuencia respiratoria debe ser mayor a 0"));
    }

    @Test
    void user_CreatePatientRecordWithInvalidArguments_RespirationRate_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_RESPIRATION_RATE_NULL_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("respiration_rate"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La frecuencia respiratoria es requerida"));
    }

    @Test
    void user_CreatePatientRecordWithInvalidArguments_HeartRate_TooBig_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_HEART_RATE_TOO_BIG_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("heart_rate"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La frecuencia cardiaca no puede ser mayor a 1000"));
    }

    @Test
    @Order(9)
    @DirtiesContext
    void user_CreatePatientRecordWithValidArguments_HeartRate_MaxValue_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_HEART_RATE_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_HEART_RATE_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getReason()),
                        jsonPath("$.content.physical_exam").value(
                                VALID_HEART_RATE_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_HEART_RATE_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius().toString()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_HEART_RATE_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_HEART_RATE_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_HEART_RATE_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_HEART_RATE_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_HEART_RATE_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_HEART_RATE_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getDiagnosis()),
                        jsonPath("$.content.vet.id")
                                .value(VALID_HEART_RATE_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    void user_CreatePatientRecordWithInvalidArguments_HeartRate_Zero_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_HEART_RATE_ZERO_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("heart_rate"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La frecuencia cardiaca debe ser mayor a 0"));
    }

    @Test
    void user_CreatePatientRecordWithInvalidArguments_HeartRate_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_HEART_RATE_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("heart_rate"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La frecuencia cardiaca debe ser mayor a 0"));
    }

    @Test
    void user_CreatePatientRecordWithInvalidArguments_HeartRate_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_HEART_RATE_NULL_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("heart_rate"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La frecuencia cardiaca es requerida"));
    }

    @Test
    void user_CreatePatientRecordWithInvalidArguments_Weight_TooBig_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_WEIGHT_TOO_BIG_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("weight"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El peso no puede ser mayor a 999.99"));
    }

    @Test
    @Order(10)
    @DirtiesContext
    void user_CreatePatientRecordWithValidArguments_Weight_MaxValue_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_WEIGHT_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_WEIGHT_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getReason()),
                        jsonPath("$.content.physical_exam").value(
                                VALID_WEIGHT_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_WEIGHT_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius().toString()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_WEIGHT_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_WEIGHT_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_WEIGHT_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_WEIGHT_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_WEIGHT_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_WEIGHT_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getDiagnosis()),
                        jsonPath("$.content.vet.id")
                                .value(VALID_WEIGHT_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    void user_CreatePatientRecordWithInvalidArguments_Weight_Zero_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_WEIGHT_ZERO_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("weight"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El peso debe ser mayor a 0"));
    }

    @Test
    void user_CreatePatientRecordWithInvalidArguments_Weight_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_WEIGHT_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("weight"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El peso debe ser mayor a 0"));
    }

    @Test
    void user_CreatePatientRecordWithInvalidArguments_Weight_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_WEIGHT_NULL_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("weight"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El peso es requerido"));
    }

    @Test
    @Order(11)
    @DirtiesContext
    void user_CreatePatientRecordWithValidArguments_Recipe_Blank_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_RECIPE_BLANK_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_RECIPE_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .getReason()),
                        jsonPath("$.content.physical_exam").value(
                                VALID_RECIPE_BLANK_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_RECIPE_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius().toString()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_RECIPE_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_RECIPE_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_RECIPE_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_RECIPE_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .isSterilized()),
                        jsonPath("$.content.recipe").doesNotExist(),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_RECIPE_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .getDiagnosis()),
                        jsonPath("$.content.vet.id")
                                .value(VALID_RECIPE_BLANK_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @Order(12)
    @DirtiesContext
    void user_CreatePatientRecordWithValidArguments_Recipe_Empty_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_RECIPE_EMPTY_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_RECIPE_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .getReason()),
                        jsonPath("$.content.physical_exam").value(
                                VALID_RECIPE_EMPTY_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_RECIPE_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius().toString()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_RECIPE_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_RECIPE_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_RECIPE_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_RECIPE_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .isSterilized()),
                        jsonPath("$.content.recipe").doesNotExist(),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_RECIPE_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .getDiagnosis()),
                        jsonPath("$.content.vet.id")
                                .value(VALID_RECIPE_EMPTY_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @Order(13)
    @DirtiesContext
    void user_CreatePatientRecordWithValidArguments_Recipe_Null_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_RECIPE_NULL_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_RECIPE_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .getReason()),
                        jsonPath("$.content.physical_exam").value(
                                VALID_RECIPE_NULL_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_RECIPE_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius().toString()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_RECIPE_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_RECIPE_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_RECIPE_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_RECIPE_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .isSterilized()),
                        jsonPath("$.content.recipe").doesNotExist(),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_RECIPE_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .getDiagnosis()),
                        jsonPath("$.content.vet.id")
                                .value(VALID_RECIPE_NULL_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @Order(14)
    @DirtiesContext
    void user_CreatePatientRecordWithValidArguments_Diagnosis_Blank_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_DIAGNOSIS_BLANK_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_DIAGNOSIS_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .getReason()),
                        jsonPath("$.content.physical_exam").value(
                                VALID_DIAGNOSIS_BLANK_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_DIAGNOSIS_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius().toString()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_DIAGNOSIS_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_DIAGNOSIS_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_DIAGNOSIS_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_DIAGNOSIS_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_DIAGNOSIS_BLANK_CREATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis").doesNotExist(),
                        jsonPath("$.content.vet.id")
                                .value(VALID_DIAGNOSIS_BLANK_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @Order(15)
    @DirtiesContext
    void user_CreatePatientRecordWithValidArguments_Diagnosis_Empty_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_DIAGNOSIS_EMPTY_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_DIAGNOSIS_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .getReason()),
                        jsonPath("$.content.physical_exam").value(
                                VALID_DIAGNOSIS_EMPTY_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_DIAGNOSIS_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius().toString()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_DIAGNOSIS_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_DIAGNOSIS_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_DIAGNOSIS_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_DIAGNOSIS_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_DIAGNOSIS_EMPTY_CREATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis").doesNotExist(),
                        jsonPath("$.content.vet.id")
                                .value(VALID_DIAGNOSIS_EMPTY_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @Order(16)
    @DirtiesContext
    void user_CreatePatientRecordWithValidArguments_Diagnosis_Null_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_DIAGNOSIS_NULL_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_DIAGNOSIS_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .getReason()),
                        jsonPath("$.content.physical_exam").value(
                                VALID_DIAGNOSIS_NULL_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_DIAGNOSIS_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius().toString()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_DIAGNOSIS_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_DIAGNOSIS_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_DIAGNOSIS_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_DIAGNOSIS_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_DIAGNOSIS_NULL_CREATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis").doesNotExist(),
                        jsonPath("$.content.vet.id")
                                .value(VALID_DIAGNOSIS_NULL_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    void user_CreatePatientRecordWithInvalidArguments_VetID_NotFound_NotFound()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_VET_ID_NOT_FOUND_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_VET_NOT_FOUND));
    }

    @Test
    void user_CreatePatientRecordWithInvalidArguments_VetID_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_VET_ID_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("vet_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del veterinario debe ser mayor a 0"));
    }

    @Test
    void user_CreatePatientRecordWithInvalidArguments_VetID_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_VET_ID_NULL_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("vet_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del veterinario es requerido"));
    }

    @Test
    void user_CreatePatientRecordWithInvalidArguments_PatientID_NotFound_NotFound()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record",
                INVALID_PATIENT_ID_NOT_FOUND_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PATIENT_ID_NOT_FOUND_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_PATIENT_NOT_FOUND));
    }

    @Test
    void user_CreatePatientRecordWithInvalidArguments_PatientID_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record",
                INVALID_PATIENT_ID_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PATIENT_ID_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(2),
                        jsonPath("$.details[0].field").value("path.patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del paciente debe ser mayor a 0"),
                        jsonPath("$.details[1].field").value("patient_id"),
                        jsonPath("$.details[1].messages.length()").value(1),
                        jsonPath("$.details[1].messages[0]").value("El id del paciente debe ser mayor a 0"));
    }

    @Test
    void user_CreatePatientRecordWithInvalidArguments_PatientID_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PATIENT_ID_NULL_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(2),
                        jsonPath("$.details[0].field").value("path.patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El id del paciente no coincide con el id del historial médico"),
                        jsonPath("$.details[1].field").value("patient_id"),
                        jsonPath("$.details[1].messages.length()").value(1),
                        jsonPath("$.details[1].messages[0]")
                                .value("El id del paciente es requerido"));
    }

    @Test
    @Order(17)
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
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_CREATE_PATIENT_RECORD_REQUEST.getRespirationRate()),
                        jsonPath("$.content.heart_rate").value(VALID_CREATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight").value(VALID_CREATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized").value(VALID_CREATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe").value(VALID_CREATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis").value(VALID_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(VALID_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    void admin_CreatePatientRecordWithInvalidArguments_ID_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("path.patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El id del paciente no coincide con el id del historial médico"));
    }

    @Test
    void admin_CreatePatientRecordWithInvalidArguments_Reason_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_REASON_BLANK_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("reason"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La razón es requerida"));
    }

    @Test
    void admin_CreatePatientRecordWithInvalidArguments_Reason_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_REASON_EMPTY_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("reason"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La razón es requerida"));
    }

    @Test
    void admin_CreatePatientRecordWithInvalidArguments_Reason_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_REASON_NULL_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("reason"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La razón es requerida"));
    }

    @Test
    @Order(18)
    @DirtiesContext
    void admin_CreatePatientRecordWithValidArguments_EntryAt_MinusYear_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_ENTRY_AT_MINUS_YEAR_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_CREATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_CREATE_PATIENT_RECORD_REQUEST.getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_CREATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_CREATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_CREATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_CREATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @Order(19)
    @DirtiesContext
    void admin_CreatePatientRecordWithValidArguments_EntryAt_Today_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_ENTRY_AT_TODAY_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_ENTRY_AT_TODAY_CREATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_ENTRY_AT_TODAY_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_ENTRY_AT_TODAY_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_ENTRY_AT_TODAY_CREATE_PATIENT_RECORD_REQUEST.getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_ENTRY_AT_TODAY_CREATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_ENTRY_AT_TODAY_CREATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_ENTRY_AT_TODAY_CREATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_ENTRY_AT_TODAY_CREATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_ENTRY_AT_TODAY_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id")
                                .value(VALID_ENTRY_AT_TODAY_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @Order(20)
    @DirtiesContext
    void admin_CreatePatientRecordWithValidArguments_EntryAt_Future_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_ENTRY_AT_FUTURE_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_ENTRY_AT_FUTURE_CREATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_ENTRY_AT_FUTURE_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_ENTRY_AT_FUTURE_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_ENTRY_AT_FUTURE_CREATE_PATIENT_RECORD_REQUEST.getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_ENTRY_AT_FUTURE_CREATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_ENTRY_AT_FUTURE_CREATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_ENTRY_AT_FUTURE_CREATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_ENTRY_AT_FUTURE_CREATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_ENTRY_AT_FUTURE_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id")
                                .value(VALID_ENTRY_AT_FUTURE_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    void admin_CreatePatientRecordWithInvalidArguments_EntryAt_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ENTRY_AT_NULL_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("entry_at"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La fecha de ingreso es requerida"));
    }

    @Test
    @Order(21)
    @DirtiesContext
    void admin_CreatePatientRecordWithValidArguments_PhysicalExam_Blank_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PHYSICAL_EXAM_BLANK_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_PHYSICAL_EXAM_BLANK_CREATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam").doesNotExist(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_PHYSICAL_EXAM_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_PHYSICAL_EXAM_BLANK_CREATE_PATIENT_RECORD_REQUEST.getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_PHYSICAL_EXAM_BLANK_CREATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_PHYSICAL_EXAM_BLANK_CREATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_PHYSICAL_EXAM_BLANK_CREATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_PHYSICAL_EXAM_BLANK_CREATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_PHYSICAL_EXAM_BLANK_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id")
                                .value(VALID_PHYSICAL_EXAM_BLANK_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @Order(22)
    @DirtiesContext
    void admin_CreatePatientRecordWithValidArguments_PhysicalExam_Empty_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PHYSICAL_EXAM_EMPTY_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_CREATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam").doesNotExist(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_CREATE_PATIENT_RECORD_REQUEST.getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_CREATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_CREATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_CREATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_CREATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @Order(23)
    @DirtiesContext
    void admin_CreatePatientRecordWithValidArguments_PhysicalExam_Null_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PHYSICAL_EXAM_NULL_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_PHYSICAL_EXAM_NULL_CREATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam").doesNotExist(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_PHYSICAL_EXAM_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_PHYSICAL_EXAM_NULL_CREATE_PATIENT_RECORD_REQUEST.getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_PHYSICAL_EXAM_NULL_CREATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_PHYSICAL_EXAM_NULL_CREATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_PHYSICAL_EXAM_NULL_CREATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_PHYSICAL_EXAM_NULL_CREATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_PHYSICAL_EXAM_NULL_CREATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id")
                                .value(VALID_PHYSICAL_EXAM_NULL_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    void admin_CreatePatientRecordWithInvalidArguments_TemperatureInCelsius_TooBig_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_TEMPERATURE_IN_CELSIUS_TOO_BIG_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("temperature_in_celsius"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La temperatura no puede ser mayor a 100"));
    }

    @Test
    @Order(24)
    @DirtiesContext
    void admin_CreatePatientRecordWithValidArguments_TemperatureInCelsius_MaxValue_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getReason()),
                        jsonPath("$.content.physical_exam").value(
                                VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(Double.parseDouble(
                                        VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                                .getTemperatureInCelsius().toString())),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getDiagnosis()),
                        jsonPath("$.content.vet.id")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    void admin_CreatePatientRecordWithInvalidArguments_TemperatureInCelsius_Zero_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_TEMPERATURE_IN_CELSIUS_ZERO_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("temperature_in_celsius"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La temperatura debe ser mayor a 0"));
    }

    @Test
    void admin_CreatePatientRecordWithInvalidArguments_TemperatureInCelsius_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_TEMPERATURE_IN_CELSIUS_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("temperature_in_celsius"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La temperatura debe ser mayor a 0"));
    }

    @Test
    void admin_CreatePatientRecordWithInvalidArguments_TemperatureInCelsius_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_TEMPERATURE_IN_CELSIUS_NULL_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("temperature_in_celsius"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La temperatura es requerida"));
    }

    @Test
    void admin_CreatePatientRecordWithInvalidArguments_RespirationRate_Zero_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_RESPIRATION_RATE_ZERO_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("respiration_rate"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La frecuencia respiratoria debe ser mayor a 0"));
    }

    @Test
    void admin_CreatePatientRecordWithInvalidArguments_RespirationRate_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_RESPIRATION_RATE_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("respiration_rate"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La frecuencia respiratoria debe ser mayor a 0"));
    }

    @Test
    void admin_CreatePatientRecordWithInvalidArguments_RespirationRate_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_RESPIRATION_RATE_NULL_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("respiration_rate"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La frecuencia respiratoria es requerida"));
    }

    @Test
    void admin_CreatePatientRecordWithInvalidArguments_HeartRate_TooBig_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_HEART_RATE_TOO_BIG_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("heart_rate"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La frecuencia cardiaca no puede ser mayor a 1000"));
    }

    @Test
    @Order(25)
    @DirtiesContext
    void admin_CreatePatientRecordWithValidArguments_HeartRate_MaxValue_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_HEART_RATE_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_HEART_RATE_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getReason()),
                        jsonPath("$.content.physical_exam").value(
                                VALID_HEART_RATE_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_HEART_RATE_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius().toString()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_HEART_RATE_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_HEART_RATE_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_HEART_RATE_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_HEART_RATE_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_HEART_RATE_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_HEART_RATE_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getDiagnosis()),
                        jsonPath("$.content.vet.id")
                                .value(VALID_HEART_RATE_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    void admin_CreatePatientRecordWithInvalidArguments_HeartRate_Zero_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_HEART_RATE_ZERO_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("heart_rate"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La frecuencia cardiaca debe ser mayor a 0"));
    }

    @Test
    void admin_CreatePatientRecordWithInvalidArguments_HeartRate_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_HEART_RATE_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("heart_rate"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La frecuencia cardiaca debe ser mayor a 0"));
    }

    @Test
    void admin_CreatePatientRecordWithInvalidArguments_HeartRate_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_HEART_RATE_NULL_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("heart_rate"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La frecuencia cardiaca es requerida"));
    }

    @Test
    void admin_CreatePatientRecordWithInvalidArguments_Weight_TooBig_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_WEIGHT_TOO_BIG_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("weight"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El peso no puede ser mayor a 999.99"));
    }

    @Test
    @Order(26)
    @DirtiesContext
    void admin_CreatePatientRecordWithValidArguments_Weight_MaxValue_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_WEIGHT_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_WEIGHT_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getReason()),
                        jsonPath("$.content.physical_exam").value(
                                VALID_WEIGHT_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_WEIGHT_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius().toString()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_WEIGHT_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_WEIGHT_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_WEIGHT_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_WEIGHT_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_WEIGHT_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_WEIGHT_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST
                                        .getDiagnosis()),
                        jsonPath("$.content.vet.id")
                                .value(VALID_WEIGHT_MAX_VALUE_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    void admin_CreatePatientRecordWithInvalidArguments_Weight_Zero_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_WEIGHT_ZERO_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("weight"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El peso debe ser mayor a 0"));
    }

    @Test
    void admin_CreatePatientRecordWithInvalidArguments_Weight_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_WEIGHT_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("weight"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El peso debe ser mayor a 0"));
    }

    @Test
    void admin_CreatePatientRecordWithInvalidArguments_Weight_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_WEIGHT_NULL_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("weight"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El peso es requerido"));
    }

    @Test
    @Order(27)
    @DirtiesContext
    void admin_CreatePatientRecordWithValidArguments_Recipe_Blank_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_RECIPE_BLANK_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_RECIPE_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .getReason()),
                        jsonPath("$.content.physical_exam").value(
                                VALID_RECIPE_BLANK_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_RECIPE_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius().toString()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_RECIPE_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_RECIPE_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_RECIPE_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_RECIPE_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .isSterilized()),
                        jsonPath("$.content.recipe").doesNotExist(),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_RECIPE_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .getDiagnosis()),
                        jsonPath("$.content.vet.id")
                                .value(VALID_RECIPE_BLANK_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @Order(28)
    @DirtiesContext
    void admin_CreatePatientRecordWithValidArguments_Recipe_Empty_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_RECIPE_EMPTY_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_RECIPE_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .getReason()),
                        jsonPath("$.content.physical_exam").value(
                                VALID_RECIPE_EMPTY_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_RECIPE_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius().toString()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_RECIPE_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_RECIPE_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_RECIPE_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_RECIPE_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .isSterilized()),
                        jsonPath("$.content.recipe").doesNotExist(),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_RECIPE_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .getDiagnosis()),
                        jsonPath("$.content.vet.id")
                                .value(VALID_RECIPE_EMPTY_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @Order(29)
    @DirtiesContext
    void admin_CreatePatientRecordWithValidArguments_Recipe_Null_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_RECIPE_NULL_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_RECIPE_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .getReason()),
                        jsonPath("$.content.physical_exam").value(
                                VALID_RECIPE_NULL_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_RECIPE_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius().toString()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_RECIPE_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_RECIPE_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_RECIPE_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_RECIPE_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .isSterilized()),
                        jsonPath("$.content.recipe").doesNotExist(),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_RECIPE_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .getDiagnosis()),
                        jsonPath("$.content.vet.id")
                                .value(VALID_RECIPE_NULL_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @Order(30)
    @DirtiesContext
    void admin_CreatePatientRecordWithValidArguments_Diagnosis_Blank_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_DIAGNOSIS_BLANK_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_DIAGNOSIS_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .getReason()),
                        jsonPath("$.content.physical_exam").value(
                                VALID_DIAGNOSIS_BLANK_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_DIAGNOSIS_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius().toString()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_DIAGNOSIS_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_DIAGNOSIS_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_DIAGNOSIS_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_DIAGNOSIS_BLANK_CREATE_PATIENT_RECORD_REQUEST
                                        .isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_DIAGNOSIS_BLANK_CREATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis").doesNotExist(),
                        jsonPath("$.content.vet.id")
                                .value(VALID_DIAGNOSIS_BLANK_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @Order(31)
    @DirtiesContext
    void admin_CreatePatientRecordWithValidArguments_Diagnosis_Empty_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_DIAGNOSIS_EMPTY_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_DIAGNOSIS_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .getReason()),
                        jsonPath("$.content.physical_exam").value(
                                VALID_DIAGNOSIS_EMPTY_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_DIAGNOSIS_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius().toString()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_DIAGNOSIS_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_DIAGNOSIS_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_DIAGNOSIS_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_DIAGNOSIS_EMPTY_CREATE_PATIENT_RECORD_REQUEST
                                        .isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_DIAGNOSIS_EMPTY_CREATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis").doesNotExist(),
                        jsonPath("$.content.vet.id")
                                .value(VALID_DIAGNOSIS_EMPTY_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @Order(32)
    @DirtiesContext
    void admin_CreatePatientRecordWithValidArguments_Diagnosis_Null_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_DIAGNOSIS_NULL_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.reason")
                                .value(VALID_DIAGNOSIS_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .getReason()),
                        jsonPath("$.content.physical_exam").value(
                                VALID_DIAGNOSIS_NULL_CREATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_DIAGNOSIS_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius().toString()),
                        jsonPath("$.content.respiration_rate")
                                .value(VALID_DIAGNOSIS_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .getRespirationRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_DIAGNOSIS_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_DIAGNOSIS_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_DIAGNOSIS_NULL_CREATE_PATIENT_RECORD_REQUEST
                                        .isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_DIAGNOSIS_NULL_CREATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis").doesNotExist(),
                        jsonPath("$.content.vet.id")
                                .value(VALID_DIAGNOSIS_NULL_CREATE_PATIENT_RECORD_REQUEST.getVetId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    void admin_CreatePatientRecordWithInvalidArguments_VetID_NotFound_NotFound()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_VET_ID_NOT_FOUND_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_VET_NOT_FOUND));
    }

    @Test
    void admin_CreatePatientRecordWithInvalidArguments_VetID_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_VET_ID_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("vet_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del veterinario debe ser mayor a 0"));
    }

    @Test
    void admin_CreatePatientRecordWithInvalidArguments_VetID_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_VET_ID_NULL_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("vet_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del veterinario es requerido"));
    }

    @Test
    void admin_CreatePatientRecordWithInvalidArguments_PatientID_NotFound_NotFound()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record",
                INVALID_PATIENT_ID_NOT_FOUND_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PATIENT_ID_NOT_FOUND_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_PATIENT_NOT_FOUND));
    }

    @Test
    void admin_CreatePatientRecordWithInvalidArguments_PatientID_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record",
                INVALID_PATIENT_ID_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST.getPatientId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PATIENT_ID_NEGATIVE_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(2),
                        jsonPath("$.details[0].field").value("path.patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del paciente debe ser mayor a 0"),
                        jsonPath("$.details[1].field").value("patient_id"),
                        jsonPath("$.details[1].messages.length()").value(1),
                        jsonPath("$.details[1].messages[0]").value("El id del paciente debe ser mayor a 0"));
    }

    @Test
    void admin_CreatePatientRecordWithInvalidArguments_PatientID_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PATIENT_ID_NULL_CREATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(2),
                        jsonPath("$.details[0].field").value("path.patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El id del paciente no coincide con el id del historial médico"),
                        jsonPath("$.details[1].field").value("patient_id"),
                        jsonPath("$.details[1].messages.length()").value(1),
                        jsonPath("$.details[1].messages[0]")
                                .value("El id del paciente es requerido"));
    }
}
