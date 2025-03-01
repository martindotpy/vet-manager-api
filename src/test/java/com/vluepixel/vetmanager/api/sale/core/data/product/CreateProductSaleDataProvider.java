package com.vluepixel.vetmanager.api.sale.core.data.product;

import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_PRODUCT_ID_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_PRODUCT_ID_NOT_FOUND_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_PRODUCT_ID_NULL_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_DISCOUNT_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_DISCOUNT_NULL_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_DISCOUNT_TOO_BIG_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_QUANTITY_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_QUANTITY_NULL_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_QUANTITY_TOO_BIG_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.VALID_DISCOUNT_MAX_VALUE_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_DISCOUNT_ZERO_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.INVALID_QUANTITY_ZERO_UPDATE_PRODUCT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.product.UpdateProductSaleDataProvider.VALID_UPDATE_PRODUCT_SALE_REQUEST;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.CreateProductSaleRequest;

/**
 * Create product sale data provider.
 */
public class CreateProductSaleDataProvider {
    public static final CreateProductSaleRequest VALID_CREATE_PRODUCT_SALE_REQUEST = CreateProductSaleRequest
            .builder()
            .billId(1L)
            .productId(VALID_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(VALID_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(VALID_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    // Product ID
    public static final CreateProductSaleRequest INVALID_PRODUCT_ID_NOT_FOUND_CREATE_PRODUCT_SALE_REQUEST = CreateProductSaleRequest
            .builder()
            .billId(VALID_CREATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(INVALID_PRODUCT_ID_NOT_FOUND_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(VALID_CREATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(VALID_CREATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateProductSaleRequest INVALID_PRODUCT_ID_NEGATIVE_CREATE_PRODUCT_SALE_REQUEST = CreateProductSaleRequest
            .builder()
            .billId(VALID_CREATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(INVALID_PRODUCT_ID_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(VALID_CREATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(VALID_CREATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateProductSaleRequest INVALID_PRODUCT_ID_NULL_CREATE_PRODUCT_SALE_REQUEST = CreateProductSaleRequest
            .builder()
            .billId(VALID_CREATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(INVALID_PRODUCT_ID_NULL_UPDATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(VALID_CREATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(VALID_CREATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    // Quantity
    public static final CreateProductSaleRequest INVALID_QUANTITY_TOO_BIG_CREATE_PRODUCT_SALE_REQUEST = CreateProductSaleRequest
            .builder()
            .billId(VALID_CREATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(VALID_CREATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(INVALID_QUANTITY_TOO_BIG_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(VALID_CREATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateProductSaleRequest VALID_QUANTITY_MAX_VALUE_CREATE_PRODUCT_SALE_REQUEST = CreateProductSaleRequest
            .builder()
            .billId(VALID_CREATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(VALID_CREATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(VALID_QUANTITY_MAX_VALUE_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(VALID_CREATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateProductSaleRequest INVALID_QUANTITY_ZERO_CREATE_PRODUCT_SALE_REQUEST = CreateProductSaleRequest
            .builder()
            .billId(VALID_CREATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(VALID_CREATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(INVALID_QUANTITY_ZERO_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(VALID_CREATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateProductSaleRequest INVALID_QUANTITY_NEGATIVE_CREATE_PRODUCT_SALE_REQUEST = CreateProductSaleRequest
            .builder()
            .billId(VALID_CREATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(VALID_CREATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(INVALID_QUANTITY_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(VALID_CREATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateProductSaleRequest INVALID_QUANTITY_NULL_CREATE_PRODUCT_SALE_REQUEST = CreateProductSaleRequest
            .builder()
            .billId(VALID_CREATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(VALID_CREATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(INVALID_QUANTITY_NULL_UPDATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(VALID_CREATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    // Discount
    public static final CreateProductSaleRequest INVALID_DISCOUNT_TOO_BIG_CREATE_PRODUCT_SALE_REQUEST = CreateProductSaleRequest
            .builder()
            .billId(VALID_CREATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(VALID_CREATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(VALID_CREATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(INVALID_DISCOUNT_TOO_BIG_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateProductSaleRequest VALID_DISCOUNT_MAX_VALUE_CREATE_PRODUCT_SALE_REQUEST = CreateProductSaleRequest
            .builder()
            .billId(VALID_CREATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(VALID_CREATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(VALID_CREATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(VALID_DISCOUNT_MAX_VALUE_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateProductSaleRequest VALID_DISCOUNT_ZERO_CREATE_PRODUCT_SALE_REQUEST = CreateProductSaleRequest
            .builder()
            .billId(VALID_CREATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(VALID_CREATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(VALID_CREATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(INVALID_DISCOUNT_ZERO_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateProductSaleRequest VALID_DISCOUNT_NEGATIVE_CREATE_PRODUCT_SALE_REQUEST = CreateProductSaleRequest
            .builder()
            .billId(VALID_CREATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(VALID_CREATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(VALID_CREATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(INVALID_DISCOUNT_NEGATIVE_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateProductSaleRequest VALID_DISCOUNT_NULL_CREATE_PRODUCT_SALE_REQUEST = CreateProductSaleRequest
            .builder()
            .billId(VALID_CREATE_PRODUCT_SALE_REQUEST.getBillId())
            .productId(VALID_CREATE_PRODUCT_SALE_REQUEST.getProductId())
            .quantity(VALID_CREATE_PRODUCT_SALE_REQUEST.getQuantity())
            .discount(INVALID_DISCOUNT_NULL_UPDATE_PRODUCT_SALE_REQUEST.getDiscount())
            .build();
}
