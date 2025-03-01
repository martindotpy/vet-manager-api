package com.vluepixel.vetmanager.api.sale.core.integration.appointment;

import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.INVALID_APPOINTMENT_ID_NEGATIVE_UPDATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.INVALID_APPOINTMENT_ID_NOT_FOUND_UPDATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.INVALID_APPOINTMENT_ID_NULL_UPDATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.INVALID_BILL_ID_NEGATIVE_UPDATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.INVALID_BILL_ID_NOT_FOUND_UPDATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.INVALID_BILL_ID_NULL_UPDATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.INVALID_DISCOUNT_NEGATIVE_UPDATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.INVALID_DISCOUNT_NULL_UPDATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.INVALID_DISCOUNT_TOO_BIG_UPDATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.INVALID_DISCOUNT_ZERO_UPDATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.INVALID_ID_NEGATIVE_UPDATE_APPOINTMET_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.INVALID_ID_NOT_FOUND_UPDATE_APPOINTMET_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.INVALID_ID_NULL_UPDATE_APPOINTMET_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.VALID_DISCOUNT_MAX_VALUE_UPDATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.VALID_UPDATE_APPOINTMENT_SALE_REQUEST;
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
 * Integration tests for the get sale appointment use case.
 */
public class UpdateSaleAppointmentIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Venta actualizada exitosamente";
    private static final Function<String, String> MESSAGE_NOT_FOUND = parameter -> String
            .format("Venta con id %s no encontrado(a)", parameter);
    private static final Function<String, String> MESSAGE_BILL_NOT_FOUND = parameter -> String
            .format("Cita con id %s no encontrado(a)", parameter);
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

    @Test
    void admin_UpdateSaleAppointmentWithInvalidArguments_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NOT_FOUND_UPDATE_APPOINTMET_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message")
                        .value(MESSAGE_NOT_FOUND
                                .apply(INVALID_ID_NOT_FOUND_UPDATE_APPOINTMET_SALE_REQUEST.getId().toString())));
    }

    @Test
    void admin_UpdateSaleAppointmentWithInvalidArguments_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NEGATIVE_UPDATE_APPOINTMET_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El id debe ser mayor a 0"));
    }

    @Test
    void admin_UpdateSaleAppointmentWithInvalidArguments_ID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NULL_UPDATE_APPOINTMET_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El id es requerido"));
    }

    // Bill ID
    @Test // TODO: Add invalidation when Bill ID is Not Founded
    void admin_UpdateSaleAppointmentWithInvalidArguments_BillID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_BILL_ID_NOT_FOUND_UPDATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message")
                        .value(MESSAGE_NOT_FOUND
                                .apply(INVALID_ID_NOT_FOUND_UPDATE_APPOINTMET_SALE_REQUEST.getId().toString())));
    }

    @Test
    void admin_UpdateSaleAppointmentWithInvalidArguments_BillID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_BILL_ID_NEGATIVE_UPDATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("bill_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El id de la cuenta debe ser mayor a 0"));
    }

    @Test
    void admin_UpdateSaleAppointmentWithInvalidArguments_BillID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_BILL_ID_NULL_UPDATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("bill_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El id de la cuenta es requerido"));
    }

    // Appointment ID
    @Test
    void admin_UpdateSaleAppointmentWithInvalidArguments_AppointmentID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_APPOINTMENT_ID_NOT_FOUND_UPDATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message")
                        .value(MESSAGE_BILL_NOT_FOUND
                                .apply(INVALID_APPOINTMENT_ID_NOT_FOUND_UPDATE_APPOINTMENT_SALE_REQUEST
                                        .getAppointmentId()
                                        .toString())));
    }

    @Test
    void admin_UpdateSaleAppointmentWithInvalidArguments_AppointmentID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_APPOINTMENT_ID_NEGATIVE_UPDATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("appointment_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El id de la cita debe ser mayor a 0"));
    }

    @Test
    void admin_UpdateSaleAppointmentWithInvalidArguments_AppointmentID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_APPOINTMENT_ID_NULL_UPDATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("appointment_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El id de la cita es requerido"));
    }

    // Discount
    @Test
    void admin_UpdateSaleAppointmentWithInvalidArguments_Discount_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DISCOUNT_TOO_BIG_UPDATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("discount"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El descuento no puede ser mayor a 100"));
    }

    @Test
    @DirtiesContext
    void admin_UpdateSaleAppointmentWithValidArguments_Discount_MaxValue_Ok() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DISCOUNT_MAX_VALUE_UPDATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id")
                                .value(VALID_DISCOUNT_MAX_VALUE_UPDATE_APPOINTMENT_SALE_REQUEST.getId()),
                        jsonPath("$.content.discount")
                                .value(VALID_DISCOUNT_MAX_VALUE_UPDATE_APPOINTMENT_SALE_REQUEST.getDiscount()));
    }

    @Test
    void admin_UpdateSaleAppointmentWithInvalidArguments_Discount_Zero_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DISCOUNT_ZERO_UPDATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("discount"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El descuento debe ser mayor a 0"));
    }

    @Test
    void admin_UpdateSaleAppointmentWithInvalidArguments_Discount_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DISCOUNT_NEGATIVE_UPDATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("discount"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El descuento debe ser mayor a 0"));
    }

    @Test
    void admin_UpdateSaleAppointmentWithInvalidArguments_Discount_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DISCOUNT_NULL_UPDATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("discount"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El descuento es requerido"));
    }
}
