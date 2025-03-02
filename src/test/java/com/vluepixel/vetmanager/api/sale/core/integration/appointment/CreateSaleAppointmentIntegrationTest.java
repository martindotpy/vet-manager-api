package com.vluepixel.vetmanager.api.sale.core.integration.appointment;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_USER_JWT;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.CreateAppointmentSaleDataProvider.INVALID_APPOINTMENT_ID_NEGATIVE_CREATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.CreateAppointmentSaleDataProvider.INVALID_APPOINTMENT_ID_NOT_FOUND_CREATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.CreateAppointmentSaleDataProvider.INVALID_APPOINTMENT_ID_NULL_CREATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.CreateAppointmentSaleDataProvider.INVALID_BILL_ID_NEGATIVE_CREATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.CreateAppointmentSaleDataProvider.INVALID_BILL_ID_NOT_FOUND_CREATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.CreateAppointmentSaleDataProvider.INVALID_BILL_ID_NULL_CREATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.CreateAppointmentSaleDataProvider.INVALID_DISCOUNT_NEGATIVE_CREATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.CreateAppointmentSaleDataProvider.INVALID_DISCOUNT_NULL_CREATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.CreateAppointmentSaleDataProvider.INVALID_DISCOUNT_TOO_BIG_CREATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.CreateAppointmentSaleDataProvider.INVALID_DISCOUNT_ZERO_CREATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.CreateAppointmentSaleDataProvider.VALID_CREATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.CreateAppointmentSaleDataProvider.VALID_DISCOUNT_MAX_VALUE_CREATE_APPOINTMENT_SALE_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.function.Function;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the create sale appointment use case.
 */
public class CreateSaleAppointmentIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Venta creada exitosamente";
    private static final Function<String, String> MESSAGE_NOT_FOUND = parameter -> String
            .format("Cita con id %s no encontrado(a)", parameter);
    private static final Function<String, String> MESSAGE_BILL_NOT_FOUND = parameter -> String
            .format("Cuenta con id %s no encontrado(a)", parameter);
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    @Test
    void noUser_CreateSaleAppointmentWithValidArguments_Forbidden() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_APPOINTMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Bill ID
    @Test
    void noUser_CreateSaleAppointmentWithInvalidArguments_BillID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_BILL_ID_NOT_FOUND_CREATE_APPOINTMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateSaleAppointmentWithInvalidArguments_BillID_Negative_Forbidden() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_BILL_ID_NEGATIVE_CREATE_APPOINTMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateSaleAppointmentWithInvalidArguments_BillID_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_BILL_ID_NULL_CREATE_APPOINTMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Appointment ID
    @Test
    void noUser_CreateSaleAppointmentWithInvalidArguments_AppointmentID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_APPOINTMENT_ID_NOT_FOUND_CREATE_APPOINTMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateSaleAppointmentWithInvalidArguments_AppointmentID_Negative_Forbidden()
            throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_APPOINTMENT_ID_NEGATIVE_CREATE_APPOINTMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateSaleAppointmentWithInvalidArguments_AppointmentID_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_APPOINTMENT_ID_NULL_CREATE_APPOINTMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Discount
    @Test
    void noUser_CreateSaleAppointmentWithInvalidArguments_Discount_TooBig_Forbidden() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DISCOUNT_TOO_BIG_CREATE_APPOINTMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateSaleAppointmentWithValidArguments_Discount_MaxValue_Forbidden() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DISCOUNT_MAX_VALUE_CREATE_APPOINTMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateSaleAppointmentWithInvalidArguments_Discount_Zero_Forbidden() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DISCOUNT_ZERO_CREATE_APPOINTMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateSaleAppointmentWithInvalidArguments_Discount_Negative_Forbidden() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DISCOUNT_NEGATIVE_CREATE_APPOINTMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateSaleAppointmentWithInvalidArguments_Discount_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DISCOUNT_NULL_CREATE_APPOINTMENT_SALE_REQUEST)))
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
    void user_CreateSaleAppointmentWithValidArguments_Ok() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(4),
                        jsonPath("$.content.discount").value(VALID_CREATE_APPOINTMENT_SALE_REQUEST.getDiscount()),
                        jsonPath("$.content.appointment.id")
                                .value(VALID_CREATE_APPOINTMENT_SALE_REQUEST.getAppointmentId()));
    }

    // Bill ID
    @Test
    void user_CreateSaleAppointmentWithInvalidArguments_BillID_NotFound_NotFound() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_BILL_ID_NOT_FOUND_CREATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpectAll(
                        jsonPath("$.message").value(
                                MESSAGE_BILL_NOT_FOUND.apply(INVALID_BILL_ID_NOT_FOUND_CREATE_APPOINTMENT_SALE_REQUEST
                                        .getBillId().toString())));
    }

    @Test
    void user_CreateSaleAppointmentWithInvalidArguments_BillID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_BILL_ID_NEGATIVE_CREATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
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
    void user_CreateSaleAppointmentWithInvalidArguments_BillID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_BILL_ID_NULL_CREATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
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
    void user_CreateSaleAppointmentWithInvalidArguments_AppointmentID_NotFound_NotFound() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_APPOINTMENT_ID_NOT_FOUND_CREATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpectAll(
                        jsonPath("$.message").value(
                                MESSAGE_NOT_FOUND.apply(INVALID_APPOINTMENT_ID_NOT_FOUND_CREATE_APPOINTMENT_SALE_REQUEST
                                        .getAppointmentId().toString())));
    }

    @Test
    void user_CreateSaleAppointmentWithInvalidArguments_AppointmentID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_APPOINTMENT_ID_NEGATIVE_CREATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
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
    void user_CreateSaleAppointmentWithInvalidArguments_AppointmentID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_APPOINTMENT_ID_NULL_CREATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
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
    void user_CreateSaleAppointmentWithInvalidArguments_Discount_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DISCOUNT_TOO_BIG_CREATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
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
    @Order(2)
    @DirtiesContext
    void user_CreateSaleAppointmentWithValidArguments_Discount_MaxValue_Ok() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DISCOUNT_MAX_VALUE_CREATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(4),
                        jsonPath("$.content.discount")
                                .value(VALID_DISCOUNT_MAX_VALUE_CREATE_APPOINTMENT_SALE_REQUEST.getDiscount()),
                        jsonPath("$.content.appointment.id")
                                .value(VALID_DISCOUNT_MAX_VALUE_CREATE_APPOINTMENT_SALE_REQUEST.getAppointmentId()));
    }

    @Test
    void user_CreateSaleAppointmentWithInvalidArguments_Discount_Zero_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DISCOUNT_ZERO_CREATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
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
    void user_CreateSaleAppointmentWithInvalidArguments_Discount_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DISCOUNT_NEGATIVE_CREATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
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
    void user_CreateSaleAppointmentWithInvalidArguments_Discount_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DISCOUNT_NULL_CREATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("discount"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El descuento es requerido"));
    }

    // Role: ADMIN
    @Test
    @Order(2)
    @DirtiesContext
    void admin_CreateSaleAppointmentWithValidArguments_Ok() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(4),
                        jsonPath("$.content.discount").value(VALID_CREATE_APPOINTMENT_SALE_REQUEST.getDiscount()),
                        jsonPath("$.content.appointment.id")
                                .value(VALID_CREATE_APPOINTMENT_SALE_REQUEST.getAppointmentId()));
    }

    // Bill ID
    @Test
    void admin_CreateSaleAppointmentWithInvalidArguments_BillID_NotFound_NotFound() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_BILL_ID_NOT_FOUND_CREATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpectAll(
                        jsonPath("$.message").value(
                                MESSAGE_BILL_NOT_FOUND.apply(INVALID_BILL_ID_NOT_FOUND_CREATE_APPOINTMENT_SALE_REQUEST
                                        .getBillId().toString())));
    }

    @Test
    void admin_CreateSaleAppointmentWithInvalidArguments_BillID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_BILL_ID_NEGATIVE_CREATE_APPOINTMENT_SALE_REQUEST))
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
    void admin_CreateSaleAppointmentWithInvalidArguments_BillID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_BILL_ID_NULL_CREATE_APPOINTMENT_SALE_REQUEST))
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
    void admin_CreateSaleAppointmentWithInvalidArguments_AppointmentID_NotFound_NotFound() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_APPOINTMENT_ID_NOT_FOUND_CREATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpectAll(
                        jsonPath("$.message").value(
                                MESSAGE_NOT_FOUND.apply(INVALID_APPOINTMENT_ID_NOT_FOUND_CREATE_APPOINTMENT_SALE_REQUEST
                                        .getAppointmentId().toString())));
    }

    @Test
    void admin_CreateSaleAppointmentWithInvalidArguments_AppointmentID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_APPOINTMENT_ID_NEGATIVE_CREATE_APPOINTMENT_SALE_REQUEST))
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
    void admin_CreateSaleAppointmentWithInvalidArguments_AppointmentID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_APPOINTMENT_ID_NULL_CREATE_APPOINTMENT_SALE_REQUEST))
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
    void admin_CreateSaleAppointmentWithInvalidArguments_Discount_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DISCOUNT_TOO_BIG_CREATE_APPOINTMENT_SALE_REQUEST))
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
    @Order(2)
    @DirtiesContext
    void admin_CreateSaleAppointmentWithValidArguments_Discount_MaxValue_Ok() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DISCOUNT_MAX_VALUE_CREATE_APPOINTMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(4),
                        jsonPath("$.content.discount")
                                .value(VALID_DISCOUNT_MAX_VALUE_CREATE_APPOINTMENT_SALE_REQUEST.getDiscount()),
                        jsonPath("$.content.appointment.id")
                                .value(VALID_DISCOUNT_MAX_VALUE_CREATE_APPOINTMENT_SALE_REQUEST.getAppointmentId()));
    }

    @Test
    void admin_CreateSaleAppointmentWithInvalidArguments_Discount_Zero_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DISCOUNT_ZERO_CREATE_APPOINTMENT_SALE_REQUEST))
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
    void admin_CreateSaleAppointmentWithInvalidArguments_Discount_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DISCOUNT_NEGATIVE_CREATE_APPOINTMENT_SALE_REQUEST))
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
    void admin_CreateSaleAppointmentWithInvalidArguments_Discount_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper
                        .writeValueAsString(INVALID_DISCOUNT_NULL_CREATE_APPOINTMENT_SALE_REQUEST))
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
