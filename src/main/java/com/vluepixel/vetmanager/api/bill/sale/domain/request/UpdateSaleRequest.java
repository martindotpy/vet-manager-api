package com.vluepixel.vetmanager.api.bill.sale.domain.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.vluepixel.vetmanager.api.shared.domain.request.Request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Update sale request.
 */
@Getter
@SuperBuilder
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = UpdateAppointmentSaleRequest.class, name = "appointment"),
        @JsonSubTypes.Type(value = UpdateTreatmentSaleRequest.class, name = "treatment"),
        @JsonSubTypes.Type(value = UpdateProductSaleRequest.class, name = "product"),
})
public abstract class UpdateSaleRequest implements Request {
    @NotNull(message = "El id es requerido")
    @Positive(message = "El id debe ser mayor a 0")
    private Integer id;

    @NotNull(message = "El precio es requerido")
    @Positive(message = "El precio debe ser mayor a 0")
    @DecimalMax(value = "999999.99", message = "El precio debe ser menor a 999999.99")
    private BigDecimal price;
    @NotNull(message = "El descuento es requerido")
    @Positive(message = "El descuento debe ser mayor a 0")
    @Max(value = 100, message = "El descuento debe ser menor a 100")
    private Integer discount;
}
