package com.vluepixel.vetmanager.api.product.category.domain.request;

import com.vluepixel.vetmanager.api.shared.domain.payload.Payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Create category request.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class CreateCategoryRequest implements Payload {
    @NotBlank(message = "El nombre es requerido")
    @Size(max = 12, message = "El nombre no puede tener más de 12 caracteres")
    private String name;
}
