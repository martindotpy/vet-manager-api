package com.vluepixel.vetmanager.api.bill.core.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_USER_JWT;
import static com.vluepixel.vetmanager.api.bill.core.data.CreateBillDataProvider.INVALID_CLIENT_ID_NEGATIVE_CREATE_BILL_REQUEST;
import static com.vluepixel.vetmanager.api.bill.core.data.CreateBillDataProvider.INVALID_CLIENT_ID_NOT_FOUND_CREATE_BILL_REQUEST;
import static com.vluepixel.vetmanager.api.bill.core.data.CreateBillDataProvider.INVALID_CLIENT_NULL_NEGATIVE_CREATE_BILL_REQUEST;
import static com.vluepixel.vetmanager.api.bill.core.data.CreateBillDataProvider.INVALID_DISCOUNT_NEGATIVE_CREATE_BILL_REQUEST;
import static com.vluepixel.vetmanager.api.bill.core.data.CreateBillDataProvider.INVALID_DISCOUNT_NULL_CREATE_BILL_REQUEST;
import static com.vluepixel.vetmanager.api.bill.core.data.CreateBillDataProvider.INVALID_DISCOUNT_TOO_BIG_CREATE_BILL_REQUEST;
import static com.vluepixel.vetmanager.api.bill.core.data.CreateBillDataProvider.VALID_CREATE_BILL_REQUEST;
import static com.vluepixel.vetmanager.api.bill.core.data.CreateBillDataProvider.VALID_DISCOUNT_MAX_VALUE_CREATE_BILL_REQUEST;
import static com.vluepixel.vetmanager.api.bill.core.data.CreateBillDataProvider.VALID_DISCOUNT_ZERO_CREATE_BILL_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the create bill use case.
 */
public class CreateBillIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Cuenta creada exitosamente";
    private static final String MESSAGE_NOT_FOUND = "Cliente no encontrado(a)";
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    @Test
    void noUser_CreateBillWithValidArguments_Forbidden() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_BILL_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Discount
    @Test
    void noUser_CreateBillWithInvalidArguments_Discount_TooBig_Forbidden() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_TOO_BIG_CREATE_BILL_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateBillWithValidArguments_Discount_MaxValue_Forbidden() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DISCOUNT_MAX_VALUE_CREATE_BILL_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateBillWithInvalidArguments_Discount_Zero_Forbidden() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DISCOUNT_ZERO_CREATE_BILL_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateBillWithInvalidArguments_Discount_Negative_Forbidden() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_NEGATIVE_CREATE_BILL_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateBillWithInvalidArguments_Discount_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_NULL_CREATE_BILL_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Client ID
    @Test
    void noUser_CreateBillWithInvalidArguments_ClientID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CLIENT_ID_NOT_FOUND_CREATE_BILL_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateBillWithInvalidArguments_ClientID_Negative_Forbidden() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CLIENT_ID_NEGATIVE_CREATE_BILL_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateBillWithInvalidArguments_ClientID_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CLIENT_NULL_NEGATIVE_CREATE_BILL_REQUEST)))
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
    void user_CreateBillWithValidArguments_Ok() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_BILL_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.discount").value(VALID_CREATE_BILL_REQUEST.getDiscount()),
                        jsonPath("$.content.client.id").value(VALID_CREATE_BILL_REQUEST.getClientId()));
    }

    // Discount
    @Test
    void user_CreateBillWithInvalidArguments_Discount_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_TOO_BIG_CREATE_BILL_REQUEST))
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
    @Order(2)
    @DirtiesContext
    void user_CreateBillWithValidArguments_Discount_MaxValue_Ok() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DISCOUNT_MAX_VALUE_CREATE_BILL_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.discount")
                                .value(VALID_DISCOUNT_MAX_VALUE_CREATE_BILL_REQUEST.getDiscount()),
                        jsonPath("$.content.client.id")
                                .value(VALID_DISCOUNT_MAX_VALUE_CREATE_BILL_REQUEST.getClientId()));
    }

    @Test // TODO
    void user_CreateBillWithInvalidArguments_Discount_Zero_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DISCOUNT_ZERO_CREATE_BILL_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("discount"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("must be greater than 0")); // TODO: TRANSLATE
    }

    @Test
    void user_CreateBillWithInvalidArguments_Discount_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_NEGATIVE_CREATE_BILL_REQUEST))
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
    void user_CreateBillWithInvalidArguments_Discount_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_NULL_CREATE_BILL_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("discount"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El descuento es requerido"));
    }

    // Client ID
    @Test
    void user_CreateBillWithInvalidArguments_ClientID_NotFound_NotFound() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CLIENT_ID_NOT_FOUND_CREATE_BILL_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND));
    }

    @Test
    void user_CreateBillWithInvalidArguments_ClientID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CLIENT_ID_NEGATIVE_CREATE_BILL_REQUEST))
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
    void user_CreateBillWithInvalidArguments_ClientID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CLIENT_NULL_NEGATIVE_CREATE_BILL_REQUEST))
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
    @Order(3)
    @DirtiesContext
    void admin_CreateBillWithValidArguments_Ok() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_BILL_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.discount").value(VALID_CREATE_BILL_REQUEST.getDiscount()),
                        jsonPath("$.content.client.id").value(VALID_CREATE_BILL_REQUEST.getClientId()));
    }

    // Discount
    @Test
    void admin_CreateBillWithInvalidArguments_Discount_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_TOO_BIG_CREATE_BILL_REQUEST))
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
    @Order(4)
    @DirtiesContext
    void admin_CreateBillWithValidArguments_Discount_MaxValue_Ok() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DISCOUNT_MAX_VALUE_CREATE_BILL_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.discount")
                                .value(VALID_DISCOUNT_MAX_VALUE_CREATE_BILL_REQUEST.getDiscount()),
                        jsonPath("$.content.client.id")
                                .value(VALID_DISCOUNT_MAX_VALUE_CREATE_BILL_REQUEST.getClientId()));
    }

    @Test // TODO
    void admin_CreateBillWithInvalidArguments_Discount_Zero_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DISCOUNT_ZERO_CREATE_BILL_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("discount"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("must be greater than 0")); // TODO: TRANSLATE
    }

    @Test
    void admin_CreateBillWithInvalidArguments_Discount_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_NEGATIVE_CREATE_BILL_REQUEST))
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
    void admin_CreateBillWithInvalidArguments_Discount_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_NULL_CREATE_BILL_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("discount"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El descuento es requerido"));
    }

    // Client ID
    @Test
    void admin_CreateBillWithInvalidArguments_ClientID_NotFound_NotFound() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CLIENT_ID_NOT_FOUND_CREATE_BILL_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND));
    }

    @Test
    void admin_CreateBillWithInvalidArguments_ClientID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CLIENT_ID_NEGATIVE_CREATE_BILL_REQUEST))
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
    void admin_CreateBillWithInvalidArguments_ClientID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/bill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CLIENT_NULL_NEGATIVE_CREATE_BILL_REQUEST))
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
