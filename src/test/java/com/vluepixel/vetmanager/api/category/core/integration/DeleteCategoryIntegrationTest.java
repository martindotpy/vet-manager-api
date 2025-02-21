package com.vluepixel.vetmanager.api.category.core.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_USER_JWT;
import static com.vluepixel.vetmanager.api.category.core.data.UpdateCategoryDataProvider.VALID_UPDATE_CATEGORY_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the delete category functionality.
 */
public class DeleteCategoryIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Categoría eliminada exitosamente";
    private static final String MESSAGE_CONFLICT = "No se puede eliminar el/la category porque está en uso en otros registros";
    private static final Function<String, String> MESSAGE_NOT_FOUND = parameter -> String
            .format("Category con id %s no encontrado(a)", parameter);

    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    @Test
    @DirtiesContext
    void noUser_DeleteCategoryWithValidParams_Forbidden() throws Exception {
        mockMvc.perform(delete("/category/{id}", 3))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_DeleteCategoryWithInvalidParams_WithUse_Forbidden() throws Exception {
        mockMvc.perform(delete("/category/{id}", VALID_UPDATE_CATEGORY_REQUEST.getId()))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_DeleteCategoryWithInvalidParams_ID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(delete("/category/{id}", 10))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_DeleteCategoryWithInvalidParams_ID_Invalid_Forbidden() throws Exception {
        mockMvc.perform(delete("/category/{id}", "invalid"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_DeleteCategoryWithInvalidParams_ID_Negative_Forbidden() throws Exception {
        mockMvc.perform(delete("/category/{id}", -1))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER
    @Test
    @DirtiesContext
    void user_DeleteCategoryWithValidParams_Ok() throws Exception {
        mockMvc.perform(delete("/category/{id}", 2)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(MESSAGE_OK));
    }

    @Test
    void user_DeleteCategoryWithInvalidParams_WithUse_Conflict() throws Exception {
        mockMvc.perform(delete("/category/{id}", VALID_UPDATE_CATEGORY_REQUEST.getId())
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value(MESSAGE_CONFLICT));
    }

    @Test
    void user_DeleteCategoryWithInvalidParams_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(delete("/category/{id}", 10)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND.apply("10")));
    }

    @Test
    void user_DeleteCategoryWithInvalidParams_ID_Invalid_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/category/{id}", "invalid")
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("path.id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("Valor numérico inválido"));
    }

    @Test
    void user_DeleteCategoryWithInvalidParams_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/category/{id}", -1)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("query.id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id debe ser mayor a 0"));
    }

    // Role: ADMIN
    @Test
    @DirtiesContext
    void admin_DeleteCategoryWithValidParams_Ok() throws Exception {
        mockMvc.perform(delete("/category/{id}", 2)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(MESSAGE_OK));
    }

    @Test
    void admin_DeleteCategoryWithInvalidParams_WithUse_Conflict() throws Exception {
        mockMvc.perform(delete("/category/{id}", VALID_UPDATE_CATEGORY_REQUEST.getId())
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value(MESSAGE_CONFLICT));
    }

    @Test
    void admin_DeleteCategoryWithInvalidParams_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(delete("/category/{id}", 10)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND.apply("10")));
    }

    @Test
    void admin_DeleteCategoryWithInvalidParams_ID_Invalid_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/category/{id}", "invalid")
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("path.id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("Valor numérico inválido"));
    }

    @Test
    void admin_DeleteCategoryWithInvalidParams_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/category/{id}", -1)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("query.id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id debe ser mayor a 0"));
    }
}
