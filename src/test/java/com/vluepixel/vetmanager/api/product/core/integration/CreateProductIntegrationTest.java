package com.vluepixel.vetmanager.api.product.core.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_USER_JWT;
import static com.vluepixel.vetmanager.api.product.core.data.CreateProductDataProvider.INVALID_CATEGORY_IDS_EMPTY_CREATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.CreateProductDataProvider.INVALID_CATEGORY_IDS_NEGATIVE_CREATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.CreateProductDataProvider.INVALID_CATEGORY_IDS_NOT_FOUND_CREATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.CreateProductDataProvider.INVALID_CATEGORY_IDS_NULL_CREATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.CreateProductDataProvider.INVALID_CATEGORY_IDS_ONE_NULL_CREATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.CreateProductDataProvider.INVALID_DESCRIPTION_BLANK_CREATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.CreateProductDataProvider.INVALID_DESCRIPTION_EMPTY_CREATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.CreateProductDataProvider.INVALID_DESCRIPTION_NULL_CREATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.CreateProductDataProvider.INVALID_NAME_BLANK_CREATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.CreateProductDataProvider.INVALID_NAME_EMPTY_CREATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.CreateProductDataProvider.INVALID_NAME_NULL_CREATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.CreateProductDataProvider.INVALID_NAME_TOO_LONG_CREATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.CreateProductDataProvider.INVALID_PRICE_NEGATIVE_CREATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.CreateProductDataProvider.INVALID_PRICE_NULL_CREATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.CreateProductDataProvider.INVALID_PRICE_TOO_BIG_CREATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.CreateProductDataProvider.INVALID_PRICE_ZERO_CREATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.CreateProductDataProvider.INVALID_QUANTITY_NEGATIVE_CREATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.CreateProductDataProvider.INVALID_QUANTITY_NULL_CREATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.CreateProductDataProvider.INVALID_QUANTITY_TOO_BIG_CREATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.CreateProductDataProvider.INVALID_QUANTITY_ZERO_CREATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.CreateProductDataProvider.VALID_CREATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.CreateProductDataProvider.VALID_NAME_MAX_LENGTH_CREATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.CreateProductDataProvider.VALID_PRICE_MAX_VALUE_CREATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.CreateProductDataProvider.VALID_QUANTITY_MAX_VALUE_CREATE_PRODUCT_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the create product use case.
 */
public class CreateProductIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Categoría creada exitosamente";
    private static final String MESSAGE_CATEGORY_NOT_FOUND = "Category no encontrado(a)";
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    @Test
    void noUser_CreateProductWithValidArguments_Forbidden() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // - Invalidations

    // Name
    @Test
    void noUser_CreateProductWithInvalidArguments_Name_TooLong_Forbidden() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_TOO_LONG_CREATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateProductWithValidArguments_Name_MaxLength_Forbidden() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_NAME_MAX_LENGTH_CREATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateProductWithInvalidArguments_Name_Blank_Forbidden() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_BLANK_CREATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateProductWithInvalidArguments_Name_Empty_Forbidden() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_EMPTY_CREATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateProductWithInvalidArguments_Name_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_NULL_CREATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Description
    @Test
    void noUser_CreateProductWithInvalidArguments_Description_Blank_Forbidden() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_BLANK_CREATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateProductWithInvalidArguments_Description_Empty_Forbidden() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_EMPTY_CREATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateProductWithInvalidArguments_Description_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_NULL_CREATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Price
    @Test
    void noUser_CreateProductWithInvalidArguments_Price_TooBig_Forbidden() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_TOO_BIG_CREATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateProductWithValidArguments_Price_MaxValue_Forbidden() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PRICE_MAX_VALUE_CREATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateProductWithInvalidArguments_Price_Zero_Forbidden() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_ZERO_CREATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateProductWithInvalidArguments_Price_Negative_Forbidden() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_NEGATIVE_CREATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateProductWithInvalidArguments_Price_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_NULL_CREATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Quantity
    @Test
    void noUser_CreateProductWithInvalidArguments_Quantity_TooBig_Forbidden() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_TOO_BIG_CREATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateProductWithValidArguments_Quantity_MaxValue_Forbidden() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_QUANTITY_MAX_VALUE_CREATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateProductWithInvalidArguments_Quantity_Zero_Forbidden() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_ZERO_CREATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateProductWithInvalidArguments_Quantity_Negative_Forbidden() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_NEGATIVE_CREATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateProductWithInvalidArguments_Quantity_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_NULL_CREATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Category IDs
    @Test
    void noUser_CreateProductWithInvalidArguments_CategoryIDs_Negative_Forbidden() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_NEGATIVE_CREATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateProductWithInvalidArguments_CategoryIDs_NotFound_Forbidden() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_NOT_FOUND_CREATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateProductWithInvalidArguments_CategoryIDs_One_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_ONE_NULL_CREATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateProductWithValidArguments_CategoryIDs_Empty_Forbidden() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_EMPTY_CREATE_PRODUCT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreateProductWithInvalidArguments_CategoryIDs_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_NULL_CREATE_PRODUCT_REQUEST)))
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
    void user_CreateProductWithValidArguments_Ok() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.name").value(VALID_CREATE_PRODUCT_REQUEST.getName()),
                        jsonPath("$.content.description").value(VALID_CREATE_PRODUCT_REQUEST.getDescription()),
                        jsonPath("$.content.price").value(VALID_CREATE_PRODUCT_REQUEST.getPrice().toString()),
                        jsonPath("$.content.quantity").value(VALID_CREATE_PRODUCT_REQUEST.getQuantity()),
                        jsonPath("$.content.categories").isArray());
    }

    // - Invalidations

    // Name
    @Test
    void user_CreateProductWithInvalidArguments_Name_TooLong_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_TOO_LONG_CREATE_PRODUCT_REQUEST))
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
    @Order(2)
    @DirtiesContext
    void user_CreateProductWithValidArguments_Name_MaxLength_Ok() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_NAME_MAX_LENGTH_CREATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.name").value(VALID_NAME_MAX_LENGTH_CREATE_PRODUCT_REQUEST.getName()),
                        jsonPath("$.content.description")
                                .value(VALID_NAME_MAX_LENGTH_CREATE_PRODUCT_REQUEST.getDescription()),
                        jsonPath("$.content.price")
                                .value(VALID_NAME_MAX_LENGTH_CREATE_PRODUCT_REQUEST.getPrice().toString()),
                        jsonPath("$.content.quantity")
                                .value(VALID_NAME_MAX_LENGTH_CREATE_PRODUCT_REQUEST.getQuantity()),
                        jsonPath("$.content.categories").isArray());
    }

    @Test
    void user_CreateProductWithInvalidArguments_Name_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_BLANK_CREATE_PRODUCT_REQUEST))
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
    void user_CreateProductWithInvalidArguments_Name_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_EMPTY_CREATE_PRODUCT_REQUEST))
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
    void user_CreateProductWithInvalidArguments_Name_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_NULL_CREATE_PRODUCT_REQUEST))
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
    void user_CreateProductWithInvalidArguments_Description_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_BLANK_CREATE_PRODUCT_REQUEST))
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
    void user_CreateProductWithInvalidArguments_Description_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_EMPTY_CREATE_PRODUCT_REQUEST))
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
    void user_CreateProductWithInvalidArguments_Description_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_NULL_CREATE_PRODUCT_REQUEST))
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
    void user_CreateProductWithInvalidArguments_Price_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_TOO_BIG_CREATE_PRODUCT_REQUEST))
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
    @Order(3)
    @DirtiesContext
    void user_CreateProductWithValidArguments_Price_MaxValue_Ok() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PRICE_MAX_VALUE_CREATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.name").value(VALID_PRICE_MAX_VALUE_CREATE_PRODUCT_REQUEST.getName()),
                        jsonPath("$.content.description")
                                .value(VALID_PRICE_MAX_VALUE_CREATE_PRODUCT_REQUEST.getDescription()),
                        jsonPath("$.content.price")
                                .value(VALID_PRICE_MAX_VALUE_CREATE_PRODUCT_REQUEST.getPrice().toString()),
                        jsonPath("$.content.quantity")
                                .value(VALID_PRICE_MAX_VALUE_CREATE_PRODUCT_REQUEST.getQuantity()),
                        jsonPath("$.content.categories").isArray());
    }

    @Test
    void user_CreateProductWithInvalidArguments_Price_Zero_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_ZERO_CREATE_PRODUCT_REQUEST))
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
    void user_CreateProductWithInvalidArguments_Price_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_NEGATIVE_CREATE_PRODUCT_REQUEST))
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
    void user_CreateProductWithInvalidArguments_Price_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_NULL_CREATE_PRODUCT_REQUEST))
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
    void user_CreateProductWithInvalidArguments_Quantity_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_TOO_BIG_CREATE_PRODUCT_REQUEST))
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
    @Order(4)
    @DirtiesContext
    void user_CreateProductWithValidArguments_Quantity_MaxValue_Ok() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_QUANTITY_MAX_VALUE_CREATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.name").value(VALID_QUANTITY_MAX_VALUE_CREATE_PRODUCT_REQUEST.getName()),
                        jsonPath("$.content.description")
                                .value(VALID_QUANTITY_MAX_VALUE_CREATE_PRODUCT_REQUEST.getDescription()),
                        jsonPath("$.content.price")
                                .value(VALID_QUANTITY_MAX_VALUE_CREATE_PRODUCT_REQUEST.getPrice().toString()),
                        jsonPath("$.content.quantity")
                                .value(VALID_QUANTITY_MAX_VALUE_CREATE_PRODUCT_REQUEST.getQuantity()),
                        jsonPath("$.content.categories").isArray());
    }

    @Test
    void user_CreateProductWithInvalidArguments_Quantity_Zero_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_ZERO_CREATE_PRODUCT_REQUEST))
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
    void user_CreateProductWithInvalidArguments_Quantity_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_NEGATIVE_CREATE_PRODUCT_REQUEST))
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
    void user_CreateProductWithInvalidArguments_Quantity_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_NULL_CREATE_PRODUCT_REQUEST))
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
    void user_CreateProductWithInvalidArguments_CategoryIDs_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_NEGATIVE_CREATE_PRODUCT_REQUEST))
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
    void user_CreateProductWithInvalidArguments_CategoryIDs_NotFound_NotFound() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_NOT_FOUND_CREATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_CATEGORY_NOT_FOUND));
    }

    @Test
    void user_CreateProductWithInvalidArguments_CategoryIDs_One_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_ONE_NULL_CREATE_PRODUCT_REQUEST))
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
    @Order(5)
    @DirtiesContext
    void user_CreateProductWithValidArguments_CategoryIDs_Empty_Ok() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_EMPTY_CREATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.name").value(INVALID_CATEGORY_IDS_EMPTY_CREATE_PRODUCT_REQUEST.getName()),
                        jsonPath("$.content.description")
                                .value(INVALID_CATEGORY_IDS_EMPTY_CREATE_PRODUCT_REQUEST.getDescription()),
                        jsonPath("$.content.price")
                                .value(INVALID_CATEGORY_IDS_EMPTY_CREATE_PRODUCT_REQUEST.getPrice().toString()),
                        jsonPath("$.content.quantity")
                                .value(INVALID_CATEGORY_IDS_EMPTY_CREATE_PRODUCT_REQUEST.getQuantity()),
                        jsonPath("$.content.categories").isArray());
    }

    @Test // TODO: Return Unprocessable Entity or Ok
    void user_CreateProductWithInvalidArguments_CategoryIDs_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_NULL_CREATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("categories"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("Las categorías son requeridas"));
    }

    // Role: ADMIN
    @Test
    @Order(6)
    @DirtiesContext
    void admin_CreateProductWithValidArguments_Ok() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.name").value(VALID_CREATE_PRODUCT_REQUEST.getName()),
                        jsonPath("$.content.description").value(VALID_CREATE_PRODUCT_REQUEST.getDescription()),
                        jsonPath("$.content.price").value(VALID_CREATE_PRODUCT_REQUEST.getPrice().toString()),
                        jsonPath("$.content.quantity").value(VALID_CREATE_PRODUCT_REQUEST.getQuantity()),
                        jsonPath("$.content.categories").isArray());
    }

    // - Invalidations

    // Name
    @Test
    void admin_CreateProductWithInvalidArguments_Name_TooLong_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_TOO_LONG_CREATE_PRODUCT_REQUEST))
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
    @Order(7)
    @DirtiesContext
    void admin_CreateProductWithValidArguments_Name_MaxLength_Ok() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_NAME_MAX_LENGTH_CREATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.name").value(VALID_NAME_MAX_LENGTH_CREATE_PRODUCT_REQUEST.getName()),
                        jsonPath("$.content.description")
                                .value(VALID_NAME_MAX_LENGTH_CREATE_PRODUCT_REQUEST.getDescription()),
                        jsonPath("$.content.price")
                                .value(VALID_NAME_MAX_LENGTH_CREATE_PRODUCT_REQUEST.getPrice().toString()),
                        jsonPath("$.content.quantity")
                                .value(VALID_NAME_MAX_LENGTH_CREATE_PRODUCT_REQUEST.getQuantity()),
                        jsonPath("$.content.categories").isArray());
    }

    @Test
    void admin_CreateProductWithInvalidArguments_Name_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_BLANK_CREATE_PRODUCT_REQUEST))
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
    void admin_CreateProductWithInvalidArguments_Name_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_EMPTY_CREATE_PRODUCT_REQUEST))
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
    void admin_CreateProductWithInvalidArguments_Name_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_NULL_CREATE_PRODUCT_REQUEST))
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
    void admin_CreateProductWithInvalidArguments_Description_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_BLANK_CREATE_PRODUCT_REQUEST))
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
    void admin_CreateProductWithInvalidArguments_Description_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_EMPTY_CREATE_PRODUCT_REQUEST))
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
    void admin_CreateProductWithInvalidArguments_Description_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DESCRIPTION_NULL_CREATE_PRODUCT_REQUEST))
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
    void admin_CreateProductWithInvalidArguments_Price_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_TOO_BIG_CREATE_PRODUCT_REQUEST))
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
    @Order(8)
    @DirtiesContext
    void admin_CreateProductWithValidArguments_Price_MaxValue_Ok() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_PRICE_MAX_VALUE_CREATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.name").value(VALID_PRICE_MAX_VALUE_CREATE_PRODUCT_REQUEST.getName()),
                        jsonPath("$.content.description")
                                .value(VALID_PRICE_MAX_VALUE_CREATE_PRODUCT_REQUEST.getDescription()),
                        jsonPath("$.content.price")
                                .value(VALID_PRICE_MAX_VALUE_CREATE_PRODUCT_REQUEST.getPrice().toString()),
                        jsonPath("$.content.quantity")
                                .value(VALID_PRICE_MAX_VALUE_CREATE_PRODUCT_REQUEST.getQuantity()),
                        jsonPath("$.content.categories").isArray());
    }

    @Test
    void admin_CreateProductWithInvalidArguments_Price_Zero_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_ZERO_CREATE_PRODUCT_REQUEST))
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
    void admin_CreateProductWithInvalidArguments_Price_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_NEGATIVE_CREATE_PRODUCT_REQUEST))
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
    void admin_CreateProductWithInvalidArguments_Price_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRICE_NULL_CREATE_PRODUCT_REQUEST))
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
    void admin_CreateProductWithInvalidArguments_Quantity_TooBig_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_TOO_BIG_CREATE_PRODUCT_REQUEST))
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
    @Order(9)
    @DirtiesContext
    void admin_CreateProductWithValidArguments_Quantity_MaxValue_Ok() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_QUANTITY_MAX_VALUE_CREATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.name").value(VALID_QUANTITY_MAX_VALUE_CREATE_PRODUCT_REQUEST.getName()),
                        jsonPath("$.content.description")
                                .value(VALID_QUANTITY_MAX_VALUE_CREATE_PRODUCT_REQUEST.getDescription()),
                        jsonPath("$.content.price")
                                .value(VALID_QUANTITY_MAX_VALUE_CREATE_PRODUCT_REQUEST.getPrice().toString()),
                        jsonPath("$.content.quantity")
                                .value(VALID_QUANTITY_MAX_VALUE_CREATE_PRODUCT_REQUEST.getQuantity()),
                        jsonPath("$.content.categories").isArray());
    }

    @Test
    void admin_CreateProductWithInvalidArguments_Quantity_Zero_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_ZERO_CREATE_PRODUCT_REQUEST))
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
    void admin_CreateProductWithInvalidArguments_Quantity_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_NEGATIVE_CREATE_PRODUCT_REQUEST))
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
    void admin_CreateProductWithInvalidArguments_Quantity_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_QUANTITY_NULL_CREATE_PRODUCT_REQUEST))
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
    void admin_CreateProductWithInvalidArguments_CategoryIDs_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_NEGATIVE_CREATE_PRODUCT_REQUEST))
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
    void admin_CreateProductWithInvalidArguments_CategoryIDs_NotFound_NotFound() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_NOT_FOUND_CREATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_CATEGORY_NOT_FOUND));
    }

    @Test
    void admin_CreateProductWithInvalidArguments_CategoryIDs_One_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_ONE_NULL_CREATE_PRODUCT_REQUEST))
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
    @Order(10)
    @DirtiesContext
    void admin_CreateProductWithValidArguments_CategoryIDs_Empty_Ok() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_EMPTY_CREATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(3),
                        jsonPath("$.content.name").value(INVALID_CATEGORY_IDS_EMPTY_CREATE_PRODUCT_REQUEST.getName()),
                        jsonPath("$.content.description")
                                .value(INVALID_CATEGORY_IDS_EMPTY_CREATE_PRODUCT_REQUEST.getDescription()),
                        jsonPath("$.content.price")
                                .value(INVALID_CATEGORY_IDS_EMPTY_CREATE_PRODUCT_REQUEST.getPrice().toString()),
                        jsonPath("$.content.quantity")
                                .value(INVALID_CATEGORY_IDS_EMPTY_CREATE_PRODUCT_REQUEST.getQuantity()),
                        jsonPath("$.content.categories").isArray());
    }

    @Test // TODO: Return Unprocessable Entity or Ok
    void admin_CreateProductWithInvalidArguments_CategoryIDs_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_CATEGORY_IDS_NULL_CREATE_PRODUCT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("categories"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("Las categorías son requeridas"));
    }
}
