package com.vluepixel.vetmanager.api.appointment.core.integration;

import static com.vluepixel.vetmanager.api.appointment.core.data.CreateAppointmentDataProvider.INVALID_DESCRIPTION_BLANK_CREATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.CreateAppointmentDataProvider.INVALID_DESCRIPTION_EMPTY_CREATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.CreateAppointmentDataProvider.INVALID_DESCRIPTION_NULL_CREATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.CreateAppointmentDataProvider.INVALID_DETAILS_DURATION_IN_MINUTES_NEGATIVE_CREATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.CreateAppointmentDataProvider.INVALID_DETAILS_DURATION_IN_MINUTES_TOO_BIG_CREATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.CreateAppointmentDataProvider.INVALID_DETAILS_DURATION_IN_MINUTES_ZERO_CREATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.CreateAppointmentDataProvider.INVALID_DETAILS_PRICE_NEGATIVE_CREATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.CreateAppointmentDataProvider.INVALID_DETAILS_PRICE_NULL_CREATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.CreateAppointmentDataProvider.INVALID_DETAILS_PRICE_TOO_BIG_CREATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.CreateAppointmentDataProvider.INVALID_DETAILS_TYPE_ID_NEGATIVE_CREATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.CreateAppointmentDataProvider.INVALID_DETAILS_TYPE_ID_NOT_FOUND_CREATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.CreateAppointmentDataProvider.INVALID_DETAILS_TYPE_ID_NULL_CREATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.CreateAppointmentDataProvider.INVALID_PATIENT_ID_NEGATIVE_CREATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.CreateAppointmentDataProvider.INVALID_PATIENT_ID_NOT_FOUND_CREATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.CreateAppointmentDataProvider.INVALID_PATIENT_ID_NULL_CREATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.CreateAppointmentDataProvider.INVALID_START_AT_MINUS_YEAR_CREATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.CreateAppointmentDataProvider.INVALID_START_AT_NULL_CREATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.CreateAppointmentDataProvider.VALID_CREATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.CreateAppointmentDataProvider.VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_CREATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.CreateAppointmentDataProvider.VALID_DETAILS_PRICE_MAX_VALUE_CREATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.CreateAppointmentDataProvider.VALID_START_AT_FUTURE_CREATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.CreateAppointmentDataProvider.VALID_START_AT_TODAY_CREATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_USER_JWT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the create appointment use case.
 */
public class CreateAppointmentIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Cita creada exitosamente";
    private static final String MESSAGE_APPOINTMET_TYPE_NOT_FOUND = "Tipo de cita no encontrado(a)";
    private static final String MESSAGE_PATIENT_NOT_FOUND = "Paciente no encontrado(a)";
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    @Test
    void noUser_CreateAppointmentWithValidArguments_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_APPOINTMENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Start At
    @Test
    void noUser_CreateAppointmentWithValidArguments_StartAt_MinusYear_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_START_AT_MINUS_YEAR_CREATE_APPOINTMENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateAppointmentWithValidArguments_StartAt_Today_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_START_AT_TODAY_CREATE_APPOINTMENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateAppointmentWithInvalidArguments_StartAt_Future_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_START_AT_FUTURE_CREATE_APPOINTMENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateAppointmentWithInvalidArguments_StartAt_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_START_AT_NULL_CREATE_APPOINTMENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Description
    @Test
    void noUser_CreateAppointmentWithValidArguments_Description_Blank_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_BLANK_CREATE_APPOINTMENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateAppointmentWithValidArguments_Description_Empty_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_EMPTY_CREATE_APPOINTMENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateAppointmentWithValidArguments_Description_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_NULL_CREATE_APPOINTMENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Details
    // Duration In Minutes
    @Test
    void noUser_CreateAppointmentWithInvalidArguments_DurationInMinutes_TooBig_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_DURATION_IN_MINUTES_TOO_BIG_CREATE_APPOINTMENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateAppointmentWithValidArguments_DurationInMinutes_MaxValue_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_CREATE_APPOINTMENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateAppointmentWithInvalidArguments_DurationInMinutes_Zero_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_DURATION_IN_MINUTES_ZERO_CREATE_APPOINTMENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateAppointmentWithInvalidArguments_DurationInMinutes_Negative_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_DURATION_IN_MINUTES_NEGATIVE_CREATE_APPOINTMENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Price
    @Test
    void noUser_CreateAppointmentWithInvalidArguments_Price_TooBig_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_PRICE_TOO_BIG_CREATE_APPOINTMENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateAppointmentWithValidArguments_Price_MaxValue_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_DETAILS_PRICE_MAX_VALUE_CREATE_APPOINTMENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateAppointmentWithInvalidArguments_Price_Negative_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_PRICE_NEGATIVE_CREATE_APPOINTMENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateAppointmentWithInvalidArguments_Price_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_PRICE_NULL_CREATE_APPOINTMENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Type ID
    @Test
    void noUser_CreateAppointmentWithInvalidArguments_TypeID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_TYPE_ID_NOT_FOUND_CREATE_APPOINTMENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateAppointmentWithInvalidArguments_TypeID_Negative_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_TYPE_ID_NEGATIVE_CREATE_APPOINTMENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateAppointmentWithInvalidArguments_TypeID_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_TYPE_ID_NULL_CREATE_APPOINTMENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Patient ID
    @Test
    void noUser_CreateAppointmentWithInvalidArguments_PatientID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PATIENT_ID_NOT_FOUND_CREATE_APPOINTMENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateAppointmentWithInvalidArguments_PatientID_Negative_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PATIENT_ID_NEGATIVE_CREATE_APPOINTMENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateAppointmentWithInvalidArguments_PatientID_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PATIENT_ID_NULL_CREATE_APPOINTMENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpectAll(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER
    @Test
    @Order(1)
    @DirtiesContext
    void user_CreateAppointmentWithValidArguments_Ok() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.start_at").isString(),
                        jsonPath("$.content.description").value(VALID_CREATE_APPOINTMENT_REQUEST.getDescription()),
                        jsonPath("$.content.details[0].id")
                                .value(3),
                        jsonPath("$.content.details[0].duration_in_minutes")
                                .value(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getDurationInMinutes()),
                        jsonPath("$.content.details[0].price")
                                .value(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getPrice()),
                        jsonPath("$.content.details[0].appointment_type.id")
                                .value(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getAppointmentTypeId()),
                        jsonPath("$.content.patient.id").value(VALID_CREATE_APPOINTMENT_REQUEST.getPatientId()));
    }

    // Start At
    @Test
    void user_CreateAppointmentWithValidArguments_StartAt_MinusYear_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_START_AT_MINUS_YEAR_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("start_at"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("La fecha de inicio debe ser mayor a la fecha actual"));
    }

    @Test
    @Order(2)
    @DirtiesContext
    void user_CreateAppointmentWithValidArguments_StartAt_Today_Ok() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_START_AT_TODAY_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.start_at").isString(),
                        jsonPath("$.content.description")
                                .value(VALID_START_AT_TODAY_CREATE_APPOINTMENT_REQUEST.getDescription()),
                        jsonPath("$.content.details[0].id")
                                .value(3),
                        jsonPath("$.content.details[0].duration_in_minutes")
                                .value(VALID_START_AT_TODAY_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getDurationInMinutes()),
                        jsonPath("$.content.details[0].price")
                                .value(VALID_START_AT_TODAY_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getPrice()),
                        jsonPath("$.content.details[0].appointment_type.id")
                                .value(VALID_START_AT_TODAY_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getAppointmentTypeId()),
                        jsonPath("$.content.patient.id")
                                .value(VALID_START_AT_TODAY_CREATE_APPOINTMENT_REQUEST.getPatientId()));
    }

    @Test
    @Order(3)
    @DirtiesContext
    void user_CreateAppointmentWithInvalidArguments_StartAt_Future_Ok() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_START_AT_FUTURE_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.start_at").isString(),
                        jsonPath("$.content.description")
                                .value(VALID_START_AT_FUTURE_CREATE_APPOINTMENT_REQUEST.getDescription()),
                        jsonPath("$.content.details[0].id")
                                .value(3),
                        jsonPath("$.content.details[0].duration_in_minutes")
                                .value(VALID_START_AT_FUTURE_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getDurationInMinutes()),
                        jsonPath("$.content.details[0].price")
                                .value(VALID_START_AT_FUTURE_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getPrice()),
                        jsonPath("$.content.details[0].appointment_type.id")
                                .value(VALID_START_AT_FUTURE_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getAppointmentTypeId()),
                        jsonPath("$.content.patient.id")
                                .value(VALID_START_AT_FUTURE_CREATE_APPOINTMENT_REQUEST.getPatientId()));
    }

    @Test
    void user_CreateAppointmentWithInvalidArguments_StartAt_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_START_AT_NULL_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("start_at"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La fecha de inicio es requerida"));
    }

    // Description
    @Test
    @Order(4)
    @DirtiesContext
    void user_CreateAppointmentWithValidArguments_Description_Blank_Ok() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_BLANK_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.start_at").isString(),
                        jsonPath("$.content.description").doesNotExist(),
                        jsonPath("$.content.details[0].id")
                                .value(3),
                        jsonPath("$.content.details[0].duration_in_minutes")
                                .value(INVALID_DESCRIPTION_BLANK_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getDurationInMinutes()),
                        jsonPath("$.content.details[0].price")
                                .value(INVALID_DESCRIPTION_BLANK_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getPrice()),
                        jsonPath("$.content.details[0].appointment_type.id")
                                .value(INVALID_DESCRIPTION_BLANK_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getAppointmentTypeId()),
                        jsonPath("$.content.patient.id")
                                .value(INVALID_DESCRIPTION_BLANK_CREATE_APPOINTMENT_REQUEST.getPatientId()));
    }

    @Test
    @Order(5)
    @DirtiesContext
    void user_CreateAppointmentWithValidArguments_Description_Empty_Ok() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_EMPTY_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.start_at").isString(),
                        jsonPath("$.content.description").doesNotExist(),
                        jsonPath("$.content.details[0].id")
                                .value(3),
                        jsonPath("$.content.details[0].duration_in_minutes")
                                .value(INVALID_DESCRIPTION_EMPTY_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getDurationInMinutes()),
                        jsonPath("$.content.details[0].price")
                                .value(INVALID_DESCRIPTION_EMPTY_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getPrice()),
                        jsonPath("$.content.details[0].appointment_type.id")
                                .value(INVALID_DESCRIPTION_EMPTY_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getAppointmentTypeId()),
                        jsonPath("$.content.patient.id")
                                .value(INVALID_DESCRIPTION_EMPTY_CREATE_APPOINTMENT_REQUEST.getPatientId()));
    }

    @Test
    @Order(6)
    @DirtiesContext
    void user_CreateAppointmentWithValidArguments_Description_Null_Ok() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_NULL_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.start_at").isString(),
                        jsonPath("$.content.description")
                                .value(INVALID_DESCRIPTION_NULL_CREATE_APPOINTMENT_REQUEST.getDescription()),
                        jsonPath("$.content.details[0].id")
                                .value(3),
                        jsonPath("$.content.details[0].duration_in_minutes")
                                .value(INVALID_DESCRIPTION_NULL_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getDurationInMinutes()),
                        jsonPath("$.content.details[0].price")
                                .value(INVALID_DESCRIPTION_NULL_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getPrice()),
                        jsonPath("$.content.details[0].appointment_type.id")
                                .value(INVALID_DESCRIPTION_NULL_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getAppointmentTypeId()),
                        jsonPath("$.content.patient.id")
                                .value(INVALID_DESCRIPTION_NULL_CREATE_APPOINTMENT_REQUEST.getPatientId()));
    }

    // Details
    // Duration In Minutes
    @Test
    void user_CreateAppointmentWithInvalidArguments_DurationInMinutes_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_DURATION_IN_MINUTES_TOO_BIG_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("details[0].duration_in_minutes"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("La duración de la cita no puede ser mayor a 1440 minutos"));
    }

    @Test
    @Order(7)
    @DirtiesContext
    void user_CreateAppointmentWithValidArguments_DurationInMinutes_MaxValue_Ok() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.start_at").isString(),
                        jsonPath("$.content.description")
                                .value(VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_CREATE_APPOINTMENT_REQUEST
                                        .getDescription()),
                        jsonPath("$.content.details[0].id").value(3),
                        jsonPath("$.content.details[0].duration_in_minutes")
                                .value(VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_CREATE_APPOINTMENT_REQUEST
                                        .getDetails().get(0)
                                        .getDurationInMinutes()),
                        jsonPath("$.content.details[0].price")
                                .value(Double.parseDouble(
                                        VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_CREATE_APPOINTMENT_REQUEST
                                                .getDetails().get(0)
                                                .getPrice().toString())),
                        jsonPath("$.content.details[0].appointment_type.id")
                                .value(VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_CREATE_APPOINTMENT_REQUEST
                                        .getDetails().get(0)
                                        .getAppointmentTypeId()),
                        jsonPath("$.content.patient.id")
                                .value(VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_CREATE_APPOINTMENT_REQUEST
                                        .getPatientId()));
    }

    @Test
    void user_CreateAppointmentWithInvalidArguments_DurationInMinutes_Zero_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_DURATION_IN_MINUTES_ZERO_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("details[0].duration_in_minutes"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La duración de la cita debe ser mayor a 0"));
    }

    @Test
    void user_CreateAppointmentWithInvalidArguments_DurationInMinutes_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_DURATION_IN_MINUTES_NEGATIVE_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("details[0].duration_in_minutes"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La duración de la cita debe ser mayor a 0"));
    }

    // Price
    @Test
    void user_CreateAppointmentWithInvalidArguments_Price_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_PRICE_TOO_BIG_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("details[0].price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El precio de la cita no puede ser mayor a 9999.99"));
    }

    @Test
    @Order(8)
    @DirtiesContext
    void user_CreateAppointmentWithValidArguments_Price_MaxValue_Ok() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_DETAILS_PRICE_MAX_VALUE_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id")
                                .value(3),
                        jsonPath("$.content.start_at").isString(),
                        jsonPath("$.content.description")
                                .value(VALID_DETAILS_PRICE_MAX_VALUE_CREATE_APPOINTMENT_REQUEST
                                        .getDescription()),
                        jsonPath("$.content.details[0].id")
                                .value(3),
                        jsonPath("$.content.details[0].duration_in_minutes")
                                .value(VALID_DETAILS_PRICE_MAX_VALUE_CREATE_APPOINTMENT_REQUEST
                                        .getDetails().get(0)
                                        .getDurationInMinutes()),
                        jsonPath("$.content.details[0].price")
                                .value(VALID_DETAILS_PRICE_MAX_VALUE_CREATE_APPOINTMENT_REQUEST
                                        .getDetails().get(0)
                                        .getPrice()),
                        jsonPath("$.content.details[0].appointment_type.id")
                                .value(VALID_DETAILS_PRICE_MAX_VALUE_CREATE_APPOINTMENT_REQUEST
                                        .getDetails().get(0)
                                        .getAppointmentTypeId()),
                        jsonPath("$.content.patient.id")
                                .value(VALID_DETAILS_PRICE_MAX_VALUE_CREATE_APPOINTMENT_REQUEST
                                        .getPatientId()));
    }

    @Test
    void user_CreateAppointmentWithInvalidArguments_Price_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_PRICE_NEGATIVE_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("details[0].price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El precio de la cita debe ser mayor a 0"));
    }

    @Test
    void user_CreateAppointmentWithInvalidArguments_Price_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_PRICE_NULL_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("details[0].price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El precio de la cita es requerido"));
    }

    // Type ID
    @Test
    void user_CreateAppointmentWithInvalidArguments_TypeID_NotFound_NotFound() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_TYPE_ID_NOT_FOUND_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_APPOINTMET_TYPE_NOT_FOUND));
    }

    @Test
    void user_CreateAppointmentWithInvalidArguments_TypeID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_TYPE_ID_NEGATIVE_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("details[0].appointment_type_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del tipo de cita debe ser mayor a 0"));
    }

    @Test
    void user_CreateAppointmentWithInvalidArguments_TypeID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_TYPE_ID_NULL_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("details[0].appointment_type_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del tipo de cita es requerido"));
    }

    // Patient ID
    @Test
    void user_CreateAppointmentWithInvalidArguments_PatientID_NotFound_NotFound() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PATIENT_ID_NOT_FOUND_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_PATIENT_NOT_FOUND));
    }

    @Test
    void user_CreateAppointmentWithInvalidArguments_PatientID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PATIENT_ID_NEGATIVE_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del paciente debe ser mayor a 0"));
    }

    @Test
    void user_CreateAppointmentWithInvalidArguments_PatientID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PATIENT_ID_NULL_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del paciente es requerido"));
    }

    // Role: ADMIN
    @Test
    @Order(9)
    @DirtiesContext
    void admin_CreateAppointmentWithValidArguments_Ok() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.start_at").isString(),
                        jsonPath("$.content.description").value(VALID_CREATE_APPOINTMENT_REQUEST.getDescription()),
                        jsonPath("$.content.details[0].id")
                                .value(3),
                        jsonPath("$.content.details[0].duration_in_minutes")
                                .value(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getDurationInMinutes()),
                        jsonPath("$.content.details[0].price")
                                .value(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getPrice()),
                        jsonPath("$.content.details[0].appointment_type.id")
                                .value(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getAppointmentTypeId()),
                        jsonPath("$.content.patient.id").value(VALID_CREATE_APPOINTMENT_REQUEST.getPatientId()));
    }

    // Start At
    @Test
    void admin_CreateAppointmentWithValidArguments_StartAt_MinusYear_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_START_AT_MINUS_YEAR_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("start_at"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("La fecha de inicio debe ser mayor a la fecha actual"));
    }

    @Test
    @Order(10)
    @DirtiesContext
    void admin_CreateAppointmentWithValidArguments_StartAt_Today_Ok() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_START_AT_TODAY_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.start_at").isString(),
                        jsonPath("$.content.description")
                                .value(VALID_START_AT_TODAY_CREATE_APPOINTMENT_REQUEST.getDescription()),
                        jsonPath("$.content.details[0].id")
                                .value(3),
                        jsonPath("$.content.details[0].duration_in_minutes")
                                .value(VALID_START_AT_TODAY_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getDurationInMinutes()),
                        jsonPath("$.content.details[0].price")
                                .value(VALID_START_AT_TODAY_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getPrice()),
                        jsonPath("$.content.details[0].appointment_type.id")
                                .value(VALID_START_AT_TODAY_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getAppointmentTypeId()),
                        jsonPath("$.content.patient.id")
                                .value(VALID_START_AT_TODAY_CREATE_APPOINTMENT_REQUEST.getPatientId()));
    }

    @Test
    @Order(11)
    @DirtiesContext
    void admin_CreateAppointmentWithInvalidArguments_StartAt_Future_Ok() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_START_AT_FUTURE_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.start_at").isString(),
                        jsonPath("$.content.description")
                                .value(VALID_START_AT_FUTURE_CREATE_APPOINTMENT_REQUEST.getDescription()),
                        jsonPath("$.content.details[0].id")
                                .value(3),
                        jsonPath("$.content.details[0].duration_in_minutes")
                                .value(VALID_START_AT_FUTURE_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getDurationInMinutes()),
                        jsonPath("$.content.details[0].price")
                                .value(VALID_START_AT_FUTURE_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getPrice()),
                        jsonPath("$.content.details[0].appointment_type.id")
                                .value(VALID_START_AT_FUTURE_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getAppointmentTypeId()),
                        jsonPath("$.content.patient.id")
                                .value(VALID_START_AT_FUTURE_CREATE_APPOINTMENT_REQUEST.getPatientId()));
    }

    @Test
    void admin_CreateAppointmentWithInvalidArguments_StartAt_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_START_AT_NULL_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("start_at"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La fecha de inicio es requerida"));
    }

    // Description
    @Test
    @Order(12)
    @DirtiesContext
    void admin_CreateAppointmentWithValidArguments_Description_Blank_Ok() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_BLANK_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.start_at").isString(),
                        jsonPath("$.content.description").doesNotExist(),
                        jsonPath("$.content.details[0].id")
                                .value(3),
                        jsonPath("$.content.details[0].duration_in_minutes")
                                .value(INVALID_DESCRIPTION_BLANK_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getDurationInMinutes()),
                        jsonPath("$.content.details[0].price")
                                .value(INVALID_DESCRIPTION_BLANK_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getPrice()),
                        jsonPath("$.content.details[0].appointment_type.id")
                                .value(INVALID_DESCRIPTION_BLANK_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getAppointmentTypeId()),
                        jsonPath("$.content.patient.id")
                                .value(INVALID_DESCRIPTION_BLANK_CREATE_APPOINTMENT_REQUEST.getPatientId()));
    }

    @Test
    @Order(13)
    @DirtiesContext
    void admin_CreateAppointmentWithValidArguments_Description_Empty_Ok() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_EMPTY_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.start_at").isString(),
                        jsonPath("$.content.description").doesNotExist(),
                        jsonPath("$.content.details[0].id")
                                .value(3),
                        jsonPath("$.content.details[0].duration_in_minutes")
                                .value(INVALID_DESCRIPTION_EMPTY_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getDurationInMinutes()),
                        jsonPath("$.content.details[0].price")
                                .value(INVALID_DESCRIPTION_EMPTY_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getPrice()),
                        jsonPath("$.content.details[0].appointment_type.id")
                                .value(INVALID_DESCRIPTION_EMPTY_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getAppointmentTypeId()),
                        jsonPath("$.content.patient.id")
                                .value(INVALID_DESCRIPTION_EMPTY_CREATE_APPOINTMENT_REQUEST.getPatientId()));
    }

    @Test
    @Order(14)
    @DirtiesContext
    void admin_CreateAppointmentWithValidArguments_Description_Null_Ok() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_NULL_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.start_at").isString(),
                        jsonPath("$.content.description")
                                .value(INVALID_DESCRIPTION_NULL_CREATE_APPOINTMENT_REQUEST.getDescription()),
                        jsonPath("$.content.details[0].id")
                                .value(3),
                        jsonPath("$.content.details[0].duration_in_minutes")
                                .value(INVALID_DESCRIPTION_NULL_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getDurationInMinutes()),
                        jsonPath("$.content.details[0].price")
                                .value(INVALID_DESCRIPTION_NULL_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getPrice()),
                        jsonPath("$.content.details[0].appointment_type.id")
                                .value(INVALID_DESCRIPTION_NULL_CREATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getAppointmentTypeId()),
                        jsonPath("$.content.patient.id")
                                .value(INVALID_DESCRIPTION_NULL_CREATE_APPOINTMENT_REQUEST.getPatientId()));
    }

    // Details
    // Duration In Minutes
    @Test
    void admin_CreateAppointmentWithInvalidArguments_DurationInMinutes_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_DURATION_IN_MINUTES_TOO_BIG_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("details[0].duration_in_minutes"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("La duración de la cita no puede ser mayor a 1440 minutos"));
    }

    @Test
    @Order(15)
    @DirtiesContext
    void admin_CreateAppointmentWithValidArguments_DurationInMinutes_MaxValue_Ok() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.start_at").isString(),
                        jsonPath("$.content.description")
                                .value(VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_CREATE_APPOINTMENT_REQUEST
                                        .getDescription()),
                        jsonPath("$.content.details[0].id").value(3),
                        jsonPath("$.content.details[0].duration_in_minutes")
                                .value(VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_CREATE_APPOINTMENT_REQUEST
                                        .getDetails().get(0)
                                        .getDurationInMinutes()),
                        jsonPath("$.content.details[0].price")
                                .value(Double.parseDouble(
                                        VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_CREATE_APPOINTMENT_REQUEST
                                                .getDetails().get(0)
                                                .getPrice().toString())),
                        jsonPath("$.content.details[0].appointment_type.id")
                                .value(VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_CREATE_APPOINTMENT_REQUEST
                                        .getDetails().get(0)
                                        .getAppointmentTypeId()),
                        jsonPath("$.content.patient.id")
                                .value(VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_CREATE_APPOINTMENT_REQUEST
                                        .getPatientId()));
    }

    @Test
    void admin_CreateAppointmentWithInvalidArguments_DurationInMinutes_Zero_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_DURATION_IN_MINUTES_ZERO_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("details[0].duration_in_minutes"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La duración de la cita debe ser mayor a 0"));
    }

    @Test
    void admin_CreateAppointmentWithInvalidArguments_DurationInMinutes_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_DURATION_IN_MINUTES_NEGATIVE_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("details[0].duration_in_minutes"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La duración de la cita debe ser mayor a 0"));
    }

    // Price
    @Test
    void admin_CreateAppointmentWithInvalidArguments_Price_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_PRICE_TOO_BIG_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("details[0].price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El precio de la cita no puede ser mayor a 9999.99"));
    }

    @Test
    @Order(16)
    @DirtiesContext
    void admin_CreateAppointmentWithValidArguments_Price_MaxValue_Ok() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_DETAILS_PRICE_MAX_VALUE_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id")
                                .value(3),
                        jsonPath("$.content.start_at").isString(),
                        jsonPath("$.content.description")
                                .value(VALID_DETAILS_PRICE_MAX_VALUE_CREATE_APPOINTMENT_REQUEST
                                        .getDescription()),
                        jsonPath("$.content.details[0].id")
                                .value(3),
                        jsonPath("$.content.details[0].duration_in_minutes")
                                .value(VALID_DETAILS_PRICE_MAX_VALUE_CREATE_APPOINTMENT_REQUEST
                                        .getDetails().get(0)
                                        .getDurationInMinutes()),
                        jsonPath("$.content.details[0].price")
                                .value(VALID_DETAILS_PRICE_MAX_VALUE_CREATE_APPOINTMENT_REQUEST
                                        .getDetails().get(0)
                                        .getPrice()),
                        jsonPath("$.content.details[0].appointment_type.id")
                                .value(VALID_DETAILS_PRICE_MAX_VALUE_CREATE_APPOINTMENT_REQUEST
                                        .getDetails().get(0)
                                        .getAppointmentTypeId()),
                        jsonPath("$.content.patient.id")
                                .value(VALID_DETAILS_PRICE_MAX_VALUE_CREATE_APPOINTMENT_REQUEST
                                        .getPatientId()));
    }

    @Test
    void admin_CreateAppointmentWithInvalidArguments_Price_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_PRICE_NEGATIVE_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("details[0].price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El precio de la cita debe ser mayor a 0"));
    }

    @Test
    void admin_CreateAppointmentWithInvalidArguments_Price_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_PRICE_NULL_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("details[0].price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El precio de la cita es requerido"));
    }

    // Type ID
    @Test
    void admin_CreateAppointmentWithInvalidArguments_TypeID_NotFound_NotFound() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_TYPE_ID_NOT_FOUND_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_APPOINTMET_TYPE_NOT_FOUND));
    }

    @Test
    void admin_CreateAppointmentWithInvalidArguments_TypeID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_TYPE_ID_NEGATIVE_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("details[0].appointment_type_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del tipo de cita debe ser mayor a 0"));
    }

    @Test
    void admin_CreateAppointmentWithInvalidArguments_TypeID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_TYPE_ID_NULL_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("details[0].appointment_type_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del tipo de cita es requerido"));
    }

    // Patient ID
    @Test
    void admin_CreateAppointmentWithInvalidArguments_PatientID_NotFound_NotFound() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PATIENT_ID_NOT_FOUND_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_PATIENT_NOT_FOUND));
    }

    @Test
    void admin_CreateAppointmentWithInvalidArguments_PatientID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PATIENT_ID_NEGATIVE_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del paciente debe ser mayor a 0"));
    }

    @Test
    void admin_CreateAppointmentWithInvalidArguments_PatientID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PATIENT_ID_NULL_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del paciente es requerido"));
    }
}
