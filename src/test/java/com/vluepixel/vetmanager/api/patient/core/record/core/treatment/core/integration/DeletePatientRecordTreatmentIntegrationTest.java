package com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_USER_JWT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the delete patient record treatment use case.
 */
public class DeletePatientRecordTreatmentIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Tratamiento eliminado exitosamente";
    private static final Function<String, String> MESSAGE_PATIENT_NOT_FOUND = parameter -> String
            .format("Paciente con id %s no encontrado(a)", parameter);
    private static Function<String, String> MESSAGE_TREATMENT_NOT_FOUND = parameter -> String
            .format("Tratamiento con id %s no encontrado(a)", parameter);
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
    void admin_DeletePatientRecordTreatmentWithValidParams_Ok() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/record/{record_id}/treatment/{id}", 1, 1, 1)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(MESSAGE_OK));
    }

    // Patient ID
    @Test
    void admin_DeletePatientRecordTreatmentWithInvalidParams_PatientID_NotFound_NotFound() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/record/{record_id}/treatment/{id}", 10, 1, 1)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_PATIENT_NOT_FOUND.apply("10")));
    }

    @Test
    void admin_DeletePatientRecordTreatmentWithInvalidParams_PatientID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/record/{record_id}/treatment/{id}", -10, 1, 1)
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
    void admin_DeletePatientRecordTreatmentWithInvalidParams_PatientID_NotNumber_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/record/{record_id}/treatment/{id}", "abcde", 1, 1)
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
    void admin_DeletePatientRecordTreatmentWithInvalidParams_RecordID_NotFound_NotFound() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/record/{record_id}/treatment/{id}", 1, 10, 1)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_PATIENT_NOT_FOUND.apply("1")));
    }

    @Test
    void admin_DeletePatientRecordTreatmentWithInvalidParams_RecordID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/record/{record_id}/treatment/{id}", 1, -10, 1)
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
    void admin_DeletePatientRecordTreatmentWithInvalidParams_RecordID_NotNumber_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/record/{record_id}/treatment/{id}", 1, "abcde", 1)
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

    // Treatment ID
    @Test
    void admin_DeletePatientRecordTreatmentWithInvalidParams_TreatmentID_NotFound_NotFound() throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/record/{record_id}/treatment/{id}", 1, 1, 10)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_TREATMENT_NOT_FOUND.apply("10")));
    }

    @Test
    void admin_DeletePatientRecordTreatmentWithInvalidParams_TreatmentID_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/record/{record_id}/treatment/{id}", 1, 1, -10)
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
    void admin_DeletePatientRecordTreatmentWithInvalidParams_TreatmentID_NotNumber_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(delete("/patient/{patient_id}/record/{record_id}/treatment/{id}", 1, 1, "abcde")
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
}
