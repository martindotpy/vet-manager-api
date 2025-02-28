package com.vluepixel.vetmanager.api.sale.core.integration.appointment;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_USER_JWT;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.INVALID_ID_NOT_FOUND_UPDATE_APPOINTMET_SALE_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the delete sale appointment functionality.
 */
public class DeleteSaleAppointmentIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_CONFLICT = "No se puede eliminar el/la venta porque está en uso en otros registros";
    private static final Function<String, String> MESSAGE_NOT_FOUND = parameter -> String
            .format("Venta con id %s no encontrado(a)", parameter);
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    @Test
    void noUser_DeleteSaleAppointmentWithValidParams_Forbidden() throws Exception {
        mockMvc.perform(delete("/sale/{id}", 1))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_DeleteSaleAppointmentWithValidParams_ID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(delete("/sale/{id}", INVALID_ID_NOT_FOUND_UPDATE_APPOINTMET_SALE_REQUEST.getId()))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_DeleteSaleAppointmentWithValidParams_ID_Negative_Forbidden() throws Exception {
        mockMvc.perform(delete("/sale/{id}", -10))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_DeleteSaleAppointmentWithValidParams_ID_NotNumber_Forbidden() throws Exception {
        mockMvc.perform(delete("/sale/{id}", "abcde"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER
    @Test
    void user_DeleteSaleAppointmentWithValidParams_Conflict() throws Exception {
        mockMvc.perform(delete("/sale/{id}", 1)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value(MESSAGE_CONFLICT));
    }

    @Test
    void user_DeleteSaleAppointmentWithValidParams_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(delete("/sale/{id}", INVALID_ID_NOT_FOUND_UPDATE_APPOINTMET_SALE_REQUEST.getId())
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND
                        .apply(INVALID_ID_NOT_FOUND_UPDATE_APPOINTMET_SALE_REQUEST.getId().toString())));
    }

    @Test
    void user_DeleteSaleAppointmentWithValidParams_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/sale/{id}", -10)
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
    void user_DeleteSaleAppointmentWithValidParams_ID_NotNumber_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/sale/{id}", "abcde")
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
    void admin_DeleteSaleAppointmentWithValidParams_Conflict() throws Exception {
        mockMvc.perform(delete("/sale/{id}", 1)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value(MESSAGE_CONFLICT));
    }

    @Test
    void admin_DeleteSaleAppointmentWithValidParams_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(delete("/sale/{id}", INVALID_ID_NOT_FOUND_UPDATE_APPOINTMET_SALE_REQUEST.getId())
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND
                        .apply(INVALID_ID_NOT_FOUND_UPDATE_APPOINTMET_SALE_REQUEST.getId().toString())));
    }

    @Test
    void admin_DeleteSaleAppointmentWithValidParams_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/sale/{id}", -10)
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
    void admin_DeleteSaleAppointmentWithValidParams_ID_NotNumber_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/sale/{id}", "abcde")
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
