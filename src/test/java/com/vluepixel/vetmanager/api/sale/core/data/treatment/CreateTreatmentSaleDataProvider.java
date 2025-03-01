package com.vluepixel.vetmanager.api.sale.core.data.treatment;

import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.INVALID_DISCOUNT_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.INVALID_DISCOUNT_NULL_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.INVALID_DISCOUNT_TOO_BIG_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.INVALID_PRICE_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.INVALID_PRICE_NULL_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.INVALID_PRICE_TOO_BIG_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.INVALID_TREATMENT_ID_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.INVALID_TREATMENT_ID_NOT_FOUND_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.INVALID_TREATMENT_ID_NULL_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.VALID_DISCOUNT_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.VALID_DISCOUNT_ZERO_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.VALID_PRICE_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.VALID_PRICE_ZERO_UPDATE_TREATMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.UpdateTreatmentSaleDataProvider.VALID_UPDATE_TREATMENT_SALE_REQUEST;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.CreateTreatmentSaleRequest;

/**
 * Create treatment sale data provider.
 */
public class CreateTreatmentSaleDataProvider {
    public static final CreateTreatmentSaleRequest VALID_CREATE_TREATMENT_SALE_REQUEST = CreateTreatmentSaleRequest
            .builder()
            .treatmentId(VALID_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(VALID_UPDATE_TREATMENT_SALE_REQUEST.getPrice())
            .discount(VALID_UPDATE_TREATMENT_SALE_REQUEST.getDiscount())
            .build();

    // Treatment ID
    public static final CreateTreatmentSaleRequest INVALID_TREATMENT_ID_NOT_FOUND_CREATE_TREATMENT_SALE_REQUEST = CreateTreatmentSaleRequest
            .builder()
            .treatmentId(INVALID_TREATMENT_ID_NOT_FOUND_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(VALID_UPDATE_TREATMENT_SALE_REQUEST.getPrice())
            .discount(VALID_UPDATE_TREATMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateTreatmentSaleRequest INVALID_TREATMENT_ID_NEGATIVE_CREATE_TREATMENT_SALE_REQUEST = CreateTreatmentSaleRequest
            .builder()
            .treatmentId(INVALID_TREATMENT_ID_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(VALID_UPDATE_TREATMENT_SALE_REQUEST.getPrice())
            .discount(VALID_UPDATE_TREATMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateTreatmentSaleRequest INVALID_TREATMENT_ID_NULL_CREATE_TREATMENT_SALE_REQUEST = CreateTreatmentSaleRequest
            .builder()
            .treatmentId(INVALID_TREATMENT_ID_NULL_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(VALID_UPDATE_TREATMENT_SALE_REQUEST.getPrice())
            .discount(VALID_UPDATE_TREATMENT_SALE_REQUEST.getDiscount())
            .build();

    // Price
    public static final CreateTreatmentSaleRequest INVALID_PRICE_TOO_BIG_CREATE_TREATMENT_SALE_REQUEST = CreateTreatmentSaleRequest
            .builder()
            .treatmentId(VALID_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(INVALID_PRICE_TOO_BIG_UPDATE_TREATMENT_SALE_REQUEST.getPrice())
            .discount(VALID_UPDATE_TREATMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateTreatmentSaleRequest VALID_PRICE_MAX_VALUE_CREATE_TREATMENT_SALE_REQUEST = CreateTreatmentSaleRequest
            .builder()
            .treatmentId(VALID_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(VALID_PRICE_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST.getPrice())
            .discount(VALID_UPDATE_TREATMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateTreatmentSaleRequest VALID_PRICE_ZERO_CREATE_TREATMENT_SALE_REQUEST = CreateTreatmentSaleRequest
            .builder()
            .treatmentId(VALID_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(VALID_PRICE_ZERO_UPDATE_TREATMENT_SALE_REQUEST.getPrice())
            .discount(VALID_UPDATE_TREATMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateTreatmentSaleRequest INVALID_PRICE_NEGATIVE_CREATE_TREATMENT_SALE_REQUEST = CreateTreatmentSaleRequest
            .builder()
            .treatmentId(VALID_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(INVALID_PRICE_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST.getPrice())
            .discount(VALID_UPDATE_TREATMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateTreatmentSaleRequest INVALID_PRICE_NULL_CREATE_TREATMENT_SALE_REQUEST = CreateTreatmentSaleRequest
            .builder()
            .treatmentId(VALID_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(INVALID_PRICE_NULL_UPDATE_TREATMENT_SALE_REQUEST.getPrice())
            .discount(VALID_UPDATE_TREATMENT_SALE_REQUEST.getDiscount())
            .build();

    // Discount
    public static final CreateTreatmentSaleRequest INVALID_DISCOUNT_TOO_BIG_CREATE_TREATMENT_SALE_REQUEST = CreateTreatmentSaleRequest
            .builder()
            .treatmentId(VALID_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(VALID_UPDATE_TREATMENT_SALE_REQUEST.getPrice())
            .discount(INVALID_DISCOUNT_TOO_BIG_UPDATE_TREATMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateTreatmentSaleRequest VALID_DISCOUNT_MAX_VALUE_CREATE_TREATMENT_SALE_REQUEST = CreateTreatmentSaleRequest
            .builder()
            .treatmentId(VALID_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(VALID_UPDATE_TREATMENT_SALE_REQUEST.getPrice())
            .discount(VALID_DISCOUNT_MAX_VALUE_UPDATE_TREATMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateTreatmentSaleRequest VALID_DISCOUNT_ZERO_CREATE_TREATMENT_SALE_REQUEST = CreateTreatmentSaleRequest
            .builder()
            .treatmentId(VALID_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(VALID_UPDATE_TREATMENT_SALE_REQUEST.getPrice())
            .discount(VALID_DISCOUNT_ZERO_UPDATE_TREATMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateTreatmentSaleRequest INVALID_DISCOUNT_NEGATIVE_CREATE_TREATMENT_SALE_REQUEST = CreateTreatmentSaleRequest
            .builder()
            .treatmentId(VALID_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(VALID_UPDATE_TREATMENT_SALE_REQUEST.getPrice())
            .discount(INVALID_DISCOUNT_NEGATIVE_UPDATE_TREATMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateTreatmentSaleRequest INVALID_DISCOUNT_NULL_CREATE_TREATMENT_SALE_REQUEST = CreateTreatmentSaleRequest
            .builder()
            .treatmentId(VALID_UPDATE_TREATMENT_SALE_REQUEST.getTreatmentId())
            .price(VALID_UPDATE_TREATMENT_SALE_REQUEST.getPrice())
            .discount(INVALID_DISCOUNT_NULL_UPDATE_TREATMENT_SALE_REQUEST.getDiscount())
            .build();
}
