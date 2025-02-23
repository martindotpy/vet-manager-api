package com.vluepixel.vetmanager.api.appointment.core.integration;

import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DESCRIPTION_BLANK_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DESCRIPTION_EMPTY_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DESCRIPTION_NULL_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DETAILS_DURATION_IN_MINUTES_NEGATIVE_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DETAILS_DURATION_IN_MINUTES_TOO_BIG_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DETAILS_DURATION_IN_MINUTES_ZERO_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DETAILS_ID_NEGATIVE_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DETAILS_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DETAILS_ID_NULL_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DETAILS_PRICE_NEGATIVE_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DETAILS_PRICE_NULL_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DETAILS_PRICE_TOO_BIG_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DETAILS_TYPE_ID_NEGATIVE_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DETAILS_TYPE_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DETAILS_TYPE_ID_NULL_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_ID_NEGATIVE_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_ID_NULL_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_PATIENT_ID_NEGATIVE_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_PATIENT_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_PATIENT_ID_NULL_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_START_AT_MINUS_YEAR_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_START_AT_NULL_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.VALID_DETAILS_PRICE_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.VALID_START_AT_FUTURE_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.VALID_START_AT_TODAY_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.VALID_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the update appointment use case.
 */
public class UpdateAppointmentIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Cita actualizada exitosamente";
    private static final Function<String, String> MESSAGE_NOT_FOUND = parameter -> String
            .format("Cita con id %s no encontrado(a)", parameter);
    private static final String MESSAGE_APPOINTMET_TYPE_NOT_FOUND = "Tipo de cita no encontrado(a)";
    private static final String MESSAGE_PATIENT_NOT_FOUND = "Paciente no encontrado(a)";
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
    void admin_UpdateAppointmentWithValidArguments_Ok() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_UPDATE_APPOINTMENT_REQUEST.getId()),
                        jsonPath("$.content.start_at").value(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt().toString()),
                        jsonPath("$.content.description").value(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription()),
                        jsonPath("$.content.details[0].id")
                                .value(VALID_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0).getId()),
                        jsonPath("$.content.details[0].duration_in_minutes")
                                .value(VALID_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0).getDurationInMinutes()),
                        jsonPath("$.content.details[0].price")
                                .value(VALID_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0).getPrice()),
                        jsonPath("$.content.details[0].appointment_type.id")
                                .value(VALID_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0).getAppointmentTypeId()),
                        jsonPath("$.content.patient.id").value(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId()));
    }

    // ID
    @Test
    void admin_UpdateAppointmentWithInvalidArguments_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(
                        MESSAGE_NOT_FOUND.apply(INVALID_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST.getId().toString())));
    }

    @Test
    void admin_UpdateAppointmentWithInvalidArguments_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NEGATIVE_UPDATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del tipo de cita debe ser mayor a 0"));
    }

    @Test
    void admin_UpdateAppointmentWithInvalidArguments_ID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NULL_UPDATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del tipo de cita es requerido"));
    }

    // Start At
    @Test
    void admin_UpdateAppointmentWithValidArguments_StartAt_MinusYear_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_START_AT_MINUS_YEAR_UPDATE_APPOINTMENT_REQUEST))
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
    @DirtiesContext
    void admin_UpdateAppointmentWithValidArguments_StartAt_Today_Ok() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_START_AT_TODAY_UPDATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_START_AT_TODAY_UPDATE_APPOINTMENT_REQUEST.getId()),
                        jsonPath("$.content.start_at")
                                .value(VALID_START_AT_TODAY_UPDATE_APPOINTMENT_REQUEST.getStartAt().toString()),
                        jsonPath("$.content.description")
                                .value(VALID_START_AT_TODAY_UPDATE_APPOINTMENT_REQUEST.getDescription()),
                        jsonPath("$.content.details[0].id")
                                .value(VALID_START_AT_TODAY_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getId()),
                        jsonPath("$.content.details[0].duration_in_minutes")
                                .value(VALID_START_AT_TODAY_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getDurationInMinutes()),
                        jsonPath("$.content.details[0].price")
                                .value(VALID_START_AT_TODAY_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getPrice()),
                        jsonPath("$.content.details[0].appointment_type.id")
                                .value(VALID_START_AT_TODAY_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getAppointmentTypeId()),
                        jsonPath("$.content.patient.id")
                                .value(VALID_START_AT_TODAY_UPDATE_APPOINTMENT_REQUEST.getPatientId()));
    }

    @Test
    @DirtiesContext
    void admin_UpdateAppointmentWithInvalidArguments_StartAt_Future_Ok() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_START_AT_FUTURE_UPDATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_START_AT_FUTURE_UPDATE_APPOINTMENT_REQUEST.getId()),
                        jsonPath("$.content.start_at")
                                .value(VALID_START_AT_FUTURE_UPDATE_APPOINTMENT_REQUEST.getStartAt().toString()),
                        jsonPath("$.content.description")
                                .value(VALID_START_AT_FUTURE_UPDATE_APPOINTMENT_REQUEST.getDescription()),
                        jsonPath("$.content.details[0].id")
                                .value(VALID_START_AT_FUTURE_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getId()),
                        jsonPath("$.content.details[0].duration_in_minutes")
                                .value(VALID_START_AT_FUTURE_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getDurationInMinutes()),
                        jsonPath("$.content.details[0].price")
                                .value(VALID_START_AT_FUTURE_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getPrice()),
                        jsonPath("$.content.details[0].appointment_type.id")
                                .value(VALID_START_AT_FUTURE_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getAppointmentTypeId()),
                        jsonPath("$.content.patient.id")
                                .value(VALID_START_AT_FUTURE_UPDATE_APPOINTMENT_REQUEST.getPatientId()));
    }

    @Test
    void admin_UpdateAppointmentWithInvalidArguments_StartAt_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_START_AT_NULL_UPDATE_APPOINTMENT_REQUEST))
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
    @DirtiesContext // TODO: Description must be null
    void admin_UpdateAppointmentWithValidArguments_Description_Blank_Ok() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_BLANK_UPDATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(INVALID_DESCRIPTION_BLANK_UPDATE_APPOINTMENT_REQUEST.getId()),
                        jsonPath("$.content.start_at")
                                .value(INVALID_DESCRIPTION_BLANK_UPDATE_APPOINTMENT_REQUEST.getStartAt().toString()),
                        jsonPath("$.content.description")
                                .value(""),
                        jsonPath("$.content.details[0].id")
                                .value(INVALID_DESCRIPTION_BLANK_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getId()),
                        jsonPath("$.content.details[0].duration_in_minutes")
                                .value(INVALID_DESCRIPTION_BLANK_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getDurationInMinutes()),
                        jsonPath("$.content.details[0].price")
                                .value(INVALID_DESCRIPTION_BLANK_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getPrice()),
                        jsonPath("$.content.details[0].appointment_type.id")
                                .value(INVALID_DESCRIPTION_BLANK_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getAppointmentTypeId()),
                        jsonPath("$.content.patient.id")
                                .value(INVALID_DESCRIPTION_BLANK_UPDATE_APPOINTMENT_REQUEST.getPatientId()));
    }

    @Test
    @DirtiesContext // TODO: Description must be null
    void admin_UpdateAppointmentWithValidArguments_Description_Empty_Ok() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_EMPTY_UPDATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(INVALID_DESCRIPTION_EMPTY_UPDATE_APPOINTMENT_REQUEST.getId()),
                        jsonPath("$.content.start_at")
                                .value(INVALID_DESCRIPTION_EMPTY_UPDATE_APPOINTMENT_REQUEST.getStartAt().toString()),
                        jsonPath("$.content.description")
                                .value(INVALID_DESCRIPTION_EMPTY_UPDATE_APPOINTMENT_REQUEST.getDescription()),
                        jsonPath("$.content.details[0].id")
                                .value(INVALID_DESCRIPTION_EMPTY_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getId()),
                        jsonPath("$.content.details[0].duration_in_minutes")
                                .value(INVALID_DESCRIPTION_EMPTY_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getDurationInMinutes()),
                        jsonPath("$.content.details[0].price")
                                .value(INVALID_DESCRIPTION_EMPTY_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getPrice()),
                        jsonPath("$.content.details[0].appointment_type.id")
                                .value(INVALID_DESCRIPTION_EMPTY_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getAppointmentTypeId()),
                        jsonPath("$.content.patient.id")
                                .value(INVALID_DESCRIPTION_EMPTY_UPDATE_APPOINTMENT_REQUEST.getPatientId()));
    }

    @Test
    @DirtiesContext
    void admin_UpdateAppointmentWithValidArguments_Description_Null_Ok() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_NULL_UPDATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(INVALID_DESCRIPTION_NULL_UPDATE_APPOINTMENT_REQUEST.getId()),
                        jsonPath("$.content.start_at")
                                .value(INVALID_DESCRIPTION_NULL_UPDATE_APPOINTMENT_REQUEST.getStartAt().toString()),
                        jsonPath("$.content.description")
                                .value(INVALID_DESCRIPTION_NULL_UPDATE_APPOINTMENT_REQUEST.getDescription()),
                        jsonPath("$.content.details[0].id")
                                .value(INVALID_DESCRIPTION_NULL_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getId()),
                        jsonPath("$.content.details[0].duration_in_minutes")
                                .value(INVALID_DESCRIPTION_NULL_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getDurationInMinutes()),
                        jsonPath("$.content.details[0].price")
                                .value(INVALID_DESCRIPTION_NULL_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getPrice()),
                        jsonPath("$.content.details[0].appointment_type.id")
                                .value(INVALID_DESCRIPTION_NULL_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0)
                                        .getAppointmentTypeId()),
                        jsonPath("$.content.patient.id")
                                .value(INVALID_DESCRIPTION_NULL_UPDATE_APPOINTMENT_REQUEST.getPatientId()));
    }

    // Details
    @Test
    void admin_UpdateAppointmentWithInvalidArguments_DetailsID_NotFound_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DETAILS_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("details"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value(String.format(
                                "El detalle con id %s no pertenece a la cita",
                                INVALID_DETAILS_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0).getId())));
    }

    @Test
    void admin_UpdateAppointmentWithInvalidArguments_DetailsID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DETAILS_ID_NEGATIVE_UPDATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("details"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value(String.format(
                                "El detalle con id %s no pertenece a la cita",
                                INVALID_DETAILS_ID_NEGATIVE_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0).getId())));
    }

    @Test
    void admin_UpdateAppointmentWithInvalidArguments_DetailsID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DETAILS_ID_NULL_UPDATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("details"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value(String.format(
                                "El detalle con id %s no pertenece a la cita",
                                INVALID_DETAILS_ID_NULL_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0).getId())));
    }

    // Duration In Minutes
    @Test
    void admin_UpdateAppointmentWithInvalidArguments_DurationInMinutes_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_DURATION_IN_MINUTES_TOO_BIG_UPDATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("duration_in_minutes"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("must be less than or equal to 1440")); // TODO
    }

    @Test
    @DirtiesContext
    void admin_UpdateAppointmentWithValidArguments_DurationInMinutes_MaxValue_Ok() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id")
                                .value(VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST.getId()),
                        jsonPath("$.content.start_at")
                                .value(VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST
                                        .getStartAt().toString()),
                        jsonPath("$.content.description")
                                .value(VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST
                                        .getDescription()),
                        jsonPath("$.content.details[0].id")
                                .value(VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST
                                        .getDetails().get(0)
                                        .getId()),
                        jsonPath("$.content.details[0].duration_in_minutes")
                                .value(VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST
                                        .getDetails().get(0)
                                        .getDurationInMinutes()),
                        jsonPath("$.content.details[0].price")
                                .value(VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST
                                        .getDetails().get(0)
                                        .getPrice()),
                        jsonPath("$.content.details[0].appointment_type.id")
                                .value(VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST
                                        .getDetails().get(0)
                                        .getAppointmentTypeId()),
                        jsonPath("$.content.patient.id")
                                .value(VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST
                                        .getPatientId()));
    }

    @Test
    void admin_UpdateAppointmentWithInvalidArguments_DurationInMinutes_Zero_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_DURATION_IN_MINUTES_ZERO_UPDATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("duration_in_minutes"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("must be greater than 0")); // TODO
    }

    @Test
    void admin_UpdateAppointmentWithInvalidArguments_DurationInMinutes_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_DURATION_IN_MINUTES_NEGATIVE_UPDATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("duration_in_minutes"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("must be greater than 0")); // TODO
    }

    // Price
    @Test
    void admin_UpdateAppointmentWithInvalidArguments_Price_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_PRICE_TOO_BIG_UPDATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("must be less than or equal to 9999.99")); // TODO
    }

    @Test
    @DirtiesContext
    void admin_UpdateAppointmentWithValidArguments_Price_MaxValue_Ok() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_DETAILS_PRICE_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id")
                                .value(VALID_DETAILS_PRICE_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST.getId()),
                        jsonPath("$.content.start_at")
                                .value(VALID_DETAILS_PRICE_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST
                                        .getStartAt().toString()),
                        jsonPath("$.content.description")
                                .value(VALID_DETAILS_PRICE_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST
                                        .getDescription()),
                        jsonPath("$.content.details[0].id")
                                .value(VALID_DETAILS_PRICE_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST
                                        .getDetails().get(0)
                                        .getId()),
                        jsonPath("$.content.details[0].duration_in_minutes")
                                .value(VALID_DETAILS_PRICE_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST
                                        .getDetails().get(0)
                                        .getDurationInMinutes()),
                        jsonPath("$.content.details[0].price")
                                .value(VALID_DETAILS_PRICE_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST
                                        .getDetails().get(0)
                                        .getPrice()),
                        jsonPath("$.content.details[0].appointment_type.id")
                                .value(VALID_DETAILS_PRICE_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST
                                        .getDetails().get(0)
                                        .getAppointmentTypeId()),
                        jsonPath("$.content.patient.id")
                                .value(VALID_DETAILS_PRICE_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST
                                        .getPatientId()));
    }

    @Test
    void admin_UpdateAppointmentWithInvalidArguments_Price_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_PRICE_NEGATIVE_UPDATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("must be greater than 0")); // TODO
    }

    @Test
    void admin_UpdateAppointmentWithInvalidArguments_Price_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_PRICE_NULL_UPDATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("must not be null")); // TODO
    }

    // Type ID
    @Test
    void admin_UpdateAppointmentWithInvalidArguments_TypeID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_TYPE_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_APPOINTMET_TYPE_NOT_FOUND));
    }

    @Test // TODO: Implements Type ID validations
    void admin_UpdateAppointmentWithInvalidArguments_TypeID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_TYPE_ID_NEGATIVE_UPDATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("appointment_type"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("must not be null")); // TODO
    }

    @Test
    void admin_UpdateAppointmentWithInvalidArguments_TypeID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DETAILS_TYPE_ID_NULL_UPDATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("appointment_type"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("must not be null")); // TODO
    }

    // Patient ID
    @Test
    void admin_UpdateAppointmentWithInvalidArguments_PatientID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PATIENT_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_PATIENT_NOT_FOUND));
    }

    @Test // TODO: Implements Patient ID validations
    void admin_UpdateAppointmentWithInvalidArguments_PatientID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PATIENT_ID_NEGATIVE_UPDATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("must not be null")); // TODO
    }

    @Test // TODO: Implements Patient ID validations
    void admin_UpdateAppointmentWithInvalidArguments_PatientID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PATIENT_ID_NULL_UPDATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("patient_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("must not be null")); // TODO
    }
}
