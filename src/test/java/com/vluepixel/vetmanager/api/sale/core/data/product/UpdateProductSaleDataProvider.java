package com.vluepixel.vetmanager.api.sale.core.data.product;

import com.vluepixel.vetmanager.api.bill.sale.domain.request.UpdateProductSaleRequest;

/**
 * Update product sale data provider.
 */
public class UpdateProductSaleDataProvider {
    public static final UpdateProductSaleRequest INVALID_ID_NOT_FOUND_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(10)
            .productId(1L)
            .quantity(10)
            .discount(20)
            .build();

    public static final UpdateProductSaleRequest INVALID_ID_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(-1)
            .productId(INVALID_ID_NOT_FOUND_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(INVALID_ID_NOT_FOUND_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(INVALID_ID_NOT_FOUND_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateProductSaleRequest INVALID_ID_NULL_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(null)
            .productId(INVALID_ID_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(INVALID_ID_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(INVALID_ID_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateProductSaleRequest VALID_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(2)
            .productId(INVALID_ID_NULL_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(INVALID_ID_NULL_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(INVALID_ID_NULL_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    // -----------------------------------------------------------------------------------------------------------------
    // Validations
    // -----------------------------------------------------------------------------------------------------

    // Product ID
    public static final UpdateProductSaleRequest INVALID_PRODUCT_ID_NOT_FOUND_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .productId(10L)
            .quantity(VALID_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(VALID_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateProductSaleRequest INVALID_PRODUCT_ID_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .productId(-1L)
            .quantity(VALID_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(VALID_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateProductSaleRequest INVALID_PRODUCT_ID_NULL_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .productId(null)
            .quantity(VALID_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(VALID_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    // Quantity
    private static final int MAX_QUANTITY_VALUE = 99;
    public static final UpdateProductSaleRequest INVALID_QUANTITY_TOO_BIG_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .productId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(MAX_QUANTITY_VALUE + 1)
            .discount(VALID_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateProductSaleRequest VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .productId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(MAX_QUANTITY_VALUE)
            .discount(VALID_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateProductSaleRequest INVALID_QUANTITY_ZERO_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .productId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(0)
            .discount(VALID_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateProductSaleRequest INVALID_QUANTITY_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .productId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(-1)
            .discount(VALID_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateProductSaleRequest INVALID_QUANTITY_NULL_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .productId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(null)
            .discount(VALID_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    // Discount
    private static final int MAX_DISCOUNT_VALUE = 100;
    public static final UpdateProductSaleRequest INVALID_DISCOUNT_TOO_BIG_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .productId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(VALID_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(MAX_DISCOUNT_VALUE + 1)
            .build();

    public static final UpdateProductSaleRequest VALID_DISCOUNT_MAX_VALUE_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .productId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(VALID_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(MAX_DISCOUNT_VALUE)
            .build();

    public static final UpdateProductSaleRequest INVALID_DISCOUNT_ZERO_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .productId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(VALID_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(0)
            .build();

    public static final UpdateProductSaleRequest INVALID_DISCOUNT_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .productId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(VALID_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(-1)
            .build();

    public static final UpdateProductSaleRequest INVALID_DISCOUNT_NULL_UPDATE_PRODUCT_SALE_REQUEST = UpdateProductSaleRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_SALE_REQUEST.getId())
            .productId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(VALID_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(null)
            .build();
}
