package com.vluepixel.vetmanager.api.patient.core.record.integration;

import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.INVALID_ENTRY_AT_NULL_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.INVALID_HEART_RATE_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.INVALID_HEART_RATE_NULL_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.INVALID_HEART_RATE_TOO_BIG_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.INVALID_HEART_RATE_ZERO_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.INVALID_ID_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.INVALID_ID_NULL_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.INVALID_REASON_BLANK_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.INVALID_REASON_EMPTY_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.INVALID_REASON_NULL_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.INVALID_RESPIRATION_RATE_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.INVALID_RESPIRATION_RATE_NULL_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.INVALID_RESPIRATION_RATE_ZERO_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.INVALID_TEMPERATURE_IN_CELSIUS_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.INVALID_TEMPERATURE_IN_CELSIUS_NULL_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.INVALID_TEMPERATURE_IN_CELSIUS_TOO_BIG_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.INVALID_TEMPERATURE_IN_CELSIUS_ZERO_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.INVALID_VET_ID_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.INVALID_VET_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.INVALID_VET_ID_NULL_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.INVALID_WEIGHT_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.INVALID_WEIGHT_NULL_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.INVALID_WEIGHT_TOO_BIG_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.INVALID_WEIGHT_ZERO_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.VALID_DIAGNOSIS_BLANK_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.VALID_DIAGNOSIS_EMPTY_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.VALID_DIAGNOSIS_NULL_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.VALID_PHYSICAL_EXAM_BLANK_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.VALID_PHYSICAL_EXAM_EMPTY_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.VALID_RECIPE_BLANK_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.VALID_RECIPE_EMPTY_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.VALID_RECIPE_NULL_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.VALID_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.data.UpdatePatientRecordDataProvider.VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.ADMIN_DTO;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_USER_JWT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the update patient record use case.
 */
public class UpdatePatientRecordIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Registro médico actualizado exitosamente";
    private static final Function<String, String> MESSAGE_NOT_FOUND = parameter -> String
            .format("Registro médico con id %s no encontrado(a)", parameter);
    private static final String MESSAGE_VET_NOT_FOUND = "Veterinario no encontrado(a)";
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    @Test
    void noUser_UpdatePatientRecordWithValidArguments_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithInvalidArguments_ID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithInvalidArguments_ID_Negative_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithInvalidArguments_ID_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NULL_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Reason
    @Test
    void noUser_UpdatePatientRecordWithInvalidArguments_Reason_Blank_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_REASON_BLANK_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithInvalidArguments_Reason_Empty_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_REASON_EMPTY_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithInvalidArguments_Reason_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_REASON_NULL_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Entry At
    @Test
    void noUser_UpdatePatientRecordWithValidArguments_EntryAt_MinusYear_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithValidArguments_EntryAt_Today_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithValidArguments_EntryAt_Future_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithInvalidArguments_EntryAt_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ENTRY_AT_NULL_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Physical Exam
    @Test
    void noUser_UpdatePatientRecordWithValidArguments_PhysicalExam_Blank_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PHYSICAL_EXAM_BLANK_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithValidArguments_PhysicalExam_Empty_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PHYSICAL_EXAM_EMPTY_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithValidArguments_PhysicalExam_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Temperature In Celsius
    @Test
    void noUser_UpdatePatientRecordWithInvalidArguments_TemperatureInCelsius_TooBig_Forbidden()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_TEMPERATURE_IN_CELSIUS_TOO_BIG_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithValidArguments_TemperatureInCelsius_MaxValue_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithInvalidArguments_TemperatureInCelsius_Zero_Forbidden()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_TEMPERATURE_IN_CELSIUS_ZERO_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithInvalidArguments_TemperatureInCelsius_Negative_Forbidden()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_TEMPERATURE_IN_CELSIUS_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithInvalidArguments_TemperatureInCelsius_Null_Forbidden()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_TEMPERATURE_IN_CELSIUS_NULL_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Respiration Rate
    @Test
    void noUser_UpdatePatientRecordWithInvalidArguments_RespirationRate_Zero_Forbidden()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_RESPIRATION_RATE_ZERO_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithInvalidArguments_RespirationRate_Negative_Forbidden()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_RESPIRATION_RATE_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithInvalidArguments_RespirationRate_Null_Forbidden()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_RESPIRATION_RATE_NULL_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Heart Rate
    @Test
    void noUser_UpdatePatientRecordWithInvalidArguments_HeartRate_TooBig_Forbidden()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_HEART_RATE_TOO_BIG_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithValidArguments_HeartRate_MaxValue_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithInvalidArguments_HeartRate_Zero_Forbidden()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_HEART_RATE_ZERO_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithInvalidArguments_HeartRate_Negative_Forbidden()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_HEART_RATE_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithInvalidArguments_HeartRate_Null_Forbidden()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_HEART_RATE_NULL_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Weight
    @Test
    void noUser_UpdatePatientRecordWithInvalidArguments_Weight_TooBig_Forbidden()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_WEIGHT_TOO_BIG_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithValidArguments_Weight_MaxValue_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithInvalidArguments_Weight_Zero_Forbidden()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_WEIGHT_ZERO_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithInvalidArguments_Weight_Negative_Forbidden()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_WEIGHT_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithInvalidArguments_Weight_Null_Forbidden()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_WEIGHT_NULL_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Sterilized

    // Recipe
    @Test
    void noUser_UpdatePatientRecordWithValidArguments_Recype_Blank_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_RECIPE_BLANK_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithValidArguments_Recype_Empty_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_RECIPE_EMPTY_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithValidArguments_Recype_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_RECIPE_NULL_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Diagnosis
    @Test
    void noUser_UpdatePatientRecordWithValidArguments_Diagnosis_Blank_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DIAGNOSIS_BLANK_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithValidArguments_Diagnosis_Empty_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DIAGNOSIS_EMPTY_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithValidArguments_Diagnosis_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DIAGNOSIS_NULL_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Vet ID
    @Test
    void noUser_UpdatePatientRecordWithInvalidArguments_VetID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_VET_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithInvalidArguments_VetID_Negative_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_VET_ID_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdatePatientRecordWithInvalidArguments_VetID_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_VET_ID_NULL_UPDATE_PATIENT_RECORD_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER
    @Test
    @DirtiesContext
    void user_UpdatePatientRecordWithValidArguments_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason").value(VALID_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate").value(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight").value(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized").value(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe").value(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis").value(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    void user_UpdatePatientRecordWithInvalidArguments_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND
                        .apply(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST.getId().toString())));
    }

    @Test
    void user_UpdatePatientRecordWithInvalidArguments_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST))
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
    void user_UpdatePatientRecordWithInvalidArguments_ID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NULL_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id es requerido"));
    }

    // Reason
    @Test
    void user_UpdatePatientRecordWithInvalidArguments_Reason_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_REASON_BLANK_UPDATE_PATIENT_RECORD_REQUEST))
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
    void user_UpdatePatientRecordWithInvalidArguments_Reason_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_REASON_EMPTY_UPDATE_PATIENT_RECORD_REQUEST))
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
    void user_UpdatePatientRecordWithInvalidArguments_Reason_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_REASON_NULL_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("reason"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La razón es requerida"));
    }

    // Entry At
    @Test
    @DirtiesContext
    void user_UpdatePatientRecordWithValidArguments_EntryAt_MinusYear_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @DirtiesContext
    void user_UpdatePatientRecordWithValidArguments_EntryAt_Today_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @DirtiesContext
    void user_UpdatePatientRecordWithValidArguments_EntryAt_Future_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    void user_UpdatePatientRecordWithInvalidArguments_EntryAt_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ENTRY_AT_NULL_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("entry_at"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La fecha de ingreso es requerida"));
    }

    // Physical Exam
    @Test
    @DirtiesContext // TODO: Physical should be null
    void user_UpdatePatientRecordWithValidArguments_PhysicalExam_Blank_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PHYSICAL_EXAM_BLANK_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_PHYSICAL_EXAM_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_PHYSICAL_EXAM_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam").doesNotExist(),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_PHYSICAL_EXAM_BLANK_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_PHYSICAL_EXAM_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_PHYSICAL_EXAM_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_PHYSICAL_EXAM_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_PHYSICAL_EXAM_BLANK_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_PHYSICAL_EXAM_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_PHYSICAL_EXAM_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @DirtiesContext // TODO: Physical should be null
    void user_UpdatePatientRecordWithValidArguments_PhysicalExam_Empty_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PHYSICAL_EXAM_EMPTY_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_PHYSICAL_EXAM_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam").doesNotExist(),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @DirtiesContext
    void user_UpdatePatientRecordWithValidArguments_PhysicalExam_Null_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    // Temperature In Celsius
    @Test
    void user_UpdatePatientRecordWithInvalidArguments_TemperatureInCelsius_TooBig_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_TEMPERATURE_IN_CELSIUS_TOO_BIG_UPDATE_PATIENT_RECORD_REQUEST))
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
    @DirtiesContext
    void user_UpdatePatientRecordWithValidArguments_TemperatureInCelsius_MaxValue_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST
                                        .getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST
                                        .getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(Double.parseDouble(
                                        VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST
                                                .getTemperatureInCelsius().toString())),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST
                                        .getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST
                                        .getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST
                                        .getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST
                                        .isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST
                                        .getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST
                                        .getDiagnosis()),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    void user_UpdatePatientRecordWithInvalidArguments_TemperatureInCelsius_Zero_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_TEMPERATURE_IN_CELSIUS_ZERO_UPDATE_PATIENT_RECORD_REQUEST))
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
    void user_UpdatePatientRecordWithInvalidArguments_TemperatureInCelsius_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_TEMPERATURE_IN_CELSIUS_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST))
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
    void user_UpdatePatientRecordWithInvalidArguments_TemperatureInCelsius_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_TEMPERATURE_IN_CELSIUS_NULL_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("temperature_in_celsius"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La temperatura es requerida"));
    }

    // Respiration Rate
    @Test
    void user_UpdatePatientRecordWithInvalidArguments_RespirationRate_Zero_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_RESPIRATION_RATE_ZERO_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("respitarion_rate"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La frecuencia respiratoria debe ser mayor a 0"));
    }

    @Test
    void user_UpdatePatientRecordWithInvalidArguments_RespirationRate_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_RESPIRATION_RATE_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("respitarion_rate"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La frecuencia respiratoria debe ser mayor a 0"));
    }

    @Test
    void user_UpdatePatientRecordWithInvalidArguments_RespirationRate_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_RESPIRATION_RATE_NULL_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("respitarion_rate"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La frecuencia respiratoria es requerida"));
    }

    // Heart Rate
    @Test
    void user_UpdatePatientRecordWithInvalidArguments_HeartRate_TooBig_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_HEART_RATE_TOO_BIG_UPDATE_PATIENT_RECORD_REQUEST))
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
    @DirtiesContext
    void user_UpdatePatientRecordWithValidArguments_HeartRate_MaxValue_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id")
                                .value(VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    void user_UpdatePatientRecordWithInvalidArguments_HeartRate_Zero_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_HEART_RATE_ZERO_UPDATE_PATIENT_RECORD_REQUEST))
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
    void user_UpdatePatientRecordWithInvalidArguments_HeartRate_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_HEART_RATE_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST))
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
    void user_UpdatePatientRecordWithInvalidArguments_HeartRate_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_HEART_RATE_NULL_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("heart_rate"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La frecuencia cardiaca es requerida"));
    }

    // Weight
    @Test
    void user_UpdatePatientRecordWithInvalidArguments_Weight_TooBig_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_WEIGHT_TOO_BIG_UPDATE_PATIENT_RECORD_REQUEST))
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
    @DirtiesContext
    void user_UpdatePatientRecordWithValidArguments_Weight_MaxValue_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id")
                                .value(VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    void user_UpdatePatientRecordWithInvalidArguments_Weight_Zero_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_WEIGHT_ZERO_UPDATE_PATIENT_RECORD_REQUEST))
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
    void user_UpdatePatientRecordWithInvalidArguments_Weight_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_WEIGHT_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST))
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
    void user_UpdatePatientRecordWithInvalidArguments_Weight_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_WEIGHT_NULL_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("weight"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El peso es requerido"));
    }

    // Sterilized

    // Recipe
    @Test
    @DirtiesContext // TODO: Recipe should be null
    void user_UpdatePatientRecordWithValidArguments_Recype_Blank_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_RECIPE_BLANK_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_RECIPE_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_RECIPE_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_RECIPE_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_RECIPE_BLANK_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_RECIPE_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_RECIPE_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_RECIPE_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_RECIPE_BLANK_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe").doesNotExist(),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_RECIPE_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @DirtiesContext // TODO: Recipe should be null
    void user_UpdatePatientRecordWithValidArguments_Recype_Empty_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_RECIPE_EMPTY_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_RECIPE_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_RECIPE_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_RECIPE_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_RECIPE_EMPTY_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_RECIPE_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_RECIPE_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_RECIPE_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_RECIPE_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe").doesNotExist(),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_RECIPE_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @DirtiesContext
    void user_UpdatePatientRecordWithValidArguments_Recype_Null_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_RECIPE_NULL_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_RECIPE_NULL_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_RECIPE_NULL_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_RECIPE_NULL_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_RECIPE_NULL_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_RECIPE_NULL_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_RECIPE_NULL_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_RECIPE_NULL_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_RECIPE_NULL_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe").doesNotExist(),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_RECIPE_NULL_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    // Diagnosis
    @Test
    @DirtiesContext // TODO: Diagnosis should be null
    void user_UpdatePatientRecordWithValidArguments_Diagnosis_Blank_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DIAGNOSIS_BLANK_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_DIAGNOSIS_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_DIAGNOSIS_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_DIAGNOSIS_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_DIAGNOSIS_BLANK_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_DIAGNOSIS_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_DIAGNOSIS_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_DIAGNOSIS_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_DIAGNOSIS_BLANK_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_DIAGNOSIS_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis").doesNotExist(),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @DirtiesContext // TODO: Diagnosis should be null
    void user_UpdatePatientRecordWithValidArguments_Diagnosis_Empty_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DIAGNOSIS_EMPTY_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_DIAGNOSIS_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_DIAGNOSIS_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_DIAGNOSIS_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_DIAGNOSIS_EMPTY_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_DIAGNOSIS_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_DIAGNOSIS_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_DIAGNOSIS_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_DIAGNOSIS_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_DIAGNOSIS_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis").doesNotExist(),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @DirtiesContext
    void user_UpdatePatientRecordWithValidArguments_Diagnosis_Null_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DIAGNOSIS_NULL_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_DIAGNOSIS_NULL_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_DIAGNOSIS_NULL_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_DIAGNOSIS_NULL_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_DIAGNOSIS_NULL_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_DIAGNOSIS_NULL_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_DIAGNOSIS_NULL_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_DIAGNOSIS_NULL_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_DIAGNOSIS_NULL_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_DIAGNOSIS_NULL_UPDATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis").doesNotExist(),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    // Vet ID
    @Test
    void user_UpdatePatientRecordWithInvalidArguments_VetID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_VET_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_VET_NOT_FOUND));
    }

    @Test
    void user_UpdatePatientRecordWithInvalidArguments_VetID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_VET_ID_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST))
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
    void user_UpdatePatientRecordWithInvalidArguments_VetID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_VET_ID_NULL_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("vet_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del veterinario es requerido"));
    }

    // Role: ADMIN
    @Test
    @DirtiesContext
    void admin_UpdatePatientRecordWithValidArguments_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
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
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_UPDATE_PATIENT_RECORD_REQUEST.getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate").value(VALID_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight").value(VALID_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized").value(VALID_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe").value(VALID_UPDATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis").value(VALID_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    void admin_UpdatePatientRecordWithInvalidArguments_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND
                        .apply(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST.getId().toString())));
    }

    @Test
    void admin_UpdatePatientRecordWithInvalidArguments_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST))
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
    void admin_UpdatePatientRecordWithInvalidArguments_ID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NULL_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id es requerido"));
    }

    // Reason
    @Test
    void admin_UpdatePatientRecordWithInvalidArguments_Reason_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_REASON_BLANK_UPDATE_PATIENT_RECORD_REQUEST))
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
    void admin_UpdatePatientRecordWithInvalidArguments_Reason_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_REASON_EMPTY_UPDATE_PATIENT_RECORD_REQUEST))
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
    void admin_UpdatePatientRecordWithInvalidArguments_Reason_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_REASON_NULL_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("reason"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La razón es requerida"));
    }

    // Entry At
    @Test
    @DirtiesContext
    void admin_UpdatePatientRecordWithValidArguments_EntryAt_MinusYear_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_ENTRY_AT_MINUS_YEAR_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @DirtiesContext
    void admin_UpdatePatientRecordWithValidArguments_EntryAt_Today_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_ENTRY_AT_TODAY_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @DirtiesContext
    void admin_UpdatePatientRecordWithValidArguments_EntryAt_Future_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_ENTRY_AT_FUTURE_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    void admin_UpdatePatientRecordWithInvalidArguments_EntryAt_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ENTRY_AT_NULL_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("entry_at"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La fecha de ingreso es requerida"));
    }

    // Physical Exam
    @Test
    @DirtiesContext // TODO: Physical should be null
    void admin_UpdatePatientRecordWithValidArguments_PhysicalExam_Blank_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PHYSICAL_EXAM_BLANK_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_PHYSICAL_EXAM_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_PHYSICAL_EXAM_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam").doesNotExist(),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_PHYSICAL_EXAM_BLANK_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_PHYSICAL_EXAM_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_PHYSICAL_EXAM_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_PHYSICAL_EXAM_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_PHYSICAL_EXAM_BLANK_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_PHYSICAL_EXAM_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_PHYSICAL_EXAM_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @DirtiesContext // TODO: Physical should be null
    void admin_UpdatePatientRecordWithValidArguments_PhysicalExam_Empty_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PHYSICAL_EXAM_EMPTY_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_PHYSICAL_EXAM_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam").doesNotExist(),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_PHYSICAL_EXAM_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @DirtiesContext
    void admin_UpdatePatientRecordWithValidArguments_PhysicalExam_Null_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_PHYSICAL_EXAM_NULL_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    // Temperature In Celsius
    @Test
    void admin_UpdatePatientRecordWithInvalidArguments_TemperatureInCelsius_TooBig_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_TEMPERATURE_IN_CELSIUS_TOO_BIG_UPDATE_PATIENT_RECORD_REQUEST))
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
    @DirtiesContext
    void admin_UpdatePatientRecordWithValidArguments_TemperatureInCelsius_MaxValue_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST
                                        .getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST
                                        .getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(Double.parseDouble(
                                        VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST
                                                .getTemperatureInCelsius().toString())),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST
                                        .getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST
                                        .getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST
                                        .getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST
                                        .isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST
                                        .getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_TEMPERATURE_IN_CELSIUS_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST
                                        .getDiagnosis()),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    void admin_UpdatePatientRecordWithInvalidArguments_TemperatureInCelsius_Zero_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_TEMPERATURE_IN_CELSIUS_ZERO_UPDATE_PATIENT_RECORD_REQUEST))
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
    void admin_UpdatePatientRecordWithInvalidArguments_TemperatureInCelsius_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_TEMPERATURE_IN_CELSIUS_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST))
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
    void admin_UpdatePatientRecordWithInvalidArguments_TemperatureInCelsius_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_TEMPERATURE_IN_CELSIUS_NULL_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("temperature_in_celsius"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La temperatura es requerida"));
    }

    // Respiration Rate
    @Test
    void admin_UpdatePatientRecordWithInvalidArguments_RespirationRate_Zero_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_RESPIRATION_RATE_ZERO_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("respitarion_rate"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La frecuencia respiratoria debe ser mayor a 0"));
    }

    @Test
    void admin_UpdatePatientRecordWithInvalidArguments_RespirationRate_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_RESPIRATION_RATE_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("respitarion_rate"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La frecuencia respiratoria debe ser mayor a 0"));
    }

    @Test
    void admin_UpdatePatientRecordWithInvalidArguments_RespirationRate_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_RESPIRATION_RATE_NULL_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("respitarion_rate"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La frecuencia respiratoria es requerida"));
    }

    // Heart Rate
    @Test
    void admin_UpdatePatientRecordWithInvalidArguments_HeartRate_TooBig_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_HEART_RATE_TOO_BIG_UPDATE_PATIENT_RECORD_REQUEST))
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
    @DirtiesContext
    void admin_UpdatePatientRecordWithValidArguments_HeartRate_MaxValue_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id")
                                .value(VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_HEART_RATE_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    void admin_UpdatePatientRecordWithInvalidArguments_HeartRate_Zero_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_HEART_RATE_ZERO_UPDATE_PATIENT_RECORD_REQUEST))
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
    void admin_UpdatePatientRecordWithInvalidArguments_HeartRate_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_HEART_RATE_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST))
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
    void admin_UpdatePatientRecordWithInvalidArguments_HeartRate_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_HEART_RATE_NULL_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("heart_rate"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La frecuencia cardiaca es requerida"));
    }

    // Weight
    @Test
    void admin_UpdatePatientRecordWithInvalidArguments_Weight_TooBig_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_WEIGHT_TOO_BIG_UPDATE_PATIENT_RECORD_REQUEST))
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
    @DirtiesContext
    void admin_UpdatePatientRecordWithValidArguments_Weight_MaxValue_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id")
                                .value(VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_WEIGHT_MAX_VALUE_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    void admin_UpdatePatientRecordWithInvalidArguments_Weight_Zero_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_WEIGHT_ZERO_UPDATE_PATIENT_RECORD_REQUEST))
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
    void admin_UpdatePatientRecordWithInvalidArguments_Weight_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_WEIGHT_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST))
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
    void admin_UpdatePatientRecordWithInvalidArguments_Weight_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_WEIGHT_NULL_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("weight"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El peso es requerido"));
    }

    // Sterilized

    // Recipe
    @Test
    @DirtiesContext // TODO: Recipe should be null
    void admin_UpdatePatientRecordWithValidArguments_Recype_Blank_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_RECIPE_BLANK_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_RECIPE_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_RECIPE_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_RECIPE_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_RECIPE_BLANK_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_RECIPE_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_RECIPE_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_RECIPE_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_RECIPE_BLANK_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe").doesNotExist(),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_RECIPE_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @DirtiesContext // TODO: Recipe should be null
    void admin_UpdatePatientRecordWithValidArguments_Recype_Empty_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_RECIPE_EMPTY_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_RECIPE_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_RECIPE_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_RECIPE_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_RECIPE_EMPTY_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_RECIPE_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_RECIPE_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_RECIPE_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_RECIPE_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe").doesNotExist(),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_RECIPE_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @DirtiesContext
    void admin_UpdatePatientRecordWithValidArguments_Recype_Null_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_RECIPE_NULL_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_RECIPE_NULL_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_RECIPE_NULL_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_RECIPE_NULL_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_RECIPE_NULL_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_RECIPE_NULL_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_RECIPE_NULL_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_RECIPE_NULL_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_RECIPE_NULL_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe").doesNotExist(),
                        jsonPath("$.content.diagnosis")
                                .value(VALID_RECIPE_NULL_UPDATE_PATIENT_RECORD_REQUEST.getDiagnosis()),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    // Diagnosis
    @Test
    @DirtiesContext // TODO: Diagnosis should be null
    void admin_UpdatePatientRecordWithValidArguments_Diagnosis_Blank_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DIAGNOSIS_BLANK_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_DIAGNOSIS_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_DIAGNOSIS_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_DIAGNOSIS_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_DIAGNOSIS_BLANK_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_DIAGNOSIS_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_DIAGNOSIS_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_DIAGNOSIS_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_DIAGNOSIS_BLANK_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_DIAGNOSIS_BLANK_UPDATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis").doesNotExist(),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @DirtiesContext // TODO: Diagnosis should be null
    void admin_UpdatePatientRecordWithValidArguments_Diagnosis_Empty_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DIAGNOSIS_EMPTY_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_DIAGNOSIS_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_DIAGNOSIS_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_DIAGNOSIS_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_DIAGNOSIS_EMPTY_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_DIAGNOSIS_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_DIAGNOSIS_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_DIAGNOSIS_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_DIAGNOSIS_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_DIAGNOSIS_EMPTY_UPDATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis").doesNotExist(),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    @Test
    @DirtiesContext
    void admin_UpdatePatientRecordWithValidArguments_Diagnosis_Null_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DIAGNOSIS_NULL_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_DIAGNOSIS_NULL_UPDATE_PATIENT_RECORD_REQUEST.getId()),
                        jsonPath("$.content.reason")
                                .value(VALID_DIAGNOSIS_NULL_UPDATE_PATIENT_RECORD_REQUEST.getReason()),
                        jsonPath("$.content.physical_exam")
                                .value(VALID_DIAGNOSIS_NULL_UPDATE_PATIENT_RECORD_REQUEST.getPhysicalExam()),
                        jsonPath("$.content.entry_at").isString(),
                        jsonPath("$.content.temperature_in_celsius")
                                .value(VALID_DIAGNOSIS_NULL_UPDATE_PATIENT_RECORD_REQUEST
                                        .getTemperatureInCelsius()),
                        jsonPath("$.content.respitarion_rate")
                                .value(VALID_DIAGNOSIS_NULL_UPDATE_PATIENT_RECORD_REQUEST.getRespitarionRate()),
                        jsonPath("$.content.heart_rate")
                                .value(VALID_DIAGNOSIS_NULL_UPDATE_PATIENT_RECORD_REQUEST.getHeartRate()),
                        jsonPath("$.content.weight")
                                .value(VALID_DIAGNOSIS_NULL_UPDATE_PATIENT_RECORD_REQUEST.getWeight()),
                        jsonPath("$.content.sterilized")
                                .value(VALID_DIAGNOSIS_NULL_UPDATE_PATIENT_RECORD_REQUEST.isSterilized()),
                        jsonPath("$.content.recipe")
                                .value(VALID_DIAGNOSIS_NULL_UPDATE_PATIENT_RECORD_REQUEST.getRecipe()),
                        jsonPath("$.content.diagnosis").doesNotExist(),
                        jsonPath("$.content.vet.id").value(ADMIN_DTO.getId()),
                        jsonPath("$.content.treatments").isArray());
    }

    // Vet ID
    @Test
    void admin_UpdatePatientRecordWithInvalidArguments_VetID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_VET_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_VET_NOT_FOUND));
    }

    @Test
    void admin_UpdatePatientRecordWithInvalidArguments_VetID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_VET_ID_NEGATIVE_UPDATE_PATIENT_RECORD_REQUEST))
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
    void admin_UpdatePatientRecordWithInvalidArguments_VetID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_VET_ID_NULL_UPDATE_PATIENT_RECORD_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("vet_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del veterinario es requerido"));
    }

}
