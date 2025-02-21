package com.vluepixel.vetmanager.api.product.core.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_USER_JWT;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_CATEGORY_IDS_EMPTY_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_CATEGORY_IDS_NEGATIVE_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_CATEGORY_IDS_NOT_FOUND_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_CATEGORY_IDS_NULL_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_CATEGORY_IDS_ONE_NULL_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_DESCRIPTION_BLANK_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_DESCRIPTION_EMPTY_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_DESCRIPTION_NULL_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_ID_NEGATIVE_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_ID_NOT_FOUND_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_NAME_BLANK_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_NAME_EMPTY_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_NAME_NULL_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_NAME_TOO_LONG_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_PRICE_NEGATIVE_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_PRICE_NULL_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_PRICE_TOO_BIG_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_PRICE_ZERO_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_QUANTITY_NEGATIVE_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_QUANTITY_NULL_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_QUANTITY_TOO_BIG_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_QUANTITY_ZERO_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.VALID_NAME_MAX_LENGTH_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.VALID_PRICE_MAX_VALUE_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.VALID_UPDATE_PRODUCT_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the update product use case.
 */
public class UpdateProductintegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Producto actualizado exitosamente";
    private static final String MESSAGE_NOT_FOUND = "Product no encontrado(a)";
    private static final String MESSAGE_CATEGORY_NOT_FOUND = "Categoría no encontrado(a)";
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    @Test
    @DirtiesContext
    void noUser_UpdateProductWithValidArguments_Forbidden() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // - Invalidations

    // ID
    @Test
    void noUser_UpdateProductWithInvalidArguments_ID_Forbidden() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NOT_FOUND_UPDATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateProductWithInvalidArguments_ID_Negative_Forbidden() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NEGATIVE_UPDATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Name
    @Test
    void noUser_UpdateProductWithInvalidArguments_Name_TooLong_Forbidden() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_TOO_LONG_UPDATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    @DirtiesContext
    void noUser_UpdateProductWithValidArguments_Name_MaxLength_Forbidden() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_NAME_MAX_LENGTH_UPDATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateProductWithInvalidArguments_Name_Blank_Forbidden() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_BLANK_UPDATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateProductWithInvalidArguments_Name_Empty_Forbidden() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_EMPTY_UPDATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateProductWithInvalidArguments_Name_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_NULL_UPDATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Description
    @Test
    void noUser_UpdateProductWithInvalidArguments_Description_Blank_Forbidden() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_BLANK_UPDATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateProductWithInvalidArguments_Description_Empty_Forbidden() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_EMPTY_UPDATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateProductWithInvalidArguments_Description_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_NULL_UPDATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Price
    @Test
    void noUser_UpdateProductWithInvalidArguments_Price_TooBig_Forbidden() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_TOO_BIG_UPDATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateProductWithValidArguments_Price_MaxValue_Forbidden() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PRICE_MAX_VALUE_UPDATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateProductWithInvalidArguments_Price_Zero_Forbidden() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_ZERO_UPDATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateProductWithInvalidArguments_Price_Negative_Forbidden() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_NEGATIVE_UPDATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateProductWithInvalidArguments_Price_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_NULL_UPDATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Quantity
    @Test
    void noUser_UpdateProductWithInvalidArguments_Quantity_TooBig_Forbidden() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_TOO_BIG_UPDATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateProductWithValidArguments_Quantity_MaxValue_Forbidden() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateProductWithInvalidArguments_Quantity_Zero_Forbidden() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_ZERO_UPDATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateProductWithInvalidArguments_Quantity_Negative_Forbidden() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_NEGATIVE_UPDATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateProductWithInvalidArguments_Quantity_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_NULL_UPDATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Category IDs
    @Test
    void noUser_UpdateProductWithInvalidArguments_CategoryIDs_Negative_Forbidden() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_NEGATIVE_UPDATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateProductWithInvalidArguments_CategoryIDs_NotFound_Forbidden() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_NOT_FOUND_UPDATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateProductWithInvalidArguments_CategoryIDs_One_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_ONE_NULL_UPDATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateProductWithValidArguments_CategoryIDs_Empty_Forbidden() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_EMPTY_UPDATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateProductWithInvalidArguments_CategoryIDs_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_NULL_UPDATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER
    @Test
    @DirtiesContext
    void user_UpdateProductWithValidArguments_Ok() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_UPDATE_PRODUCT_REQUEST.getId()),
                        jsonPath("$.content.name").value(VALID_UPDATE_PRODUCT_REQUEST.getName()),
                        jsonPath("$.content.description").value(VALID_UPDATE_PRODUCT_REQUEST.getDescription()),
                        jsonPath("$.content.price").value(VALID_UPDATE_PRODUCT_REQUEST.getPrice().toString()),
                        jsonPath("$.content.quantity").value(VALID_UPDATE_PRODUCT_REQUEST.getQuantity()),
                        jsonPath("$.content.categories").isArray());
    }

    // - Invalidations

    // ID
    @Test
    void user_UpdateProductWithInvalidArguments_ID_NotFound() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NOT_FOUND_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND));
    }

    @Test
    void user_UpdateProductWithInvalidArguments_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NEGATIVE_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El id debe ser mayor a 0"));
    }

    // Name
    @Test
    void user_UpdateProductWithInvalidArguments_Name_TooLong_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_TOO_LONG_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El nombre no puede tener más de 125 caracteres"));
    }

    @Test
    @DirtiesContext
    void user_UpdateProductWithValidArguments_Name_MaxLength_Ok() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_NAME_MAX_LENGTH_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_NAME_MAX_LENGTH_UPDATE_PRODUCT_REQUEST.getId()),
                        jsonPath("$.content.name").value(VALID_NAME_MAX_LENGTH_UPDATE_PRODUCT_REQUEST.getName()),
                        jsonPath("$.content.description")
                                .value(VALID_NAME_MAX_LENGTH_UPDATE_PRODUCT_REQUEST.getDescription()),
                        jsonPath("$.content.price")
                                .value(VALID_NAME_MAX_LENGTH_UPDATE_PRODUCT_REQUEST.getPrice().toString()),
                        jsonPath("$.content.quantity")
                                .value(VALID_NAME_MAX_LENGTH_UPDATE_PRODUCT_REQUEST.getQuantity()),
                        jsonPath("$.content.categories").isArray());
    }

    @Test
    void user_UpdateProductWithInvalidArguments_Name_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_BLANK_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El nombre es requerido"));
    }

    @Test
    void user_UpdateProductWithInvalidArguments_Name_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_EMPTY_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El nombre es requerido"));
    }

    @Test
    void user_UpdateProductWithInvalidArguments_Name_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_NULL_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El nombre es requerido"));
    }

    // Description
    @Test
    void user_UpdateProductWithInvalidArguments_Description_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_BLANK_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("description"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("La descripción es requerida"));
    }

    @Test
    void user_UpdateProductWithInvalidArguments_Description_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_EMPTY_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("description"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("La descripción es requerida"));
    }

    @Test
    void user_UpdateProductWithInvalidArguments_Description_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_NULL_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("description"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("La descripción es requerida"));
    }

    // Price
    @Test
    void user_UpdateProductWithInvalidArguments_Price_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_TOO_BIG_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El precio no puede ser mayor a 9999.99"));
    }

    @Test
    @DirtiesContext
    void user_UpdateProductWithValidArguments_Price_MaxValue_Ok() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PRICE_MAX_VALUE_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_PRICE_MAX_VALUE_UPDATE_PRODUCT_REQUEST.getId()),
                        jsonPath("$.content.name").value(VALID_PRICE_MAX_VALUE_UPDATE_PRODUCT_REQUEST.getName()),
                        jsonPath("$.content.description")
                                .value(VALID_PRICE_MAX_VALUE_UPDATE_PRODUCT_REQUEST.getDescription()),
                        jsonPath("$.content.price")
                                .value(VALID_PRICE_MAX_VALUE_UPDATE_PRODUCT_REQUEST.getPrice().toString()),
                        jsonPath("$.content.quantity")
                                .value(VALID_PRICE_MAX_VALUE_UPDATE_PRODUCT_REQUEST.getQuantity()),
                        jsonPath("$.content.categories").isArray());
    }

    @Test
    void user_UpdateProductWithInvalidArguments_Price_Zero_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_ZERO_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El precio debe ser mayor a 0"));
    }

    @Test
    void user_UpdateProductWithInvalidArguments_Price_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_NEGATIVE_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El precio debe ser mayor a 0"));
    }

    @Test
    void user_UpdateProductWithInvalidArguments_Price_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_NULL_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El precio es requerido"));
    }

    // Quantity
    @Test
    void user_UpdateProductWithInvalidArguments_Quantity_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_TOO_BIG_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
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
    @DirtiesContext
    void user_UpdateProductWithValidArguments_Quantity_MaxValue_Ok() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_REQUEST.getId()),
                        jsonPath("$.content.name").value(VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_REQUEST.getName()),
                        jsonPath("$.content.description")
                                .value(VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_REQUEST.getDescription()),
                        jsonPath("$.content.price")
                                .value(VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_REQUEST.getPrice().toString()),
                        jsonPath("$.content.quantity")
                                .value(VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_REQUEST.getQuantity()),
                        jsonPath("$.content.categories").isArray());
    }

    @Test
    void user_UpdateProductWithInvalidArguments_Quantity_Zero_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_ZERO_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
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
    void user_UpdateProductWithInvalidArguments_Quantity_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_NEGATIVE_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
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
    void user_UpdateProductWithInvalidArguments_Quantity_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_NULL_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("quantity"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("La cantidad es requerida"));
    }

    // Category IDs
    @Test
    void user_UpdateProductWithInvalidArguments_CategoryIDs_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_NEGATIVE_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").isString(),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El id de la categoría debe ser mayor a 0"));
    }

    @Test
    void user_UpdateProductWithInvalidArguments_CategoryIDs_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_NOT_FOUND_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_CATEGORY_NOT_FOUND));
    }

    @Test
    void user_UpdateProductWithInvalidArguments_CategoryIDs_One_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_ONE_NULL_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").isString(),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El id de la categoría es requerido"));
    }

    @Test
    @DirtiesContext
    void user_UpdateProductWithValidArguments_CategoryIDs_Empty_Ok() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_EMPTY_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(INVALID_CATEGORY_IDS_EMPTY_UPDATE_PRODUCT_REQUEST.getId()),
                        jsonPath("$.content.name").value(INVALID_CATEGORY_IDS_EMPTY_UPDATE_PRODUCT_REQUEST.getName()),
                        jsonPath("$.content.description")
                                .value(INVALID_CATEGORY_IDS_EMPTY_UPDATE_PRODUCT_REQUEST.getDescription()),
                        jsonPath("$.content.price")
                                .value(INVALID_CATEGORY_IDS_EMPTY_UPDATE_PRODUCT_REQUEST.getPrice().toString()),
                        jsonPath("$.content.quantity")
                                .value(INVALID_CATEGORY_IDS_EMPTY_UPDATE_PRODUCT_REQUEST.getQuantity()),
                        jsonPath("$.content.categories").isArray());
    }

    @Test
    @DirtiesContext
    void user_UpdateProductWithInvalidArguments_CategoryIDs_Null_Ok() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_NULL_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(INVALID_CATEGORY_IDS_NULL_UPDATE_PRODUCT_REQUEST.getId()),
                        jsonPath("$.content.name").value(INVALID_CATEGORY_IDS_NULL_UPDATE_PRODUCT_REQUEST.getName()),
                        jsonPath("$.content.description")
                                .value(INVALID_CATEGORY_IDS_NULL_UPDATE_PRODUCT_REQUEST.getDescription()),
                        jsonPath("$.content.price")
                                .value(INVALID_CATEGORY_IDS_NULL_UPDATE_PRODUCT_REQUEST.getPrice().toString()),
                        jsonPath("$.content.quantity")
                                .value(INVALID_CATEGORY_IDS_NULL_UPDATE_PRODUCT_REQUEST.getQuantity()),
                        jsonPath("$.content.categories").isArray());
    }

    // Role: ADMIN
    @Test
    @DirtiesContext
    void admin_UpdateProductWithValidArguments_Ok() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_UPDATE_PRODUCT_REQUEST.getId()),
                        jsonPath("$.content.name").value(VALID_UPDATE_PRODUCT_REQUEST.getName()),
                        jsonPath("$.content.description").value(VALID_UPDATE_PRODUCT_REQUEST.getDescription()),
                        jsonPath("$.content.price").value(VALID_UPDATE_PRODUCT_REQUEST.getPrice().toString()),
                        jsonPath("$.content.quantity").value(VALID_UPDATE_PRODUCT_REQUEST.getQuantity()),
                        jsonPath("$.content.categories").isArray());
    }

    // - Invalidations

    // ID
    @Test
    void admin_UpdateProductWithInvalidArguments_ID_NotFound() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NOT_FOUND_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND));
    }

    @Test
    void admin_UpdateProductWithInvalidArguments_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NEGATIVE_UPDATE_PRODUCT_REQUEST))
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

    // Name
    @Test
    void admin_UpdateProductWithInvalidArguments_Name_TooLong_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_TOO_LONG_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El nombre no puede tener más de 125 caracteres"));
    }

    @Test
    @DirtiesContext
    void admin_UpdateProductWithValidArguments_Name_MaxLength_Ok() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_NAME_MAX_LENGTH_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_NAME_MAX_LENGTH_UPDATE_PRODUCT_REQUEST.getId()),
                        jsonPath("$.content.name").value(VALID_NAME_MAX_LENGTH_UPDATE_PRODUCT_REQUEST.getName()),
                        jsonPath("$.content.description")
                                .value(VALID_NAME_MAX_LENGTH_UPDATE_PRODUCT_REQUEST.getDescription()),
                        jsonPath("$.content.price")
                                .value(VALID_NAME_MAX_LENGTH_UPDATE_PRODUCT_REQUEST.getPrice().toString()),
                        jsonPath("$.content.quantity")
                                .value(VALID_NAME_MAX_LENGTH_UPDATE_PRODUCT_REQUEST.getQuantity()),
                        jsonPath("$.content.categories").isArray());
    }

    @Test
    void admin_UpdateProductWithInvalidArguments_Name_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_BLANK_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El nombre es requerido"));
    }

    @Test
    void admin_UpdateProductWithInvalidArguments_Name_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_EMPTY_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El nombre es requerido"));
    }

    @Test
    void admin_UpdateProductWithInvalidArguments_Name_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_NULL_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El nombre es requerido"));
    }

    // Description
    @Test
    void admin_UpdateProductWithInvalidArguments_Description_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_BLANK_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("description"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("La descripción es requerida"));
    }

    @Test
    void admin_UpdateProductWithInvalidArguments_Description_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_EMPTY_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("description"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("La descripción es requerida"));
    }

    @Test
    void admin_UpdateProductWithInvalidArguments_Description_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_NULL_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("description"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("La descripción es requerida"));
    }

    // Price
    @Test
    void admin_UpdateProductWithInvalidArguments_Price_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_TOO_BIG_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El precio no puede ser mayor a 9999.99"));
    }

    @Test
    @DirtiesContext
    void admin_UpdateProductWithValidArguments_Price_MaxValue_Ok() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PRICE_MAX_VALUE_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_PRICE_MAX_VALUE_UPDATE_PRODUCT_REQUEST.getId()),
                        jsonPath("$.content.name").value(VALID_PRICE_MAX_VALUE_UPDATE_PRODUCT_REQUEST.getName()),
                        jsonPath("$.content.description")
                                .value(VALID_PRICE_MAX_VALUE_UPDATE_PRODUCT_REQUEST.getDescription()),
                        jsonPath("$.content.price")
                                .value(VALID_PRICE_MAX_VALUE_UPDATE_PRODUCT_REQUEST.getPrice().toString()),
                        jsonPath("$.content.quantity")
                                .value(VALID_PRICE_MAX_VALUE_UPDATE_PRODUCT_REQUEST.getQuantity()),
                        jsonPath("$.content.categories").isArray());
    }

    @Test
    void admin_UpdateProductWithInvalidArguments_Price_Zero_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_ZERO_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El precio debe ser mayor a 0"));
    }

    @Test
    void admin_UpdateProductWithInvalidArguments_Price_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_NEGATIVE_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El precio debe ser mayor a 0"));
    }

    @Test
    void admin_UpdateProductWithInvalidArguments_Price_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_NULL_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("price"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El precio es requerido"));
    }

    // Quantity
    @Test
    void admin_UpdateProductWithInvalidArguments_Quantity_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_TOO_BIG_UPDATE_PRODUCT_REQUEST))
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
    @DirtiesContext
    void admin_UpdateProductWithValidArguments_Quantity_MaxValue_Ok() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_REQUEST.getId()),
                        jsonPath("$.content.name").value(VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_REQUEST.getName()),
                        jsonPath("$.content.description")
                                .value(VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_REQUEST.getDescription()),
                        jsonPath("$.content.price")
                                .value(VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_REQUEST.getPrice().toString()),
                        jsonPath("$.content.quantity")
                                .value(VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_REQUEST.getQuantity()),
                        jsonPath("$.content.categories").isArray());
    }

    @Test
    void admin_UpdateProductWithInvalidArguments_Quantity_Zero_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_ZERO_UPDATE_PRODUCT_REQUEST))
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
    void admin_UpdateProductWithInvalidArguments_Quantity_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_NEGATIVE_UPDATE_PRODUCT_REQUEST))
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
    void admin_UpdateProductWithInvalidArguments_Quantity_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_NULL_UPDATE_PRODUCT_REQUEST))
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

    // Category IDs
    @Test
    void admin_UpdateProductWithInvalidArguments_CategoryIDs_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_NEGATIVE_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").isString(),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El id de la categoría debe ser mayor a 0"));
    }

    @Test
    void admin_UpdateProductWithInvalidArguments_CategoryIDs_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_NOT_FOUND_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_CATEGORY_NOT_FOUND));
    }

    @Test
    void admin_UpdateProductWithInvalidArguments_CategoryIDs_One_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_ONE_NULL_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").isString(),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El id de la categoría es requerido"));
    }

    @Test
    @DirtiesContext
    void admin_UpdateProductWithValidArguments_CategoryIDs_Empty_Ok() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_EMPTY_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(INVALID_CATEGORY_IDS_EMPTY_UPDATE_PRODUCT_REQUEST.getId()),
                        jsonPath("$.content.name").value(INVALID_CATEGORY_IDS_EMPTY_UPDATE_PRODUCT_REQUEST.getName()),
                        jsonPath("$.content.description")
                                .value(INVALID_CATEGORY_IDS_EMPTY_UPDATE_PRODUCT_REQUEST.getDescription()),
                        jsonPath("$.content.price")
                                .value(INVALID_CATEGORY_IDS_EMPTY_UPDATE_PRODUCT_REQUEST.getPrice().toString()),
                        jsonPath("$.content.quantity")
                                .value(INVALID_CATEGORY_IDS_EMPTY_UPDATE_PRODUCT_REQUEST.getQuantity()),
                        jsonPath("$.content.categories").isArray());
    }

    @Test
    @DirtiesContext
    void admin_UpdateProductWithInvalidArguments_CategoryIDs_Null_Ok() throws Exception {
        mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_NULL_UPDATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(INVALID_CATEGORY_IDS_NULL_UPDATE_PRODUCT_REQUEST.getId()),
                        jsonPath("$.content.name").value(INVALID_CATEGORY_IDS_NULL_UPDATE_PRODUCT_REQUEST.getName()),
                        jsonPath("$.content.description")
                                .value(INVALID_CATEGORY_IDS_NULL_UPDATE_PRODUCT_REQUEST.getDescription()),
                        jsonPath("$.content.price")
                                .value(INVALID_CATEGORY_IDS_NULL_UPDATE_PRODUCT_REQUEST.getPrice().toString()),
                        jsonPath("$.content.quantity")
                                .value(INVALID_CATEGORY_IDS_NULL_UPDATE_PRODUCT_REQUEST.getQuantity()),
                        jsonPath("$.content.categories").isArray());
    }
}
