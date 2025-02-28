package com.vluepixel.vetmanager.api.sale.core.integration.appointment;

import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.VALID_UPDATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the get sale appointment use case.
 */
public class UpdateSaleAppointmentIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Venta actualizada exitosamente";
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER

    // Role: ADMIN
    @Test
    void admin_UpdateSaleAppointmentWithValidArguments_Ok() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getId()),
                        jsonPath("$.content.discount").value(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getDiscount()));
    }
}
