package com.vluepixel.vetmanager.api.product.category.domain.request;

import com.vluepixel.vetmanager.api.shared.domain.payload.Payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Update category request.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class UpdateCategoryRequest implements Payload {
    @NotNull(message = "El id es requerido")
    @Positive(message = "El id debe ser mayor a 0")
    private Integer id;

    @NotBlank(message = "El nombre es requerido")
    @Size(max = 12, message = "El nombre no puede tener más de 12 caracteres")
    private String name;
}
