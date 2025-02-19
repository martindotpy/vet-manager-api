package com.vluepixel.vetmanager.api.bill.core.domain.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.vluepixel.vetmanager.api.shared.domain.request.Request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Update bill request.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class UpdateBillRequest implements Request {
    @NotNull(message = "El id es requerido")
    @Positive(message = "El id debe ser mayor a 0")
    private Long id;

    @NotNull(message = "El descuento es requerido")
    @Positive(message = "El descuento debe ser mayor a 0")
    @Max(value = 100, message = "El descuento no puede ser mayor a 100")
    private Integer discount;
    @NotNull(message = "El total pagado es requerido")
    @Positive(message = "El total pagado debe ser mayor a 0")
    private BigDecimal totalPaid;
    private LocalDateTime lastPaidAt;

    @NotNull(message = "El id del cliente es requerido")
    @Positive(message = "El id del cliente debe ser mayor a 0")
    private Long clientId;
}
