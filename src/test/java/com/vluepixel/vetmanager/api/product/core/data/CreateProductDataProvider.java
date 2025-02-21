package com.vluepixel.vetmanager.api.product.core.data;

import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_CATEGORY_IDS_EMPTY_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_CATEGORY_IDS_NEGATIVE_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_CATEGORY_IDS_NOT_FOUND_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_CATEGORY_IDS_NULL_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_CATEGORY_IDS_ONE_NULL_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_DESCRIPTION_BLANK_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_DESCRIPTION_EMPTY_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_DESCRIPTION_NULL_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_NAME_BLANK_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_NAME_EMPTY_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_NAME_NULL_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_NAME_TOO_LONG_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_PRICE_NEGATIVE_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_PRICE_NULL_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_PRICE_TOO_BIG_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_PRICE_ZERO_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_QUANTITY_NEGATIVE_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_QUANTITY_NULL_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_QUANTITY_TOO_BIG_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.INVALID_QUANTITY_ZERO_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.VALID_NAME_MAX_LENGTH_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.VALID_PRICE_MAX_VALUE_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_REQUEST;
import static com.vluepixel.vetmanager.api.product.core.data.UpdateProductDataprovider.VALID_UPDATE_PRODUCT_REQUEST;

import com.vluepixel.vetmanager.api.product.core.domain.request.CreateProductRequest;

/**
 * Create product data provider.
 */
public class CreateProductDataProvider {
    public static final CreateProductRequest VALID_CREATE_PRODUCT_REQUEST = CreateProductRequest
            .builder()
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    // - Invalidations

    // Name
    public static final CreateProductRequest INVALID_NAME_TOO_LONG_CREATE_PRODUCT_REQUEST = CreateProductRequest
            .builder()
            .name(INVALID_NAME_TOO_LONG_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final CreateProductRequest VALID_NAME_MAX_LENGTH_CREATE_PRODUCT_REQUEST = CreateProductRequest
            .builder()
            .name(VALID_NAME_MAX_LENGTH_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final CreateProductRequest INVALID_NAME_BLANK_CREATE_PRODUCT_REQUEST = CreateProductRequest
            .builder()
            .name(INVALID_NAME_BLANK_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final CreateProductRequest INVALID_NAME_EMPTY_CREATE_PRODUCT_REQUEST = CreateProductRequest
            .builder()
            .name(INVALID_NAME_EMPTY_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final CreateProductRequest INVALID_NAME_NULL_CREATE_PRODUCT_REQUEST = CreateProductRequest
            .builder()
            .name(INVALID_NAME_NULL_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    // Description
    public static final CreateProductRequest INVALID_DESCRIPTION_BLANK_CREATE_PRODUCT_REQUEST = CreateProductRequest
            .builder()
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(INVALID_DESCRIPTION_BLANK_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final CreateProductRequest INVALID_DESCRIPTION_EMPTY_CREATE_PRODUCT_REQUEST = CreateProductRequest
            .builder()
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(INVALID_DESCRIPTION_EMPTY_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final CreateProductRequest INVALID_DESCRIPTION_NULL_CREATE_PRODUCT_REQUEST = CreateProductRequest
            .builder()
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(INVALID_DESCRIPTION_NULL_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    // Price
    public static final CreateProductRequest INVALID_PRICE_TOO_BIG_CREATE_PRODUCT_REQUEST = CreateProductRequest
            .builder()
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(INVALID_PRICE_TOO_BIG_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final CreateProductRequest VALID_PRICE_MAX_VALUE_CREATE_PRODUCT_REQUEST = CreateProductRequest
            .builder()
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_PRICE_MAX_VALUE_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final CreateProductRequest INVALID_PRICE_ZERO_CREATE_PRODUCT_REQUEST = CreateProductRequest
            .builder()
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(INVALID_PRICE_ZERO_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final CreateProductRequest INVALID_PRICE_NEGATIVE_CREATE_PRODUCT_REQUEST = CreateProductRequest
            .builder()
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(INVALID_PRICE_NEGATIVE_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final CreateProductRequest INVALID_PRICE_NULL_CREATE_PRODUCT_REQUEST = CreateProductRequest
            .builder()
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(INVALID_PRICE_NULL_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    // Quantity
    public static final CreateProductRequest INVALID_QUANTITY_TOO_BIG_CREATE_PRODUCT_REQUEST = CreateProductRequest
            .builder()
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(INVALID_QUANTITY_TOO_BIG_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final CreateProductRequest VALID_QUANTITY_MAX_VALUE_CREATE_PRODUCT_REQUEST = CreateProductRequest
            .builder()
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final CreateProductRequest INVALID_QUANTITY_ZERO_CREATE_PRODUCT_REQUEST = CreateProductRequest
            .builder()
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(INVALID_QUANTITY_ZERO_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final CreateProductRequest INVALID_QUANTITY_NEGATIVE_CREATE_PRODUCT_REQUEST = CreateProductRequest
            .builder()
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(INVALID_QUANTITY_NEGATIVE_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final CreateProductRequest INVALID_QUANTITY_NULL_CREATE_PRODUCT_REQUEST = CreateProductRequest
            .builder()
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(INVALID_QUANTITY_NULL_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(VALID_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    // Category IDs
    public static final CreateProductRequest INVALID_CATEGORY_IDS_NEGATIVE_CREATE_PRODUCT_REQUEST = CreateProductRequest
            .builder()
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(INVALID_CATEGORY_IDS_NEGATIVE_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final CreateProductRequest INVALID_CATEGORY_IDS_NOT_FOUND_CREATE_PRODUCT_REQUEST = CreateProductRequest
            .builder()
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(INVALID_CATEGORY_IDS_NOT_FOUND_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final CreateProductRequest INVALID_CATEGORY_IDS_ONE_NULL_CREATE_PRODUCT_REQUEST = CreateProductRequest
            .builder()
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(INVALID_CATEGORY_IDS_ONE_NULL_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final CreateProductRequest INVALID_CATEGORY_IDS_EMPTY_CREATE_PRODUCT_REQUEST = CreateProductRequest
            .builder()
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(INVALID_CATEGORY_IDS_EMPTY_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();

    public static final CreateProductRequest INVALID_CATEGORY_IDS_NULL_CREATE_PRODUCT_REQUEST = CreateProductRequest
            .builder()
            .name(VALID_UPDATE_PRODUCT_REQUEST.getName())
            .description(VALID_UPDATE_PRODUCT_REQUEST.getDescription())
            .price(VALID_UPDATE_PRODUCT_REQUEST.getPrice())
            .quantity(VALID_UPDATE_PRODUCT_REQUEST.getQuantity())
            .categoryIds(INVALID_CATEGORY_IDS_NULL_UPDATE_PRODUCT_REQUEST.getCategoryIds())
            .build();
}
