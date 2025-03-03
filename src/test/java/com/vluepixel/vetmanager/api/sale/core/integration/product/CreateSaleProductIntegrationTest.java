package com.vluepixel.vetmanager.api.sale.core.integration.product;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.sale.core.data.product.CreateProductSaleDataProvider.INVALID_BILL_ID_NEGATIVE_CREATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.CreateProductSaleDataProvider.INVALID_BILL_ID_NOT_FOUND_CREATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.CreateProductSaleDataProvider.INVALID_BILL_ID_NULL_CREATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.CreateProductSaleDataProvider.INVALID_PRODUCT_ID_NEGATIVE_CREATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.CreateProductSaleDataProvider.INVALID_PRODUCT_ID_NOT_FOUND_CREATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.CreateProductSaleDataProvider.INVALID_PRODUCT_ID_NULL_CREATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.CreateProductSaleDataProvider.INVALID_QUANTITY_TOO_BIG_CREATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.CreateProductSaleDataProvider.VALID_CREATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.CreateProductSaleDataProvider.VALID_QUANTITY_MAX_VALUE_CREATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_DISCOUNT_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_DISCOUNT_NULL_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_DISCOUNT_TOO_BIG_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_DISCOUNT_ZERO_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_QUANTITY_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_QUANTITY_NULL_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_QUANTITY_ZERO_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.VALID_DISCOUNT_MAX_VALUE_UPDATE_PRODUCT_SALE_REQUEST;
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
 * Integration tests for the create sale product use case.
 */
public class CreateSaleProductIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Venta creada exitosamente";
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
    @Order(1)
    @DirtiesContext
    void admin_CreateSaleProductWithValidArguments_Ok() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_PRODUCT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(4),
                        jsonPath("$.content.discount").value(VALID_CREATE_PRODUCT_SALE_REQUEST.getDiscount()),
                        jsonPath("$.content.product.id").value(VALID_CREATE_PRODUCT_SALE_REQUEST.getProductId()),
                        jsonPath("$.content.quantity").value(VALID_CREATE_PRODUCT_SALE_REQUEST.getQuantity()));
    }

    // Bill ID
    @Test // TODO
    void admin_CreateSaleProductWithInvalidArguments_BillID_NotFound_NotFound() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_BILL_ID_NOT_FOUND_CREATE_PRODUCT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_PRODUCT_NOT_FOUND
                        .apply(INVALID_BILL_ID_NOT_FOUND_CREATE_PRODUCT_SALE_REQUEST.getBillId().toString())));
    }

    @Test
    void admin_CreateSaleProductWithInvalidArguments_BillID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_BILL_ID_NEGATIVE_CREATE_PRODUCT_SALE_REQUEST))
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
    void admin_CreateSaleProductWithInvalidArguments_BillID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_BILL_ID_NULL_CREATE_PRODUCT_SALE_REQUEST))
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
    void admin_CreateSaleProductWithInvalidArguments_ProductID_NotFound_NotFound() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRODUCT_ID_NOT_FOUND_CREATE_PRODUCT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_PRODUCT_NOT_FOUND
                        .apply(INVALID_PRODUCT_ID_NOT_FOUND_CREATE_PRODUCT_SALE_REQUEST.getProductId().toString())));
    }

    @Test
    void admin_CreateSaleProductWithInvalidArguments_ProductID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRODUCT_ID_NEGATIVE_CREATE_PRODUCT_SALE_REQUEST))
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
    void admin_CreateSaleProductWithInvalidArguments_ProductID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRODUCT_ID_NULL_CREATE_PRODUCT_SALE_REQUEST))
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
    void admin_CreateSaleProductWithInvalidArguments_Quantity_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_TOO_BIG_CREATE_PRODUCT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("quantity"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("La cantidad no puede ser mayor a 99"));
    }

    @Test
    @Order(2)
    @DirtiesContext // TODO: Returns 409
    void admin_CreateSaleProductWithValidArguments_Quantity_MaxValue_Ok() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_QUANTITY_MAX_VALUE_CREATE_PRODUCT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(4),
                        jsonPath("$.content.discount")
                                .value(VALID_QUANTITY_MAX_VALUE_CREATE_PRODUCT_SALE_REQUEST.getDiscount()),
                        jsonPath("$.content.product.id")
                                .value(VALID_QUANTITY_MAX_VALUE_CREATE_PRODUCT_SALE_REQUEST.getProductId()),
                        jsonPath("$.content.quantity")
                                .value(VALID_QUANTITY_MAX_VALUE_CREATE_PRODUCT_SALE_REQUEST.getQuantity()));
    }

    @Test // TODO
    void admin_CreateSaleProductWithInvalidArguments_Quantity_Zero_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
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
                                .value("La cantidad no puede ser mayor a 99"));
    }

    @Test // TODO
    void admin_CreateSaleProductWithInvalidArguments_Quantity_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
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
                                .value("La cantidad no puede ser mayor a 99"));
    }

    @Test // TODO
    void admin_CreateSaleProductWithInvalidArguments_Quantity_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
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
                                .value("La cantidad no puede ser mayor a 99"));
    }

    // Discount
    @Test // TODO:
    void admin_CreateSaleProductWithInvalidArguments_Discount_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
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
                                .value("La cantidad no puede ser mayor a 99"));
    }

    @Test
    @Order(3)
    @DirtiesContext // TODO: Returns 400
    void admin_CreateSaleProductWithValidArguments_Discount_MaxValue_Ok() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_DISCOUNT_MAX_VALUE_UPDATE_PRODUCT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(4),
                        jsonPath("$.content.discount")
                                .value(VALID_DISCOUNT_MAX_VALUE_UPDATE_PRODUCT_SALE_REQUEST.getDiscount()),
                        jsonPath("$.content.product.id")
                                .value(VALID_DISCOUNT_MAX_VALUE_UPDATE_PRODUCT_SALE_REQUEST.getProductId()),
                        jsonPath("$.content.quantity")
                                .value(VALID_DISCOUNT_MAX_VALUE_UPDATE_PRODUCT_SALE_REQUEST.getQuantity()));
    }

    @Test // TODO
    void admin_CreateSaleProductWithInvalidArguments_Discount_Zero_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
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
                                .value("La cantidad no puede ser mayor a 99"));
    }

    @Test // TODO
    void admin_CreateSaleProductWithInvalidArguments_Discount_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
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
                                .value("La cantidad no puede ser mayor a 99"));
    }

    @Test // TODO
    void admin_CreateSaleProductWithInvalidArguments_Discount_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/sale")
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
                                .value("La cantidad no puede ser mayor a 99"));
    }
}
