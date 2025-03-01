package com.vluepixel.vetmanager.api.sale.core.integration.product;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_PRODUCT_ID_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_PRODUCT_ID_NOT_FOUND_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_PRODUCT_ID_NULL_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_QUANTITY_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_QUANTITY_NULL_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_QUANTITY_TOO_BIG_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_QUANTITY_ZERO_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.VALID_DISCOUNT_MAX_VALUE_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_DISCOUNT_ZERO_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_BILL_ID_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_BILL_ID_NOT_FOUND_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_BILL_ID_NULL_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_DISCOUNT_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_DISCOUNT_NULL_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_DISCOUNT_TOO_BIG_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_ID_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_ID_NOT_FOUND_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_ID_NULL_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.VALID_UPDATE_PRODUCT_SALE_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the get sale product use case.
 */
public class UpdateSaleProductIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Venta actualizada exitosamente";
    private static final Function<String, String> MESSAGE_NOT_FOUND = parameter -> String
            .format("Venta con id %s no encontrado(a)", parameter);
    private static final Function<String, String> MESSAGE_PRODUCT_NOT_FOUND = parameter -> String
            .format("Producto con id %s no encontrado(a)", parameter);
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
    void admin_UpdateSaleProductWithValidArguments_Ok() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PRODUCT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId()),
                        jsonPath("$.content.discount").value(VALID_UPDATE_PRODUCT_SALE_REQUEST.getDiscount()),
                        jsonPath("$.content.quantity").value(VALID_UPDATE_PRODUCT_SALE_REQUEST.getQuantity()),
                        jsonPath("$.content.product.id").value(VALID_UPDATE_PRODUCT_SALE_REQUEST.getProductId()));
    }

    @Test
    void admin_UpdateSaleProductWithInvalidArguments_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NOT_FOUND_UPDATE_PRODUCT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(
                        MESSAGE_NOT_FOUND.apply(INVALID_ID_NOT_FOUND_UPDATE_PRODUCT_SALE_REQUEST.getId().toString())));
    }

    @Test
    void admin_UpdateSaleProductWithInvalidArguments_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST))
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
    void admin_UpdateSaleProductWithInvalidArguments_ID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NULL_UPDATE_PRODUCT_SALE_REQUEST))
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
    @Test // TODO
    void admin_UpdateSaleProductWithInvalidArguments_BillID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_BILL_ID_NOT_FOUND_UPDATE_PRODUCT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(
                        MESSAGE_NOT_FOUND.apply(INVALID_ID_NOT_FOUND_UPDATE_PRODUCT_SALE_REQUEST.getId().toString())));
    }

    @Test
    void admin_UpdateSaleProductWithInvalidArguments_BillID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_BILL_ID_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST))
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
    void admin_UpdateSaleProductWithInvalidArguments_BillID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_BILL_ID_NULL_UPDATE_PRODUCT_SALE_REQUEST))
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

    // Product ID
    @Test
    void admin_UpdateSaleProductWithInvalidArguments_ProductID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRODUCT_ID_NOT_FOUND_UPDATE_PRODUCT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(
                        MESSAGE_PRODUCT_NOT_FOUND.apply(INVALID_PRODUCT_ID_NOT_FOUND_UPDATE_PRODUCT_SALE_REQUEST
                                .getProductId().toString())));
    }

    @Test
    void admin_UpdateSaleProductWithInvalidArguments_ProductID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRODUCT_ID_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("product_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El id del producto debe ser mayor a 0"));
    }

    @Test
    void admin_UpdateSaleProductWithInvalidArguments_ProductID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRODUCT_ID_NULL_UPDATE_PRODUCT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("product_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El id del producto es requerido"));
    }

    // Quantity
    @Test
    void admin_UpdateSaleProductWithInvalidArguments_Quantity_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_TOO_BIG_UPDATE_PRODUCT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("quantity"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("La cantidad no puede ser mayor a 999"));
    }

    @Test
    @DirtiesContext // TODO: Returns 500 when quantity has maximum value.
    void admin_UpdateSaleProductWithValidArguments_Quantity_MaxValue_Ok() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_SALE_REQUEST.getId()),
                        jsonPath("$.content.discount")
                                .value(VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_SALE_REQUEST.getDiscount()),
                        jsonPath("$.content.quantity")
                                .value(VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_SALE_REQUEST.getQuantity()),
                        jsonPath("$.content.product.id")
                                .value(VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_SALE_REQUEST.getProductId()));
    }

    @Test
    void admin_UpdateSaleProductWithInvalidArguments_Quantity_Zero_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_ZERO_UPDATE_PRODUCT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("quantity"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("La cantidad debe ser mayor a 0"));
    }

    @Test
    void admin_UpdateSaleProductWithInvalidArguments_Quantity_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("quantity"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("La cantidad debe ser mayor a 0"));
    }

    @Test
    void admin_UpdateSaleProductWithInvalidArguments_Quantity_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_NULL_UPDATE_PRODUCT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("quantity"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("La cantidad es requerida"));
    }

    // Discount
    @Test
    void admin_UpdateSaleProductWithInvalidArguments_Discount_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_TOO_BIG_UPDATE_PRODUCT_SALE_REQUEST))
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
    void admin_UpdateSaleProductWithValidArguments_Discount_MaxValue_Ok() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DISCOUNT_MAX_VALUE_UPDATE_PRODUCT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_DISCOUNT_MAX_VALUE_UPDATE_PRODUCT_SALE_REQUEST.getId()),
                        jsonPath("$.content.discount")
                                .value(VALID_DISCOUNT_MAX_VALUE_UPDATE_PRODUCT_SALE_REQUEST.getDiscount()),
                        jsonPath("$.content.quantity")
                                .value(VALID_DISCOUNT_MAX_VALUE_UPDATE_PRODUCT_SALE_REQUEST.getQuantity()),
                        jsonPath("$.content.product.id")
                                .value(VALID_DISCOUNT_MAX_VALUE_UPDATE_PRODUCT_SALE_REQUEST.getProductId()));
    }

    @Test
    void admin_UpdateSaleProductWithValidArguments_Discount_Zero_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_ZERO_UPDATE_PRODUCT_SALE_REQUEST))
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
    void admin_UpdateSaleProductWithValidArguments_Discount_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST))
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
    void admin_UpdateSaleProductWithValidArguments_Discount_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DISCOUNT_NULL_UPDATE_PRODUCT_SALE_REQUEST))
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
