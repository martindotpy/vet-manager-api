package com.vluepixel.vetmanager.api.appointment.core.integration;

import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.VALID_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the update appointment use case.
 */
public class UpdateAppointmentIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Cita actualizada exitosamente";
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
}
