package com.vluepixel.vetmanager.api.sale.core.data.product;

import com.vluepixel.vetmanager.api.bill.sale.domain.request.UpdateProductSaleRequest;

/**
 * Update product sale data provider.
 */
public class UpdateProductSaleDataProvider {
    public static final UpdateProductSaleRequest INVALID_ID_NOT_FOUND_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(10)
            .billId(1L)
            .productId(1L)
            .quantity(10)
            .discount(20)
            .build();

    public static final UpdateProductSaleRequest INVALID_ID_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(-1)
            .billId(INVALID_ID_NOT_FOUND_UPDATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(INVALID_ID_NOT_FOUND_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(INVALID_ID_NOT_FOUND_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(INVALID_ID_NOT_FOUND_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateProductSaleRequest INVALID_ID_NULL_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(null)
            .billId(INVALID_ID_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(INVALID_ID_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(INVALID_ID_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(INVALID_ID_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateProductSaleRequest VALID_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(1)
            .billId(INVALID_ID_NULL_UPDATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(INVALID_ID_NULL_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(INVALID_ID_NULL_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(INVALID_ID_NULL_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    // -----------------------------------------------------------------------------------------------------------------
    // Validations
    // -----------------------------------------------------------------------------------------------------

    // Bill ID
    public static final UpdateProductSaleRequest INVALID_BILL_ID_NOT_FOUND_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .billId(10L)
            .productId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(VALID_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(VALID_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateProductSaleRequest INVALID_BILL_ID_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .billId(-1L)
            .productId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(VALID_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(VALID_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateProductSaleRequest INVALID_BILL_ID_NULL_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .billId(null)
            .productId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(VALID_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(VALID_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    // Product ID
    public static final UpdateProductSaleRequest INVALID_APPOINTMENT_ID_NOT_FOUND_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .billId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(10L)
            .quantity(VALID_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(VALID_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateProductSaleRequest INVALID_APPOINTMENT_ID_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .billId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(-1L)
            .quantity(VALID_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(VALID_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateProductSaleRequest INVALID_APPOINTMENT_ID_NULL_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .billId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(null)
            .quantity(VALID_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(VALID_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    // Quantity
    private static final int MAX_QUANTITY_VALUE = 999;
    public static final UpdateProductSaleRequest INVALID_QUANTITY_TOO_BIG_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .billId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(MAX_QUANTITY_VALUE + 1)
            .discount(VALID_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateProductSaleRequest VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .billId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(MAX_QUANTITY_VALUE)
            .discount(VALID_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateProductSaleRequest VALID_QUANTITY_ZERO_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .billId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(0)
            .discount(VALID_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateProductSaleRequest INVALID_QUANTITY_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .billId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(-1)
            .discount(VALID_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateProductSaleRequest INVALID_QUANTITY_NULL_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .billId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(null)
            .discount(VALID_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    // Discount
    private static final int MAX_DISCOUNT_VALUE = 100;
    public static final UpdateProductSaleRequest INVALID_DISCOUNT_TOO_BIG_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .billId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(VALID_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(MAX_DISCOUNT_VALUE + 1)
            .build();

    public static final UpdateProductSaleRequest VALID_DISCOUNT_MAX_VALUE_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .billId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(VALID_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(MAX_DISCOUNT_VALUE)
            .build();

    public static final UpdateProductSaleRequest VALID_DISCOUNT_ZERO_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .billId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(VALID_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(0)
            .build();

    public static final UpdateProductSaleRequest INVALID_DISCOUNT_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .billId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(VALID_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(-1)
            .build();

    public static final UpdateProductSaleRequest INVALID_DISCOUNT_NULL_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .billId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(VALID_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(null)
            .build();
}
