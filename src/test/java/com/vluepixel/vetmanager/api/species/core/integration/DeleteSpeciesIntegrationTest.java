package com.vluepixel.vetmanager.api.species.core.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_USER_JWT;
import static com.vluepixel.vetmanager.api.species.core.data.UpdateSpeciesDataProvider.VALID_UPDATE_SPECIES_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the delete species functionality.
 */
public class DeleteSpeciesIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Cita eliminada exitosamente";
    private static final String MESSAGE_CONFLICT = "No se puede eliminar el/la especie porque está en uso en otros registros";
    private static final String MESSAGE_NOT_FOUND = "Especie con id 10 no encontrado(a)";

    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    @Test
    @DirtiesContext
    void noUser_DeleteSpeciesWithValidParams_Forbidden() throws Exception {
        mockMvc.perform(delete("/species/{id}", 3))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_DeleteSpeciesWithInvalidParams_WithUse_Forbidden() throws Exception {
        mockMvc.perform(delete("/species/{id}", VALID_UPDATE_SPECIES_REQUEST.getId()))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_DeleteSpeciesWithInvalidParams_ID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(delete("/species/{id}", 10))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_DeleteSpeciesWithInvalidParams_ID_Invalid_Forbidden() throws Exception {
        mockMvc.perform(delete("/species/{id}", "invalid"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_DeleteSpeciesWithInvalidParams_ID_Negative_Forbidden() throws Exception {
        mockMvc.perform(delete("/species/{id}", -1))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER
    @Test
    @DirtiesContext
    void user_DeleteSpeciesWithValidParams_Ok() throws Exception {
        mockMvc.perform(delete("/species/{id}", 3)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(MESSAGE_OK));
    }

    @Test
    void user_DeleteSpeciesWithInvalidParams_WithUse_Conflict() throws Exception {
        mockMvc.perform(delete("/species/{id}", VALID_UPDATE_SPECIES_REQUEST.getId())
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value(MESSAGE_CONFLICT));
    }

    @Test
    void user_DeleteSpeciesWithInvalidParams_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(delete("/species/{id}", 10)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND));
    }

    @Test
    void user_DeleteSpeciesWithInvalidParams_ID_Invalid_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/species/{id}", "invalid")
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
    void user_DeleteSpeciesWithInvalidParams_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/species/{id}", -1)
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
    void admin_DeleteSpeciesWithValidParams_Ok() throws Exception {
        mockMvc.perform(delete("/species/{id}", 3)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(MESSAGE_OK));
    }

    @Test
    void admin_DeleteSpeciesWithInvalidParams_WithUse_Conflict() throws Exception {
        mockMvc.perform(delete("/species/{id}", VALID_UPDATE_SPECIES_REQUEST.getId())
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value(MESSAGE_CONFLICT));
    }

    @Test
    void admin_DeleteSpeciesWithInvalidParams_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(delete("/species/{id}", 10)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND));
    }

    @Test
    void admin_DeleteSpeciesWithInvalidParams_ID_Invalid_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/species/{id}", "invalid")
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
    void admin_DeleteSpeciesWithInvalidParams_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(delete("/species/{id}", -1)
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
