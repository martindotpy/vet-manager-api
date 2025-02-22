package com.vluepixel.vetmanager.api.user.core.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.ADMIN_DTO;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_USER_JWT;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.USER_DTO;
import static com.vluepixel.vetmanager.api.user.core.data.UpdateUserDataProvider.INVALID_FIRSTNAME_BLANK_UPDATE_ADMIN_REQUEST;
import static com.vluepixel.vetmanager.api.user.core.data.UpdateUserDataProvider.INVALID_FIRSTNAME_BLANK_UPDATE_USER_REQUEST;
import static com.vluepixel.vetmanager.api.user.core.data.UpdateUserDataProvider.INVALID_FIRSTNAME_EMPTY_UPDATE_ADMIN_REQUEST;
import static com.vluepixel.vetmanager.api.user.core.data.UpdateUserDataProvider.INVALID_FIRSTNAME_EMPTY_UPDATE_USER_REQUEST;
import static com.vluepixel.vetmanager.api.user.core.data.UpdateUserDataProvider.INVALID_FIRSTNAME_NULL_UPDATE_ADMIN_REQUEST;
import static com.vluepixel.vetmanager.api.user.core.data.UpdateUserDataProvider.INVALID_FIRSTNAME_NULL_UPDATE_USER_REQUEST;
import static com.vluepixel.vetmanager.api.user.core.data.UpdateUserDataProvider.INVALID_FIRSTNAME_TOO_LONG_UPDATE_ADMIN_REQUEST;
import static com.vluepixel.vetmanager.api.user.core.data.UpdateUserDataProvider.INVALID_FIRSTNAME_TOO_LONG_UPDATE_USER_REQUEST;
import static com.vluepixel.vetmanager.api.user.core.data.UpdateUserDataProvider.INVALID_LASTNAME_BLANK_UPDATE_ADMIN_REQUEST;
import static com.vluepixel.vetmanager.api.user.core.data.UpdateUserDataProvider.INVALID_LASTNAME_BLANK_UPDATE_USER_REQUEST;
import static com.vluepixel.vetmanager.api.user.core.data.UpdateUserDataProvider.INVALID_LASTNAME_EMPTY_UPDATE_ADMIN_REQUEST;
import static com.vluepixel.vetmanager.api.user.core.data.UpdateUserDataProvider.INVALID_LASTNAME_EMPTY_UPDATE_USER_REQUEST;
import static com.vluepixel.vetmanager.api.user.core.data.UpdateUserDataProvider.INVALID_LASTNAME_NULL_UPDATE_ADMIN_REQUEST;
import static com.vluepixel.vetmanager.api.user.core.data.UpdateUserDataProvider.INVALID_LASTNAME_NULL_UPDATE_USER_REQUEST;
import static com.vluepixel.vetmanager.api.user.core.data.UpdateUserDataProvider.INVALID_LASTNAME_TOO_LONG_UPDATE_ADMIN_REQUEST;
import static com.vluepixel.vetmanager.api.user.core.data.UpdateUserDataProvider.INVALID_LASTNAME_TOO_LONG_UPDATE_USER_REQUEST;
import static com.vluepixel.vetmanager.api.user.core.data.UpdateUserDataProvider.INVALID_UPDATE_USER_REQUEST;
import static com.vluepixel.vetmanager.api.user.core.data.UpdateUserDataProvider.VALID_FIRSTNAME_MAX_LENGTH_UPDATE_ADMIN_REQUEST;
import static com.vluepixel.vetmanager.api.user.core.data.UpdateUserDataProvider.VALID_FIRSTNAME_MAX_LENGTH_UPDATE_USER_REQUEST;
import static com.vluepixel.vetmanager.api.user.core.data.UpdateUserDataProvider.VALID_LASTNAME_MAX_LENGTH_UPDATE_ADMIN_REQUEST;
import static com.vluepixel.vetmanager.api.user.core.data.UpdateUserDataProvider.VALID_LASTNAME_MAX_LENGTH_UPDATE_USER_REQUEST;
import static com.vluepixel.vetmanager.api.user.core.data.UpdateUserDataProvider.VALID_UPDATE_ADMIN_REQUEST;
import static com.vluepixel.vetmanager.api.user.core.data.UpdateUserDataProvider.VALID_UPDATE_USER_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.function.Function;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the update user functionality.
 *
 * <p>
 * Only the admin user can update other users.
 * </p>
 */
class UpdateUserIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Usuario actualizado exitosamente";
    private static final Function<String, String> MESSAGE_NOT_FOUND = parameter -> String
            .format("Usuario con id %s no encontrado(a)", parameter);

    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // /user
    @Test
    void noUser_UpdateCurrentUserAsOtherWithValidArguments_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_ADMIN_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateCurrentUserAsOtherWithAnother_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_USER_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // - Invalid Arguments
    // FirstName
    @Test
    void noUser_UpdateCurrentUserAsOtherWithAnotherWithInvalidArgument_FirstName_TooLong_Forbidden()
            throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_TOO_LONG_UPDATE_USER_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateCurrentUserAsOtherWithAnotherWithValidArgument_FirstName_MaxLength_Forbidden()
            throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_FIRSTNAME_MAX_LENGTH_UPDATE_USER_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateCurrentUserAsOtherWithAnotherWithInvalidArgument_FirstName_Blank_Forbidden()
            throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_BLANK_UPDATE_USER_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateCurrentUserAsOtherWithAnotherWithInvalidArgument_FirstName_Empty_Forbidden()
            throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_EMPTY_UPDATE_USER_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateCurrentUserAsOtherWithAnotherWithInvalidArgument_FirstName_Null_Forbidden()
            throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_NULL_UPDATE_USER_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // LastName
    @Test
    void noUser_UpdateCurrentUserAsOtherWithAnotherWithInvalidArgument_LastName_TooLong_Forbidden()
            throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_TOO_LONG_UPDATE_USER_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateCurrentUserAsOtherWithWithAnotherValidArgument_LastName_MaxLength_Forbidden()
            throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_LASTNAME_MAX_LENGTH_UPDATE_USER_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateCurrentUserAsOtherWithAnotherWithInvalidArgument_LastName_Blank_Forbidden()
            throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_BLANK_UPDATE_USER_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateCurrentUserAsOtherAsOtherWithWithAnotherInvalidArgument_LastName_Empty_Forbidden()
            throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_EMPTY_UPDATE_USER_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateCurrentUserAsOtherWithAnotherIDWithInvalidArgument_LastName_Null_Forbidden()
            throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_NULL_UPDATE_USER_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // - Invalid arguments

    // FirstName
    @Test
    void noUser_UpdateCurrentUserAsOtherWithInvalidArgument_FirstName_TooLong_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_TOO_LONG_UPDATE_ADMIN_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateCurrentUserAsOtherWithValidArgument_FirstName_MaxLength_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_FIRSTNAME_MAX_LENGTH_UPDATE_ADMIN_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateCurrentUserAsOtherWithInvalidArgument_FirstName_Blank_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_BLANK_UPDATE_ADMIN_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateCurrentUserAsOtherWithInvalidArgument_FirstName_Empty_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_EMPTY_UPDATE_ADMIN_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateCurrentUserAsOtherWithInvalidArgument_FirstName_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_NULL_UPDATE_ADMIN_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // LastName
    @Test
    void noUser_UpdateCurrentUserAsOtherWithInvalidArgument_LastName_TooLong_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_TOO_LONG_UPDATE_ADMIN_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateCurrentUserAsOtherWithValidArgument_LastName_MaxLength_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_LASTNAME_MAX_LENGTH_UPDATE_ADMIN_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateCurrentUserAsOtherWithInvalidArgument_LastName_Blank_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_BLANK_UPDATE_ADMIN_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateCurrentUserAsOtherWithInvalidArgument_LastName_Empty_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_EMPTY_UPDATE_ADMIN_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateCurrentUserAsOtherWithInvalidArgument_LastName_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_NULL_UPDATE_ADMIN_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // /user/{id}
    @Test
    void noUser_UpdateOtherUserAsOther_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", USER_DTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_USER_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));

    }

    @Test
    void noUser_UpdateCurrentUserAsOtherAsOtherUser_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", ADMIN_DTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_USER_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // - Invalid arguments

    // ID
    @Test
    void noUser_UpdateOtherUserAsOtherWithInvalidArgument_ID_Invalid_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", "invalid")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_USER_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateOtherUserAsOtherWithInvalidArgument_ID_Negative_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", -1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_USER_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateOtherUserAsOtherWithInvalidArgument_ID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 10)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_UPDATE_USER_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // - Invalid arguments

    // FirstName
    @Test
    void noUser_UpdateOtherUserAsOtherWithInvalidArgument_FirstName_TooLong_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_TOO_LONG_UPDATE_USER_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateOtherUserAsOtherWithValidArgument_FirstName_MaxLength_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_FIRSTNAME_MAX_LENGTH_UPDATE_USER_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateOtherUserAsOtherWithInvalidArgument_FirstName_Blank_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_BLANK_UPDATE_USER_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateOtherUserAsOtherWithInvalidArgument_FirstName_Empty_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_EMPTY_UPDATE_USER_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateOtherUserAsOtherWithInvalidArgument_FirstName_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_NULL_UPDATE_USER_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // LastName
    @Test
    void noUser_UpdateOtherUserAsOtherWithInvalidArgument_LastName_TooLong_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_TOO_LONG_UPDATE_USER_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateOtherUserAsOtherWithValidArgument_LastName_MaxLength_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_LASTNAME_MAX_LENGTH_UPDATE_USER_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateOtherUserAsOtherWithInvalidArgument_LastName_Blank_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_BLANK_UPDATE_USER_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateOtherUserAsOtherWithInvalidArgument_LastName_Empty_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_EMPTY_UPDATE_USER_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_UpdateOtherUserAsOtherWithInvalidArgument_LastName_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_NULL_UPDATE_USER_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER AND OTHER
    // /user
    @Test
    void user_UpdateCurrentUserAsOtherWithValidArguments_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserAsOtherWithAnother_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // - Invalid Arguments
    // FirstName
    @Test
    void user_UpdateCurrentUserAsOtherWithAnotherWithInvalidArgument_FirstName_TooLong_Forbidden()
            throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_TOO_LONG_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserAsOtherWithAnotherWithValidArgument_FirstName_MaxLength_Forbidden()
            throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_FIRSTNAME_MAX_LENGTH_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserAsOtherWithAnotherWithInvalidArgument_FirstName_Blank_Forbidden()
            throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_BLANK_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserAsOtherWithAnotherWithInvalidArgument_FirstName_Empty_Forbidden()
            throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_EMPTY_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserAsOtherWithAnotherWithInvalidArgument_FirstName_Null_Forbidden()
            throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_NULL_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // LastName
    @Test
    void user_UpdateCurrentUserAsOtherWithAnotherWithInvalidArgument_LastName_TooLong_Forbidden()
            throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_TOO_LONG_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserAsOtherWithWithAnotherValidArgument_LastName_MaxLength_Forbidden()
            throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_LASTNAME_MAX_LENGTH_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserAsOtherWithAnotherWithInvalidArgument_LastName_Blank_Forbidden()
            throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_BLANK_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserAsOtherAsOtherWithWithAnotherInvalidArgument_LastName_Empty_Forbidden()
            throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_EMPTY_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserAsOtherWithAnotherIDWithInvalidArgument_LastName_Null_Forbidden()
            throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_NULL_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // - Invalid arguments

    // FirstName
    @Test
    void user_UpdateCurrentUserAsOtherWithInvalidArgument_FirstName_TooLong_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_TOO_LONG_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserAsOtherWithValidArgument_FirstName_MaxLength_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_FIRSTNAME_MAX_LENGTH_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserAsOtherWithInvalidArgument_FirstName_Blank_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_BLANK_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserAsOtherWithInvalidArgument_FirstName_Empty_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_EMPTY_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserAsOtherWithInvalidArgument_FirstName_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_NULL_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // LastName
    @Test
    void user_UpdateCurrentUserAsOtherWithInvalidArgument_LastName_TooLong_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_TOO_LONG_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserAsOtherWithValidArgument_LastName_MaxLength_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_LASTNAME_MAX_LENGTH_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserAsOtherWithInvalidArgument_LastName_Blank_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_BLANK_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserAsOtherWithInvalidArgument_LastName_Empty_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_EMPTY_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserAsOtherWithInvalidArgument_LastName_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_NULL_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // /user/{id}
    @Test
    void user_UpdateOtherUserAsOther_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", USER_DTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));

    }

    @Test
    void user_UpdateCurrentUserAsOtherAsOtherUser_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", ADMIN_DTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // - Invalid arguments

    // ID
    @Test
    void user_UpdateOtherUserAsOtherWithInvalidArgument_ID_Invalid_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", "invalid")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateOtherUserAsOtherWithInvalidArgument_ID_Negative_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", -1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateOtherUserAsOtherWithInvalidArgument_ID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 10)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // - Invalid arguments

    // FirstName
    @Test
    void user_UpdateOtherUserAsOtherWithInvalidArgument_FirstName_TooLong_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_TOO_LONG_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateOtherUserAsOtherWithValidArgument_FirstName_MaxLength_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_FIRSTNAME_MAX_LENGTH_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateOtherUserAsOtherWithInvalidArgument_FirstName_Blank_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_BLANK_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateOtherUserAsOtherWithInvalidArgument_FirstName_Empty_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_EMPTY_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateOtherUserAsOtherWithInvalidArgument_FirstName_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_NULL_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // LastName
    @Test
    void user_UpdateOtherUserAsOtherWithInvalidArgument_LastName_TooLong_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_TOO_LONG_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateOtherUserAsOtherWithValidArgument_LastName_MaxLength_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_LASTNAME_MAX_LENGTH_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateOtherUserAsOtherWithInvalidArgument_LastName_Blank_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_BLANK_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateOtherUserAsOtherWithInvalidArgument_LastName_Empty_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_EMPTY_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateOtherUserAsOtherWithInvalidArgument_LastName_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_NULL_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Role: USER AND SAME
    // /user
    @Test
    void user_UpdateCurrentUserWithValidArguments_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserWithAnother_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // - Invalid Arguments
    // FirstName
    @Test
    void user_UpdateCurrentUserWithAnotherWithInvalidArgument_FirstName_TooLong_Forbidden()
            throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_TOO_LONG_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserWithAnotherWithValidArgument_FirstName_MaxLength_Forbidden()
            throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_FIRSTNAME_MAX_LENGTH_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserWithAnotherWithInvalidArgument_FirstName_Blank_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_BLANK_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserWithAnotherWithInvalidArgument_FirstName_Empty_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_EMPTY_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserWithAnotherWithInvalidArgument_FirstName_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_NULL_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // LastName
    @Test
    void user_UpdateCurrentUserWithAnotherWithInvalidArgument_LastName_TooLong_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_TOO_LONG_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserWithWithAnotherValidArgument_LastName_MaxLength_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_LASTNAME_MAX_LENGTH_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserWithAnotherWithInvalidArgument_LastName_Blank_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_BLANK_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserWithWithAnotherInvalidArgument_LastName_Empty_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_EMPTY_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserWithAnotherIDWithInvalidArgument_LastName_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_NULL_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // - Invalid arguments

    // FirstName
    @Test
    void user_UpdateCurrentUserWithInvalidArgument_FirstName_TooLong_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_TOO_LONG_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserWithValidArgument_FirstName_MaxLength_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_FIRSTNAME_MAX_LENGTH_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserWithInvalidArgument_FirstName_Blank_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_BLANK_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserWithInvalidArgument_FirstName_Empty_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_EMPTY_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserWithInvalidArgument_FirstName_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_NULL_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // LastName
    @Test
    void user_UpdateCurrentUserWithInvalidArgument_LastName_TooLong_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_TOO_LONG_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserWithValidArgument_LastName_MaxLength_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_LASTNAME_MAX_LENGTH_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserWithInvalidArgument_LastName_Blank_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_BLANK_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserWithInvalidArgument_LastName_Empty_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_EMPTY_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateCurrentUserWithInvalidArgument_LastName_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_NULL_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // /user/{id}
    @Test
    void user_UpdateOtherUser_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", USER_DTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));

    }

    @Test
    void user_UpdateCurrentUserAsOtherUser_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", ADMIN_DTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // - Invalid arguments

    // ID
    @Test
    void user_UpdateOtherUserWithInvalidArgument_ID_Invalid_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", "invalid")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateOtherUserWithInvalidArgument_ID_Negative_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", -1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateOtherUserWithInvalidArgument_ID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 10)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // - Invalid arguments

    // FirstName
    @Test
    void user_UpdateOtherUserWithInvalidArgument_FirstName_TooLong_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_TOO_LONG_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateOtherUserWithValidArgument_FirstName_MaxLength_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_FIRSTNAME_MAX_LENGTH_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateOtherUserWithInvalidArgument_FirstName_Blank_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_BLANK_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateOtherUserWithInvalidArgument_FirstName_Empty_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_EMPTY_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateOtherUserWithInvalidArgument_FirstName_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_NULL_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // LastName
    @Test
    void user_UpdateOtherUserWithInvalidArgument_LastName_TooLong_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_TOO_LONG_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateOtherUserWithValidArgument_LastName_MaxLength_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_LASTNAME_MAX_LENGTH_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateOtherUserWithInvalidArgument_LastName_Blank_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_BLANK_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateOtherUserWithInvalidArgument_LastName_Empty_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_EMPTY_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void user_UpdateOtherUserWithInvalidArgument_LastName_Null_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_NULL_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Role: ADMIN
    // /user
    @Test
    @Order(4)
    @DirtiesContext
    void admin_UpdateCurrentUserWithValidArguments_Ok() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.jwt").isString());
    }

    @Test
    void admin_UpdateCurrentUserWithAnother_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("path.id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("Id del usuario y del cuerpo no coinciden"));
    }

    // - Invalid Arguments
    // FirstName
    @Test
    void admin_UpdateCurrentUserWithAnotherWithInvalidArgument_FirstName_TooLong_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_TOO_LONG_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(2),
                        jsonPath("$.details[0].field").value("first_name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre es muy largo"),
                        jsonPath("$.details[1].field").value("path.id"),
                        jsonPath("$.details[1].messages.length()").value(1),
                        jsonPath("$.details[1].messages").value("Id del usuario y del cuerpo no coinciden"));
    }

    @Test
    void admin_UpdateCurrentUserWithAnotherWithValidArgument_FirstName_MaxLength_UnprocessableEntity()
            throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_FIRSTNAME_MAX_LENGTH_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("path.id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("Id del usuario y del cuerpo no coinciden"));
    }

    @Test
    void admin_UpdateCurrentUserWithAnotherWithInvalidArgument_FirstName_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_BLANK_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(2),
                        jsonPath("$.details[0].field").value("first_name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre es requerido"),
                        jsonPath("$.details[1].field").value("path.id"),
                        jsonPath("$.details[1].messages.length()").value(1),
                        jsonPath("$.details[1].messages").value("Id del usuario y del cuerpo no coinciden"));
    }

    @Test
    void admin_UpdateCurrentUserWithAnotherWithInvalidArgument_FirstName_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_EMPTY_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(2),
                        jsonPath("$.details[0].field").value("first_name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre es requerido"),
                        jsonPath("$.details[1].field").value("path.id"),
                        jsonPath("$.details[1].messages.length()").value(1),
                        jsonPath("$.details[1].messages").value("Id del usuario y del cuerpo no coinciden"));
    }

    @Test
    void admin_UpdateCurrentUserWithAnotherWithInvalidArgument_FirstName_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_NULL_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(2),
                        jsonPath("$.details[0].field").value("first_name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre es requerido"),
                        jsonPath("$.details[1].field").value("path.id"),
                        jsonPath("$.details[1].messages.length()").value(1),
                        jsonPath("$.details[1].messages").value("Id del usuario y del cuerpo no coinciden"));
    }

    // LastName
    @Test
    void admin_UpdateCurrentUserWithAnotherWithInvalidArgument_LastName_TooLong_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_TOO_LONG_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(2),
                        jsonPath("$.details[0].field").value("last_name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El apellido es muy largo"),
                        jsonPath("$.details[1].field").value("path.id"),
                        jsonPath("$.details[1].messages.length()").value(1),
                        jsonPath("$.details[1].messages").value("Id del usuario y del cuerpo no coinciden"));
    }

    @Test
    void admin_UpdateCurrentUserWithWithAnotherValidArgument_LastName_MaxLength_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_LASTNAME_MAX_LENGTH_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("path.id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("Id del usuario y del cuerpo no coinciden"));
    }

    @Test
    void admin_UpdateCurrentUserWithAnotherWithInvalidArgument_LastName_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_BLANK_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(2),
                        jsonPath("$.details[0].field").value("last_name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El apellido es requerido"),
                        jsonPath("$.details[1].field").value("path.id"),
                        jsonPath("$.details[1].messages.length()").value(1),
                        jsonPath("$.details[1].messages").value("Id del usuario y del cuerpo no coinciden"));
    }

    @Test
    void admin_UpdateCurrentUserWithWithAnotherInvalidArgument_LastName_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_EMPTY_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(2),
                        jsonPath("$.details[0].field").value("last_name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El apellido es requerido"),
                        jsonPath("$.details[1].field").value("path.id"),
                        jsonPath("$.details[1].messages.length()").value(1),
                        jsonPath("$.details[1].messages").value("Id del usuario y del cuerpo no coinciden"));
    }

    @Test
    void admin_UpdateCurrentUserWithAnotherIDWithInvalidArgument_LastName_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_NULL_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(2),
                        jsonPath("$.details[0].field").value("last_name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El apellido es requerido"),
                        jsonPath("$.details[1].field").value("path.id"),
                        jsonPath("$.details[1].messages.length()").value(1),
                        jsonPath("$.details[1].messages").value("Id del usuario y del cuerpo no coinciden"));
    }

    // - Invalid arguments

    // FirstName
    @Test
    void admin_UpdateCurrentUserWithInvalidArgument_FirstName_TooLong_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_TOO_LONG_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("first_name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre es muy largo"));
    }

    @Test
    @Order(5)
    @DirtiesContext
    void admin_UpdateCurrentUserWithValidArgument_FirstName_MaxLength_Ok() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_FIRSTNAME_MAX_LENGTH_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.jwt").isString());
    }

    @Test
    void admin_UpdateCurrentUserWithInvalidArgument_FirstName_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_BLANK_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("first_name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre es requerido"));
    }

    @Test
    void admin_UpdateCurrentUserWithInvalidArgument_FirstName_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_EMPTY_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("first_name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre es requerido"));
    }

    @Test
    void admin_UpdateCurrentUserWithInvalidArgument_FirstName_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_NULL_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("first_name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre es requerido"));
    }

    // LastName
    @Test
    void admin_UpdateCurrentUserWithInvalidArgument_LastName_TooLong_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_TOO_LONG_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("last_name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El apellido es muy largo"));
    }

    @Test
    @Order(6)
    @DirtiesContext
    void admin_UpdateCurrentUserWithValidArgument_LastName_MaxLength_Ok() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_LASTNAME_MAX_LENGTH_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.jwt").isString());
    }

    @Test
    void admin_UpdateCurrentUserWithInvalidArgument_LastName_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_BLANK_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("last_name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El apellido es requerido"));
    }

    @Test
    void admin_UpdateCurrentUserWithInvalidArgument_LastName_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_EMPTY_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("last_name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El apellido es requerido"));
    }

    @Test
    void admin_UpdateCurrentUserWithInvalidArgument_LastName_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_NULL_UPDATE_ADMIN_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("last_name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El apellido es requerido"));
    }

    // /user/{id}
    @Test
    @Order(7)
    @DirtiesContext
    void admin_UpdateOtherUser_Success() throws Exception {
        mockMvc.perform(put("/user/{id}", USER_DTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(USER_DTO.getId()),
                        jsonPath("$.content.first_name").value(VALID_UPDATE_USER_REQUEST.getFirstName()),
                        jsonPath("$.content.last_name").value(VALID_UPDATE_USER_REQUEST.getLastName()),
                        jsonPath("$.content.email").value(USER_DTO.getEmail()),
                        jsonPath("$.content.roles").isArray(),
                        jsonPath("$.content.roles.length()").value(1),
                        jsonPath("$.content.roles").value("USER"),
                        jsonPath("$.content.profile_image_url").isEmpty());

    }

    @Test
    void admin_UpdateCurrentUserAsOtherUser_Forbidden() throws Exception {
        mockMvc.perform(put("/user/{id}", ADMIN_DTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // - Invalid arguments

    // ID
    @Test
    void admin_UpdateOtherUserWithInvalidArgument_ID_Invalid_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user/{id}", "invalid")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("path.id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("Valor numérico inválido"));
    }

    @Test
    void admin_UpdateOtherUserWithInvalidArgument_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user/{id}", -1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("path.id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("Id de la ruta y del cuerpo no coinciden"));
    }

    @Test
    void admin_UpdateOtherUserWithInvalidArgument_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/user/{id}", 10)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND.apply("10")));
    }

    // - Invalid arguments

    // FirstName
    @Test
    void admin_UpdateOtherUserWithInvalidArgument_FirstName_TooLong_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_TOO_LONG_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("first_name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre es muy largo"));
    }

    @Test
    @Order(8)
    @DirtiesContext
    void admin_UpdateOtherUserWithValidArgument_FirstName_MaxLength_Ok() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_FIRSTNAME_MAX_LENGTH_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(USER_DTO.getId()),
                        jsonPath("$.content.first_name")
                                .value(VALID_FIRSTNAME_MAX_LENGTH_UPDATE_USER_REQUEST.getFirstName()),
                        jsonPath("$.content.last_name")
                                .value(VALID_FIRSTNAME_MAX_LENGTH_UPDATE_USER_REQUEST.getLastName()),
                        jsonPath("$.content.email").value(USER_DTO.getEmail()),
                        jsonPath("$.content.roles").isArray(),
                        jsonPath("$.content.roles.length()").value(1),
                        jsonPath("$.content.roles").value("USER"),
                        jsonPath("$.content.profile_image_url").isEmpty());
    }

    @Test
    void admin_UpdateOtherUserWithInvalidArgument_FirstName_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_BLANK_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("first_name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre es requerido"));
    }

    @Test
    void admin_UpdateOtherUserWithInvalidArgument_FirstName_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_EMPTY_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("first_name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre es requerido"));
    }

    @Test
    void admin_UpdateOtherUserWithInvalidArgument_FirstName_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_FIRSTNAME_NULL_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("first_name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre es requerido"));
    }

    // LastName
    @Test
    void admin_UpdateOtherUserWithInvalidArgument_LastName_TooLong_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_TOO_LONG_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("last_name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El apellido es muy largo"));
    }

    @Test
    @Order(9)
    @DirtiesContext
    void admin_UpdateOtherUserWithValidArgument_LastName_MaxLength_Ok() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_LASTNAME_MAX_LENGTH_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(USER_DTO.getId()),
                        jsonPath("$.content.first_name")
                                .value(VALID_LASTNAME_MAX_LENGTH_UPDATE_USER_REQUEST.getFirstName()),
                        jsonPath("$.content.last_name")
                                .value(VALID_LASTNAME_MAX_LENGTH_UPDATE_USER_REQUEST.getLastName()),
                        jsonPath("$.content.email").value(USER_DTO.getEmail()),
                        jsonPath("$.content.roles").isArray(),
                        jsonPath("$.content.roles.length()").value(1),
                        jsonPath("$.content.roles").value("USER"),
                        jsonPath("$.content.profile_image_url").isEmpty());
    }

    @Test
    void admin_UpdateOtherUserWithInvalidArgument_LastName_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_BLANK_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("last_name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El apellido es requerido"));
    }

    @Test
    void admin_UpdateOtherUserWithInvalidArgument_LastName_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_EMPTY_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("last_name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El apellido es requerido"));
    }

    @Test
    void admin_UpdateOtherUserWithInvalidArgument_LastName_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_LASTNAME_NULL_UPDATE_USER_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("last_name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El apellido es requerido"));
    }
}