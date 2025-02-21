package com.vluepixel.vetmanager.api.bill.sale.domain.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.enums.SaleType;
import com.vluepixel.vetmanager.api.shared.domain.request.Request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Update sale request.
 */
@Getter
@SuperBuilder
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = UpdateAppointmentSaleRequest.class, name = "APPOINTMENT"),
        @JsonSubTypes.Type(value = UpdateTreatmentSaleRequest.class, name = "TREATMENT"),
        @JsonSubTypes.Type(value = UpdateProductSaleRequest.class, name = "PRODUCT"),
})
@NoArgsConstructor
@AllArgsConstructor
public abstract class UpdateSaleRequest implements Request {
    public abstract SaleType getType();

    @NotNull(message = "El id es requerido")
    @Positive(message = "El id debe ser mayor a 0")
    private Integer id;

    @NotNull(message = "El descuento es requerido")
    @Positive(message = "El descuento debe ser mayor a 0")
    @Max(value = 100, message = "El descuento no puede ser mayor a 100")
    private Integer discount;

    @NotNull(message = "El id de la cuenta es requerido")
    @Positive(message = "El id de la cuenta debe ser mayor a 0")
    private Long billId;
}
