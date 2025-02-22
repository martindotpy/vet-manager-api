package com.vluepixel.vetmanager.api.appointment.core.type.integration;

import static com.vluepixel.vetmanager.api.appointment.core.type.data.UpdateAppointmentTypeDataProvider.INVALID_UPDATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.type.data.UpdateAppointmentTypeDataProvider.VALID_UPDATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_USER_JWT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the delete appointment type functionality.
 */
public class DeleteAppointmentTypeIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Tipo de cita eliminada exitosamente";
    private static final Function<String, String> MESSAGE_NOT_FOUND = parameter -> String
            .format("Tipo de cita con id %s no encontrado(a)", parameter);
    private static final String MESSAGE_CONFLICT = "No se puede eliminar el/la tipo de cita porque está en uso en otros registros";
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    @Test
    void noUser_DeleteAppointmentTypeWithValidParams_Forbidden() throws Exception {
        mockMvc.perform(delete("/appointment/type/{id}", 3))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_DeleteAppointmentTypeWithValidParams_WithUse_Forbidden() throws Exception {
        mockMvc.perform(delete("/appointment/type/{id}", VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getId()))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_DeleteAppointmentTypeWithInvalidParams_ID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(delete("/appointment/type/{id}", INVALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getId()))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_DeleteAppointmentTypeWithInvalidParams_ID_Negative_Forbidden() throws Exception {
        mockMvc.perform(delete("/appointment/type/{id}", -10))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_DeleteAppointmentTypeWithInvalidParams_ID_NotNumber_Forbidden() throws Exception {
        mockMvc.perform(delete("/appointment/type/{id}", "abcde"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER

    @Test
    @DirtiesContext
    void user_DeleteAppointmentTypeWithValidParams_Ok() throws Exception {
        mockMvc.perform(delete("/appointment/type/{id}", 3)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(MESSAGE_OK));
    }

    @Test
    void user_DeleteAppointmentTypeWithValidParams_WithUse_Conflict() throws Exception {
        mockMvc.perform(delete("/appointment/type/{id}", VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getId())
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value(MESSAGE_CONFLICT));
    }

    @Test
    void user_DeleteAppointmentTypeWithInvalidParams_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(delete("/appointment/type/{id}", INVALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getId())
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(
                        jsonPath("$.message")
                                .value(MESSAGE_NOT_FOUND
                                        .apply(INVALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getId().toString())));
    }

    @Test
    void user_DeleteAppointmentTypeWithInvalidParams_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/appointment/type/{id}", -10)
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
    void user_DeleteAppointmentTypeWithInvalidParams_ID_NotNumber_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/appointment/type/{id}", "abcde")
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

    // Role: ADMIN
    @Test
    @DirtiesContext
    void admin_DeleteAppointmentTypeWithValidParams_Ok() throws Exception {
        mockMvc.perform(delete("/appointment/type/{id}", 3)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(MESSAGE_OK));
    }

    @Test
    void admin_DeleteAppointmentTypeWithValidParams_WithUse_Conflict() throws Exception {
        mockMvc.perform(delete("/appointment/type/{id}", VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getId())
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value(MESSAGE_CONFLICT));
    }

    @Test
    void admin_DeleteAppointmentTypeWithInvalidParams_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(delete("/appointment/type/{id}", INVALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getId())
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(
                        jsonPath("$.message")
                                .value(MESSAGE_NOT_FOUND
                                        .apply(INVALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getId().toString())));
    }

    @Test
    void admin_DeleteAppointmentTypeWithInvalidParams_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/appointment/type/{id}", -10)
                .header("Authorization", BEARER_ADMIN_JWT))
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
    void admin_DeleteAppointmentTypeWithInvalidParams_ID_NotNumber_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/appointment/type/{id}", "abcde")
                .header("Authorization", BEARER_ADMIN_JWT))
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
