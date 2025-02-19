package com.vluepixel.vetmanager.api.product.core.domain.request;

import java.math.BigDecimal;
import java.util.List;

import com.vluepixel.vetmanager.api.shared.domain.request.Request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Update product request.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class UpdateProductRequest implements Request {
    @NotNull(message = "El id es requerido")
    @Positive(message = "El id debe ser mayor a 0")
    private Long id;

    @NotBlank(message = "El nombre es requerido")
    @Size(max = 125, message = "El nombre no puede tener más de 125 caracteres")
    private String name;
    @NotBlank(message = "La descripción es requerida")
    private String description;
    @NotNull(message = "El precio es requerido")
    @Positive(message = "El precio debe ser mayor a 0")
    @DecimalMax(value = "9999.99", message = "El precio no puede ser mayor a 9999.99")
    private BigDecimal price;
    @NotNull(message = "La cantidad es requerida")
    @Positive(message = "La cantidad debe ser mayor a 0")
    @Max(value = 999, message = "La cantidad no puede ser mayor a 999")
    private Integer quantity;

    private List<@NotNull(message = "El id de la categoría es requerido") @Positive(message = "El id de la categoría debe ser mayor a 0") Integer> categoryIds;
}
