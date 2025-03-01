package com.vluepixel.vetmanager.api.sale.core.data.treatment;

import java.math.BigDecimal;

import com.vluepixel.vetmanager.api.bill.sale.domain.request.UpdateTreatmentSaleRequest;

/**
 * Update treatment sale data provider.
 */
public class UpdateTreatmentSaleDataProvider {
    public static final UpdateTreatmentSaleRequest INVALID_ID_NOT_FOUND_UPDATE_TREATMENT_SALE_REQUEST = UpdateTreatmentSaleRequest
            .builder()
            .id(10)
            .treatmentId(1L)
            .price(BigDecimal.valueOf(300))
            .discount(20)
            .build();

    public static final UpdateTreatmentSaleRequest INVALID_ID_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST = UpdateTreatmentSaleRequest
            .builder()
            .id(-1)
            .treatmentId(INVALID_ID_NOT_FOUND_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(INVALID_ID_NOT_FOUND_UPDATE_TREATMENT_SALE_REQUEST.getPrice())
            .discount(INVALID_ID_NOT_FOUND_UPDATE_TREATMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateTreatmentSaleRequest INVALID_ID_NULL_UPDATE_TREATMENT_SALE_REQUEST = UpdateTreatmentSaleRequest
            .builder()
            .id(null)
            .treatmentId(INVALID_ID_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(INVALID_ID_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST.getPrice())
            .discount(INVALID_ID_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateTreatmentSaleRequest VALID_UPDATE_TREATMENT_SALE_REQUEST = UpdateTreatmentSaleRequest
            .builder()
            .id(3)
            .treatmentId(INVALID_ID_NULL_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(INVALID_ID_NULL_UPDATE_TREATMENT_SALE_REQUEST.getPrice())
            .discount(INVALID_ID_NULL_UPDATE_TREATMENT_SALE_REQUEST.getDiscount())
            .build();

    // -----------------------------------------------------------------------------------------------------------------
    // Validations
    // -----------------------------------------------------------------------------------------------------

    // Treatment ID
    public static final UpdateTreatmentSaleRequest INVALID_TREATMENT_ID_NOT_FOUND_UPDATE_TREATMENT_SALE_REQUEST = UpdateTreatmentSaleRequest
            .builder()
            .id(VALID_UPDATE_TREATMENT_SALE_REQUEST.getId())
            .treatmentId(10L)
            .price(VALID_UPDATE_TREATMENT_SALE_REQUEST.getPrice())
            .discount(VALID_UPDATE_TREATMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateTreatmentSaleRequest INVALID_TREATMENT_ID_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST = UpdateTreatmentSaleRequest
            .builder()
            .id(VALID_UPDATE_TREATMENT_SALE_REQUEST.getId())
            .treatmentId(-1L)
            .price(VALID_UPDATE_TREATMENT_SALE_REQUEST.getPrice())
            .discount(VALID_UPDATE_TREATMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateTreatmentSaleRequest INVALID_TREATMENT_ID_NULL_UPDATE_TREATMENT_SALE_REQUEST = UpdateTreatmentSaleRequest
            .builder()
            .id(VALID_UPDATE_TREATMENT_SALE_REQUEST.getId())
            .treatmentId(null)
            .price(VALID_UPDATE_TREATMENT_SALE_REQUEST.getPrice())
            .discount(VALID_UPDATE_TREATMENT_SALE_REQUEST.getDiscount())
            .build();

    // Price
    private static final int MAX_PRICE_VALUE = 999999;
    public static final UpdateTreatmentSaleRequest INVALID_PRICE_TOO_BIG_UPDATE_TREATMENT_SALE_REQUEST = UpdateTreatmentSaleRequest
            .builder()
            .id(VALID_UPDATE_TREATMENT_SALE_REQUEST.getId())
            .treatmentId(VALID_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(BigDecimal.valueOf(MAX_PRICE_VALUE + 1))
            .discount(VALID_UPDATE_TREATMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateTreatmentSaleRequest VALID_PRICE_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST = UpdateTreatmentSaleRequest
            .builder()
            .id(VALID_UPDATE_TREATMENT_SALE_REQUEST.getId())
            .treatmentId(VALID_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(BigDecimal.valueOf(MAX_PRICE_VALUE + 0.99))
            .discount(VALID_UPDATE_TREATMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateTreatmentSaleRequest INVALID_PRICE_ZERO_UPDATE_TREATMENT_SALE_REQUEST = UpdateTreatmentSaleRequest
            .builder()
            .id(VALID_UPDATE_TREATMENT_SALE_REQUEST.getId())
            .treatmentId(VALID_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(BigDecimal.valueOf(0))
            .discount(VALID_UPDATE_TREATMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateTreatmentSaleRequest INVALID_PRICE_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST = UpdateTreatmentSaleRequest
            .builder()
            .id(VALID_UPDATE_TREATMENT_SALE_REQUEST.getId())
            .treatmentId(VALID_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(BigDecimal.valueOf(-1))
            .discount(VALID_UPDATE_TREATMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateTreatmentSaleRequest INVALID_PRICE_NULL_UPDATE_TREATMENT_SALE_REQUEST = UpdateTreatmentSaleRequest
            .builder()
            .id(VALID_UPDATE_TREATMENT_SALE_REQUEST.getId())
            .treatmentId(VALID_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(null)
            .discount(VALID_UPDATE_TREATMENT_SALE_REQUEST.getDiscount())
            .build();

    // Discount
    private static final int MAX_DISCOUNT_VALUE = 100;
    public static final UpdateTreatmentSaleRequest INVALID_DISCOUNT_TOO_BIG_UPDATE_TREATMENT_SALE_REQUEST = UpdateTreatmentSaleRequest
            .builder()
            .id(VALID_UPDATE_TREATMENT_SALE_REQUEST.getId())
            .treatmentId(VALID_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(VALID_UPDATE_TREATMENT_SALE_REQUEST.getPrice())
            .discount(MAX_DISCOUNT_VALUE + 1)
            .build();

    public static final UpdateTreatmentSaleRequest VALID_DISCOUNT_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST = UpdateTreatmentSaleRequest
            .builder()
            .id(VALID_UPDATE_TREATMENT_SALE_REQUEST.getId())
            .treatmentId(VALID_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(VALID_UPDATE_TREATMENT_SALE_REQUEST.getPrice())
            .discount(MAX_DISCOUNT_VALUE)
            .build();

    public static final UpdateTreatmentSaleRequest INVALID_DISCOUNT_ZERO_UPDATE_TREATMENT_SALE_REQUEST = UpdateTreatmentSaleRequest
            .builder()
            .id(VALID_UPDATE_TREATMENT_SALE_REQUEST.getId())
            .treatmentId(VALID_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(VALID_UPDATE_TREATMENT_SALE_REQUEST.getPrice())
            .discount(0)
            .build();

    public static final UpdateTreatmentSaleRequest INVALID_DISCOUNT_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST = UpdateTreatmentSaleRequest
            .builder()
            .id(VALID_UPDATE_TREATMENT_SALE_REQUEST.getId())
            .treatmentId(VALID_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(VALID_UPDATE_TREATMENT_SALE_REQUEST.getPrice())
            .discount(-1)
            .build();

    public static final UpdateTreatmentSaleRequest INVALID_DISCOUNT_NULL_UPDATE_TREATMENT_SALE_REQUEST = UpdateTreatmentSaleRequest
            .builder()
            .id(VALID_UPDATE_TREATMENT_SALE_REQUEST.getId())
            .treatmentId(VALID_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(VALID_UPDATE_TREATMENT_SALE_REQUEST.getPrice())
            .discount(null)
            .build();
}
