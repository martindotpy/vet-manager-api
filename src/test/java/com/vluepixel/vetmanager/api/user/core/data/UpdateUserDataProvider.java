package com.vluepixel.vetmanager.api.user.core.data;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.ADMIN_DTO;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.USER_DTO;
import static com.vluepixel.vetmanager.api.auth.core.data.RegisterUserDataProvider.INVALID_FIRSTNAME_BLANK_REGISTER_USER_REQUEST;
import static com.vluepixel.vetmanager.api.auth.core.data.RegisterUserDataProvider.INVALID_FIRSTNAME_EMPTY_REGISTER_USER_REQUEST;
import static com.vluepixel.vetmanager.api.auth.core.data.RegisterUserDataProvider.INVALID_FIRSTNAME_NULL_REGISTER_USER_REQUEST;
import static com.vluepixel.vetmanager.api.auth.core.data.RegisterUserDataProvider.INVALID_FIRSTNAME_TOO_LONG_REGISTER_USER_REQUEST;
import static com.vluepixel.vetmanager.api.auth.core.data.RegisterUserDataProvider.INVALID_LASTNAME_BLANK_REGISTER_USER_REQUEST;
import static com.vluepixel.vetmanager.api.auth.core.data.RegisterUserDataProvider.INVALID_LASTNAME_EMPTY_REGISTER_USER_REQUEST;
import static com.vluepixel.vetmanager.api.auth.core.data.RegisterUserDataProvider.INVALID_LASTNAME_NULL_REGISTER_USER_REQUEST;
import static com.vluepixel.vetmanager.api.auth.core.data.RegisterUserDataProvider.INVALID_LASTNAME_TOO_LONG_REGISTER_USER_REQUEST;
import static com.vluepixel.vetmanager.api.auth.core.data.RegisterUserDataProvider.VALID_FIRSTNAME_MAX_LENGTH_REGISTER_USER_REQUEST;
import static com.vluepixel.vetmanager.api.auth.core.data.RegisterUserDataProvider.VALID_LASTNAME_MAX_LENGTH_REGISTER_USER_REQUEST;
import static com.vluepixel.vetmanager.api.auth.core.data.RegisterUserDataProvider.VALID_REGISTER_USER_REQUEST;

import com.vluepixel.vetmanager.api.user.core.application.dto.UserDto;
import com.vluepixel.vetmanager.api.user.core.domain.request.UpdateUserRequest;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Update user data provider.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UpdateUserDataProvider {
    public static final UpdateUserRequest INVALID_UPDATE_USER_REQUEST = UpdateUserRequest
            .builder()
            .id(10L)
            .firstName("New first name")
            .lastName("New last name")
            .build();

    // -----------------------------------------------------------------------------------------------------------------
    // Users
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER
    public static final UpdateUserRequest VALID_UPDATE_USER_REQUEST = UpdateUserRequest.builder()
            .id(USER_DTO.getId())
            .firstName("New first name")
            .lastName("New last name")
            .build();

    public static final UserDto UPDATED_USER = UserDto.builder()
            .firstName(VALID_UPDATE_USER_REQUEST.getFirstName())
            .lastName(VALID_UPDATE_USER_REQUEST.getLastName())
            .build();

    // -----------------------------------------------------------------------------------------------------------------
    // Validations
    // -----------------------------------------------------------------------------------------------------

    // - First name
    public static final UpdateUserRequest INVALID_FIRSTNAME_TOO_LONG_UPDATE_USER_REQUEST = UpdateUserRequest
            .builder()
            .id(USER_DTO.getId())
            .firstName(INVALID_FIRSTNAME_TOO_LONG_REGISTER_USER_REQUEST.getFirstName())
            .lastName(VALID_REGISTER_USER_REQUEST.getLastName())
            .build();

    public static final UpdateUserRequest VALID_FIRSTNAME_MAX_LENGTH_UPDATE_USER_REQUEST = UpdateUserRequest
            .builder()
            .id(USER_DTO.getId())
            .firstName(VALID_FIRSTNAME_MAX_LENGTH_REGISTER_USER_REQUEST.getFirstName())
            .lastName(VALID_REGISTER_USER_REQUEST.getLastName())
            .build();

    public static final UpdateUserRequest INVALID_FIRSTNAME_BLANK_UPDATE_USER_REQUEST = UpdateUserRequest
            .builder()
            .id(USER_DTO.getId())
            .firstName(INVALID_FIRSTNAME_BLANK_REGISTER_USER_REQUEST.getFirstName())
            .lastName(VALID_REGISTER_USER_REQUEST.getLastName())
            .build();

    public static final UpdateUserRequest INVALID_FIRSTNAME_EMPTY_UPDATE_USER_REQUEST = UpdateUserRequest
            .builder()
            .id(USER_DTO.getId())
            .firstName(INVALID_FIRSTNAME_EMPTY_REGISTER_USER_REQUEST.getFirstName())
            .lastName(VALID_REGISTER_USER_REQUEST.getLastName())
            .build();

    public static final UpdateUserRequest INVALID_FIRSTNAME_NULL_UPDATE_USER_REQUEST = UpdateUserRequest
            .builder()
            .id(USER_DTO.getId())
            .firstName(INVALID_FIRSTNAME_NULL_REGISTER_USER_REQUEST.getFirstName())
            .lastName(VALID_REGISTER_USER_REQUEST.getLastName())
            .build();

    // - Last name
    public static final UpdateUserRequest INVALID_LASTNAME_TOO_LONG_UPDATE_USER_REQUEST = UpdateUserRequest
            .builder()
            .id(USER_DTO.getId())
            .firstName(VALID_REGISTER_USER_REQUEST.getFirstName())
            .lastName(INVALID_LASTNAME_TOO_LONG_REGISTER_USER_REQUEST.getLastName())
            .build();

    public static final UpdateUserRequest VALID_LASTNAME_MAX_LENGTH_UPDATE_USER_REQUEST = UpdateUserRequest
            .builder()
            .id(USER_DTO.getId())
            .firstName(VALID_REGISTER_USER_REQUEST.getFirstName())
            .lastName(VALID_LASTNAME_MAX_LENGTH_REGISTER_USER_REQUEST.getLastName())
            .build();

    public static final UpdateUserRequest INVALID_LASTNAME_BLANK_UPDATE_USER_REQUEST = UpdateUserRequest
            .builder()
            .id(USER_DTO.getId())
            .firstName(VALID_REGISTER_USER_REQUEST.getFirstName())
            .lastName(INVALID_LASTNAME_BLANK_REGISTER_USER_REQUEST.getLastName())
            .build();

    public static final UpdateUserRequest INVALID_LASTNAME_EMPTY_UPDATE_USER_REQUEST = UpdateUserRequest
            .builder()
            .id(USER_DTO.getId())
            .firstName(VALID_REGISTER_USER_REQUEST.getFirstName())
            .lastName(INVALID_LASTNAME_EMPTY_REGISTER_USER_REQUEST.getLastName())
            .build();

    public static final UpdateUserRequest INVALID_LASTNAME_NULL_UPDATE_USER_REQUEST = UpdateUserRequest
            .builder()
            .id(USER_DTO.getId())
            .firstName(VALID_REGISTER_USER_REQUEST.getFirstName())
            .lastName(INVALID_LASTNAME_NULL_REGISTER_USER_REQUEST.getLastName())
            .build();

    // Role: ADMIN
    public static final UpdateUserRequest VALID_UPDATE_ADMIN_REQUEST = UpdateUserRequest.builder()
            .id(ADMIN_DTO.getId())
            .firstName("New first name")
            .lastName("New last name")
            .build();

    public static final UserDto UPDATED_ADMIN = UserDto.builder()
            .firstName(VALID_UPDATE_ADMIN_REQUEST.getFirstName())
            .lastName(VALID_UPDATE_ADMIN_REQUEST.getLastName())
            .build();

    // -----------------------------------------------------------------------------------------------------------------
    // Validations
    // -----------------------------------------------------------------------------------------------------

    // - First name
    public static final UpdateUserRequest INVALID_FIRSTNAME_TOO_LONG_UPDATE_ADMIN_REQUEST = UpdateUserRequest
            .builder()
            .id(ADMIN_DTO.getId())
            .firstName(INVALID_FIRSTNAME_TOO_LONG_REGISTER_USER_REQUEST.getFirstName())
            .lastName(VALID_REGISTER_USER_REQUEST.getLastName())
            .build();

    public static final UpdateUserRequest VALID_FIRSTNAME_MAX_LENGTH_UPDATE_ADMIN_REQUEST = UpdateUserRequest
            .builder()
            .id(ADMIN_DTO.getId())
            .firstName(VALID_FIRSTNAME_MAX_LENGTH_REGISTER_USER_REQUEST.getFirstName())
            .lastName(VALID_REGISTER_USER_REQUEST.getLastName())
            .build();

    public static final UpdateUserRequest INVALID_FIRSTNAME_BLANK_UPDATE_ADMIN_REQUEST = UpdateUserRequest
            .builder()
            .id(ADMIN_DTO.getId())
            .firstName(INVALID_FIRSTNAME_BLANK_REGISTER_USER_REQUEST.getFirstName())
            .lastName(VALID_REGISTER_USER_REQUEST.getLastName())
            .build();

    public static final UpdateUserRequest INVALID_FIRSTNAME_EMPTY_UPDATE_ADMIN_REQUEST = UpdateUserRequest
            .builder()
            .id(ADMIN_DTO.getId())
            .firstName(INVALID_FIRSTNAME_EMPTY_REGISTER_USER_REQUEST.getFirstName())
            .lastName(VALID_REGISTER_USER_REQUEST.getLastName())
            .build();

    public static final UpdateUserRequest INVALID_FIRSTNAME_NULL_UPDATE_ADMIN_REQUEST = UpdateUserRequest
            .builder()
            .id(ADMIN_DTO.getId())
            .firstName(INVALID_FIRSTNAME_NULL_REGISTER_USER_REQUEST.getFirstName())
            .lastName(VALID_REGISTER_USER_REQUEST.getLastName())
            .build();

    // - Last name
    public static final UpdateUserRequest INVALID_LASTNAME_TOO_LONG_UPDATE_ADMIN_REQUEST = UpdateUserRequest
            .builder()
            .id(ADMIN_DTO.getId())
            .firstName(VALID_REGISTER_USER_REQUEST.getFirstName())
            .lastName(INVALID_LASTNAME_TOO_LONG_REGISTER_USER_REQUEST.getLastName())
            .build();

    public static final UpdateUserRequest VALID_LASTNAME_MAX_LENGTH_UPDATE_ADMIN_REQUEST = UpdateUserRequest
            .builder()
            .id(ADMIN_DTO.getId())
            .firstName(VALID_REGISTER_USER_REQUEST.getFirstName())
            .lastName(VALID_LASTNAME_MAX_LENGTH_REGISTER_USER_REQUEST.getLastName())
            .build();

    public static final UpdateUserRequest INVALID_LASTNAME_BLANK_UPDATE_ADMIN_REQUEST = UpdateUserRequest
            .builder()
            .id(ADMIN_DTO.getId())
            .firstName(VALID_REGISTER_USER_REQUEST.getFirstName())
            .lastName(INVALID_LASTNAME_BLANK_REGISTER_USER_REQUEST.getLastName())
            .build();

    public static final UpdateUserRequest INVALID_LASTNAME_EMPTY_UPDATE_ADMIN_REQUEST = UpdateUserRequest
            .builder()
            .id(ADMIN_DTO.getId())
            .firstName(VALID_REGISTER_USER_REQUEST.getFirstName())
            .lastName(INVALID_LASTNAME_EMPTY_REGISTER_USER_REQUEST.getLastName())
            .build();

    public static final UpdateUserRequest INVALID_LASTNAME_NULL_UPDATE_ADMIN_REQUEST = UpdateUserRequest
            .builder()
            .id(ADMIN_DTO.getId())
            .firstName(VALID_REGISTER_USER_REQUEST.getFirstName())
            .lastName(INVALID_LASTNAME_NULL_REGISTER_USER_REQUEST.getLastName())
            .build();
}