package com.vluepixel.vetmanager.api.appointment.core.type.integration;

import static com.vluepixel.vetmanager.api.appointment.core.type.data.CreateAppointmentTypeDataProvider.INVALID_DURATION_IN_MINUTES_NEGATIVE_CREATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.type.data.CreateAppointmentTypeDataProvider.INVALID_DURATION_IN_MINUTES_TOO_BIG_CREATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.type.data.CreateAppointmentTypeDataProvider.INVALID_DURATION_IN_MINUTES_ZERO_CREATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.type.data.CreateAppointmentTypeDataProvider.INVALID_NAME_BLANK_CREATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.type.data.CreateAppointmentTypeDataProvider.INVALID_NAME_EMPTY_CREATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.type.data.CreateAppointmentTypeDataProvider.INVALID_NAME_NULL_CREATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.type.data.CreateAppointmentTypeDataProvider.INVALID_NAME_TOO_LONG_CREATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.type.data.CreateAppointmentTypeDataProvider.INVALID_PRICE_NEGATIVE_CREATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.type.data.CreateAppointmentTypeDataProvider.INVALID_PRICE_TOO_BIG_CREATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.type.data.CreateAppointmentTypeDataProvider.INVALID_PRICE_ZERO_CREATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.type.data.CreateAppointmentTypeDataProvider.VALID_CREATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.type.data.CreateAppointmentTypeDataProvider.VALID_DURATION_IN_MINUTES_MAX_VALUE_CREATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.type.data.CreateAppointmentTypeDataProvider.VALID_NAME_MAX_LENGTH_CREATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.type.data.CreateAppointmentTypeDataProvider.VALID_PRICE_MAX_VALUE_CREATE_APPOINTMENT_TYPE_REQUEST;
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
 * Integration tests for the create appointment type functionality.
 */
public class CreateAppointmentType extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Tipo de cita creada";
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    @Test
    void noUser_CreateAppointmentTypeWithValidArguments_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_APPOINTMENT_TYPE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Name
    @Test
    void noUser_CreateAppointmentTypeWithInvalidArguments_Name_TooLong_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_TOO_LONG_CREATE_APPOINTMENT_TYPE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateAppointmentTypeWithValidArguments_Name_MaxLength_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_NAME_MAX_LENGTH_CREATE_APPOINTMENT_TYPE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateAppointmentTypeWithInvalidArguments_Name_Blank_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_BLANK_CREATE_APPOINTMENT_TYPE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateAppointmentTypeWithInvalidArguments_Name_Empty_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_EMPTY_CREATE_APPOINTMENT_TYPE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateAppointmentTypeWithInvalidArguments_Name_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_NULL_CREATE_APPOINTMENT_TYPE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Duration In Minutes
    @Test
    void noUser_CreateAppointmentTypeWithInvalidArguments_DurationInMinutes_Negative_Forbidden()
            throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DURATION_IN_MINUTES_NEGATIVE_CREATE_APPOINTMENT_TYPE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateAppointmentTypeWithInvalidArguments_DurationInMinutes_Zero_Forbidden()
            throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DURATION_IN_MINUTES_ZERO_CREATE_APPOINTMENT_TYPE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateAppointmentTypeWithInvalidArguments_DurationInMinutes_TooBig_Forbidden()
            throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DURATION_IN_MINUTES_TOO_BIG_CREATE_APPOINTMENT_TYPE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateAppointmentTypeWithValidArguments_DurationUnMinutes_MaxValue_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_DURATION_IN_MINUTES_MAX_VALUE_CREATE_APPOINTMENT_TYPE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Price
    @Test
    void noUser_CreateAppointmentTypeWithInvalidArguments_Price_Negative_Forbidden()
            throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PRICE_NEGATIVE_CREATE_APPOINTMENT_TYPE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateAppointmentTypeWithInvalidArguments_Price_Zero_Forbidden()
            throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PRICE_ZERO_CREATE_APPOINTMENT_TYPE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateAppointmentTypeWithInvalidArguments_Price_TooBig_Forbidden()
            throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PRICE_TOO_BIG_CREATE_APPOINTMENT_TYPE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateAppointmentTypeWithValidArguments_Price_MaxValue_Forbidden() throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_PRICE_MAX_VALUE_CREATE_APPOINTMENT_TYPE_REQUEST)))
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
    void user_CreateAppointmentTypeWithValidArguments_Ok() throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.name").value(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getName()),
                        jsonPath("$.content.duration_in_minutes")
                                .value(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes()),
                        jsonPath("$.content.price").value(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getPrice()));
    }

    // Name
    @Test
    void user_CreateAppointmentTypeWithInvalidArguments_Name_TooLong_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_TOO_LONG_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El nombre del tipo de cita no puede tener más de 20 caracteres"));
    }

    @Test
    @Order(2)
    @DirtiesContext
    void user_CreateAppointmentTypeWithValidArguments_Name_MaxLength_Ok() throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_NAME_MAX_LENGTH_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.name")
                                .value(VALID_NAME_MAX_LENGTH_CREATE_APPOINTMENT_TYPE_REQUEST.getName()),
                        jsonPath("$.content.duration_in_minutes")
                                .value(VALID_NAME_MAX_LENGTH_CREATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes()),
                        jsonPath("$.content.price")
                                .value(VALID_NAME_MAX_LENGTH_CREATE_APPOINTMENT_TYPE_REQUEST.getPrice()));
    }

    @Test
    void user_CreateAppointmentTypeWithInvalidArguments_Name_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_BLANK_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El nombre del tipo de cita es requerido"));
    }

    @Test
    void user_CreateAppointmentTypeWithInvalidArguments_Name_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_EMPTY_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El nombre del tipo de cita es requerido"));
    }

    @Test
    void user_CreateAppointmentTypeWithInvalidArguments_Name_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_NULL_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El nombre del tipo de cita es requerido"));
    }

    // Duration In Minutes
    @Test
    void user_CreateAppointmentTypeWithInvalidArguments_DurationInMinutes_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DURATION_IN_MINUTES_NEGATIVE_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("duration_in_minutes"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("La duración de la cita debe ser mayor a 0"));
    }

    @Test
    void user_CreateAppointmentTypeWithInvalidArguments_DurationInMinutes_Zero_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DURATION_IN_MINUTES_ZERO_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("duration_in_minutes"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("La duración de la cita debe ser mayor a 0"));
    }

    @Test
    void user_CreateAppointmentTypeWithInvalidArguments_DurationInMinutes_TooBig_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DURATION_IN_MINUTES_TOO_BIG_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("duration_in_minutes"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("La duración de la cita no puede ser mayor a 720 minutos"));
    }

    @Test
    @Order(3)
    @DirtiesContext
    void user_CreateAppointmentTypeWithValidArguments_DurationUnMinutes_MaxValue_Ok() throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_DURATION_IN_MINUTES_MAX_VALUE_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.name")
                                .value(VALID_DURATION_IN_MINUTES_MAX_VALUE_CREATE_APPOINTMENT_TYPE_REQUEST.getName()),
                        jsonPath("$.content.duration_in_minutes")
                                .value(VALID_DURATION_IN_MINUTES_MAX_VALUE_CREATE_APPOINTMENT_TYPE_REQUEST
                                        .getDurationInMinutes()),
                        jsonPath("$.content.price")
                                .value(VALID_DURATION_IN_MINUTES_MAX_VALUE_CREATE_APPOINTMENT_TYPE_REQUEST.getPrice()));
    }

    // Price
    @Test
    void user_CreateAppointmentTypeWithInvalidArguments_Price_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PRICE_NEGATIVE_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El precio de la cita debe ser mayor a 0"));
    }

    @Test
    void user_CreateAppointmentTypeWithInvalidArguments_Price_Zero_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PRICE_ZERO_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El precio de la cita debe ser mayor a 0"));
    }

    @Test
    void user_CreateAppointmentTypeWithInvalidArguments_Price_TooBig_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PRICE_TOO_BIG_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages")
                                .value("El precio de la cita no puede ser mayor a 999.99"));
    }

    @Test
    @Order(4)
    @DirtiesContext
    void user_CreateAppointmentTypeWithValidArguments_Price_MaxValue_Ok() throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_PRICE_MAX_VALUE_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.name")
                                .value(VALID_PRICE_MAX_VALUE_CREATE_APPOINTMENT_TYPE_REQUEST.getName()),
                        jsonPath("$.content.duration_in_minutes")
                                .value(VALID_PRICE_MAX_VALUE_CREATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes()),
                        jsonPath("$.content.price")
                                .value(VALID_PRICE_MAX_VALUE_CREATE_APPOINTMENT_TYPE_REQUEST.getPrice()));
    }

    // Role: ADMIN
    @Test
    @Order(1)
    @DirtiesContext
    void admin_CreateAppointmentTypeWithValidArguments_Ok() throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.name").value(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getName()),
                        jsonPath("$.content.duration_in_minutes")
                                .value(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes()),
                        jsonPath("$.content.price").value(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getPrice()));
    }

    // Name
    @Test
    void admin_UpdateAppointmentTypeWithInvalidArguments_Name_TooLong_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_TOO_LONG_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El nombre del tipo de cita no puede tener más de 20 caracteres"));
    }

    @Test
    @Order(2)
    @DirtiesContext
    void admin_UpdateAppointmentTypeWithValidArguments_Name_MaxLength_Ok() throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_NAME_MAX_LENGTH_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.name")
                                .value(VALID_NAME_MAX_LENGTH_CREATE_APPOINTMENT_TYPE_REQUEST.getName()),
                        jsonPath("$.content.duration_in_minutes")
                                .value(VALID_NAME_MAX_LENGTH_CREATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes()),
                        jsonPath("$.content.price")
                                .value(VALID_NAME_MAX_LENGTH_CREATE_APPOINTMENT_TYPE_REQUEST.getPrice()));
    }

    @Test
    void admin_UpdateAppointmentTypeWithInvalidArguments_Name_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_BLANK_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El nombre del tipo de cita es requerido"));
    }

    @Test
    void admin_UpdateAppointmentTypeWithInvalidArguments_Name_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_EMPTY_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El nombre del tipo de cita es requerido"));
    }

    @Test
    void admin_UpdateAppointmentTypeWithInvalidArguments_Name_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_NULL_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El nombre del tipo de cita es requerido"));
    }

    // Duration In Minutes
    @Test
    void admin_UpdateAppointmentTypeWithInvalidArguments_DurationInMinutes_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DURATION_IN_MINUTES_NEGATIVE_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("duration_in_minutes"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("La duración de la cita debe ser mayor a 0"));
    }

    @Test
    void admin_UpdateAppointmentTypeWithInvalidArguments_DurationInMinutes_Zero_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DURATION_IN_MINUTES_ZERO_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("duration_in_minutes"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("La duración de la cita debe ser mayor a 0"));
    }

    @Test
    void admin_UpdateAppointmentTypeWithInvalidArguments_DurationInMinutes_TooBig_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DURATION_IN_MINUTES_TOO_BIG_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("duration_in_minutes"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("La duración de la cita no puede ser mayor a 720 minutos"));
    }

    @Test
    @Order(3)
    @DirtiesContext
    void admin_UpdateAppointmentTypeWithValidArguments_DurationUnMinutes_MaxValue_Ok() throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_DURATION_IN_MINUTES_MAX_VALUE_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.name")
                                .value(VALID_DURATION_IN_MINUTES_MAX_VALUE_CREATE_APPOINTMENT_TYPE_REQUEST.getName()),
                        jsonPath("$.content.duration_in_minutes")
                                .value(VALID_DURATION_IN_MINUTES_MAX_VALUE_CREATE_APPOINTMENT_TYPE_REQUEST
                                        .getDurationInMinutes()),
                        jsonPath("$.content.price")
                                .value(VALID_DURATION_IN_MINUTES_MAX_VALUE_CREATE_APPOINTMENT_TYPE_REQUEST.getPrice()));
    }

    // Price
    @Test
    void admin_UpdateAppointmentTypeWithInvalidArguments_Price_Negative_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PRICE_NEGATIVE_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El precio de la cita debe ser mayor a 0"));
    }

    @Test
    void admin_UpdateAppointmentTypeWithInvalidArguments_Price_Zero_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PRICE_ZERO_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El precio de la cita debe ser mayor a 0"));
    }

    @Test
    void admin_UpdateAppointmentTypeWithInvalidArguments_Price_TooBig_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_PRICE_TOO_BIG_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages")
                                .value("El precio de la cita no puede ser mayor a 999.99"));
    }

    @Test
    @Order(4)
    @DirtiesContext
    void admin_UpdateAppointmentTypeWithValidArguments_Price_MaxValue_Ok() throws Exception {
        mockMvc.perform(post("/appointment/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(VALID_PRICE_MAX_VALUE_CREATE_APPOINTMENT_TYPE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.name")
                                .value(VALID_PRICE_MAX_VALUE_CREATE_APPOINTMENT_TYPE_REQUEST.getName()),
                        jsonPath("$.content.duration_in_minutes")
                                .value(VALID_PRICE_MAX_VALUE_CREATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes()),
                        jsonPath("$.content.price")
                                .value(VALID_PRICE_MAX_VALUE_CREATE_APPOINTMENT_TYPE_REQUEST.getPrice()));
    }

}
