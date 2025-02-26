package com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_USER_JWT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the get patient record treatment use case.
 */
public class GetPatientRecordTreatmentIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Tratamientos del paciente encontrados exitosamente";
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    @Test
    void noUser_GetPatientRecordTreatmentWithValidParams_Forbidden() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/record/{record_id}/treatment", 1, 1))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Patient ID
    @Test
    void noUser_GetPatientRecordTreatmentWithInvalidParams_PatientID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/record/{record_id}/treatment", 10, 1))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetPatientRecordTreatmentWithInvalidParams_PatientID_Negative_Forbidden() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/record/{record_id}/treatment", -10, 1))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetPatientRecordTreatmentWithInvalidParams_PatientID_NotNumber_Forbidden() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/record/{record_id}/treatment", "abcde", 1))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Record ID
    @Test
    void noUser_GetPatientRecordTreatmentWithInvalidParams_RecordID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/record/{record_id}/treatment", 1, 10))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetPatientRecordTreatmentWithInvalidParams_RecordID_Negative_Forbidden() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/record/{record_id}/treatment", 1, -10))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetPatientRecordTreatmentWithInvalidParams_RecordID_NotNumber_Forbidden() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/record/{record_id}/treatment", 1, "abcde"))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER
    @Test
    void user_GetPatientRecordTreatmentWithValidParams_Ok() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/record/{record_id}/treatment", 1, 1)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.length()").value(2));
    }

    // Patient ID
    @Test
    void user_GetPatientRecordTreatmentWithInvalidParams_PatientID_NotFound_Ok() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/record/{record_id}/treatment", 10, 1)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.length()").value(0));
    }

    @Test
    void user_GetPatientRecordTreatmentWithInvalidParams_PatientID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/record/{record_id}/treatment", -10, 1)
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
    void user_GetPatientRecordTreatmentWithInvalidParams_PatientID_NotNumber_UnprocessableEntity() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/record/{record_id}/treatment", "abcde", 1)
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

    // Record ID
    @Test
    void user_GetPatientRecordTreatmentWithInvalidParams_RecordID_NotFound_Ok() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/record/{record_id}/treatment", 1, 10)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.length()").value(0));
    }

    @Test
    void user_GetPatientRecordTreatmentWithInvalidParams_RecordID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/record/{record_id}/treatment", 1, -10)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("path.record_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id del historial médico debe ser mayor a 0"));
    }

    @Test
    void user_GetPatientRecordTreatmentWithInvalidParams_RecordID_NotNumber_UnprocessableEntity() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/record/{record_id}/treatment", 1, "abcde")
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("path.record_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("Valor numérico inválido"));
    }

    // Role: ADMIN
    @Test
    void admin_GetPatientRecordTreatmentWithValidParams_Ok() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/record/{record_id}/treatment", 1, 1)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.length()").value(2));
    }

    // Patient ID
    @Test
    void admin_GetPatientRecordTreatmentWithInvalidParams_PatientID_NotFound_Ok() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/record/{record_id}/treatment", 10, 1)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.length()").value(0));
    }

    @Test
    void admin_GetPatientRecordTreatmentWithInvalidParams_PatientID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/record/{record_id}/treatment", -10, 1)
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
    void admin_GetPatientRecordTreatmentWithInvalidParams_PatientID_NotNumber_UnprocessableEntity() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/record/{record_id}/treatment", "abcde", 1)
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

    // Record ID
    @Test
    void admin_GetPatientRecordTreatmentWithInvalidParams_RecordID_NotFound_Ok() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/record/{record_id}/treatment", 1, 10)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.length()").value(0));
    }

    @Test
    void admin_GetPatientRecordTreatmentWithInvalidParams_RecordID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/record/{record_id}/treatment", 1, -10)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("path.record_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id del historial médico debe ser mayor a 0"));
    }

    @Test
    void admin_GetPatientRecordTreatmentWithInvalidParams_RecordID_NotNumber_UnprocessableEntity() throws Exception {
        mockMvc.perform(get("/patient/{patient_id}/record/{record_id}/treatment", 1, "abcde")
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("path.record_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("Valor numérico inválido"));
    }
}
