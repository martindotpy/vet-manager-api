package com.vluepixel.vetmanager.api.auth.core.adapter.in.request;

import com.vluepixel.vetmanager.api.auth.core.domain.payload.UpdatePasswordPayload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Update password request.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class UpdatePasswordRequest implements UpdatePasswordPayload {
    private String password;
    private String newPassword;
}
