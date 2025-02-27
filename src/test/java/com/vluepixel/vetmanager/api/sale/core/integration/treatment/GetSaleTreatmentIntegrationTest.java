package com.vluepixel.vetmanager.api.sale.core.integration.treatment;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_USER_JWT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the get sale treatment use case.
 */
public class GetSaleTreatmentIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Venta encontrada";
    private static final Function<String, String> MESSAGE_NOT_FOUND = parameter -> String
            .format("Sale con id %s no encontrado(a)", parameter);
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    @Test
    void noUser_GetSaleTreatmentWithValidParams_Forbidden() throws Exception {
        mockMvc.perform(get("/sale/{id}", 2))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetSaleTreatmentWithInvalidParams_ID_Forbidden() throws Exception {
        mockMvc.perform(get("/sale/{id}", 10))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetSaleTreatmentWithInvalidParams_ID_Negative_Forbidden() throws Exception {
        mockMvc.perform(get("/sale/{id}", -10))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetSaleTreatmentWithInvalidParams_ID_NotNumber_Forbidden() throws Exception {
        mockMvc.perform(get("/sale/{id}", "abcde"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER
    @Test
    void user_GetSaleTreatmentWithValidParams_Ok() throws Exception {
        mockMvc.perform(get("/sale/{id}", 3)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.treatment").isMap());
    }

    @Test
    void user_GetSaleTreatmentWithInvalidParams_ID_NotFound() throws Exception {
        mockMvc.perform(get("/sale/{id}", 10)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND.apply("10")));
    }

    @Test
    void user_GetSaleTreatmentWithInvalidParams_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(get("/sale/{id}", -10)
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
    void user_GetSaleTreatmentWithInvalidParams_ID_NotNumber_UnprocessableEntity() throws Exception {
        mockMvc.perform(get("/sale/{id}", "abcde")
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
    void admin_GetSaleAppointmentWithValidParams_Ok() throws Exception {
        mockMvc.perform(get("/sale/{id}", 3)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.treatment").isMap());
    }

    @Test
    void admin_GetSaleAppointmentWithInvalidParams_ID_NotFound() throws Exception {
        mockMvc.perform(get("/sale/{id}", 10)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND.apply("10")));
    }

    @Test
    void admin_GetSaleAppointmentWithInvalidParams_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(get("/sale/{id}", -10)
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
    void admin_GetSaleAppointmentWithInvalidParams_ID_NotNumber_UnprocessableEntity() throws Exception {
        mockMvc.perform(get("/sale/{id}", "abcde")
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
