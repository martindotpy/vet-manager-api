package com.vluepixel.vetmanager.api.product.core.data;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.vluepixel.vetmanager.api.product.core.domain.request.UpdateProductRequest;

/**
 * Update product data provider.
 */
public class UpdateProductDataprovider {
    public static final UpdateProductRequest VALID_UPDATE_PRODUCT_REQUEST = UpdateProductRequest
            .builder()
            .id(1L)
            .name("New Product")
            .description("New Product Available")
            .price(BigDecimal.valueOf(300.00))
            .quantity(10)
            .categoryIds(new ArrayList<Integer>() {
                {
                    add(1);
                    add(2);
                }
            })
            .build();

    // - Invalidations

    // ID
    public static final UpdateProductRequest INVALID_ID_NOT_FOUND_UPDATE_PRODUCT_REQUEST = UpdateProductRequest
            .builder()
            .id(10L)
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final UpdateProductRequest INVALID_ID_NEGATIVE_UPDATE_PRODUCT_REQUEST = UpdateProductRequest
            .builder()
            .id(-1L)
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    // Name
    private static final int MAX_NAME_LENGTH = 125;
    public static final UpdateProductRequest INVALID_NAME_TOO_LONG_UPDATE_PRODUCT_REQUEST = UpdateProductRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_REQUEST.getId())
            .name("a".repeat(MAX_NAME_LENGTH + 1))
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final UpdateProductRequest VALID_NAME_MAX_LENGTH_UPDATE_PRODUCT_REQUEST = UpdateProductRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_REQUEST.getId())
            .name("a".repeat(MAX_NAME_LENGTH))
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final UpdateProductRequest INVALID_NAME_BLANK_UPDATE_PRODUCT_REQUEST = UpdateProductRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_REQUEST.getId())
            .name(" ")
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final UpdateProductRequest INVALID_NAME_EMPTY_UPDATE_PRODUCT_REQUEST = UpdateProductRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_REQUEST.getId())
            .name("")
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final UpdateProductRequest INVALID_NAME_NULL_UPDATE_PRODUCT_REQUEST = UpdateProductRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_REQUEST.getId())
            .name(null)
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    // Description
    public static final UpdateProductRequest INVALID_DESCRIPTION_BLANK_UPDATE_PRODUCT_REQUEST = UpdateProductRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_REQUEST.getId())
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(" ")
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final UpdateProductRequest INVALID_DESCRIPTION_EMPTY_UPDATE_PRODUCT_REQUEST = UpdateProductRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_REQUEST.getId())
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description("")
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final UpdateProductRequest INVALID_DESCRIPTION_NULL_UPDATE_PRODUCT_REQUEST = UpdateProductRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_REQUEST.getId())
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(null)
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    // Price
    private static final int MAX_PRICE_VALUE = 999;
    public static final UpdateProductRequest INVALID_PRICE_TOO_BIG_UPDATE_PRODUCT_REQUEST = UpdateProductRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_REQUEST.getId())
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(BigDecimal.valueOf(MAX_PRICE_VALUE + 1))
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final UpdateProductRequest VALID_PRICE_MAX_VALUE_UPDATE_PRODUCT_REQUEST = UpdateProductRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_REQUEST.getId())
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(BigDecimal.valueOf(MAX_PRICE_VALUE + 0.99))
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final UpdateProductRequest INVALID_PRICE_ZERO_UPDATE_PRODUCT_REQUEST = UpdateProductRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_REQUEST.getId())
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(BigDecimal.valueOf(0))
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final UpdateProductRequest INVALID_PRICE_NEGATIVE_UPDATE_PRODUCT_REQUEST = UpdateProductRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_REQUEST.getId())
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(BigDecimal.valueOf(-1))
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final UpdateProductRequest INVALID_PRICE_NULL_UPDATE_PRODUCT_REQUEST = UpdateProductRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_REQUEST.getId())
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(null)
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    // Quantity
    private static final int MAX_QUANTITY_VALUE = 99;
    public static final UpdateProductRequest INVALID_QUANTITY_TOO_BIG_UPDATE_PRODUCT_REQUEST = UpdateProductRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_REQUEST.getId())
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(MAX_QUANTITY_VALUE + 1)
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final UpdateProductRequest VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_REQUEST = UpdateProductRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_REQUEST.getId())
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(MAX_QUANTITY_VALUE)
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final UpdateProductRequest INVALID_QUANTITY_ZERO_UPDATE_PRODUCT_REQUEST = UpdateProductRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_REQUEST.getId())
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(0)
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final UpdateProductRequest INVALID_QUANTITY_NEGATIVE_UPDATE_PRODUCT_REQUEST = UpdateProductRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_REQUEST.getId())
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(-1)
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final UpdateProductRequest INVALID_QUANTITY_NULL_UPDATE_PRODUCT_REQUEST = UpdateProductRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_REQUEST.getId())
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(null)
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    // Category IDs
    public static final UpdateProductRequest INVALID_CATEGORY_IDS_NEGATIVE_UPDATE_PRODUCT_REQUEST = UpdateProductRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_REQUEST.getId())
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(new ArrayList<Integer>() {
                {
                    add(-1);
                    add(2);
                }
            })
            .build();

    public static final UpdateProductRequest INVALID_CATEGORY_IDS_NOT_FOUND_UPDATE_PRODUCT_REQUEST = UpdateProductRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_REQUEST.getId())
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(new ArrayList<Integer>() {
                {
                    add(10);
                    add(2);
                }
            })
            .build();

    public static final UpdateProductRequest INVALID_CATEGORY_IDS_ONE_NULL_UPDATE_PRODUCT_REQUEST = UpdateProductRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_REQUEST.getId())
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(new ArrayList<Integer>() {
                {
                    add(10);
                    add(null);
                }
            })
            .build();

    public static final UpdateProductRequest INVALID_CATEGORY_IDS_EMPTY_UPDATE_PRODUCT_REQUEST = UpdateProductRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_REQUEST.getId())
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(new ArrayList<Integer>())
            .build();

    public static final UpdateProductRequest INVALID_CATEGORY_IDS_NULL_UPDATE_PRODUCT_REQUEST = UpdateProductRequest
            .builder()
            .id(VALID_UPDATE_PRODUCT_REQUEST.getId())
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(null)
            .build();
}
