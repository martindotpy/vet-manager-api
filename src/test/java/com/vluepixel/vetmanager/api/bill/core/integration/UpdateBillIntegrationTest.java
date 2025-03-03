package com.vluepixel.vetmanager.api.bill.core.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_USER_JWT;
import static com.vluepixel.vetmanager.api.bill.core.data.UpdateBillDataProvider.INVALID_CLIENT_ID_NEGATIVE_UPDATE_BILL_REQUEST;
import static com.vluepixel.vetmanager.api.bill.core.data.UpdateBillDataProvider.INVALID_CLIENT_ID_NOT_FOUND_UPDATE_BILL_REQUEST;
import static com.vluepixel.vetmanager.api.bill.core.data.UpdateBillDataProvider.INVALID_CLIENT_NULL_NEGATIVE_UPDATE_BILL_REQUEST;
import static com.vluepixel.vetmanager.api.bill.core.data.UpdateBillDataProvider.INVALID_DISCOUNT_NEGATIVE_UPDATE_BILL_REQUEST;
import static com.vluepixel.vetmanager.api.bill.core.data.UpdateBillDataProvider.INVALID_DISCOUNT_NULL_UPDATE_BILL_REQUEST;
import static com.vluepixel.vetmanager.api.bill.core.data.UpdateBillDataProvider.INVALID_DISCOUNT_TOO_BIG_UPDATE_BILL_REQUEST;
import static com.vluepixel.vetmanager.api.bill.core.data.UpdateBillDataProvider.VALID_DISCOUNT_ZERO_UPDATE_BILL_REQUEST;
import static com.vluepixel.vetmanager.api.bill.core.data.UpdateBillDataProvider.INVALID_ID_NEGATIVE_UPDATE_BILL_REQUEST;
import static com.vluepixel.vetmanager.api.bill.core.data.UpdateBillDataProvider.INVALID_ID_NOT_FOUND_UPDATE_BILL_REQUEST;
import static com.vluepixel.vetmanager.api.bill.core.data.UpdateBillDataProvider.INVALID_TOTAL_PAID_NEGATIVE_UPDATE_BILL_REQUEST;
import static com.vluepixel.vetmanager.api.bill.core.data.UpdateBillDataProvider.INVALID_TOTAL_PAID_NULL_UPDATE_BILL_REQUEST;
import static com.vluepixel.vetmanager.api.bill.core.data.UpdateBillDataProvider.VALID_TOTAL_PAID_ZERO_UPDATE_BILL_REQUEST;
import static com.vluepixel.vetmanager.api.bill.core.data.UpdateBillDataProvider.VALID_DISCOUNT_MAX_VALUE_UPDATE_BILL_REQUEST;
import static com.vluepixel.vetmanager.api.bill.core.data.UpdateBillDataProvider.VALID_TOTAL_PAID_GREATER_THAN_TOTAL_UPDATE_BILL_REQUEST;
import static com.vluepixel.vetmanager.api.bill.core.data.UpdateBillDataProvider.VALID_UPDATE_BILL_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the update bill use case.
 */
public class UpdateBillIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Cuenta actualizada exitosamente";
    private static final Function<String, String> MESSAGE_NOT_FOUND = parameter -> String
            .format("Cuenta con id %s no encontrado(a)", parameter);
    private static final String MESSAGE_CLIENT_NOT_FOUND = "Cliente no encontrado(a)";
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    @Test
    @DirtiesContext
    void noUser_UpdateBillWithValidArguments_Ok() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_BILL_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateBillWithInvalidArguments_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NOT_FOUND_UPDATE_BILL_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateFORMESSAGE_FORBIDDENents_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NEGATIVE_UPDATE_BILL_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Discount
    @Test
    void noUser_UpdateBillWithInvalidArguments_Discount_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_TOO_BIG_UPDATE_BILL_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    @DirtiesContext
    void noUser_UpdateBillWithValidArguments_Discount_MaxValue_Ok() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DISCOUNT_MAX_VALUE_UPDATE_BILL_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateBillWithInvalidArguments_Discount_Zero_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DISCOUNT_ZERO_UPDATE_BILL_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateBillWithInvalidArguments_Discount_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_NEGATIVE_UPDATE_BILL_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateBillWithInvalidArguments_Discount_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_NULL_UPDATE_BILL_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Total Paid
    @Test
    @DirtiesContext
    void noUser_UpdateBillWithInvalidArguments_TotalPaid_GreatherThanTotal_Ok() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_TOTAL_PAID_GREATER_THAN_TOTAL_UPDATE_BILL_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    @DirtiesContext
    void noUser_UpdateBillWithInvalidArguments_TotalPaid_Zero_Ok() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_TOTAL_PAID_ZERO_UPDATE_BILL_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateBillWithInvalidArguments_TotalPaid_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_TOTAL_PAID_NEGATIVE_UPDATE_BILL_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateBillWithInvalidArguments_TotalPaid_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_TOTAL_PAID_NULL_UPDATE_BILL_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Client ID
    @Test
    void noUser_UpdateBillWithInvalidArguments_ClientID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CLIENT_ID_NOT_FOUND_UPDATE_BILL_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateBFORMESSAGE_FORBIDDENnts_ClientID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CLIENT_ID_NEGATIVE_UPDATE_BILL_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateBillWithInvalidArguments_ClientID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CLIENT_NULL_NEGATIVE_UPDATE_BILL_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER
    @Test
    @DirtiesContext
    void user_UpdateBillWithValidArguments_Ok() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_UPDATE_BILL_REQUEST.getId()),
                        jsonPath("$.content.total_paid")
                                .value(VALID_UPDATE_BILL_REQUEST.getTotalPaid().doubleValue()),
                        jsonPath("$.content.discount").value(VALID_UPDATE_BILL_REQUEST.getDiscount()),
                        jsonPath("$.content.client.id").value(VALID_UPDATE_BILL_REQUEST.getClientId()));
    }

    @Test
    void user_UpdateBillWithInvalidArguments_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NOT_FOUND_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message")
                        .value(MESSAGE_NOT_FOUND.apply(INVALID_ID_NOT_FOUND_UPDATE_BILL_REQUEST.getId().toString())));
    }

    @Test
    void user_UpdateBillWithInvalidArguments_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NEGATIVE_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id debe ser mayor a 0"));
    }

    // Discount
    @Test
    void user_UpdateBillWithInvalidArguments_Discount_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_TOO_BIG_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("discount"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El descuento no puede ser mayor a 100"));
    }

    @Test
    @DirtiesContext
    void user_UpdateBillWithValidArguments_Discount_MaxValue_Ok() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DISCOUNT_MAX_VALUE_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_DISCOUNT_MAX_VALUE_UPDATE_BILL_REQUEST.getId()),
                        jsonPath("$.content.total_paid")
                                .value(VALID_DISCOUNT_MAX_VALUE_UPDATE_BILL_REQUEST.getTotalPaid().doubleValue()),
                        jsonPath("$.content.discount")
                                .value(VALID_DISCOUNT_MAX_VALUE_UPDATE_BILL_REQUEST.getDiscount()),
                        jsonPath("$.content.client.id")
                                .value(VALID_DISCOUNT_MAX_VALUE_UPDATE_BILL_REQUEST.getClientId()));
    }

    @Test
    @DirtiesContext
    void user_UpdateBillWithInvalidArguments_Discount_Zero_Ok() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DISCOUNT_ZERO_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_DISCOUNT_ZERO_UPDATE_BILL_REQUEST.getId()),
                        jsonPath("$.content.total_paid")
                                .value(VALID_DISCOUNT_ZERO_UPDATE_BILL_REQUEST.getTotalPaid().doubleValue()),
                        jsonPath("$.content.discount")
                                .value(VALID_DISCOUNT_ZERO_UPDATE_BILL_REQUEST.getDiscount()),
                        jsonPath("$.content.client.id")
                                .value(VALID_DISCOUNT_ZERO_UPDATE_BILL_REQUEST.getClientId()));
    }

    @Test
    void user_UpdateBillWithInvalidArguments_Discount_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_NEGATIVE_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("discount"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El descuento debe ser mayor o igual a 0"));
    }

    @Test
    void user_UpdateBillWithInvalidArguments_Discount_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_NULL_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("discount"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El descuento es requerido"));
    }

    // Total Paid
    @Test
    void user_UpdateBillWithInvalidArguments_TotalPaid_GreatherThanTotal_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_TOTAL_PAID_GREATER_THAN_TOTAL_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("total_paid"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El total pagado no puede ser mayor al total de la cuenta"));
    }

    @Test
    @DirtiesContext
    void user_UpdateBillWithInvalidArguments_TotalPaid_Zero_Ok() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_TOTAL_PAID_ZERO_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_TOTAL_PAID_ZERO_UPDATE_BILL_REQUEST.getId()),
                        jsonPath("$.content.total_paid")
                                .value(VALID_TOTAL_PAID_ZERO_UPDATE_BILL_REQUEST.getTotalPaid().doubleValue()),
                        jsonPath("$.content.discount")
                                .value(VALID_TOTAL_PAID_ZERO_UPDATE_BILL_REQUEST.getDiscount()),
                        jsonPath("$.content.client.id")
                                .value(VALID_TOTAL_PAID_ZERO_UPDATE_BILL_REQUEST.getClientId()));
    }

    @Test
    void user_UpdateBillWithInvalidArguments_TotalPaid_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_TOTAL_PAID_NEGATIVE_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("total_paid"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El total pagado debe ser mayor o igual a 0"));
    }

    @Test
    void user_UpdateBillWithInvalidArguments_TotalPaid_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_TOTAL_PAID_NULL_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("total_paid"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El total pagado es requerido"));
    }

    // Client ID
    @Test
    void user_UpdateBillWithInvalidArguments_ClientID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CLIENT_ID_NOT_FOUND_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_CLIENT_NOT_FOUND));
    }

    @Test
    void user_UpdateBillWithInvalidArguments_ClientID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CLIENT_ID_NEGATIVE_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("client_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del cliente debe ser mayor a 0"));
    }

    @Test
    void user_UpdateBillWithInvalidArguments_ClientID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CLIENT_NULL_NEGATIVE_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("client_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del cliente es requerido"));
    }

    // Role: ADMIN
    @Test
    @DirtiesContext
    void admin_UpdateBillWithValidArguments_Ok() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_UPDATE_BILL_REQUEST.getId()),
                        jsonPath("$.content.total_paid")
                                .value(VALID_UPDATE_BILL_REQUEST.getTotalPaid().doubleValue()),
                        jsonPath("$.content.discount").value(VALID_UPDATE_BILL_REQUEST.getDiscount()),
                        jsonPath("$.content.client.id").value(VALID_UPDATE_BILL_REQUEST.getClientId()));
    }

    @Test
    void admin_UpdateBillWithInvalidArguments_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NOT_FOUND_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message")
                        .value(MESSAGE_NOT_FOUND.apply(INVALID_ID_NOT_FOUND_UPDATE_BILL_REQUEST.getId().toString())));
    }

    @Test
    void admin_UpdateBillWithInvalidArguments_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NEGATIVE_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id debe ser mayor a 0"));
    }

    // Discount
    @Test
    void admin_UpdateBillWithInvalidArguments_Discount_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_TOO_BIG_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("discount"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El descuento no puede ser mayor a 100"));
    }

    @Test
    @DirtiesContext
    void admin_UpdateBillWithValidArguments_Discount_MaxValue_Ok() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DISCOUNT_MAX_VALUE_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_DISCOUNT_MAX_VALUE_UPDATE_BILL_REQUEST.getId()),
                        jsonPath("$.content.total_paid")
                                .value(VALID_DISCOUNT_MAX_VALUE_UPDATE_BILL_REQUEST.getTotalPaid().doubleValue()),
                        jsonPath("$.content.discount")
                                .value(VALID_DISCOUNT_MAX_VALUE_UPDATE_BILL_REQUEST.getDiscount()),
                        jsonPath("$.content.client.id")
                                .value(VALID_DISCOUNT_MAX_VALUE_UPDATE_BILL_REQUEST.getClientId()));
    }

    @Test
    @DirtiesContext
    void admin_UpdateBillWithInvalidArguments_Discount_Zero_Ok() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DISCOUNT_ZERO_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_DISCOUNT_ZERO_UPDATE_BILL_REQUEST.getId()),
                        jsonPath("$.content.total_paid")
                                .value(VALID_DISCOUNT_ZERO_UPDATE_BILL_REQUEST.getTotalPaid().doubleValue()),
                        jsonPath("$.content.discount")
                                .value(VALID_DISCOUNT_ZERO_UPDATE_BILL_REQUEST.getDiscount()),
                        jsonPath("$.content.client.id")
                                .value(VALID_DISCOUNT_ZERO_UPDATE_BILL_REQUEST.getClientId()));
    }

    @Test
    void admin_UpdateBillWithInvalidArguments_Discount_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_NEGATIVE_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("discount"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El descuento debe ser mayor o igual a 0"));
    }

    @Test
    void admin_UpdateBillWithInvalidArguments_Discount_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_NULL_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("discount"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El descuento es requerido"));
    }

    // Total Paid
    @Test
    void admin_UpdateBillWithInvalidArguments_TotalPaid_GreatherThanTotal_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_TOTAL_PAID_GREATER_THAN_TOTAL_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("total_paid"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El total pagado no puede ser mayor al total de la cuenta"));
    }

    @Test
    @DirtiesContext
    void admin_UpdateBillWithInvalidArguments_TotalPaid_Zero_Ok() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_TOTAL_PAID_ZERO_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_TOTAL_PAID_ZERO_UPDATE_BILL_REQUEST.getId()),
                        jsonPath("$.content.total_paid")
                                .value(VALID_TOTAL_PAID_ZERO_UPDATE_BILL_REQUEST.getTotalPaid().doubleValue()),
                        jsonPath("$.content.discount")
                                .value(VALID_TOTAL_PAID_ZERO_UPDATE_BILL_REQUEST.getDiscount()),
                        jsonPath("$.content.client.id")
                                .value(VALID_TOTAL_PAID_ZERO_UPDATE_BILL_REQUEST.getClientId()));
    }

    @Test
    void admin_UpdateBillWithInvalidArguments_TotalPaid_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_TOTAL_PAID_NEGATIVE_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("total_paid"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El total pagado debe ser mayor o igual a 0"));
    }

    @Test
    void admin_UpdateBillWithInvalidArguments_TotalPaid_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_TOTAL_PAID_NULL_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("total_paid"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El total pagado es requerido"));
    }

    // Client ID
    @Test
    void admin_UpdateBillWithInvalidArguments_ClientID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CLIENT_ID_NOT_FOUND_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_CLIENT_NOT_FOUND));
    }

    @Test
    void admin_UpdateBillWithInvalidArguments_ClientID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CLIENT_ID_NEGATIVE_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("client_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del cliente debe ser mayor a 0"));
    }

    @Test
    void admin_UpdateBillWithInvalidArguments_ClientID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CLIENT_NULL_NEGATIVE_UPDATE_BILL_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("client_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del cliente es requerido"));
    }
}
