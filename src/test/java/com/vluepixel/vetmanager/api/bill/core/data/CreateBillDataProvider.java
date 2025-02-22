package com.vluepixel.vetmanager.api.bill.core.data;

import com.vluepixel.vetmanager.api.bill.core.domain.request.CreateBillRequest;

/**
 * Create bill data provider.
 */
public class CreateBillDataProvider {
    public static final CreateBillRequest VALID_CREATE_BILL_REQUEST = CreateBillRequest
            .builder()
            .discount(1)
            .clientId(1L)
            .build();

    // -----------------------------------------------------------------------------------------------------------------
    // Validations
    // -----------------------------------------------------------------------------------------------------

    // Discount
    private static final int MAX_DISCOUNT_VALUE = 100;
    public static final CreateBillRequest INVALID_DISCOUNT_TOO_BIG_CREATE_BILL_REQUEST = CreateBillRequest
            .builder()
            .discount(MAX_DISCOUNT_VALUE + 1)
            .clientId(VALID_CREATE_BILL_REQUEST.getClientId())
            .build();

    public static final CreateBillRequest VALID_DISCOUNT_MAX_VALUE_CREATE_BILL_REQUEST = CreateBillRequest
            .builder()
            .discount(MAX_DISCOUNT_VALUE)
            .clientId(VALID_CREATE_BILL_REQUEST.getClientId())
            .build();

    public static final CreateBillRequest VALID_DISCOUNT_ZERO_CREATE_BILL_REQUEST = CreateBillRequest
            .builder()
            .discount(0)
            .clientId(VALID_CREATE_BILL_REQUEST.getClientId())
            .build();

    public static final CreateBillRequest INVALID_DISCOUNT_NEGATIVE_CREATE_BILL_REQUEST = CreateBillRequest
            .builder()
            .discount(-1)
            .clientId(VALID_CREATE_BILL_REQUEST.getClientId())
            .build();

    public static final CreateBillRequest INVALID_DISCOUNT_NULL_CREATE_BILL_REQUEST = CreateBillRequest
            .builder()
            .discount(null)
            .clientId(VALID_CREATE_BILL_REQUEST.getClientId())
            .build();

    // Client ID
    public static final CreateBillRequest INVALID_CLIENT_ID_NOT_FOUND_CREATE_BILL_REQUEST = CreateBillRequest
            .builder()
            .discount(VALID_CREATE_BILL_REQUEST.getDiscount())
            .clientId(10L)
            .build();

    public static final CreateBillRequest INVALID_CLIENT_ID_NEGATIVE_CREATE_BILL_REQUEST = CreateBillRequest
            .builder()
            .discount(VALID_CREATE_BILL_REQUEST.getDiscount())
            .clientId(-1L)
            .build();

    public static final CreateBillRequest INVALID_CLIENT_NULL_NEGATIVE_CREATE_BILL_REQUEST = CreateBillRequest
            .builder()
            .discount(VALID_CREATE_BILL_REQUEST.getDiscount())
            .clientId(null)
            .build();
}
