package com.vluepixel.vetmanager.api.user.core.application.dto;

import java.util.List;

import com.vluepixel.vetmanager.api.user.core.domain.model.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * User DTO.
 */
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public final class UserDto {
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private List<UserRole> roles;
    private String profileImageUrl;
}
