package com.vluepixel.vetmanager.api.category.core.data;

import com.vluepixel.vetmanager.api.product.category.domain.request.UpdateCategoryRequest;

/**
 * Update category data provider.
 */
public class UpdateCategoryDataProvider {
    public static final UpdateCategoryRequest INVALID_ID_NOT_FOUND_UPDATE_CATEGORY_REQUEST = UpdateCategoryRequest
            .builder()
            .id(10)
            .name("Nueva Salud")
            .build();

    public static final UpdateCategoryRequest INVALID_ID_NEGATIVE_UPDATE_CATEGORY_REQUEST = UpdateCategoryRequest
            .builder()
            .id(-10)
            .name("Nueva Salud")
            .build();

    public static final UpdateCategoryRequest VALID_UPDATE_CATEGORY_REQUEST = UpdateCategoryRequest
            .builder()
            .id(1)
            .name("Nueva Salud")
            .build();

    // -----------------------------------------------------------------------------------------------------------------
    // Validations
    // -----------------------------------------------------------------------------------------------------

    // Name
    public static final int MAX_NAME_LENGTH = 12;
    public static final UpdateCategoryRequest INVALID_NAME_TOO_LONG_UPDATE_CATEGORY_REQUEST = UpdateCategoryRequest
            .builder()
            .id(VALID_UPDATE_CATEGORY_REQUEST.getId())
            .name("a".repeat(MAX_NAME_LENGTH + 1))
            .build();

    public static final UpdateCategoryRequest VALID_NAME_MAX_LENGTH_UPDATE_CATEGORY_REQUEST = UpdateCategoryRequest
            .builder()
            .id(VALID_UPDATE_CATEGORY_REQUEST.getId())
            .name("a".repeat(MAX_NAME_LENGTH))
            .build();

    public static final UpdateCategoryRequest INVALID_NAME_BLANK_UPDATE_CATEGORY_REQUEST = UpdateCategoryRequest
            .builder()
            .id(VALID_UPDATE_CATEGORY_REQUEST.getId())
            .name(" ")
            .build();

    public static final UpdateCategoryRequest INVALID_NAME_EMPTY_UPDATE_CATEGORY_REQUEST = UpdateCategoryRequest
            .builder()
            .id(VALID_UPDATE_CATEGORY_REQUEST.getId())
            .name("")
            .build();

    public static final UpdateCategoryRequest INVALID_NAME_NULL_UPDATE_CATEGORY_REQUEST = UpdateCategoryRequest
            .builder()
            .id(VALID_UPDATE_CATEGORY_REQUEST.getId())
            .name(null)
            .build();
}
