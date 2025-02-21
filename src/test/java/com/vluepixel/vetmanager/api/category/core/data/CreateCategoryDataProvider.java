package com.vluepixel.vetmanager.api.category.core.data;

import static com.vluepixel.vetmanager.api.category.core.data.UpdateCategoryDataProvider.INVALID_NAME_BLANK_UPDATE_CATEGORY_REQUEST;
import static com.vluepixel.vetmanager.api.category.core.data.UpdateCategoryDataProvider.INVALID_NAME_EMPTY_UPDATE_CATEGORY_REQUEST;
import static com.vluepixel.vetmanager.api.category.core.data.UpdateCategoryDataProvider.INVALID_NAME_NULL_UPDATE_CATEGORY_REQUEST;
import static com.vluepixel.vetmanager.api.category.core.data.UpdateCategoryDataProvider.INVALID_NAME_TOO_LONG_UPDATE_CATEGORY_REQUEST;
import static com.vluepixel.vetmanager.api.category.core.data.UpdateCategoryDataProvider.VALID_NAME_MAX_LENGTH_UPDATE_CATEGORY_REQUEST;

import com.vluepixel.vetmanager.api.product.category.domain.request.CreateCategoryRequest;

/**
 * Create category data provider.
 */
public class CreateCategoryDataProvider {
    public static final CreateCategoryRequest VALID_CREATE_CATEGORY_REQUEST = CreateCategoryRequest
            .builder()
            .name("Salud Prem")
            .build();

    // -----------------------------------------------------------------------------------------------------------------
    // Validations
    // -----------------------------------------------------------------------------------------------------

    // Name
    public static final CreateCategoryRequest INVALID_NAME_TOO_LONG_CREATE_CATEGORY_REQUEST = CreateCategoryRequest
            .builder()
            .name(INVALID_NAME_TOO_LONG_UPDATE_CATEGORY_REQUEST.getName())
            .build();

    public static final CreateCategoryRequest VALID_NAME_MAX_LENGTH_CREATE_CATEGORY_REQUEST = CreateCategoryRequest
            .builder()
            .name(VALID_NAME_MAX_LENGTH_UPDATE_CATEGORY_REQUEST.getName())
            .build();

    public static final CreateCategoryRequest INVALID_NAME_BLANK_CREATE_CATEGORY_REQUEST = CreateCategoryRequest
            .builder()
            .name(INVALID_NAME_BLANK_UPDATE_CATEGORY_REQUEST.getName())
            .build();

    public static final CreateCategoryRequest INVALID_NAME_EMPTY_CREATE_CATEGORY_REQUEST = CreateCategoryRequest
            .builder()
            .name(INVALID_NAME_EMPTY_UPDATE_CATEGORY_REQUEST.getName())
            .build();

    public static final CreateCategoryRequest INVALID_NAME_NULL_CREATE_CATEGORY_REQUEST = CreateCategoryRequest
            .builder()
            .name(INVALID_NAME_NULL_UPDATE_CATEGORY_REQUEST.getName())
            .build();
}
