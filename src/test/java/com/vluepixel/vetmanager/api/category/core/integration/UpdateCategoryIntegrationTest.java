package com.vluepixel.vetmanager.api.category.core.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_USER_JWT;
import static com.vluepixel.vetmanager.api.category.core.data.UpdateCategoryDataProvider.INVALID_ID_NEGATIVE_UPDATE_CATEGORY_REQUEST;
import static com.vluepixel.vetmanager.api.category.core.data.UpdateCategoryDataProvider.INVALID_ID_NOT_FOUND_UPDATE_CATEGORY_REQUEST;
import static com.vluepixel.vetmanager.api.category.core.data.UpdateCategoryDataProvider.INVALID_NAME_BLANK_UPDATE_CATEGORY_REQUEST;
import static com.vluepixel.vetmanager.api.category.core.data.UpdateCategoryDataProvider.INVALID_NAME_EMPTY_UPDATE_CATEGORY_REQUEST;
import static com.vluepixel.vetmanager.api.category.core.data.UpdateCategoryDataProvider.INVALID_NAME_NULL_UPDATE_CATEGORY_REQUEST;
import static com.vluepixel.vetmanager.api.category.core.data.UpdateCategoryDataProvider.INVALID_NAME_TOO_LONG_UPDATE_CATEGORY_REQUEST;
import static com.vluepixel.vetmanager.api.category.core.data.UpdateCategoryDataProvider.VALID_NAME_MAX_LENGTH_UPDATE_CATEGORY_REQUEST;
import static com.vluepixel.vetmanager.api.category.core.data.UpdateCategoryDataProvider.VALID_UPDATE_CATEGORY_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the update category use case.
 */
public class UpdateCategoryIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Categoría actualizada exitosamente";
    private static final String MESSAGE_NOT_FOUND = "Categoría no encontrado(a)";
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    @Test
    void noUser_UpdateCategoryWithValidArguments_Forbidden() throws Exception {
        mockMvc.perform(put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_CATEGORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // - Validations

    // ID

    @Test
    void noUser_UpdateCategoryWithInvalidArguments_ID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NOT_FOUND_UPDATE_CATEGORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateCategoryWithInvalidArguments_ID_Negative_Forbidden() throws Exception {
        mockMvc.perform(put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NEGATIVE_UPDATE_CATEGORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Name
    @Test
    void noUser_UpdateCategoryWithInValidArguments_Name_TooLong_Forbidden() throws Exception {
        mockMvc.perform(put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_TOO_LONG_UPDATE_CATEGORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateCategoryWithValidArguments_Name_MaxLength_Forbidden() throws Exception {
        mockMvc.perform(put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_NAME_MAX_LENGTH_UPDATE_CATEGORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateCategoryWithInValidArguments_Name_Blank_Forbidden() throws Exception {
        mockMvc.perform(put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_BLANK_UPDATE_CATEGORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateCategoryWithInValidArguments_Name_Empty_Forbidden() throws Exception {
        mockMvc.perform(put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_EMPTY_UPDATE_CATEGORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateCategoryWithInValidArguments_Name_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_NULL_UPDATE_CATEGORY_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER
    @Test
    @DirtiesContext
    void user_UpdateCategoryWithValidArguments_Ok() throws Exception {
        mockMvc.perform(put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_CATEGORY_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_UPDATE_CATEGORY_REQUEST.getId()),
                        jsonPath("$.content.name").value(VALID_UPDATE_CATEGORY_REQUEST.getName()));
    }

    // - Validations

    // ID

    @Test
    void user_UpdateCategoryWithInvalidArguments_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NOT_FOUND_UPDATE_CATEGORY_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_NOT_FOUND));
    }

    @Test
    void user_UpdateCategoryWithInvalidArguments_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NEGATIVE_UPDATE_CATEGORY_REQUEST))
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

    // Name
    @Test
    void user_UpdateCategoryWithInValidArguments_Name_TooLong_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_TOO_LONG_UPDATE_CATEGORY_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre no puede tener más de 12 caracteres"));
    }

    @Test
    @DirtiesContext
    void user_UpdateCategoryWithValidArguments_Name_MaxLength_Ok() throws Exception {
        mockMvc.perform(put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_NAME_MAX_LENGTH_UPDATE_CATEGORY_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_NAME_MAX_LENGTH_UPDATE_CATEGORY_REQUEST.getId()),
                        jsonPath("$.content.name").value(VALID_NAME_MAX_LENGTH_UPDATE_CATEGORY_REQUEST.getName()));
    }

    @Test
    void user_UpdateCategoryWithInValidArguments_Name_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_BLANK_UPDATE_CATEGORY_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre es requerido"));
    }

    @Test
    void user_UpdateCategoryWithInValidArguments_Name_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_EMPTY_UPDATE_CATEGORY_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre es requerido"));
    }

    @Test
    void user_UpdateCategoryWithInValidArguments_Name_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_NULL_UPDATE_CATEGORY_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre es requerido"));
    }

    // Role: ADMIN
    @Test
    @DirtiesContext
    void admin_UpdateCategoryWithValidArguments_Ok() throws Exception {
        mockMvc.perform(put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_CATEGORY_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_UPDATE_CATEGORY_REQUEST.getId()),
                        jsonPath("$.content.name").value(VALID_UPDATE_CATEGORY_REQUEST.getName()));
    }

    // - Validations

    // ID
    @Test
    void admin_UpdateCategoryWithInvalidArguments_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NOT_FOUND_UPDATE_CATEGORY_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_NOT_FOUND));
    }

    @Test
    void admin_UpdateCategoryWithInvalidArguments_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NEGATIVE_UPDATE_CATEGORY_REQUEST))
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

    // Name
    @Test
    void admin_UpdateCategoryWithInValidArguments_Name_TooLong_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_TOO_LONG_UPDATE_CATEGORY_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre no puede tener más de 12 caracteres"));
    }

    @Test
    @DirtiesContext
    void admin_UpdateCategoryWithValidArguments_Name_MaxLength_Ok() throws Exception {
        mockMvc.perform(put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_NAME_MAX_LENGTH_UPDATE_CATEGORY_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_NAME_MAX_LENGTH_UPDATE_CATEGORY_REQUEST.getId()),
                        jsonPath("$.content.name").value(VALID_NAME_MAX_LENGTH_UPDATE_CATEGORY_REQUEST.getName()));
    }

    @Test
    void admin_UpdateCategoryWithInValidArguments_Name_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_BLANK_UPDATE_CATEGORY_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre es requerido"));
    }

    @Test
    void admin_UpdateCategoryWithInValidArguments_Name_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_EMPTY_UPDATE_CATEGORY_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre es requerido"));
    }

    @Test
    void admin_UpdateCategoryWithInValidArguments_Name_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_NULL_UPDATE_CATEGORY_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre es requerido"));
    }
}
