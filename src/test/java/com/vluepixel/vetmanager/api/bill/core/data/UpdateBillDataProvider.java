package com.vluepixel.vetmanager.api.bill.core.data;

import java.math.BigDecimal;

import com.vluepixel.vetmanager.api.bill.core.domain.request.UpdateBillRequest;

/**
 * Update bill data provider.
 */
public class UpdateBillDataProvider {
    public static final UpdateBillRequest INVALID_ID_NOT_FOUND_UPDATE_BILL_REQUEST = UpdateBillRequest
            .builder()
            .id(10L)
            .discount(100)
            .totalPaid(BigDecimal.valueOf(10))
            .clientId(1L)
            .build();

    public static final UpdateBillRequest INVALID_ID_NEGATIVE_UPDATE_BILL_REQUEST = UpdateBillRequest
            .builder()
            .id(-10L)
            .discount(100)
            .totalPaid(BigDecimal.valueOf(10))
            .clientId(1L)
            .build();

    public static final UpdateBillRequest VALID_UPDATE_BILL_REQUEST = UpdateBillRequest
            .builder()
            .id(1L)
            .discount(100)
            .totalPaid(BigDecimal.valueOf(10))
            .clientId(1L)
            .build();

    // -----------------------------------------------------------------------------------------------------------------
    // Validations
    // -----------------------------------------------------------------------------------------------------

    // Discount
    private static final int MAX_DISCOUNT_VALUE = 100;
    public static final UpdateBillRequest INVALID_DISCOUNT_TOO_BIG_UPDATE_BILL_REQUEST = UpdateBillRequest
            .builder()
            .id(VALID_UPDATE_BILL_REQUEST.getId())
            .discount(MAX_DISCOUNT_VALUE + 1)
            .totalPaid(VALID_UPDATE_BILL_REQUEST.getTotalPaid())
            .clientId(VALID_UPDATE_BILL_REQUEST.getClientId())
            .build();

    public static final UpdateBillRequest VALID_DISCOUNT_MAX_VALUE_UPDATE_BILL_REQUEST = UpdateBillRequest
            .builder()
            .id(VALID_UPDATE_BILL_REQUEST.getId())
            .discount(MAX_DISCOUNT_VALUE)
            .totalPaid(VALID_UPDATE_BILL_REQUEST.getTotalPaid())
            .clientId(VALID_UPDATE_BILL_REQUEST.getClientId())
            .build();

    public static final UpdateBillRequest INVALID_DISCOUNT_ZERO_UPDATE_BILL_REQUEST = UpdateBillRequest
            .builder()
            .id(VALID_UPDATE_BILL_REQUEST.getId())
            .discount(0)
            .totalPaid(VALID_UPDATE_BILL_REQUEST.getTotalPaid())
            .clientId(VALID_UPDATE_BILL_REQUEST.getClientId())
            .build();

    public static final UpdateBillRequest INVALID_DISCOUNT_NEGATIVE_UPDATE_BILL_REQUEST = UpdateBillRequest
            .builder()
            .id(VALID_UPDATE_BILL_REQUEST.getId())
            .discount(-1)
            .totalPaid(VALID_UPDATE_BILL_REQUEST.getTotalPaid())
            .clientId(VALID_UPDATE_BILL_REQUEST.getClientId())
            .build();

    public static final UpdateBillRequest INVALID_DISCOUNT_NULL_UPDATE_BILL_REQUEST = UpdateBillRequest
            .builder()
            .id(VALID_UPDATE_BILL_REQUEST.getId())
            .discount(null)
            .totalPaid(VALID_UPDATE_BILL_REQUEST.getTotalPaid())
            .clientId(VALID_UPDATE_BILL_REQUEST.getClientId())
            .build();

    // Total Paid
    public static final UpdateBillRequest INVALID_TOTAL_PAID_ZERO_UPDATE_BILL_REQUEST = UpdateBillRequest
            .builder()
            .id(VALID_UPDATE_BILL_REQUEST.getId())
            .discount(VALID_UPDATE_BILL_REQUEST.getDiscount())
            .totalPaid(BigDecimal.valueOf(0))
            .clientId(VALID_UPDATE_BILL_REQUEST.getClientId())
            .build();

    public static final UpdateBillRequest INVALID_TOTAL_PAID_NEGATIVE_UPDATE_BILL_REQUEST = UpdateBillRequest
            .builder()
            .id(VALID_UPDATE_BILL_REQUEST.getId())
            .discount(VALID_UPDATE_BILL_REQUEST.getDiscount())
            .totalPaid(BigDecimal.valueOf(-1))
            .clientId(VALID_UPDATE_BILL_REQUEST.getClientId())
            .build();

    public static final UpdateBillRequest INVALID_TOTAL_PAID_NULL_UPDATE_BILL_REQUEST = UpdateBillRequest
            .builder()
            .id(VALID_UPDATE_BILL_REQUEST.getId())
            .discount(VALID_UPDATE_BILL_REQUEST.getDiscount())
            .totalPaid(null)
            .clientId(VALID_UPDATE_BILL_REQUEST.getClientId())
            .build();

    // Last Paid
    public static final UpdateBillRequest VALID_LAST_PAID_MINUS_YEAR_UPDATE_BILL_REQUEST = UpdateBillRequest
            .builder()
            .id(VALID_UPDATE_BILL_REQUEST.getId())
            .discount(VALID_UPDATE_BILL_REQUEST.getDiscount())
            .totalPaid(VALID_UPDATE_BILL_REQUEST.getTotalPaid())
            .clientId(VALID_UPDATE_BILL_REQUEST.getClientId())
            .build();

    public static final UpdateBillRequest VALID_LAST_PAID_TODAY_UPDATE_BILL_REQUEST = UpdateBillRequest
            .builder()
            .id(VALID_UPDATE_BILL_REQUEST.getId())
            .discount(VALID_UPDATE_BILL_REQUEST.getDiscount())
            .totalPaid(VALID_UPDATE_BILL_REQUEST.getTotalPaid())
            .clientId(VALID_UPDATE_BILL_REQUEST.getClientId())
            .build();

    public static final UpdateBillRequest INVALID_LAST_PAID_FUTURE_UPDATE_BILL_REQUEST = UpdateBillRequest
            .builder()
            .id(VALID_UPDATE_BILL_REQUEST.getId())
            .discount(VALID_UPDATE_BILL_REQUEST.getDiscount())
            .totalPaid(VALID_UPDATE_BILL_REQUEST.getTotalPaid())
            .clientId(VALID_UPDATE_BILL_REQUEST.getClientId())
            .build();

    public static final UpdateBillRequest INVALID_LAST_PAID_NULL_UPDATE_BILL_REQUEST = UpdateBillRequest
            .builder()
            .id(VALID_UPDATE_BILL_REQUEST.getId())
            .discount(VALID_UPDATE_BILL_REQUEST.getDiscount())
            .totalPaid(VALID_UPDATE_BILL_REQUEST.getTotalPaid())
            .clientId(VALID_UPDATE_BILL_REQUEST.getClientId())
            .build();

    // Client ID
    public static final UpdateBillRequest INVALID_CLIENT_ID_NOT_FOUND_UPDATE_BILL_REQUEST = UpdateBillRequest
            .builder()
            .id(VALID_UPDATE_BILL_REQUEST.getId())
            .discount(VALID_UPDATE_BILL_REQUEST.getDiscount())
            .totalPaid(VALID_UPDATE_BILL_REQUEST.getTotalPaid())
            .clientId(10L)
            .build();

    public static final UpdateBillRequest INVALID_CLIENT_ID_NEGATIVE_UPDATE_BILL_REQUEST = UpdateBillRequest
            .builder()
            .id(VALID_UPDATE_BILL_REQUEST.getId())
            .discount(VALID_UPDATE_BILL_REQUEST.getDiscount())
            .totalPaid(VALID_UPDATE_BILL_REQUEST.getTotalPaid())
            .clientId(-1L)
            .build();

    public static final UpdateBillRequest INVALID_CLIENT_NULL_NEGATIVE_UPDATE_BILL_REQUEST = UpdateBillRequest
            .builder()
            .id(VALID_UPDATE_BILL_REQUEST.getId())
            .discount(VALID_UPDATE_BILL_REQUEST.getDiscount())
            .totalPaid(VALID_UPDATE_BILL_REQUEST.getTotalPaid())
            .clientId(null)
            .build();
}
