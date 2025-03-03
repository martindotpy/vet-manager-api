package com.vluepixel.vetmanager.api.bill.core.integration;

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
 * Integration test for the delete bill functionality.
 */
public class DeleteBillIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Cuenta eliminada exitosamente";
    private static final Function<String, String> MESSAGE_NOT_FOUND = parameter -> String
            .format("Cuenta con id %s no encontrado(a)", parameter);
    private static final String MESSAGE_CONFLICT = "No se puede eliminar el/la cuenta porque está en uso en otros registros";

    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    @Test
    void noUser_DeleteBillWithValidParams_Forbidden() throws Exception {
        mockMvc.perform(delete("/bill/{id}", 3))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_DeleteBillWithInvalidParams_ID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(delete("/bill/{id}", 10))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_DeleteBillWithInvalidParams_ID_Invalid_Forbidden() throws Exception {
        mockMvc.perform(delete("/bill/{id}", "invalid"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_DeleteBillWithInvalidParams_ID_Negative_Forbidden() throws Exception {
        mockMvc.perform(delete("/bill/{id}", -1))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER
    @Test
    @DirtiesContext
    void user_DeleteBillWithValidParams_Appointment_Conflict() throws Exception {
        mockMvc.perform(delete("/bill/{id}", 1) // APPOINTMENT
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value(MESSAGE_CONFLICT));
    }

    @Test
    @DirtiesContext
    void user_DeleteBillWithValidParams_Product_Ok() throws Exception {
        mockMvc.perform(delete("/bill/{id}", 2) // PRODUCT
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(MESSAGE_OK));
    }

    @Test
    void user_DeleteBillWithInvalidParams_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(delete("/bill/{id}", 10)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND.apply("10")));
    }

    @Test
    void user_DeleteBillWithInvalidParams_ID_Invalid_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/bill/{id}", "invalid")
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("path.id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("Valor numérico inválido"));
    }

    @Test
    void user_DeleteBillWithInvalidParams_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/bill/{id}", -1)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("query.id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id debe ser mayor a 0"));
    }

    // Role: ADMIN
    @Test
    @DirtiesContext
    void admin_DeleteBillWithValidParams_Appointment_Conflict() throws Exception {
        mockMvc.perform(delete("/bill/{id}", 1) // APPOINTMENT
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value(MESSAGE_CONFLICT));
    }

    @Test
    @DirtiesContext
    void admin_DeleteBillWithValidParams_Product_Ok() throws Exception {
        mockMvc.perform(delete("/bill/{id}", 2) // PRODUCT
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(MESSAGE_OK));
    }

    @Test
    void admin_DeleteBillWithInvalidParams_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(delete("/bill/{id}", 10)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND.apply("10")));
    }

    @Test
    void admin_DeleteBillWithInvalidParams_ID_Invalid_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/bill/{id}", "invalid")
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("path.id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("Valor numérico inválido"));
    }

    @Test
    void admin_DeleteBillWithInvalidParams_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/bill/{id}", -1)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("query.id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id debe ser mayor a 0"));
    }
}
