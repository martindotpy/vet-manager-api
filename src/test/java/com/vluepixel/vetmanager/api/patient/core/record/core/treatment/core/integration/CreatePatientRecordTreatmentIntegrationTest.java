package com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.data.CreatePatientRecordTreatmentDataProvider.INVALID_DESCRIPTION_BLANK_CREATE_PATIENT_RECORD_TREATMENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.data.CreatePatientRecordTreatmentDataProvider.INVALID_DESCRIPTION_EMPTY_CREATE_PATIENT_RECORD_TREATMENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.data.CreatePatientRecordTreatmentDataProvider.INVALID_DESCRIPTION_NULL_CREATE_PATIENT_RECORD_TREATMENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.data.CreatePatientRecordTreatmentDataProvider.INVALID_MEDICAL_RECORD_ID_NEGATIVE_CREATE_PATIENT_RECORD_TREATMENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.data.CreatePatientRecordTreatmentDataProvider.INVALID_MEDICAL_RECORD_ID_NOT_FOUND_CREATE_PATIENT_RECORD_TREATMENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.data.CreatePatientRecordTreatmentDataProvider.INVALID_MEDICAL_RECORD_ID_NULL_CREATE_PATIENT_RECORD_TREATMENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.data.CreatePatientRecordTreatmentDataProvider.INVALID_ORDER_NEGATIVE_CREATE_PATIENT_RECORD_TREATMENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.data.CreatePatientRecordTreatmentDataProvider.INVALID_ORDER_NULL_CREATE_PATIENT_RECORD_TREATMENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.data.CreatePatientRecordTreatmentDataProvider.INVALID_ORDER_ZERO_CREATE_PATIENT_RECORD_TREATMENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.record.core.treatment.core.data.CreatePatientRecordTreatmentDataProvider.VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.function.Function;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the create patient record treatment use case.
 */
public class CreatePatientRecordTreatmentIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Tratamiento creado exitosamente";
    private static final Function<String, String> MESSAGE_NOT_FOUND = parameter -> String.format("a", parameter);
    private static final String MESSAGE_RECORD_NOT_FOUND = "Registro médico no encontrado(a)";
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER

    // Role: ADMIN
    @Test
    @Order(1)
    @DirtiesContext
    void admin_CreatePatientRecordTreatmentWithValidArguments_Ok() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record/{record_id}/treatment", 1, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(4),
                        jsonPath("$.content.description")
                                .value(VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST.getDescription()),
                        jsonPath("$.content.order").value(VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST.getOrder()));
    }

    // Patient ID
    @Test
    void admin_UpdatePatientRecordTreatmentWithInvalidArguments_PatientID_NotFound() throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record/{record_id}/treatment", 10, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND.apply("1")));
    }

    @Test
    void admin_UpdatePatientRecordTreatmentWithInvalidArguments_PatientID_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record/{record_id}/treatment", -10, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST))
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
        mockMvc.perform(post("/patient/{patient_id}/record/{record_id}/treatment", "abcde", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST))
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

    // Description
    @Test
    void admin_UpdatePatientRecordTreatmentWithInvalidArguments_Description_Blank_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record/{record_id}/treatment", 1, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DESCRIPTION_BLANK_CREATE_PATIENT_RECORD_TREATMENT_REQUEST))
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
        mockMvc.perform(post("/patient/{patient_id}/record/{record_id}/treatment", 1, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DESCRIPTION_EMPTY_CREATE_PATIENT_RECORD_TREATMENT_REQUEST))
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
        mockMvc.perform(post("/patient/{patient_id}/record/{record_id}/treatment", 1, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DESCRIPTION_NULL_CREATE_PATIENT_RECORD_TREATMENT_REQUEST))
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
        mockMvc.perform(post("/patient/{patient_id}/record/{record_id}/treatment", 1, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_ORDER_NEGATIVE_CREATE_PATIENT_RECORD_TREATMENT_REQUEST))
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
        mockMvc.perform(post("/patient/{patient_id}/record/{record_id}/treatment", 1, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_ORDER_ZERO_CREATE_PATIENT_RECORD_TREATMENT_REQUEST))
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
        mockMvc.perform(post("/patient/{patient_id}/record/{record_id}/treatment", 1, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_ORDER_NULL_CREATE_PATIENT_RECORD_TREATMENT_REQUEST))
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

    // Record ID
    @Test
    void admin_UpdatePatientRecordTreatmentWithInvalidArguments_RecordID_NotFound()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record/{record_id}/treatment", 1,
                INVALID_MEDICAL_RECORD_ID_NOT_FOUND_CREATE_PATIENT_RECORD_TREATMENT_REQUEST.getMedicalRecordId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        INVALID_MEDICAL_RECORD_ID_NOT_FOUND_CREATE_PATIENT_RECORD_TREATMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_RECORD_NOT_FOUND));
    }

    @Test
    void admin_UpdatePatientRecordTreatmentWithInvalidArguments_RecordID_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record/{record_id}/treatment", 1,
                INVALID_MEDICAL_RECORD_ID_NEGATIVE_CREATE_PATIENT_RECORD_TREATMENT_REQUEST.getMedicalRecordId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        INVALID_MEDICAL_RECORD_ID_NEGATIVE_CREATE_PATIENT_RECORD_TREATMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(2),
                        jsonPath("$.details[0].field").value("path.record_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id del historial médico debe ser mayor a 0"),
                        jsonPath("$.details[1].field").value("medical_record_id"),
                        jsonPath("$.details[1].messages.length()").value(1),
                        jsonPath("$.details[1].messages").value("El id del registro médico debe ser mayor a 0"));
    }

    @Test
    void admin_UpdatePatientRecordTreatmentWithInvalidArguments_RecordID_Null_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record/{record_id}/treatment", 1,
                INVALID_MEDICAL_RECORD_ID_NEGATIVE_CREATE_PATIENT_RECORD_TREATMENT_REQUEST.getMedicalRecordId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        INVALID_MEDICAL_RECORD_ID_NULL_CREATE_PATIENT_RECORD_TREATMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(2),
                        jsonPath("$.details[0].field").value("path.record_id"),
                        jsonPath("$.details[0].messages.length()").value(2),
                        jsonPath("$.details[0].messages")
                                .value(containsInAnyOrder("El id del historial médico debe ser mayor a 0",
                                        "El id del historial médico no coincide con el id del registro médico")),
                        jsonPath("$.details[1].field").value("medical_record_id"),
                        jsonPath("$.details[1].messages.length()").value(1),
                        jsonPath("$.details[1].messages")
                                .value("El id del registro médico es requerido"));
    }

    @Test
    void admin_UpdatePatientRecordTreatmentWithInvalidArguments_RecordID_NotNumber_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/patient/{patient_id}/record/{record_id}/treatment", 1, "abcde")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_PATIENT_RECORD_TREATMENT_REQUEST))
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
