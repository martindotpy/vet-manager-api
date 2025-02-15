package com.vluepixel.vetmanager.api.user.core.adapter.in.request;

import com.vluepixel.vetmanager.api.user.core.domain.payload.UpdateUserEmailPayload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Update user email request.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class UpdateUserEmailRequest implements UpdateUserEmailPayload {
    @NotNull(message = "El id del usuario es requerido")
    @Positive(message = "El id del usuario debe ser un número positivo")
    private Long id;
    @Email(message = "El correo debe ser válido")
    @NotNull(message = "El nuevo correo es requerido")
    private String newEmail;
}
