package com.vluepixel.vetmanager.api.sale.core.integration.treatment;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_USER_JWT;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.INVALID_DISCOUNT_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.INVALID_DISCOUNT_NULL_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.INVALID_DISCOUNT_TOO_BIG_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.INVALID_ID_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.INVALID_ID_NOT_FOUND_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.INVALID_ID_NULL_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.INVALID_PRICE_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.INVALID_PRICE_NULL_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.INVALID_PRICE_TOO_BIG_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.INVALID_PRICE_ZERO_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.INVALID_TREATMENT_ID_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.INVALID_TREATMENT_ID_NOT_FOUND_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.INVALID_TREATMENT_ID_NULL_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.VALID_DISCOUNT_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.INVALID_DISCOUNT_ZERO_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.VALID_PRICE_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.VALID_UPDATE_TREATMENT_SALE_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the update sale treatment use case.
 */
public class UpdateSaleTreatmentIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Venta actualizada exitosamente";
    private static final Function<String, String> MESSAGE_NOT_FOUND = parameter -> String
            .format("Venta con id %s no encontrado(a)", parameter);
    private static final String MESSAGE_TREATMENT_NOT_FOUND = "Tratamiento no encontrado(a)";
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    @Test
    void noUser_UpdateSaleTreatmentWithValidArguments_Forbidden() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_TREATMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateSaleTreatmentWithInvalidArguments_ID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NOT_FOUND_UPDATE_TREATMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateSaleTreatmentWithInvalidArguments_ID_Negative_Forbidden() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateSaleTreatmentWithInvalidArguments_ID_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NULL_UPDATE_TREATMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Treatment ID
    @Test
    void noUser_UpdateSaleTreatmentWithInvalidArguments_TreatmentID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_TREATMENT_ID_NOT_FOUND_UPDATE_TREATMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateSaleTreatmentWithInvalidArguments_TreatmentID_Negative_Forbidden() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_TREATMENT_ID_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateSaleTreatmentWithInvalidArguments_TreatmentID_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_TREATMENT_ID_NULL_UPDATE_TREATMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Price
    @Test
    void noUser_UpdateSaleTreatmentWithInvalidArguments_Price_TooBig_Forbidden() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_TOO_BIG_UPDATE_TREATMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateSaleTreatmentWithValidArguments_Price_MaxValue_Forbidden() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PRICE_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateSaleTreatmentWithInvalidArguments_Price_Zero_Forbidden() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_ZERO_UPDATE_TREATMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateSaleTreatmentWithInvalidArguments_Price_Negative_Forbidden() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateSaleTreatmentWithInvalidArguments_Price_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_NULL_UPDATE_TREATMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Discount
    @Test
    void noUser_UpdateSaleTreatmentWithInvalidArguments_Discount_TooBig_Forbidden() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_TOO_BIG_UPDATE_TREATMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateSaleTreatmentWithValidArguments_Discount_MaxValue_Forbidden() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DISCOUNT_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateSaleTreatmentWithInvalidArguments_Discount_Zero_Forbidden() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_ZERO_UPDATE_TREATMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateSaleTreatmentWithInvalidArguments_Discount_Negative_Forbidden() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateSaleTreatmentWithInvalidArguments_Discount_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_NULL_UPDATE_TREATMENT_SALE_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER
    @Test
    @DirtiesContext
    void user_UpdateSaleTreatmentWithValidArguments_Ok() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_UPDATE_TREATMENT_SALE_REQUEST.getId()),
                        jsonPath("$.content.discount").value(VALID_UPDATE_TREATMENT_SALE_REQUEST.getDiscount()),
                        jsonPath("$.content.price")
                                .value(Double.parseDouble(VALID_UPDATE_TREATMENT_SALE_REQUEST.getPrice().toString())),
                        jsonPath("$.content.treatment.id").value(VALID_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId()));
    }

    @Test
    void user_UpdateSaleTreatmentWithInvalidArguments_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NOT_FOUND_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_NOT_FOUND
                                .apply(INVALID_ID_NOT_FOUND_UPDATE_TREATMENT_SALE_REQUEST.getId().toString())));
    }

    @Test
    void user_UpdateSaleTreatmentWithInvalidArguments_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id debe ser mayor a 0"));
    }

    @Test
    void user_UpdateSaleTreatmentWithInvalidArguments_ID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NULL_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id es requerido"));
    }

    // Treatment ID
    @Test
    void user_UpdateSaleTreatmentWithInvalidArguments_TreatmentID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_TREATMENT_ID_NOT_FOUND_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_TREATMENT_NOT_FOUND));
    }

    @Test
    void user_UpdateSaleTreatmentWithInvalidArguments_TreatmentID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_TREATMENT_ID_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("treatment_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id del tratamiento debe ser mayor a 0"));
    }

    @Test
    void user_UpdateSaleTreatmentWithInvalidArguments_TreatmentID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_TREATMENT_ID_NULL_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("treatment_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id del tratamiento es requerido"));
    }

    // Price
    @Test
    void user_UpdateSaleTreatmentWithInvalidArguments_Price_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_TOO_BIG_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El precio no puede ser mayor a 999999.99"));
    }

    @Test
    @DirtiesContext
    void user_UpdateSaleTreatmentWithValidArguments_Price_MaxValue_Ok() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PRICE_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_PRICE_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST.getId()),
                        jsonPath("$.content.discount")
                                .value(VALID_PRICE_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST.getDiscount()),
                        jsonPath("$.content.price")
                                .value(Double.parseDouble(
                                        VALID_PRICE_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST.getPrice().toString())),
                        jsonPath("$.content.treatment.id")
                                .value(VALID_PRICE_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId()));
    }

    @Test
    void user_UpdateSaleTreatmentWithInvalidArguments_Price_Zero_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_ZERO_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El precio debe ser mayor a 0"));
    }

    @Test
    void user_UpdateSaleTreatmentWithInvalidArguments_Price_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El precio debe ser mayor a 0"));
    }

    @Test
    void user_UpdateSaleTreatmentWithInvalidArguments_Price_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_NULL_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El precio es requerido"));
    }

    // Discount
    @Test
    void user_UpdateSaleTreatmentWithInvalidArguments_Discount_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_TOO_BIG_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("discount"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El descuento no puede ser mayor a 100"));
    }

    @Test
    @DirtiesContext
    void user_UpdateSaleTreatmentWithValidArguments_Discount_MaxValue_Ok() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DISCOUNT_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_DISCOUNT_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST.getId()),
                        jsonPath("$.content.discount")
                                .value(VALID_DISCOUNT_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST.getDiscount()),
                        jsonPath("$.content.price")
                                .value(Double.parseDouble(
                                        VALID_DISCOUNT_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST.getPrice().toString())),
                        jsonPath("$.content.treatment.id")
                                .value(VALID_DISCOUNT_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId()));
    }

    @Test
    void user_UpdateSaleTreatmentWithInvalidArguments_Discount_Zero_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_ZERO_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("discount"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El descuento debe ser mayor a 0"));
    }

    @Test
    void user_UpdateSaleTreatmentWithInvalidArguments_Discount_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("discount"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El descuento debe ser mayor a 0"));
    }

    @Test
    void user_UpdateSaleTreatmentWithInvalidArguments_Discount_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_NULL_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("discount"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El descuento es requerido"));
    }

    // Role: ADMIN
    @Test
    @DirtiesContext
    void admin_UpdateSaleTreatmentWithValidArguments_Ok() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_UPDATE_TREATMENT_SALE_REQUEST.getId()),
                        jsonPath("$.content.discount").value(VALID_UPDATE_TREATMENT_SALE_REQUEST.getDiscount()),
                        jsonPath("$.content.price")
                                .value(Double.parseDouble(VALID_UPDATE_TREATMENT_SALE_REQUEST.getPrice().toString())),
                        jsonPath("$.content.treatment.id").value(VALID_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId()));
    }

    @Test
    void admin_UpdateSaleTreatmentWithInvalidArguments_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NOT_FOUND_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_NOT_FOUND
                                .apply(INVALID_ID_NOT_FOUND_UPDATE_TREATMENT_SALE_REQUEST.getId().toString())));
    }

    @Test
    void admin_UpdateSaleTreatmentWithInvalidArguments_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id debe ser mayor a 0"));
    }

    @Test
    void admin_UpdateSaleTreatmentWithInvalidArguments_ID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NULL_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id es requerido"));
    }

    // Treatment ID
    @Test
    void admin_UpdateSaleTreatmentWithInvalidArguments_TreatmentID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_TREATMENT_ID_NOT_FOUND_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_TREATMENT_NOT_FOUND));
    }

    @Test
    void admin_UpdateSaleTreatmentWithInvalidArguments_TreatmentID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_TREATMENT_ID_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("treatment_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id del tratamiento debe ser mayor a 0"));
    }

    @Test
    void admin_UpdateSaleTreatmentWithInvalidArguments_TreatmentID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_TREATMENT_ID_NULL_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("treatment_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id del tratamiento es requerido"));
    }

    // Price
    @Test
    void admin_UpdateSaleTreatmentWithInvalidArguments_Price_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_TOO_BIG_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El precio no puede ser mayor a 999999.99"));
    }

    @Test
    @DirtiesContext
    void admin_UpdateSaleTreatmentWithValidArguments_Price_MaxValue_Ok() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PRICE_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_PRICE_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST.getId()),
                        jsonPath("$.content.discount")
                                .value(VALID_PRICE_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST.getDiscount()),
                        jsonPath("$.content.price")
                                .value(Double.parseDouble(
                                        VALID_PRICE_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST.getPrice().toString())),
                        jsonPath("$.content.treatment.id")
                                .value(VALID_PRICE_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId()));
    }

    @Test
    void admin_UpdateSaleTreatmentWithInvalidArguments_Price_Zero_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_ZERO_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El precio debe ser mayor a 0"));
    }

    @Test
    void admin_UpdateSaleTreatmentWithInvalidArguments_Price_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El precio debe ser mayor a 0"));
    }

    @Test
    void admin_UpdateSaleTreatmentWithInvalidArguments_Price_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_NULL_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El precio es requerido"));
    }

    // Discount
    @Test
    void admin_UpdateSaleTreatmentWithInvalidArguments_Discount_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_TOO_BIG_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("discount"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El descuento no puede ser mayor a 100"));
    }

    @Test
    @DirtiesContext
    void admin_UpdateSaleTreatmentWithValidArguments_Discount_MaxValue_Ok() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DISCOUNT_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_DISCOUNT_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST.getId()),
                        jsonPath("$.content.discount")
                                .value(VALID_DISCOUNT_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST.getDiscount()),
                        jsonPath("$.content.price")
                                .value(Double.parseDouble(
                                        VALID_DISCOUNT_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST.getPrice().toString())),
                        jsonPath("$.content.treatment.id")
                                .value(VALID_DISCOUNT_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId()));
    }

    @Test
    void admin_UpdateSaleTreatmentWithInvalidArguments_Discount_Zero_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_ZERO_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("discount"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El descuento debe ser mayor a 0"));
    }

    @Test
    void admin_UpdateSaleTreatmentWithInvalidArguments_Discount_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("discount"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El descuento debe ser mayor a 0"));
    }

    @Test
    void admin_UpdateSaleTreatmentWithInvalidArguments_Discount_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_NULL_UPDATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("discount"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El descuento es requerido"));
    }
}
