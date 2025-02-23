package com.vluepixel.vetmanager.api.appointment.core.integration;

import static com.vluepixel.vetmanager.api.appointment.core.data.CreateAppointmentDataProvider.VALID_CREATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
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
    void admin_CreateAppointmentWithValidArguments_Ok() throws Exception {
        mockMvc.perform(post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_APPOINTMENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.start_at").value(VALID_CREATE_APPOINTMENT_REQUEST.getStartAt().toString()),
                        jsonPath("$.content.description").value(VALID_CREATE_APPOINTMENT_REQUEST.getDescription()),
                        jsonPath("$.content.details[0].id")
                                .value(1),
                        jsonPath("$.content.details[0].duration_in_minutes")
                                .value(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getDurationInMinutes()),
                        jsonPath("$.content.details[0].price")
                                .value(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getPrice()),
                        jsonPath("$.content.details[0].appointment_type.id")
                                .value(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getAppointmentTypeId()),
                        jsonPath("$.content.patient.id").value(VALID_CREATE_APPOINTMENT_REQUEST.getPatientId()));
    }
}
