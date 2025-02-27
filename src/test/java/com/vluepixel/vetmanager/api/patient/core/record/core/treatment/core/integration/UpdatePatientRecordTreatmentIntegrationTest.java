package com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.data.UpdatePatientRecordTreatmentDataProvider.INVALID_DESCRIPTION_BLANK_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.data.UpdatePatientRecordTreatmentDataProvider.INVALID_DESCRIPTION_EMPTY_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.data.UpdatePatientRecordTreatmentDataProvider.INVALID_DESCRIPTION_NULL_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.data.UpdatePatientRecordTreatmentDataProvider.INVALID_ID_NEGATIVE_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.data.UpdatePatientRecordTreatmentDataProvider.INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.data.UpdatePatientRecordTreatmentDataProvider.INVALID_ID_NULL_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.data.UpdatePatientRecordTreatmentDataProvider.INVALID_MEDICAL_RECORD_ID_NEGATIVE_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.data.UpdatePatientRecordTreatmentDataProvider.INVALID_MEDICAL_RECORD_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.data.UpdatePatientRecordTreatmentDataProvider.INVALID_MEDICAL_RECORD_ID_NULL_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.data.UpdatePatientRecordTreatmentDataProvider.INVALID_ORDER_NEGATIVE_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.data.UpdatePatientRecordTreatmentDataProvider.INVALID_ORDER_NULL_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.data.UpdatePatientRecordTreatmentDataProvider.INVALID_ORDER_ZERO_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.data.UpdatePatientRecordTreatmentDataProvider.VALID_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the update patient record treatment use case.
 */
public class UpdatePatientRecordTreatmentIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Tratamiento actualizado exitosamente";
    private static final Function<String, String> MESSAGE_NOT_FOUND = parameter -> String
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
    void admin_UpdatePatientRecordTreatmentWithValidArguments_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record/{record_id}/treatment", 1, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST.getId()),
                        jsonPath("$.content.description")
                                .value(VALID_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST.getDescription()),
                        jsonPath("$.content.order").value(VALID_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST.getOrder()));
    }

    // Patient ID
    @Test
    void admin_UpdatePatientRecordTreatmentWithInvalidArguments_PatientID_NotFound() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record/{record_id}/treatment", 10, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND.apply("1")));
    }

    @Test
    void admin_UpdatePatientRecordTreatmentWithInvalidArguments_PatientID_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record/{record_id}/treatment", -10, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST))
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
    void admin_UpdatePatientRecordTreatmentWithInvalidArguments_PatientID_NotNumber_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record/{record_id}/treatment", "abcde", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST))
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
    @DirtiesContext
    void admin_UpdatePatientRecordTreatmentWithInvalidArguments_RecordID_NotFound() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record/{record_id}/treatment", 1, 10)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND
                        .apply(VALID_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST.getId().toString())));
    }

    @Test
    void admin_UpdatePatientRecordTreatmentWithInvalidArguments_RecordID_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record/{record_id}/treatment", 1, -10)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST))
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
    void admin_UpdatePatientRecordTreatmentWithInvalidArguments_RecordID_NotNumber_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record/{record_id}/treatment", 1, "abcde")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST))
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

    // Treatment ID
    @Test
    void admin_UpdatePatientRecordTreatmentWithInvalidArguments_TreatmentID_NotFound() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record/{record_id}/treatment", 1, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND
                        .apply(INVALID_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST.getId().toString())));
    }

    @Test
    void admin_UpdatePatientRecordTreatmentWithInvalidArguments_TreatmentID_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record/{record_id}/treatment", 1, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NEGATIVE_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id debe ser mayor a 0"));
    }

    @Test
    void admin_UpdatePatientRecordTreatmentWithInvalidArguments_TreatmentID_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record/{record_id}/treatment", 1, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NULL_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id es requerido"));
    }

    // Description
    @Test
    void admin_UpdatePatientRecordTreatmentWithInvalidArguments_Description_Blank_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record/{record_id}/treatment", 1, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DESCRIPTION_BLANK_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("description"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("La descripción es requerida"));
    }

    @Test
    void admin_UpdatePatientRecordTreatmentWithInvalidArguments_Description_Empty_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record/{record_id}/treatment", 1, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DESCRIPTION_EMPTY_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("description"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("La descripción es requerida"));
    }

    @Test
    void admin_UpdatePatientRecordTreatmentWithInvalidArguments_Description_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record/{record_id}/treatment", 1, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DESCRIPTION_NULL_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("description"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("La descripción es requerida"));
    }

    // Order
    @Test
    void admin_UpdatePatientRecordTreatmentWithInvalidArguments_Order_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record/{record_id}/treatment", 1, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_ORDER_NEGATIVE_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("order"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El orden debe ser mayor a 0"));
    }

    @Test
    void admin_UpdatePatientRecordTreatmentWithInvalidArguments_Order_Zero_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record/{record_id}/treatment", 1, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_ORDER_ZERO_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("order"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El orden debe ser mayor a 0"));
    }

    @Test
    void admin_UpdatePatientRecordTreatmentWithInvalidArguments_Order_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record/{record_id}/treatment", 1, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_ORDER_NULL_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("order"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El orden es requerido"));
    }

    // Medical Record ID
    @Test
    void admin_UpdatePatientRecordTreatmentWithInvalidArguments_MedicalRecordID_NotFound() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record/{record_id}/treatment", 1, 10)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        INVALID_MEDICAL_RECORD_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND
                        .apply(INVALID_MEDICAL_RECORD_ID_NOT_FOUND_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST.getId()
                                .toString())));
    }

    @Test
    void admin_UpdatePatientRecordTreatmentWithInvalidArguments_MedicalRecordID_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record/{record_id}/treatment", 1, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        INVALID_MEDICAL_RECORD_ID_NEGATIVE_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("medical_record_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id del registro médico debe ser mayor a 0"));
    }

    @Test
    void admin_UpdatePatientRecordTreatmentWithInvalidArguments_MedicalRecordID_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/record/{record_id}/treatment", 1, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        INVALID_MEDICAL_RECORD_ID_NULL_UPDATE_PATIENT_RECORD_TREATMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("medical_record_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id del registro médico es requerido"));
    }
}
