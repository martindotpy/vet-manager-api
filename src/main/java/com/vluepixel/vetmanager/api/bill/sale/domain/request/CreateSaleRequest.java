package com.vluepixel.vetmanager.api.bill.sale.domain.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.enums.SaleType;
import com.vluepixel.vetmanager.api.shared.domain.request.Request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Create sale request.
 */
@Getter
@SuperBuilder
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreateAppointmentSaleRequest.class, name = "appointment"),
        @JsonSubTypes.Type(value = CreateTreatmentSaleRequest.class, name = "treatment"),
        @JsonSubTypes.Type(value = CreateProductSaleRequest.class, name = "product"),
})
public abstract class CreateSaleRequest implements Request {
    public abstract SaleType getType();

    @NotNull(message = "El precio es requerido")
    @Positive(message = "El precio debe ser mayor a 0")
    @DecimalMax(value = "999999.99", message = "El precio no puede ser mayor a 999999.99")
    private BigDecimal price;
    @NotNull(message = "El descuento es requerido")
    @Positive(message = "El descuento debe ser mayor a 0")
    @Max(value = 100, message = "El descuento no puede ser mayor a 100")
    private Integer discount;

    @NotNull(message = "El id de la cuenta es requerido")
    @Positive(message = "El id de la cuenta debe ser mayor a 0")
    private Long billId;
}
